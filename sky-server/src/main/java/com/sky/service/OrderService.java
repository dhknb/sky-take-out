package com.sky.service;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO);

    PageResult pageQuery4User(int page, int pageSize, Integer status);

    OrderVO details(Long id);

    /**
     * 用户取消订单
     * @param id 订单id
     */
    void userCancelById(Long id);

    /**
     * 商家取消订单（无真实支付场景适配）
     * @param ordersCancelDTO 取消参数
     */
    void cancel(OrdersCancelDTO ordersCancelDTO);

    void repetition(Long id);

    /**
     * 订单搜索（管理端）
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 各个状态订单数量统计（管理端）
     */
    OrderStatisticsVO statistics();

    /**
     * 商家接单
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 商家拒单（无真实支付场景适配）
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO);

    /**
     * 商家派送订单
     */
    void delivery(Long id);

    /**
     * 商家完成订单
     */
    void complete(Long id);



    void reminder(Long id);
}
