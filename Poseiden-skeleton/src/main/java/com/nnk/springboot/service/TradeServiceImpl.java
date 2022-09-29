package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService{
    private TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<Trade> getAllTrade() {
        List<Trade> allTrade = tradeRepository.findAll();
        return allTrade;
    }

    @Override
    public Trade saveTrade(Trade trade) {
        Trade savedTrade = tradeRepository.save(trade);
        return savedTrade;
    }

    @Override
    public Trade getTradeById(Integer id) {
        Trade trade= tradeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Trade Id: "+id));
        return trade;
    }

    @Override
    public void deleteTrade(Integer id) {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Trade Id: "+id));
        tradeRepository.delete(trade);
    }
}
