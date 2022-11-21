package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @Test
    @WithMockUser
    public void testHome() throws Exception {
        List<Trade> list = new ArrayList<>();

        when(tradeService.getAllTrade()).thenReturn(list);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/trade/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfTrade"));
    }

    @Test
    @WithMockUser
    public void testAddTradeForm() throws Exception {
        Trade trade = new Trade();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/trade/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testValidate() throws Exception {
        Trade trade = new Trade();
        Trade savedTrade = new Trade();

        when(tradeService.saveTrade(trade)).thenReturn(savedTrade);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/trade/validate")
                        .param("account","Account")
                        .param("type", "type"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testShowUpdateForm() throws Exception {
        Integer id = 1;
        Trade trade = new Trade();

        when(tradeService.getTradeById(id)).thenReturn(trade);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/trade/update/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("trade"))
                .andExpect(MockMvcResultMatchers.view().name("trade/update"));
    }

    @Test
    @WithMockUser
    public void testUpdateTrade() throws Exception {
        Integer id = 1;
        Trade trade = new Trade();

        when(tradeService.saveTrade(trade)).thenReturn(trade);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/trade/update/" + id)
                        .param("account","Account")
                        .param("type", "type"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list"));
    }

    @Test
    @WithMockUser
    public void testDeleteTrade() throws Exception {
        Integer id = 1;
        Trade trade = new Trade(1, "Trade Account", "Type");

        doNothing().when(tradeService).deleteTrade(id);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/trade/delete/"+id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list"));
    }
}
