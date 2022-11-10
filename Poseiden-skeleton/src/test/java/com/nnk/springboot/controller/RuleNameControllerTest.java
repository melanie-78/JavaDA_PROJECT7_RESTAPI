package com.nnk.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
public class RuleNameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService ruleNameService;

    @Test
    @WithMockUser
    public void testHome() throws Exception {
        List<RuleName> list = new ArrayList<>();

        when(ruleNameService.getAllRuleName()).thenReturn(list);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/ruleName/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfRuleName"));
    }

    @Test
    @WithMockUser
    public void testAddRuleForm() throws Exception {
        RuleName ruleName = new RuleName();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/ruleName/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser
    public void testValidate() throws Exception {
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(ruleName);

        //when(ruleNameService.saveRuleName(ruleName)).thenReturn(ruleName);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/ruleName/validate")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfRuleName"));
    }

    @Test
    @WithMockUser
    public void testShowUpdateForm() throws Exception {
        Integer id = 1;
        RuleName ruleName = new RuleName();

        when(ruleNameService.getRuleNameById(id)).thenReturn(ruleName);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/ruleName/update/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleName"))
                .andExpect(MockMvcResultMatchers.view().name("ruleName/update"));
    }

    //@Test
    @WithMockUser
    public void testUpdateRuleName() throws Exception {
        Integer id = 1;
        RuleName ruleName = new RuleName();
        RuleName savedRuleName = new RuleName();

        //when(ruleNameService.saveRuleName(ruleName)).thenReturn(savedRuleName);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/ruleName/update/" + id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfRuleName"));
    }

    @Test
    @WithMockUser
    public void testDeleteTrade() throws Exception {
        Integer id = 1;
        RuleName ruleName = new RuleName();

        doNothing().when(ruleNameService).deleteRuleName(id);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/ruleName/delete/"+id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ruleName/list"));
    }
}