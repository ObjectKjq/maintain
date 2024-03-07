package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import com.kjq.POJO.Skill;
import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.SkillMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.service.SkillService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillMapper skillMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public FFResult getSkills(Integer page, Integer limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        Skill skill = new Skill();
        skill.setId(skillMapper.getTotal(user.getId()));
        page = (page - 1) * limit;
        List<Skill> skills = skillMapper.getSkills(page, limit, user.getId());
        skills.add(skill);
        return FFResult.success(StatusCodeEnum.SUCCESS, skills);
    }

    @Override
    public FFResult addSkill(String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        Skill skill = new Skill();
        skill.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        skill.setSkillStatus(1);
        skill.setUserId(user.getId());
        skill.setContent(content);
        if(skillMapper.addSkill(skill)){
            return FFResult.success(StatusCodeEnum.SUCCESS, skill);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult updateSkill(Skill skill) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        skill.setUserId(user.getId());
        if(skillMapper.updateSkill(skill)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult deleteSkill(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        if(skillMapper.deleteSkill(id, user.getId())){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }
}
