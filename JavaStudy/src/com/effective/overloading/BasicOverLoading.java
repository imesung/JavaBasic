package com.effective.overloading;

public class BasicOverLoading {
    String brandName;
    int since;
    int wheelPrice;
    int sheetPrice;
    int enginePrice;

    public BasicOverLoading(int since) {
        this(since, "기아");
    }

    public BasicOverLoading(String brandName) {
        this.brandName = brandName;
    }

    public BasicOverLoading(int since, String brandName) {
        this.brandName = brandName;
        this.since = since;
    }

    public int bodySizeSum(int wheelPrice) {
        return wheelPrice;
    }

    public int bodySizeSum(int wheelPrice, int sheetPrice) {
        return wheelPrice + sheetPrice;
    }

    public int bodySizeSum(int wheelPrice, int sheetPrice, int enginePrice) {
        return wheelPrice + sheetPrice + enginePrice;
    }
}
