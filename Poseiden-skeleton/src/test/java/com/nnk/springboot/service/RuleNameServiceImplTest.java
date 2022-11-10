package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleNameServiceImplTest {
    @InjectMocks
    private RuleNameServiceImpl ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    @Test
    public void getAllRuleNameTest() {
        //GIVEN
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        RuleName ruleName1 = new RuleName("Rule Name1", "Description1", "Json1", "Template1", "SQL1", "SQL Part1");

        List<RuleName> list = new ArrayList<>();
        list.add(ruleName);
        list.add(ruleName1);

        when(ruleNameRepository.findAll()).thenReturn(list);

        //WHEN
        List<RuleName> listResult = ruleNameService.getAllRuleName();

        //THEN
        assertTrue(listResult.size() == 2);
    }

    @Test
    public void saveRuleNameTest() {
        //GIVEN
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        RuleName expected = new RuleName(null, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        when(ruleNameRepository.save(ruleName)).thenReturn(expected);

        //WHEN
        RuleName actual = ruleNameService.saveRuleName(ruleName);

        //THEN
        assertEquals(actual.getName(), expected.getName());
        verify(ruleNameRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void getRuleNameByIdTest() {
        //GIVEN
        Integer id =1;
        RuleName expected = new RuleName(1, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        when(ruleNameRepository.findById(id)).thenReturn(Optional.of(expected));

        //WHEN
        RuleName actual = ruleNameService.getRuleNameById(id);

        //THEN
        assertEquals(expected.getId(), actual.getId());
        verify(ruleNameRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void getRuleNameByIdThrowsExceptionTest() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    public void deleteRuleNameTest(){
        //GIVEN
        Integer id =1;
        RuleName ruleName = new RuleName(1, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        when(ruleNameRepository.findById(id)).thenReturn(Optional.of(ruleName));
        doNothing().when(ruleNameRepository).delete(ruleName);

        //WHEN
        ruleNameService.deleteRuleName(id);

        //THEN
        verify(ruleNameRepository, Mockito.times(1)).findById(id);
        verify(ruleNameRepository, Mockito.times(1)).delete(ruleName);
    }

}
