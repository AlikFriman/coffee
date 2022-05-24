package test.cafe.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.cafe.model.OrderItem;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem, Integer> {

    // todo: "Волшебные" методы JPA-репозиториев
    Optional<OrderItem> findFirstByIdAndOrder_Id(Integer id, Integer orderId);
}
