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

public class OrderServiceImpl extends OrderService {

    @Override
    public HashMap<String, String> orderSheetRequest(Customer customer, Product product) {
        HashMap<String, String> result = new HashMap<>();

        result.put("Status", "S");

        //1. 로그인 상태 확인
        boolean loginChk = checkLoginStatus();
        if(!loginChk) {
            System.out.println("미로그인 상태");
            return result;
        }

        //2. 고객 주문 가능 상태 확인
        CustService custService = new CustServiceImpl();
        HashMap<String, String> cust = custService.reqeustCustService(customer);
        if(cust.get("custStCd").equals("false")) {
            System.out.println("해당 고객 주문 불가");
            return result;
        }

        //3. 상품 재고 및 사은품 재고 상태 확인
        ProductService productService = new ProductServiceImpl();
        HashMap<String, String> prd = productService.requestProductService(product);
        if(prd.get("prdStCd").equals("false")) {
            System.out.println("상품 및 사은품 재고 없음");
            return result;
        }

        //4. 일반 고객 주문 가능 여부 확인(사은품 상태 체크)
        if(customer.getGubun() == 1 && prd.get("giftStCd").equals("false")) {
            System.out.println("일반 고객으로 인해 사은품 재고 없어 주문 불가");
            return result;
        }

        //5. 임직원 주문 가능 여부 확인(사은품 상태 체크)
        if(customer.getGubun() == 0 && prd.get("giftStCd").equals("true")) {
            System.out.println("임직원 고개으로 인해 사은품 재고 있어 주문 불가");
            return result;
        }

        //6. 결제 진행
        PaymentService paymentService = new PaymentServiceImpl();
        HashMap<String, String> payment = paymentService.requestPayment(customer, product);
        if(payment.get("payFlg").equals("false")) {
            System.out.println("결제 실패");
            return result;
        }

        result.put("Status", "E");

        return result;
    }

    @Override
    protected boolean checkLoginStatus() {
        return true;
    }
}
