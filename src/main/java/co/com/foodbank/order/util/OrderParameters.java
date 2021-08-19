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
            "Warning, The state of packaged is open.";
    public static final String BENEFICIARY_INACTIVE = "Beneficiary inactive.";
    public static final String MSG_REPETED_PACKAGED =
            "Warning, there is a packaged with same information in this order.";

    public static final String MSG_ORDER_DELIVERED =
            "Warning, state [Delivered] in order, you can't add information.";



    public OrderParameters() {}
}
