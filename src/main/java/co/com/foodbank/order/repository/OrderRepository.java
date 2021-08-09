package co.com.foodbank.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import co.com.foodbank.order.v1.model.Order;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.order.repository 9/08/2021
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
