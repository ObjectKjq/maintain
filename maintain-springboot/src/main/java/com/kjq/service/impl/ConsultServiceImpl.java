package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import com.kjq.POJO.Consult;
import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ConsultMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.model.vo.ConsultVo;
import com.kjq.service.ConsultService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultServiceImpl implements ConsultService {

    @Autowired
    ConsultMapper consultMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public FFResult getConsults(Integer page, Integer limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        page = (page - 1) * limit;
        //获取总数
        ConsultVo consultVo = new ConsultVo();
        consultVo.setId(consultMapper.getTotal(user.getId()));
        List<ConsultVo> consults = consultMapper.getConsults(page, limit, user.getId());
        consults.add(consultVo);
        return FFResult.success(StatusCodeEnum.SUCCESS, consults);
    }

    @Override
    public FFResult getAdminConsults(Integer page, Integer limit) {
        page = (page - 1) * limit;
        List<ConsultVo> adminConsults = consultMapper.getAdminConsults(page, limit);
        ConsultVo consultVo = new ConsultVo();
        consultVo.setId(consultMapper.getAdminTotal());
        adminConsults.add(consultVo);
        return FFResult.success(StatusCodeEnum.SUCCESS, adminConsults);
    }

    @Override
    public FFResult addConsult(String content, Integer appointId, Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        Consult consult = new Consult();
        consult.setUserSendId(user.getId());
        consult.setUserAcceptId(appointId);
        consult.setContent(content);
        consult.setConsultStatus(1);
        consult.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        consult.setStatus(0);
        //把id的消息标记为已读
        if(id != null){
            if(!consultMapper.updateStatus(id)){
                return FFResult.error(StatusCodeEnum.ERROR);
            }
        }
        if(consultMapper.addConsult(consult)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult getListConsult() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        return FFResult.success(StatusCodeEnum.SUCCESS, consultMapper.getListConsult(user.getId()));
    }

    @Override
    public FFResult deleteConsult(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        if(consultMapper.deleteConsult(user.getId(), id)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult reply(Consult consult) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        //修改消息状态
        if(!consultMapper.updateIdStatus(consult.getId(), user.getId())){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        //添加消息
        consult.setUserSendId(user.getId());
        consult.setConsultStatus(1);
        consult.setStatus(0);
        consult.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        if(!consultMapper.addConsult(consult)){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        return FFResult.success(StatusCodeEnum.SUCCESS);
    }

    @Override
    public FFResult deleteAdminConsult(Integer id) {
        if(consultMapper.deleteAdminConsult(id)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }
}
