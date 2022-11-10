package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointServiceImpl implements CurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    @Override
    public List<CurvePoint> getAllCurvePoint() {
        List<CurvePoint> allCurvePoint = curvePointRepository.findAll();
        return allCurvePoint;
    }

    @Override
    public void deleteCurvePoint(Integer curveId) {
        CurvePoint curvePoint = curvePointRepository.findById(curveId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curve Point id: " + curveId));
        curvePointRepository.delete(curvePoint);
    }

    @Override
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
        return savedCurvePoint;
    }

    @Override
    public CurvePoint getCurvePointById(Integer id) {
        CurvePoint curvePointById = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id: "+ id));
        return curvePointById;
    }
}
