package test.cafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.cafe.model.CoffeeType;
import test.cafe.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
