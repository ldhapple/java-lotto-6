package lotto.domain;

public class EarningRate {
    private final double rate;

    public EarningRate(double numOfTickets, double earnedMoney) {
        this.rate = calcRate(numOfTickets, earnedMoney);
    }

    private double calcRate(double numOfTickets, double earnedMoney) {
        return earnedMoney / (numOfTickets * 1000) * 100;
    }
}