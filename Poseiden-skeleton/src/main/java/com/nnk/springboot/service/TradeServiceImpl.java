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

    /**
     * @return the list of all trade saved in database
     */
    @Override
    public List<Trade> getAllTrade() {
        List<Trade> allTrade = tradeRepository.findAll();
        return allTrade;
    }

    /**
     * @param trade a trade type object
     * @return trade saved in database with an identifier
     */
    @Override
    public Trade saveTrade(Trade trade) {
        Trade savedTrade = tradeRepository.save(trade);
        return savedTrade;
    }

    /**
     * @param id identifier of a trade we want to get
     * @return a trade object saved in database if existing
     */
    @Override
    public Trade getTradeById(Integer id) {
        Trade trade= tradeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Trade Id: "+id));
        return trade;
    }

    /**
     * @param id identifier of the trade we want to delete
     */
    @Override
    public void deleteTrade(Integer id) {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Trade Id: "+id));
        tradeRepository.delete(trade);
    }
}
