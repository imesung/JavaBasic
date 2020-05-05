package org.example.week01.product;

public class Product {
    private long prdCd;      // 상품코드
    private long prdPrc;     // 상품가격
    private long giftNo;     // 사은품 코드 - 0 일 경우 사은품 없음!
    private int stock;       // 재고

    public Product(long prdCd, long prdPrc, long giftNo, int stock) {
        this.prdCd = prdCd;
        this.prdPrc = prdPrc;
        this.giftNo = giftNo;
        this.stock = stock;
    }
}
