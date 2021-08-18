package co.com.foodbank.order.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import co.com.foodbank.message.dto.Message;
import co.com.foodbank.message.dto.MessageDTO;
import co.com.foodbank.message.dto.interfaces.IMessage;
import co.com.foodbank.message.sdk.exception.SDKMessageServiceException;
import co.com.foodbank.message.sdk.exception.SDKMessageServiceIllegalArgumentException;
import co.com.foodbank.message.sdk.model.ResponseMessageData;
import co.com.foodbank.message.sdk.service.SDKMessageService;
import co.com.foodbank.order.dto.OrderDTO;
import co.com.foodbank.order.dto.interfaces.IOrder;
import co.com.foodbank.order.exception.OrderErrorException;
import co.com.foodbank.order.exception.OrderNotFoundException;
import co.com.foodbank.order.repository.OrderRepository;
import co.com.foodbank.order.util.OrderParameters;
import co.com.foodbank.order.v1.model.DeliveredOrder;
import co.com.foodbank.order.v1.model.IStateOrder;
import co.com.foodbank.order.v1.model.Order;
import co.com.foodbank.order.v1.model.PendingOrder;
import co.com.foodbank.order.v1.model.ShipmentOrder;
import co.com.foodbank.packaged.dto.PackagedDTO;
import co.com.foodbank.packaged.dto.interfaces.IPackaged;
import co.com.foodbank.packaged.dto.state.ClosePackaged;
import co.com.foodbank.packaged.request.RequestPackaged;
import co.com.foodbank.packaged.sdk.service.SDKPackagedService;
import co.com.foodbank.user.dto.request.RequestBeneficiaryData;
import co.com.foodbank.user.dto.request.RequestVolunterData;
import co.com.foodbank.user.dto.response.BeneficiaryData;
import co.com.foodbank.user.dto.response.VolunteerData;
import co.com.foodbank.user.sdk.exception.SDKUserNotFoundException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceIllegalArgumentException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceNotAvailableException;
import co.com.foodbank.user.sdk.model.ResponseBeneficiaryData;
import co.com.foodbank.user.sdk.model.ResponseVolunteerData;
import co.com.foodbank.user.sdk.service.SDKUserService;


/**
 * @author mauricio.londono@gmail.com co.com.foodbank.order.service 9/08/2021
 */
@Service
@Transactional
public class OrderService {


    @Autowired
    private OrderRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("sdkUser")
    private SDKUserService sdkUser;

    @Autowired
    @Qualifier("sdkMessage")
    private SDKMessageService sdkMessage;

    @Autowired
    @Qualifier("sdkPackaged")
    private SDKPackagedService sdkPackaged;


