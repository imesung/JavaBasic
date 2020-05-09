package org.example.week01.product;

import java.util.HashMap;
import java.util.Map;

public abstract class ProductService {
    protected abstract boolean checkProduct(Product product);
    protected abstract boolean checkGift(Product product);

    public Map<String, String> requestProductService(Product product) {
        Map<String, String> result = new HashMap<>();

        boolean chkPrd = checkProduct(product);
        result.put("prdStCd", String.valueOf(chkPrd));

        boolean chkGift = checkGift(product);
        result.put("giftStCd", String.valueOf(chkGift));

        return result;
    }
}

