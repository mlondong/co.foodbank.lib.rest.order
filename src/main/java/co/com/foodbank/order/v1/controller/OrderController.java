package co.com.foodbank.order.v1.controller;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.com.foodbank.order.dto.IOrder;
import co.com.foodbank.order.service.OrderService;

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

}
