package com.nnk.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;

    @MockBean
    private CurvePointRepository curvePointRepository;

    @Test
    @WithMockUser
    public void testHome() throws Exception {
        List<CurvePoint> list = new ArrayList<>();

        when(curvePointService.getAllCurvePoint()).thenReturn(list);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/curvePoint/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfCurvePoint"));
    }

    @Test
    @WithMockUser
    public void testAddCurvePointForm() throws Exception {
        CurvePoint curvePoint = new CurvePoint();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/curvePoint/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testValidate() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        CurvePoint savedCurvePoint = new CurvePoint();

        when(curvePointService.saveCurvePoint(curvePoint)).thenReturn(savedCurvePoint);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/curvePoint/validate")
                        .param("curveId", "Curve Id")
                        .param("term", "10")
                        .param("value", "10d"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testShowUpdateForm() throws Exception {
        Integer id = 1;
        CurvePoint curvePoint = new CurvePoint();

        when(curvePointService.getCurvePointById(id)).thenReturn(curvePoint);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/curvePoint/update/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("curvePoint"))
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/update"));
    }

    @Test
    @WithMockUser
    public void testUpdateCurvePoint() throws Exception {
        Integer id = 1;
        CurvePoint curvePoint = new CurvePoint(1, 10, 10d, 30d);

        when(curvePointService.saveCurvePoint(curvePoint)).thenReturn(curvePoint);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/curvePoint/update/" + id)
                        .param("curveId", "Curve Id")
                        .param("term", "10")
                        .param("value", "10d"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testDeleteCurvePoint() throws Exception {
        Integer id = 1;
        CurvePoint curvePoint = new CurvePoint(1, 10, 10d, 30d);

        when(curvePointRepository.findById(id)).thenReturn(Optional.of(curvePoint));

        doNothing().when(curvePointService).deleteCurvePoint(id);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/curvePoint/delete/"+id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/curvePoint/list"));
    }
}
