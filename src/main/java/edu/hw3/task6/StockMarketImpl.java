package edu.hw3.task6;

import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    public PriorityQueue<Stock> queue = new PriorityQueue<>((o1, o2) -> o2.getPrice() - o1.getPrice());

    @Override
    public void add(Stock stock) {
        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        queue.remove(stock);
    }

    @Override
    public Stock mostvaluableStock() {
        return queue.remove();
    }
}
