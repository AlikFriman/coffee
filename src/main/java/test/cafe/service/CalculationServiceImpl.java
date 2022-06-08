package test.cafe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.cafe.model.Order;
import test.cafe.model.OrderItem;
import test.cafe.model.type.DeliveryType;

import java.math.BigDecimal;

@Service
public class CalculationServiceImpl implements CalculationServiceInternal {

    @Value("${coffee.delivery-price}")
    private BigDecimal deliveryPrice;
    @Value("${coffee.free-cup}")
    private Integer freeCup;
    @Value("${coffee.free-sum-delivery}")
    private BigDecimal freeSumDelivery;

    @Override
    public Order processOrder(Order order) {

        order.setSum(order.getItems().stream()
//      Берется заказ, обрабатываются все позиции
                .map(this::processOrderItem)
//               Принимает позицию заказа, достает стоимость позиции заказа
                .map(OrderItem::getSum)
//               Сложение всех элементов стоимости позиций заказа, в дальнейшем присваивается сумме заказа (setSum)
                .reduce(BigDecimal::add)
//                Если нет позиций заказа, возвращает 0 сумме заказа
                .orElse(BigDecimal.ZERO));

//        Добавление к сумме заказа стоимости доставки
        if (order.getDeliveryType() == DeliveryType.DELIVERY) {
            BigDecimal deliveryPrice = calculateDeliveryPrice(order);
            order.setSum(order.getSum().add(deliveryPrice));
        }

        return order;
    }

    private OrderItem processOrderItem(OrderItem orderItem) {
//        Колличество кружек
        Integer count = orderItem.getCount();
//        Цена заказа
        BigDecimal price = orderItem.getCoffeeType().getPrice();
//       Расчет суммы стоимости позиции заказа, с учетом количества бесплатных кружек
        BigDecimal sum = price.multiply(BigDecimal.valueOf(count - count / freeCup));
        orderItem.setSum(sum);
        return orderItem;
    }

    private BigDecimal calculateDeliveryPrice(Order order) {
//        Расчет стоимости доставки с учетом скидки
        return order.getSum().compareTo(freeSumDelivery) < 0 && order.getItems().size() > 0 ? deliveryPrice : BigDecimal.ZERO;
    }
}
