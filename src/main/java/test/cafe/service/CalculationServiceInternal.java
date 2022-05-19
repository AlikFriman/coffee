package test.cafe.service;

import test.cafe.model.Order;

public interface CalculationServiceInternal {

    /**
     * Рассчет стоимости заказа.
     *
     * @param order
     * @return
     */
    Order processOrder(Order order);
}
