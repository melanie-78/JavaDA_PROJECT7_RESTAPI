package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService{
    private BidListRepository bidListRepository;

    public BidListServiceImpl(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    /**
     * @return the list of all bid stored in database
     */
    @Override
    public List<BidList> getAllBidList() {
        List<BidList> bidLists = bidListRepository.findAll();
        return bidLists;
    }

    /**
     * @param bid a bidList type object
     * @return bid saved in database
     */
    @Override
    public BidList saveBid(BidList bid) {
        BidList savedBid = bidListRepository.save(bid);
        return savedBid;
    }

    /**
     * @param bidListId identifier of the bid we want to get
     * @return a bidList object saved in database if existing
     */
    @Override
    public BidList getBid(Integer bidListId) {
        BidList bidById = bidListRepository.findById(bidListId)
                .orElseThrow(()->new IllegalArgumentException("Invalid bid Id: "+ bidListId));
        return bidById;
    }

    /**
     * @param bidListId identifier of the bid we want to delete
     */
    @Override
    public void deleteBid(Integer bidListId) {
        BidList bidById= bidListRepository.findById(bidListId)
                .orElseThrow(()->new IllegalArgumentException("Invalid bid Id: " + bidListId));
        bidListRepository.delete(bidById);
    }
}
