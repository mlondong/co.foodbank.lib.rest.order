package co.com.foodbank.order.v1.model;

import java.io.Serializable;

public class ShipmentOrder implements IStateOrder, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    /**
     * Default constructor.
     */
    public ShipmentOrder() {}

    @Override
    public void delivered(Order order) {
        // TODO Auto-generated method stub
    }

    @Override
    public void shipment(Order order) {
        order.setState(this);
    }

    @Override
    public void pending(Order order) {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        return "Shipment []";
    }


}
