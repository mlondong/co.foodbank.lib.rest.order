package co.com.foodbank.order.v1.controller;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import co.com.foodbank.message.dto.MessageDTO;
import co.com.foodbank.message.sdk.exception.SDKMessageServiceException;
import co.com.foodbank.message.sdk.exception.SDKMessageServiceIllegalArgumentException;
import co.com.foodbank.order.dto.OrderDTO;
import co.com.foodbank.order.dto.interfaces.IOrder;
import co.com.foodbank.order.exception.OrderErrorException;
import co.com.foodbank.order.service.OrderService;
import co.com.foodbank.packaged.dto.PackagedDTO;
import co.com.foodbank.packaged.request.RequestPackaged;
import co.com.foodbank.user.dto.request.RequestVolunterData;
import co.com.foodbank.user.sdk.exception.SDKUserNotFoundException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceIllegalArgumentException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceNotAvailableException;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.order.v1.controller
 *         9/08/2021
 */
/**
 * @author mauricio.londono@gmail.com co.com.foodbank.order.v1.controller
 *         9/08/2021
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService service;



    /**
     * Method to find an Order.
     * 
     * @param id
     * @return {@code IOrder}
     */
    public IOrder findById(@NotBlank @NotNull String id) {
        return service.findById(id);
    }



    /**
     * Method to find all Order.
     * 
     * @return {@code Collection<IOrder>}
     */
    public Collection<IOrder> findAll() {
        return service.findAll();
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
     * @throws OrderErrorException
     */
    public IOrder create(@Valid OrderDTO dto) throws JsonMappingException,
            JsonProcessingException, SDKUserServiceNotAvailableException,
            SDKUserServiceException, SDKUserServiceIllegalArgumentException,
            SDKUserNotFoundException, OrderErrorException {
        return service.create(dto);
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
     * @throws OrderErrorException
     */
    public IOrder addVolunteer(@NotNull String id,
            @Valid RequestVolunterData data) throws JsonMappingException,
            JsonProcessingException, SDKUserServiceNotAvailableException,
            SDKUserServiceException, SDKUserServiceIllegalArgumentException,
            SDKUserNotFoundException, OrderErrorException {
        return service.addVolunteer(id, data);
    }



    /**
     * Method to add message in an order
     * 
     * @param id
     * @param messageDTO
     * @return {@code IOrder}
     * @throws SDKMessageServiceException
     * @throws SDKMessageServiceIllegalArgumentException
     * @throws OrderErrorException
     */
    public IOrder addMessage(@NotNull String id, @Valid MessageDTO messageDTO)
            throws SDKMessageServiceIllegalArgumentException,
            SDKMessageServiceException, OrderErrorException {
        return service.addMessage(id, messageDTO);
    }



    /**
     * Method to add package in an order
     * 
     * @param id
     * @param request
     * @return {@code IOrder}
     * @throws OrderErrorException
     */
    public IOrder addPackage(@NotNull String id, @Valid RequestPackaged request)
            throws OrderErrorException {
        return service.addPackage(id, request);
    }



    /**
     * Method to remove package order
     * 
     * @param id
     * @param packagedDTO
     * @return {@code IOrder}
     */
    public IOrder removePackage(@NotNull String id,
            @Valid PackagedDTO packagedDTO) {
        return service.removePackage(id, packagedDTO);
    }



    /**
     * Method to update the state order.
     * 
     * @param id
     * @param option
     * @return {@code}
     */
    public IOrder changeStateOrder(@NotNull String id, String option) {
        return service.changeStateOrder(id, option);
    }



}
