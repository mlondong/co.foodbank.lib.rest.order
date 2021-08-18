package co.com.foodbank.order.v1.model;

import java.util.Collection;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import co.com.foodbank.message.dto.interfaces.IMessage;
import co.com.foodbank.order.dto.interfaces.IOrder;
import co.com.foodbank.packaged.dto.interfaces.IPackaged;
import co.com.foodbank.user.dto.response.BeneficiaryData;
import co.com.foodbank.user.dto.response.VolunteerData;


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
    private BeneficiaryData beneficiary;
    private VolunteerData volunter;
    private IStateOrder state;

    private Collection<IPackaged> packages;
    private Collection<IMessage> message;



    /**
     * Default constructor.
     */
    public Order() {}

    @PersistenceConstructor
    public Order(String id, Date dateOrder, BeneficiaryData beneficiary,
            VolunteerData volunter, IStateOrder state,
            Collection<IPackaged> packages, Collection<IMessage> message) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.beneficiary = beneficiary;
        this.volunter = volunter;
        this.state = state;
        this.packages = packages;
        this.message = message;
    }

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
    public BeneficiaryData getBeneficiary() {
        return beneficiary;
    }


    public void setBeneficiary(BeneficiaryData beneficiary) {
        this.beneficiary = beneficiary;
    }

    @Override
    public VolunteerData getVolunter() {
        return volunter;
    }


    public void setVolunter(VolunteerData volunter) {
        this.volunter = volunter;
    }

    @Override
    public Collection<IMessage> getMessage() {
        return message;
    }


    public void setMessage(Collection<IMessage> message) {
        this.message = message;
    }


    public IStateOrder getState() {
        return state;
    }

    public void setState(IStateOrder state) {
        this.state = state;
    }


}
