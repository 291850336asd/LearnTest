package com.meng.example.service;

import com.meng.example.entity.Order;
import com.meng.example.entity.OrderItem;
import com.meng.example.repository.OrderItemRepository;
import com.meng.example.repository.OrderRepository;
import io.shardingjdbc.core.api.HintManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderItemRepository orderItemRepository;

    public void demo() {
        //创建表
        orderRepository.createIfNotExistsTable();
        orderItemRepository.createIfNotExistsTable();
        //清空表
     //   orderRepository.truncateTable();
      //  orderItemRepository.truncateTable();
        List<Long> orderIds = new ArrayList<>(10);
        System.out.println("1.Insert------插入--------");
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId(51);
            order.setStatus("INSERT_TEST");
            orderRepository.insert(order);
            long orderId = order.getOrderId();
            orderIds.add(orderId);

            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(51);
            item.setStatus("INSERT_TEST");
            orderItemRepository.insert(item);
        }
        //查询
        System.out.println(orderItemRepository.selectAll());
        //删除
    /*    for (Long each : orderIds) {
            orderRepository.delete(each);
            orderItemRepository.delete(each);
        }*/
      //  System.out.println("2.Delete--------------");
     //   HintManager hintManager = HintManager.getInstance();
        System.out.println(orderItemRepository.selectAll());
     //   hintManager.close();
      /*  orderItemRepository.dropTable();
        orderRepository.dropTable();*/
    }
}