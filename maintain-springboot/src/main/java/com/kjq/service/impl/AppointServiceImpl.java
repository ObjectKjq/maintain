package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import com.kjq.POJO.Appoint;
import com.kjq.POJO.Score;
import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.AppointMapper;
import com.kjq.mapper.ScoreMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.model.vo.AppointListVo;
import com.kjq.service.AppointService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppointServiceImpl implements AppointService {

    @Autowired
    AppointMapper appointMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ScoreMapper scopeMapper;

    @Override
    public FFResult getAppoints(Integer page, Integer num, String name, String city, String skill, String time) {
        if (name.equals("") && city.equals("") && skill.equals("") && time.equals("")){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        page = (page - 1) * num;
        List<AppointListVo> appoints = appointMapper.getAppoints(page, num, name, city, skill, time);
        for (AppointListVo appointListVo : appoints) {
            appointListVo.setScore(appointMapper.getAvgScore(appointListVo.getAppointId()));
        }
        return FFResult.success(StatusCodeEnum.SUCCESS, appoints);
    }

    @Override
    public FFResult updateStatus(Map<String, String> newAppoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        String message = "";
        message = "姓名："
        + newAppoint.get("name")
        + "。电话："
        + newAppoint.get("phone")
        + "。地址："
        + newAppoint.get("address")
        + "。描述："
        + newAppoint.get("message")
        + "。";

        if(!appointMapper.updateAppoint(user.getId(), message, Integer.valueOf(newAppoint.get("id")))){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        return FFResult.success(StatusCodeEnum.SUCCESS);
    }

    @Override
    public FFResult getTotal(Integer status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);

        return FFResult.success(StatusCodeEnum.SUCCESS, appointMapper.getTotal(user.getId(), status));
    }

    @Override
    public FFResult getListAppoint(Integer page, Integer num, Integer status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        page = (page - 1) * num;
        return FFResult.success(StatusCodeEnum.SUCCESS, appointMapper.getListAppoint(user.getId(), page, num, status));
    }

    @Override
    public FFResult cancelAppoint(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        if(appointMapper.cancelAppoint(id, user.getId())){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult tPrice(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        if(appointMapper.tPrice(id, user.getId())){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult mark(Integer id, Integer rate) {
        //判断用户是否重复给一个维修师打分
        Appoint appoint = appointMapper.getAppointById(id);
        if (appoint == null){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);

        //判断用户有没有评分过
        Score tScore = scopeMapper.getScopeByUserIds(appoint.getAppointId(), user.getId());
        if (tScore != null){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        rate *= 2;
        Score score = new Score();
        score.setScoreId(appoint.getAppointId());
        score.setByScoreId(user.getId());
        score.setPoint(rate);
        score.setScoreStatus(1);
        score.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        if(!scopeMapper.addScope(score)){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        //修改状态为5，表示完成
        if(!appointMapper.updateStatus(id, user.getId())){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        return FFResult.success(StatusCodeEnum.SUCCESS);
    }

    @Override
    public FFResult getMaintainAppoints(Integer status, Integer page, Integer limit, String title) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        page = (page - 1) * limit;

        return FFResult.success(StatusCodeEnum.SUCCESS, appointMapper.getMaintainAppoints(page, limit, status, title, user.getId()));
    }
}
