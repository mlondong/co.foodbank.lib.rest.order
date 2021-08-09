package co.com.foodbank.order.v1.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * States for all orders in Foodbank.
 * 
 * @author mauricio.londono@gmail.com co.com.foodbank.order.v1.model 9/08/2021
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
        property = "state")
@JsonSubTypes({@Type(value = DeliveredOrder.class, name = "DeliveredOrder"),
        @Type(value = ShipmentOrder.class, name = "ShipmentOrder"),
        @Type(value = PendingOrder.class, name = "PendingOrder"),})
public interface IStateOrder {

    void delivered(Order order);

    void shipment(Order order);

    void pending(Order order);

}
