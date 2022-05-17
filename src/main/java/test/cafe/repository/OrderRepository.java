package test.cafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.cafe.model.CoffeeType;
import test.cafe.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Override
    List<Order> findAll();
}
