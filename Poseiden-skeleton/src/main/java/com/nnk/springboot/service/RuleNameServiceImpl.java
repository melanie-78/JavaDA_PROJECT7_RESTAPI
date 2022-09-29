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

    @Override
    public List<RuleName> getAllRuleName() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName saveRuleName(RuleName ruleName) {
        RuleName savedRuleName = ruleNameRepository.save(ruleName);
        return savedRuleName;
    }

    @Override
    public RuleName getRuleNameById(Integer id) {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id: "+id));
        return ruleName;
    }

    @Override
    public void deleteRuleName(Integer id) {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id: "+id));
        ruleNameRepository.delete(ruleName);
    }
}
