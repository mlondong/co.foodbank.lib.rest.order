package co.com.foodbank.order.util;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.order.util 13/08/2021
 */
public final class OrderParameters {

    public static final int STATE_PENDING = 1;
    public static final int STATE_SHIPMENT = 2;
    public static final int STATE_DELIVERED = 3;
    public static final String MSG_WRONG_INFORMATON =
            "The beneficiary information is wrong.!! ";
    public static final String MSG_WRONG_STATE_PACKAGE =
            "The state of packaged isn't closed.";


    public OrderParameters() {}
}
