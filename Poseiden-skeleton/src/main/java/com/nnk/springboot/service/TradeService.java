package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;

import java.util.List;

public interface TradeService {
    List<Trade> getAllTrade();
    Trade saveTrade(Trade trade);
    Trade getTradeById(Integer id);
    void deleteTrade(Integer id);
}
