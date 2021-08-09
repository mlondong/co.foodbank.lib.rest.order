package co.com.foodbank.order.v1.model;

import java.util.Collection;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import co.com.foodbank.order.dto.IOrder;
import co.com.foodbank.packaged.dto.IPackaged;
import co.com.foodbank.user.model.IBeneficiary;
import co.com.foodbank.user.model.IVolunter;


/**
 * Class to represent an Order for foodbabk.
 * 
 * @author mauricio.londono@gmail.com co.com.foodbank.order.v1.model 9/08/2021
 */
@Document(collection = "Order")
public class Order implements IOrder {


    @Id
    private String id;
    private Date dateOrder;
    private IBeneficiary beneficiary;
    private IVolunter volunter;
    private Collection<IPackaged> packages;
    private IStateOrder state;
    // private Collection<Message> message;


    /**
     * Default constructor.
     */
    public Order() {}

    @Override
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Collection<IPackaged> getPackages() {
        return packages;
    }


    public void setPackages(Collection<IPackaged> packages) {
        this.packages = packages;
    }

    @Override
    public Date getDateOrder() {
        return dateOrder;
    }


    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }


    @Override
    public IBeneficiary getBeneficiary() {
        return beneficiary;
    }


    public void setBeneficiary(IBeneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    @Override
    public IVolunter getVolunter() {
        return volunter;
    }


    public void setVolunter(IVolunter volunter) {
        this.volunter = volunter;
    }
    /*
     * @Override public Collection<Message> getMessage() { return message; }
     * 
     * 
     * public void setMessage(Collection<Message> message) { this.message =
     * message; }
     */

    public IStateOrder getState() {
        return state;
    }

    public void setState(IStateOrder state) {
        this.state = state;
    }


}
