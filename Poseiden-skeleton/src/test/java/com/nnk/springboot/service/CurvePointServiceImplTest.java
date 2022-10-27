package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;



@SpringBootTest
public class CurvePointServiceImplTest {
    @Autowired
    private CurvePointService curvePointService;

    @Autowired
    private CurvePointRepository curvePointRepository;


    @Test
    public void getAllCurvePointTest() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
        curvePointRepository.save(curvePoint);

        //WHEN
        List<CurvePoint> listResult = curvePointService.getAllCurvePoint();

        //THEN
        Assert.assertTrue(listResult.size() > 0);
    }


    @Test
    public void saveCurvePointTest(){
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

        //WHEN
        CurvePoint savedCurvePoint = curvePointService.saveCurvePoint(curvePoint);

        //THEN
        Assert.assertNotNull(savedCurvePoint.getId());
    }

    @Test
    public void deleteCurvePointTest(){
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
        curvePointRepository.save(curvePoint);
        Integer id = curvePoint.getId();

        //WHEN
        curvePointService.deleteCurvePoint(id);

        //THEN
        Optional<CurvePoint> curvePointDeleted = curvePointRepository.findById(id);
        Assert.assertFalse(curvePointDeleted.isPresent());
    }

    @Test
    public void getCurvePointByIdTest(){
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
        curvePointRepository.save(curvePoint);
        Integer id = curvePoint.getId();

        //WHEN
        CurvePoint curvePointById = curvePointService.getCurvePointById(id);

        //THEN
        Assert.assertTrue(curvePointById.getTerm() == 10);
        Assert.assertTrue(curvePointById.getValue() == 30);
    }
}
