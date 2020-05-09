package org.example.week01.product;

import org.example.week01.customer.Customer;

public class Product {

    private final long prdCd; // 상품코드
    private final long prdPrc; // 상품가격
    private final long giftNo; // 사은품 코드 - 0 일 경우 사은품 없음!
    private final int stock; // 재고

    public Product(long prdCd, long prdPrc, long giftNo, int stock) {
        this.prdCd = prdCd;
        this.prdPrc = prdPrc;
        this.giftNo = giftNo;
        this.stock = stock;
        //System.out.println("선택하신 상품 코드 : " + this.prdCd);
    }

    //상품 재고 상태 확인
    public boolean chkPrd() {
        if(this.stock > 0) {
            return true;
        }
        return false;
    }

    //사은품 재고 상태 확인
    public boolean chkGift() {
        if(this.giftNo > 0) {
            return true;
        }
        return false;
    }

    public boolean payPrd(Customer customer) {

        //고객이 포인트 3000원 이상 가지고 있음
        if(customer.chkPoint()) {
            System.out.println("포인트를 사용하였습니다.");
            if(this.prdPrc > 3000) {
                System.out.println("결제 금액 : " + customer.usePoint(this.prdPrc));
            }
        } else {
            System.out.println("결제 금액 : " + this.prdPrc);
        }
        System.out.println("결제 완료되었습니다.");
        return true;
    }
}