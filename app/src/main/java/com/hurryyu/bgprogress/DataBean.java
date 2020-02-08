package com.hurryyu.bgprogress;

public class DataBean {
    private String money;
    private String count;
    private double percent;

    public DataBean(String money, String count, double percent) {
        this.money = money;
        this.count = count;
        this.percent = percent;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
