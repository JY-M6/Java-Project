package com.hmall.trade.listener;

import com.hmall.trade.domain.po.Order;
import com.hmall.trade.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayStatusListener {
    private final IOrderService orderService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "trade.pay.success.queue", durable = "true"),
            exchange = @Exchange(name = "pay.direct", type = ExchangeTypes.TOPIC),
            key = "pay.success"))
    public void listenPaySuccess(Long orderId) {
        //1.查询订单
        Order order = orderService.getById(orderId);
        //2.判断订单状态，订单状态是否为待支付

        if (order.getStatus() != 1 || order == null) {
            return;
        }
        //3.修改订单状态
        orderService.markOrderPaySuccess(orderId);
    }
}

