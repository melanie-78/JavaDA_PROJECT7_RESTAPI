package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BidListServiceImplTest {
/*    @InjectMocks
    private BidListServiceImpl bidListService;

    @Mock
    BidListRepository bidListRepository;

    @Test
    public void getBidTest(){
        //GIVEN
        int bidListId = 1;
        BidList bidList = new BidList();
        when((bidListRepository).findById(bidListId)).thenReturn(Optional.of(bidList));
        BidList expected = new BidList();

        //WHEN
        BidList actual = bidListService.getBid(bidListId);

        //THEN
        verify(bidListRepository, Mockito.times(1)).findById(any());
    }
}*/
}