package com.kjq.service;

import com.kjq.POJO.Skill;
import com.kjq.utils.FFResult;

public interface SkillService {
    FFResult getSkills(Integer page, Integer limit);

    FFResult addSkill(String content);

    FFResult updateSkill(Skill skill);

    FFResult deleteSkill(Integer id);
}
