package org.example.week01.product;

public class ProductServiceImpl extends ProductService{

    @Override
    protected boolean checkProduct(Product product) {
        //재고 있음
        return true;
    }

    @Override
    protected boolean checkGift(long giftNo) {
        //사은품 있음
        return false;
    }
}
