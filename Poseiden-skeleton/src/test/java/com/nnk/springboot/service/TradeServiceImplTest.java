package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TradeServiceImplTest {
    @InjectMocks
    private TradeServiceImpl tradeService;

    @Mock
    private TradeRepository tradeRepository;

    @Test
    public void getAllTradeTest() {
        //GIVEN
        Trade trade = new Trade("Trade Account", "Type");
        Trade trade1 = new Trade("Trade Account1", "Type1");

        List<Trade> list = new ArrayList<>();
        list.add(trade);
        list.add(trade1);

        when(tradeRepository.findAll()).thenReturn(list);

        //WHEN
        List<Trade> listResult = tradeService.getAllTrade();

        //THEN
        assertTrue(listResult.size() == 2);
    }

    @Test
    public void saveTradeTest() {
        //GIVEN
        Trade trade = new Trade("Trade Account", "Type");
        Trade expected = new Trade(1,"Trade Account1", "Type1");

        when(tradeRepository.save(trade)).thenReturn(expected);

        //WHEN
        Trade savedTrade = tradeService.saveTrade(trade);

        //THEN
        assertEquals(expected.getTradeId(), savedTrade.getTradeId());
        verify(tradeRepository, Mockito.times(1)).save(trade);
    }

    @Test
    public void getTradeByIdTest() {
        //GIVEN
        Integer id =1;
        Trade expected = new Trade(1,"Trade Account1", "Type1");

        when(tradeRepository.findById(id)).thenReturn(Optional.of(expected));

        //WHEN
        Trade actual = tradeService.getTradeById(id);

        //THEN
        assertEquals(expected.getTradeId(), actual.getTradeId());
        verify(tradeRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void deleteTradeTest(){
        //GIVEN
        Integer id =1;
        Trade trade = new Trade(1,"Trade Account1", "Type1");

        when(tradeRepository.findById(id)).thenReturn(Optional.of(trade));
        doNothing().when(tradeRepository).delete(trade);

        //WHEN
        tradeService.deleteTrade(id);

        //THEN
        verify(tradeRepository, Mockito.times(1)).findById(id);
        verify(tradeRepository, Mockito.times(1)).delete(trade);
    }

}