    /**
     * Method to find an Order.
     * 
     * @param id
     * @return {@code IOrder}
     */
    public IOrder findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }


    /**
     * Method to find all Order.
     * 
     * @return {@code Collection<IOrder>}
     */
    public Collection<IOrder> findAll() {

        return repository.findAll().stream()
                .map(d -> modelMapper.map(d, IOrder.class))
                .collect(Collectors.toList());
    }


    /**
     * Method to create an Order.
     * 
     * @param dto
     * @return {@code IOrder}
     * @throws SDKUserNotFoundException
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public IOrder create(@Valid OrderDTO dto)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceNotAvailableException, SDKUserServiceException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {

        return repository.save(buildOrder(dto));
    }


    /**
     * Method to build an Order.
     * 
     * @param dto
     * @return {@code Order}
     * @throws SDKUserNotFoundException
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    private Order buildOrder(OrderDTO dto)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceNotAvailableException, SDKUserServiceException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {
        Order order = new Order();
        order.setBeneficiary(initBeneficiary(dto.getBeneficiary()));
        order.setMessage(initMessage());
        order.setPackages(initPackaged());
        order.setState(initState(order));
        order.setDateOrder(new Date());
        order.setVolunter(null);
        return order;
    }



    /**
     * Method to init a Volunteer.
     * 
     * @param user
     * @return {@code VolunteerData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserNotFoundException
     */
    private VolunteerData initVolunteer(RequestVolunterData user)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceNotAvailableException, SDKUserServiceException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {

        ResponseVolunteerData data = sdkUser.findVolunteer(user);
        return modelMapper.map(data, VolunteerData.class);
    }


    /**
     * Method to initialize a Beneficiary.
     * 
     * @param user
     * @return {@code BeneficiaryData}
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws SDKUserServiceNotAvailableException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserNotFoundException
     */
    private BeneficiaryData initBeneficiary(RequestBeneficiaryData user)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceNotAvailableException, SDKUserServiceException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {

        ResponseBeneficiaryData response =
                sdkUser.findBeneficiaryById(user.getId());

        if (!response.getSocialReason().equals(user.getSocialReason())) {
            throw new SDKUserNotFoundException(user.toString(),
                    OrderParameters.MSG_WRONG_INFORMATON);
        }

        return modelMapper.map(response, BeneficiaryData.class);
    }



    private IStateOrder initState(Order order) {
        PendingOrder pending = new PendingOrder();
        pending.pending(order);
        return pending;
    }


    private Collection<IPackaged> initPackaged() {
        return new ArrayList<>();
    }


    private Collection<IMessage> initMessage() {
        return new ArrayList<>();
    }


    /**
     * Method to add Volunteer.
     * 
     * @param id
     * @param requestUser
     * @return {@code IOrder}
     * @throws SDKUserNotFoundException
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public IOrder addVolunteer(String id, RequestVolunterData data)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceNotAvailableException, SDKUserServiceException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {

        Order order = getOrder(id);
        order.setVolunter(initVolunteer(data));
        return repository.save(order);
    }

    /**
     * Method to add message in an order
     * 
     * @param id
     * @param messageDTO
     * @return {@code IOrder}
     * @throws SDKMessageServiceException
     * @throws SDKMessageServiceIllegalArgumentException
     */
    public IOrder addMessage(String id, MessageDTO messageDTO)
            throws SDKMessageServiceIllegalArgumentException,
            SDKMessageServiceException {
        Order order = getOrder(id);
        order.getMessage().add(initMessage(messageDTO));
        return repository.save(order);
    }


    /**
     * Method to initialize Message.
     * 
     * @param messageDTO
     * @return {@code IMessage}
     * @throws SDKMessageServiceIllegalArgumentException
     * @throws SDKMessageServiceException
     */
    private IMessage initMessage(MessageDTO messageDTO)
            throws SDKMessageServiceIllegalArgumentException,
            SDKMessageServiceException {
        ResponseMessageData message = sdkMessage.create(messageDTO);
        return modelMapper.map(message, Message.class);
    }


    private Order getOrder(String id) {
        IOrder iOrder = this.findById(id);
        return modelMapper.map(iOrder, Order.class);
    }


    /**
     * Method to add package in an order, and validate state in package.
     * 
     * @param id
     * @param request
     * @return {@code IOrder}
     * @throws OrderErrorException
     */
    public IOrder addPackage(String id, RequestPackaged request)
            throws OrderErrorException {
        Order order = getOrder(id);

        if (request.getState() instanceof ClosePackaged) {
            throw new OrderErrorException(request.toString()
                    + OrderParameters.MSG_WRONG_STATE_PACKAGE);
        }
        order.getPackages().add(request);
        return repository.save(order);
    }



    /**
     * Method to remove package order
     * 
     * @param id
     * @param packagedDTO
     * @return {@code IOrder}
     */
    public IOrder removePackage(String id, PackagedDTO packagedDTO) {
        Order order = getOrder(id);
        order.getPackages().remove(null);
        return null;
    }


    /**
     * Method to update the state order.
     * 
     * @param id
     * @param option
     * @return {@code}
     */
    public IOrder changeStateOrder(@NotNull String id, String option) {
        Order order = getOrder(id);
        order.setState(checkOptionState(Integer.valueOf(option), order));
        return repository.save(order);
    }


    /**
     * @param key
     * @param data
     */
    private IStateOrder checkOptionState(int option, Order order) {

        IStateOrder state = null;
        switch (option) {
            case OrderParameters.STATE_SHIPMENT:
                state = new ShipmentOrder();
                state.shipment(order);
                break;

            case OrderParameters.STATE_DELIVERED:
                state = new DeliveredOrder();
                state.delivered(order);
                break;

            case OrderParameters.STATE_PENDING:
                state = new PendingOrder();
                state.pending(order);
                break;
        }

        return state;
    }


}
