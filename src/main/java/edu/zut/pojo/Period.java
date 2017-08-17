package edu.zut.pojo;

/**
 * 彩票每一期的对象
 * Created by FDD on 2017/8/17.
 */
public class Period {
    private String date;//开奖日期

    private String issue;//期号

    private String opeNum;//开奖号码

    private String money;//销售额

    private String firstNum;//一等奖中奖注数

    private String secondNum;//二等奖中奖注数

    public Period(String date, String issue, String opeNum, String money, String firstNum, String secondNum) {
        this.date = date;
        this.issue = issue;
        this.opeNum = opeNum;
        this.money = money;
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getOpeNum() {
        return opeNum;
    }

    public void setOpeNum(String opeNum) {
        this.opeNum = opeNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(String firstNum) {
        this.firstNum = firstNum;
    }

    public String getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(String secondNum) {
        this.secondNum = secondNum;
    }
}
