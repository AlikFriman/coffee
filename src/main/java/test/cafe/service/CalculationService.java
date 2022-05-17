package test.cafe.service;

import test.cafe.model.Order;

public interface CalculationService {

    /**
     * Рассчет стоимости заказа.
     *
     * @param order
     * @return
     */
    Order processOrder(Order order);
}
