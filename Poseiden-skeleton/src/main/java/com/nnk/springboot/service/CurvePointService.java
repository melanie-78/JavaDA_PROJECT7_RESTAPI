package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface CurvePointService {
    List<CurvePoint> getAllCurvePoint();
    void deleteCurvePoint(Integer curveId);
    CurvePoint saveCurvePoint(CurvePoint curvePoint);
    CurvePoint getCurvePointById(Integer id);
}
