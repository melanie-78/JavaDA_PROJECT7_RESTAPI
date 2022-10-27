package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

public interface RuleNameService {
    List<RuleName> getAllRuleName();
    RuleName saveRuleName(RuleName ruleName);
    RuleName getRuleNameById(Integer id);
    void deleteRuleName(Integer id);
}
