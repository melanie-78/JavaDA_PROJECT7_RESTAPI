package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService {
    List<BidList> getAllBidList();
    BidList saveBid(BidList bid);
    BidList getBid(Integer bidListId);
    void deleteBid(Integer bidListId);
}
