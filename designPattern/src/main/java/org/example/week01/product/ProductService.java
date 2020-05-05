package org.example.week01.product;

import java.util.HashMap;

public abstract class ProductService {
    protected abstract boolean checkProduct(Product product);
    protected abstract boolean checkGift(long giftNo);

    public HashMap<String, String> requestProductService(Product product) {
        HashMap<String, String> result = new HashMap<>();

        System.out.println("선택하신 상품 코드 : " + product.getPrdCd());

        boolean chkPrd = checkProduct(product);
        result.put("prdStCd", String.valueOf(chkPrd));

        boolean chkGift = checkGift(product.getGiftNo());
        result.put("giftStCd", String.valueOf(chkGift));

        return result;
    }
}
