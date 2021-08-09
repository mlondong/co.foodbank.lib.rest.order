package co.com.foodbank.order.exception;


/**
 * @author mauricio.londono@gmail.com co.com.foodbank.pckage.exception
 *         11/07/2021
 */
public class OrderErrorException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OrderErrorException(String ex) {
        super(ex);
    }
}
