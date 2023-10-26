package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import edu.hw3.task6.StockMarketImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StockMarketImplTest {
    @Test
    @DisplayName("добавление акций и нахождение самой ценной")
    void stockMarket_addStocks() {
        StockMarket stockMarket = new StockMarketImpl();
        stockMarket.add(new Stock(100, true, "Coca-Cola"));
        stockMarket.add(new Stock(500, false, "Pepsi"));
        stockMarket.add(new Stock(50, true, "Chernogolovka"));
        assertEquals(stockMarket.mostvaluableStock().getCompany(), "Pepsi");
    }

    @Test
    @DisplayName("удаление самой ценной акции, и нахождение второй по ценности")
    void stockMarket_addStocks_withDeletion() {
        StockMarket stockMarket = new StockMarketImpl();
        stockMarket.add(new Stock(600, true, "Apple"));
        stockMarket.add(new Stock(450, false, "Samsung"));
        stockMarket.add(new Stock(500, true, "Huawei"));
        stockMarket.remove(stockMarket.mostvaluableStock());
        assertEquals(stockMarket.mostvaluableStock().getCompany(), "Huawei");
    }

}
