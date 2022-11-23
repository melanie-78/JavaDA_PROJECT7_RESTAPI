package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointServiceImpl implements CurvePointService {

    private CurvePointRepository curvePointRepository;

    public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    /**
     * @return the list of all curvePoint stored in database
     */
    @Override
    public List<CurvePoint> getAllCurvePoint() {
        List<CurvePoint> allCurvePoint = curvePointRepository.findAll();
        return allCurvePoint;
    }

    /**
     * @param curveId identifier of the curvePoint we want to delete
     */
    @Override
    public void deleteCurvePoint(Integer curveId) {
        CurvePoint curvePoint = curvePointRepository.findById(curveId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curve Point id: " + curveId));
        curvePointRepository.delete(curvePoint);
    }

    /**
     * @param curvePoint a curvePoint type object
     * @return curvePoint saved in database with an identifier
     */
    @Override
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
        return savedCurvePoint;
    }

    /**
     * @param id identifier of a curvePoint we want to get
     * @return curvePoint object saved in database if existing
     */
    @Override
    public CurvePoint getCurvePointById(Integer id) {
        CurvePoint curvePointById = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id: "+ id));
        return curvePointById;
    }
}
