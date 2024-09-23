package com.atguigu.mybatis_plus_demo.entity;

import lombok.Data;

@Data
public class OrderStoreUserDTO {
    private Order order;
    private Store store;
    private User user;
}
