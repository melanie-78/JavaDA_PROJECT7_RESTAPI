package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidListServiceImplTest {
    @InjectMocks
    private BidListServiceImpl bidListService;

    @Mock
    private BidListRepository bidListRepository;

    @Test
    public void getAllBidListTest() {
        //GIVEN
        BidList bidList = new BidList("Account Test", "Type Test", 10d);
        BidList bidList1 = new BidList("Account Test1", "Type Test1", 12d);

        List<BidList> list = new ArrayList<>();
        list.add(bidList);
        list.add(bidList1);

        when(bidListRepository.findAll()).thenReturn(list);

        //WHEN
        List<BidList> listResult = bidListService.getAllBidList();

        //THEN
        assertTrue(listResult.size() == 2);
        verify(bidListRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void saveBidTest() {
        //GIVEN
        BidList bidList = new BidList("Account Test", "Type Test", 10d);
        BidList savedBidList = new BidList(1,"Account Test", "Type Test", 10d);

        when(bidListRepository.save(bidList)).thenReturn(savedBidList);

        //WHEN
        BidList actual= bidListService.saveBid(bidList);

        //THEN
        verify(bidListRepository, Mockito.times(1)).save(bidList);
        assertEquals(savedBidList.getBidListId(), actual.getBidListId());
    }

    @Test
    public void getBidTest() {
        //GIVEN
        Integer id = 1;
        BidList bidList = new BidList(1,"Account Test", "Type Test", 10d);

        when(bidListRepository.findById(id)).thenReturn(Optional.of(bidList));

        //WHEN
        BidList actual = bidListService.getBid(id);

        //THEN
        assertTrue(actual.getBidQuantity() == 10);
        verify(bidListRepository, Mockito.times(1)).findById(id);
    }

    //@Test
    public void getBidThrowsExceptionTest() {
        //GIVEN
        Integer id = 1000;

        when(bidListRepository.findById(id)).thenThrow(IllegalArgumentException.class);

        //verify(bidListRepository, Mockito.times(1)).findById(id);
        //assertThrows(IllegalArgumentException.class, ()->bidListService.getBid(id));
    }


    @Test
    public void deleteBidTest() {
        //GIVEN
        Integer id = 1;
        BidList bidList = new BidList(1, "Account Test", "Type Test", 10d);

        when(bidListRepository.findById(id)).thenReturn(Optional.of(bidList));
        doNothing().when(bidListRepository).delete(bidList);

        //WHEN
        bidListService.deleteBid(id);

        //THEN
        verify (bidListRepository, Mockito.times(1)).findById(id);
        verify(bidListRepository,Mockito.times(1)).delete(bidList);
    }
}
