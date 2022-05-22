package test.cafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.cafe.model.OrderItem;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    // todo: "Волшебные" методы JPA-репозиториев
    Optional<OrderItem> findFirstByIdAndOrder_Id(Integer id, Integer orderId);
}
