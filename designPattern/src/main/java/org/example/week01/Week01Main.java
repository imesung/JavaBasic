package org.example.week01;

import org.example.week01.customer.Customer;
import org.example.week01.order.OrderService;
import org.example.week01.order.OrderServiceImpl;
import org.example.week01.product.Product;

public class Week01Main {
    public static void main(String[] args) {
        //Customer customer = new Customer("dynee313", "dy", 0, 0, false);    	  //도연 임직원
        //Customer customer = new Customer("imesung", "hs", 0, 10000, false);     //혜성 임직원
        //Customer customer = new Customer("mike6321", "jw", 1, 2000, false);     //준우 일반고객
        Customer customer = new Customer("leetsh", "sh", 2, 0, true);      	  //상현 일반고객 블랙컨슈머

        Product product1 = new Product(111111, 20000, 12345, 10);	// 상품코드, 가격, 사은품코드, 재고
        Product product2 = new Product(222222, 10000, 0, 40);
        Product product3 = new Product(222222, 10000, 0, 0);

        OrderService orderService = new OrderServiceImpl();
        orderService.createOrder(customer, product1);
        System.out.println("========================");
        orderService.createOrder(customer, product2);
        System.out.println("========================");
        orderService.createOrder(customer, product3);
        System.out.println("========================");
    }
}
