package com.imooc.mythread;

public class Result {
    private String site;

    private int price;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    int getPrice() {
        return price;
    }

    void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Result{" +
                "site='" + site + '\'' +
                ", price=" + price +
                '}';
    }
}
