package co.com.foodbank.order.service;

import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.foodbank.order.dto.IOrder;
import co.com.foodbank.order.exception.OrderNotFoundException;
import co.com.foodbank.order.repository.OrderRepository;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.order.service 9/08/2021
 */
@Service
public class OrderService {


    @Autowired
    private OrderRepository repository;


    @Autowired
    private ModelMapper modelMapper;



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


}
