package org.example.week01.order;

import org.example.week01.customer.CustService;
import org.example.week01.customer.CustServiceImpl;
import org.example.week01.customer.Customer;
import org.example.week01.payment.PaymentService;
import org.example.week01.payment.PaymentServiceImpl;
import org.example.week01.product.Product;
import org.example.week01.product.ProductService;
import org.example.week01.product.ProductServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceImpl extends OrderService {

    @Override
    public Map<String, String> orderSheetRequest(Customer customer, Product product) {
        Map<String, String> result = new HashMap<>();

        result.put("Status", "S");

        //1. 로그인 상태 확인
        //싱글톤
        if (login()) return result;

        //2. 고객 주문 가능 상태 확인
        if (orderStatus(customer)) return result;

        //3. 상품 재고 및 사은품 재고 상태 확인
        ProductService productService = new ProductServiceImpl();
        Map<String, String> prd = productService.requestProductService(product);
        if (stockStatus(prd.get("prdStCd").equals("false"), "상품 및 사은품 재고 없음")) return result;

        //4. 해당 고객 주문 가능 여부 확인(사은품 상태 체크)
        if(!customer.chkCustGubun(prd.get("giftStCd"))) {
            return result;
        }

        //5. 결제 진행
        if (payment(customer, product)) return result;

        result.put("Status", "E");

        return result;
    }

    private boolean stockStatus(boolean equals, String s) {
        if (equals) {
            System.out.println(s);
            return true;
        }
        return false;
    }

    private boolean orderStatus(Customer customer) {
        CustService custService = new CustServiceImpl();
        Map<String, String> cust = custService.reqeustCustService(customer);
        if(cust.get("custStCd").equals("false")) {
            return true;
        }
        return false;
    }

    private boolean login() {
        boolean loginChk = checkLoginStatus();
        if (stockStatus(!loginChk, "미로그인 상태")) return true;
        return false;
    }

    private boolean payment(Customer customer, Product product) {
        PaymentService paymentService = new PaymentServiceImpl();
        Map<String, String> payment = paymentService.requestPayment(customer, product);
        if(payment.get("payFlg").equals("false")) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkLoginStatus() {
        return true;
    }
}
