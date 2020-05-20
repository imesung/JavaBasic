package com.github.bakerybluprint.croissant.week_03.hs.homework.product;


import com.github.bakerybluprint.croissant.week_03.hs.homework.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Product {
    private static Logger logger = LoggerFactory.getLogger(Product.class);
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
            logger.info("상품 재고가 있습니다.");
            return true;
        }
        logger.info("상품 재고가 없습니다.");
        return false;
    }

    //사은품 재고 상태 확인
    public boolean chkGift() {
        if(this.giftNo > 0) {
            logger.info("사은품 재고가 있습니다.");
            return true;
        }
        logger.info("사은품 재고가 없습니다.");
        return false;
    }

    //포인트 사용을 위해 상품 금액이 3000원 넘나 확인.
    //객체지향적으로 트레이드오프 해야함.
    public boolean chkPrdPrc() {
        if(this.prdPrc > 3000) {
            logger.info("상품이 3000원 이하여서 포인트 사용이 불가 합니다.");
            return true;
        }
        logger.info("상품이 3000원 이상이여서 포인트 사용이 가능합니다.");
        return false;
    }
}