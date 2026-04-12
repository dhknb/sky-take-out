package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;
    @Scheduled(cron = "0 * * * * ?")

    public void processTimeoutOrder(){
        log.info("处理支付超时订单：{}", LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
       List<Orders> lists= orderMapper.getByStatusAndOrdertimeLT(Orders.PENDING_PAYMENT,time);
       if(lists!=null&&lists.size()>0){
           for (Orders list : lists) {
               list.setStatus(Orders.CANCELLED);
               list.setCancelReason("订单支付已超时");
               list.setCancelTime(LocalDateTime.now());
               orderMapper.update(list);
           }
       }
    }

    /**
     * 处理“派送中”状态的订单
     */
    @Scheduled(cron = "0 0 1 * * ?")

    public void processDeliveryOrder(){
        log.info("处理派送中订单：{}", LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> lists= orderMapper.getByStatusAndOrdertimeLT(Orders.DELIVERY_IN_PROGRESS,time);
        if(lists!=null&&lists.size()>0){
            for (Orders list : lists) {
                list.setStatus(Orders.CANCELLED);
                orderMapper.update(list);
            }
        }
    }

}
