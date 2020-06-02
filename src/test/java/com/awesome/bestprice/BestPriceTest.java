package com.awesome.bestprice;

import org.junit.Before;
import org.junit.Test;

public class BestPriceTest {

    private DiscountService discountService;
    private ExchangeService exchangeService;

    @Before
    public void setup() {
        discountService = new DiscountService();
        exchangeService = new ExchangeService();
    }

    @Test
    public void bestPrice() {
        BestPrice bestPrice = new BestPrice(discountService, exchangeService);
        bestPrice.show();
    }

    @Test
    public void showBestPrice() {
        BestPrice bestPrice = new BestPrice(discountService, exchangeService);
        bestPrice.showBestPrices();
    }
}