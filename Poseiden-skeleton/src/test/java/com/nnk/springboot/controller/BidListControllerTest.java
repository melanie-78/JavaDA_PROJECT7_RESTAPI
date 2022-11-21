package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    @MockBean
    private BidListRepository bidListRepository;


    @Test
    @WithMockUser
    public void testHome() throws Exception {
        List<BidList> list = new ArrayList<>();

        when(bidListService.getAllBidList()).thenReturn(list);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/bidList/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfBid"));
    }

    @Test
    @WithMockUser
    public void testAddBidForm() throws Exception {
        BidList bid = new BidList();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/bidList/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testShowUpdateForm() throws Exception {
        Integer id = 1;
        BidList bidList = new BidList();

        when(bidListService.getBid(id)).thenReturn(bidList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/bidList/update/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"))
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"));
    }


    @Test
    @WithMockUser
    public void testValidate() throws Exception {
        BidList bidList = new BidList();
        BidList savedBidList = new BidList();

        when(bidListService.saveBid(bidList)).thenReturn(savedBidList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/bidList/validate")
                        .param("account","Account Test")
                        .param("type", "Type test" )
                        .param("bidQuantity", "10d"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testUpdateBid() throws Exception {
        BidList bidList = new BidList();
        Integer bidListId = 1;

        when(bidListService.saveBid(bidList)).thenReturn(bidList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/bidList/update/"+ bidListId)
                        .param("account","Account Test")
                        .param("type", "Type test" )
                        .param("bidQuantity", "10"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list"));
    }

    @Test
    @WithMockUser
    public void testDeleteBid() throws Exception {
        Integer id = 1;
        BidList bidList = new BidList(1,"Account Test", "Type Test", 10d);

        when(bidListRepository.findById(id)).thenReturn(Optional.of(bidList));

        doNothing().when(bidListService).deleteBid(id);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/bidList/delete/"+id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list"));
    }
}
