package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class )
public class BidListServiceImplTest {
    @InjectMocks
    private BidListServiceImpl bidListService;

    @Mock
    BidListRepository bidListRepository;

    @Test
    public void getBidTest(){
        //GIVEN
        Integer id = 1;
        BidList bidList = new BidList("Account Test", "Type Test", 10d);

        when(bidListRepository.findById(id)).thenReturn(Optional.of(bidList));

        //WHEN
        BidList actual = bidListService.getBid(id);

        //THEN
        assertTrue(actual.getBidQuantity() == 10);
    }
}
    /*@Autowired
    private BidListServiceImpl bidListService;

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    public void getAllBidListTest() {
        //GIVEN
        BidList bidList = new BidList("Account Test", "Type Test", 10d);
        bidListRepository.save(bidList);

        //WHEN
        List<BidList> listResult = bidListService.getAllBidList();

        //THEN
        assertTrue(listResult.size() > 0);
    }
    @Test
    public void getBidTest(){
        //GIVEN
        BidList bidList = new BidList("Account Test", "Type Test", 10d);
        BidList savedBidList = bidListRepository.save(bidList);
        Integer id = savedBidList.getBidListId();

        //WHEN
        BidList actual = bidListService.getBid(id);

        //THEN
        assertTrue(actual.getBidQuantity() == 10);
    }

    @Test
    public void saveBidTest(){
        //GIVEN
        BidList bidList = new BidList("Account Test", "Type Test", 10d);

        //WHEN
        BidList savedBidList = bidListService.saveBid(bidList);

        //THEN
        assertNotNull(savedBidList.getBidListId());
    }

    @Test
    public void deleteBidTest(){
        //GIVEN
        BidList bidList = new BidList("Account Test", "Type Test", 10d);
        bidListRepository.save(bidList);
        Integer id = bidList.getBidListId();

        //WHEN
        bidListService.deleteBid(id);

        //THEN
        Optional<BidList> bidListDeleted = bidListRepository.findById(id);
        assertFalse(bidListDeleted.isPresent());
    }*/