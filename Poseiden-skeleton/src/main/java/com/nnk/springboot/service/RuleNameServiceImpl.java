package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameServiceImpl implements RuleNameService{
    private RuleNameRepository ruleNameRepository;

    public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    /**
     * @return the list of all ruleName saved in database
     */
    @Override
    public List<RuleName> getAllRuleName() {
        return ruleNameRepository.findAll();
    }

    /**
     * @param ruleName a rating type object
     * @return ruleName saved in database with an identifier
     */
    @Override
    public RuleName saveRuleName(RuleName ruleName) {
        RuleName savedRuleName = ruleNameRepository.save(ruleName);
        return savedRuleName;
    }

    /**
     * @param id identifier of a ruleName we want to get
     * @return a ruleName object saved in database if existing
     */
    @Override
    public RuleName getRuleNameById(Integer id) {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id: "+id));
        return ruleName;
    }

    /**
     * @param id identifier of the ruleName we want to delete
     */
    @Override
    public void deleteRuleName(Integer id) {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id: "+id));
        ruleNameRepository.delete(ruleName);
    }
}
