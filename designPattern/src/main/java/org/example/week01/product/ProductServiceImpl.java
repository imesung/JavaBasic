package org.example.week01.product;

public class ProductServiceImpl extends ProductService{

    @Override
    protected boolean checkProduct(Product product) {
        boolean isPrd = product.chkPrd();
        if(!isPrd) {
            System.out.println("해당 상품 재고 없음");
        }
        return isPrd;
    }

    @Override
    protected boolean checkGift(Product product) {
        boolean isGift = product.chkGift();
        if(!isGift) {
            System.out.println("해당 상품의 사은품 재고 없음");
        }
        return isGift;
    }
}
