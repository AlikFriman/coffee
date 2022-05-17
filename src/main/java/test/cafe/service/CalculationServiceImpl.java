package test.cafe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.cafe.model.Order;
import test.cafe.model.OrderItem;
import test.cafe.model.type.DeliveryType;

import java.math.BigDecimal;

@Service
public class CalculationServiceImpl implements CalculationService {

    @Value("${coffee.delivery-price}")
    private BigDecimal deliveryPrice;
    @Value("${coffee.free-cup}")
    private Integer freeCup;
    @Value("${coffee.free-sum-delivery}")
    private BigDecimal freeSumDelivery;

    @Override
    public Order processOrder(Order order) {
//        for (OrderItem orderItem : order.getItems()) {
//            processOrderItem(orderItem);
//        }

        order.setSum(order.getItems().stream()
                .map(this::processOrderItem)
                .map(OrderItem::getSum)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO));

        if (order.getDeliveryType() == DeliveryType.DELIVERY) {
            BigDecimal deliveryPrice = calculateDeliveryPrice(order);
            order.setSum(order.getSum().add(deliveryPrice));
        }

        return order;
    }

    private OrderItem processOrderItem(OrderItem orderItem) {
        Integer count = orderItem.getCount();
        BigDecimal price = orderItem.getCoffeeType().getPrice();
        BigDecimal sum = price.multiply(BigDecimal.valueOf(count - count / freeCup));
        orderItem.setSum(sum);
        return orderItem;
    }

    private BigDecimal calculateDeliveryPrice(Order order) {
        return order.getSum().compareTo(freeSumDelivery) < 0 ? deliveryPrice :  BigDecimal.ZERO;
    }
}
