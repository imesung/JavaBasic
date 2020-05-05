package org.example.week01.product;

public class Product {
    private final long prdCd;      // 상품코드
    private final long prdPrc;     // 상품가격
    private final long giftNo;     // 사은품 코드 - 0 일 경우 사은품 없음!
    private final int stock;       // 재고

    public Product(long prdCd, long prdPrc, long giftNo, int stock) {
        this.prdCd = prdCd;
        this.prdPrc = prdPrc;
        this.giftNo = giftNo;
        this.stock = stock;
    }

    public long getPrdCd() {
        return prdCd;
    }

    public long getPrdPrc() {
        return prdPrc;
    }

    public long getGiftNo() {
        return giftNo;
    }

    public int getStock() {
        return stock;
    }
}
