package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurvePointServiceImplTest {
    @InjectMocks
    private CurvePointServiceImpl curvePointService;

    @Mock
    private CurvePointRepository curvePointRepository;

    @Test
    public void getAllCurvePointTest() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
        CurvePoint curvePoint1 = new CurvePoint(12, 12d, 32d);

        List<CurvePoint> list = new ArrayList<>();

        list.add(curvePoint);
        list.add(curvePoint1);

        when(curvePointRepository.findAll()).thenReturn(list);

        //WHEN
        List<CurvePoint> listResult = curvePointService.getAllCurvePoint();

        //THEN
        verify(curvePointRepository, Mockito.times(1)).findAll();
        assertTrue(listResult.size() == 2);
    }


    @Test
    public void saveCurvePointTest(){
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
        CurvePoint expected = new CurvePoint(1, 10, 10d, 30d);

        when(curvePointRepository.save(curvePoint)).thenReturn(expected);

        //WHEN
        CurvePoint savedCurvePoint = curvePointService.saveCurvePoint(curvePoint);

        //THEN
        assertEquals(expected.getId(), savedCurvePoint.getId());
        verify(curvePointRepository, Mockito.times(1)).save(curvePoint);
    }

    @Test
    public void getCurvePointByIdTest(){
        //GIVEN
        Integer id = 1;
        CurvePoint curvePoint = new CurvePoint(1,10,10d,30d);

        when(curvePointRepository.findById(id)).thenReturn(Optional.of(curvePoint));

        //WHEN
        CurvePoint curvePointById = curvePointService.getCurvePointById(1);

        //THEN
        assertTrue(curvePointById.getTerm() == 10);
        assertTrue(curvePointById.getValue() == 30);
    }

    @Test
    public void deleteCurvePointTest(){
        //GIVEN
        Integer id =1;
        CurvePoint curvePoint = new CurvePoint(1, 10, 10d, 30d);

        when(curvePointRepository.findById(id)).thenReturn(Optional.of(curvePoint));
        doNothing().when(curvePointRepository).delete(curvePoint);

        //WHEN
        curvePointService.deleteCurvePoint(id);

        //THEN
        verify(curvePointRepository, Mockito.times(1)).findById(id);
        verify(curvePointRepository, Mockito.times(1)).delete(curvePoint);
    }
}
