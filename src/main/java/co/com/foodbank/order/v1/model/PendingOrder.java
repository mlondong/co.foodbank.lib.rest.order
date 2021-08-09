package co.com.foodbank.order.v1.model;

import java.io.Serializable;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.order.v1.model 9/08/2021
 */
public class PendingOrder implements IStateOrder, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    /**
     * Default constructor.
     */
    public PendingOrder() {}


    @Override
    public void delivered(Order order) {
        // TODO Auto-generated method stub
    }

    @Override
    public void shipment(Order order) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pending(Order order) {
        order.setState(this);
    }


    @Override
    public String toString() {
        return "Pending []";
    }


}
