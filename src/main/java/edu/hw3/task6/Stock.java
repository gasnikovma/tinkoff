package edu.hw3.task6;

public class Stock {
    private int price;
    private boolean areDividends;
    private String company;

    public Stock(int price, boolean areDividends, String company) {
        this.price = price;
        this.areDividends = areDividends;
        this.company = company;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAreDividends() {
        return areDividends;
    }

    public String getCompany() {
        return company;
    }
}

