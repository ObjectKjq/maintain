package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.kjq.POJO.User;
import com.kjq.config.MD5PasswordEncoder;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.UserMapper;
import com.kjq.model.vo.UserVo;
import com.kjq.service.UserService;
import com.kjq.utils.FFResult;
import com.kjq.utils.JwtUtils;
import com.kjq.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Resource
    AuthenticationManager authenticationManager;

    //根据用户名查询用户信息
    @Override
    public User getByUsername(String account) {
        if(account == null){
            return null;
        }
        return userMapper.queryAccountUser(account);
    }

    // 根据用户id用户的权限，在这里只用到了管理员
    @Override
    public Integer getUserAuthorityInfo(Integer userId) {
        return userMapper.selectIdUser(userId).getUserRole();
    }

    @Override
    public FFResult getUserByAccount(String account) {
        User user = userMapper.queryAccountUser(account);
        if(user == null){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        UserVo userVo = new UserVo();
        userVo.setName(user.getUsername());
        userVo.setAvatar(user.getAvatarUrl());
        List<String> list = new ArrayList<>();
        if(user.getUserRole() == 0){
            list.add("user");
        }else if(user.getUserRole() == 1){
            list.add("admin");
        }else {
            list.add("maintain");
        }
        userVo.setRoles(list);
        return FFResult.success(StatusCodeEnum.SUCCESS, userVo);
    }

    @Override
    public FFResult getToken(HttpServletRequest req, String openid) {

        //判断账号是否存在
        User user = userMapper.queryAccountUser(openid);
        if (user == null){
            User addUser = new User();
            addUser.setUsername("张三");
            addUser.setUserPassword(new MD5PasswordEncoder().encode("123"));
            addUser.setUserAccount(openid);
            addUser.setUserRole(0);
            addUser.setUserStatus(1);
            addUser.setCreateTime(DateUtil.formatDate(DateUtil.date()));
            if(!userMapper.addUser(addUser)){
                return FFResult.success(StatusCodeEnum.ERROR);
            }
        }
        //认证
//        UsernamePasswordAuthenticationToken authenticationToken
//                = new UsernamePasswordAuthenticationToken(openid, "123");
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(authenticate);
//        HttpSession session = req.getSession(true);
//        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        //根据账号生成token，并返回
        return FFResult.success(StatusCodeEnum.SUCCESS, openid);
    }

    @Override
    public FFResult upload(MultipartFile file) {
        List<String> listType = new ArrayList<>();
        //限制图片格式
        listType.add("jpg");
        listType.add("png");
        //判断用户原来的图片是不是默认的，如果是默认的不删除原来图片。
        //如果不是默认的则删除图片库中的源文件
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userMapper.queryAccountUser(name);

        //获取jar包所在的目录
        ApplicationHome ah = new ApplicationHome(getClass());
        File jarF = ah.getSource();
        String path = jarF.getParentFile().toString()+"/images/user/";
        //判断目录是否创建
        File filePath = new File(path);
        if (!filePath.exists()){
            filePath.mkdirs();
        }

        //判断文件上传类型
        try {
            //这里是hutool提供的工具类
            String fileType = FileTypeUtil.getType(file.getInputStream());
            boolean b = false;
            for (String s : listType) {
                if(fileType.equals(s)){
                    b = true;
                }
            }
            if (!b){
                return FFResult.error(StatusCodeEnum.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断用户头像是不是默认的，如果不是默认需要删除之前用户上传的头像数据
        if(!user.getAvatarUrl().equals("https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/unicloudlogo.png")){
            //删除图片数据
            String[] split = user.getAvatarUrl().split("/");
            String nameFile = split[split.length-1];
            File removeFile = new File(path + nameFile);
            if (removeFile.exists()){
                //删除文件
                removeFile.delete();
            }
        }
        //获取后缀
        String type = FileUtil.extName(file.getOriginalFilename());
        //获取uuid
        String uuid = UUID.randomUUID().toString();
        //拼写文件路径
        File uploadFile = new File(path + uuid + StrUtil.DOT + type);

        try {
            file.transferTo(uploadFile);
            //修改数据库的内容
            user.setAvatarUrl("http://localhost:8081/images/user/"+uuid+StrUtil.DOT+type);
            if(userMapper.updateAvatarUrl(user)){
                return FFResult.success(StatusCodeEnum.SUCCESS, user.getAvatarUrl());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult updateUser(String name, String sex) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String account = authentication.getName();
        User user = userMapper.queryAccountUser(account);
        user.setUsername(name);
        user.setGender(Integer.valueOf(sex));
        boolean b = userMapper.updateUser(user);
        if(b){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult getAdminUsers(Integer page, Integer limit) {
        page = (page - 1) * limit;
        User user = new User();
        user.setId(userMapper.getTotal());
        List<User> adminUsers = userMapper.getAdminUsers(page, limit);
        adminUsers.add(user);
        return FFResult.success(StatusCodeEnum.SUCCESS, adminUsers);
    }

    @Override
    public FFResult addUpload(MultipartFile file) {
        List<String> listType = new ArrayList<>();
        //限制图片格式
        listType.add("jpg");
        listType.add("png");
        //判断用户原来的图片是不是默认的，如果是默认的不删除原来图片。
        //如果不是默认的则删除图片库中的源文件
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userMapper.queryAccountUser(name);
        //获取jar包所在的目录
        ApplicationHome ah = new ApplicationHome(getClass());
        File jarF = ah.getSource();
        String path = jarF.getParentFile().toString()+"/images/user/";
        //判断目录是否创建
        File filePath = new File(path);
        if (!filePath.exists()){
            filePath.mkdirs();
        }

        //判断文件上传类型
        try {
            //这里是hutool提供的工具类
            String fileType = FileTypeUtil.getType(file.getInputStream());
            boolean b = false;
            for (String s : listType) {
                if(fileType.equals(s)){
                    b = true;
                }
            }
            if (!b){
                return FFResult.error(StatusCodeEnum.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断用户头像是不是默认的，如果不是默认需要删除之前用户上传的头像数据
        if(!user.getAvatarUrl().equals("https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/unicloudlogo.png")){
            //删除图片数据
            String[] split = user.getAvatarUrl().split("/");
            String nameFile = split[split.length-1];
            File removeFile = new File(path + nameFile);
            if (removeFile.exists()){
                //删除文件
                removeFile.delete();
            }
        }
        //获取后缀
        String type = FileUtil.extName(file.getOriginalFilename());
        //获取uuid
        String uuid = UUID.randomUUID().toString();
        //拼写文件路径
        File uploadFile = new File(path + uuid + StrUtil.DOT + type);

        try {
            file.transferTo(uploadFile);
            //修改数据库的内容
            user.setAvatarUrl("http://localhost:8081/images/user/"+uuid+StrUtil.DOT+type);
            if(userMapper.updateAvatarUrl(user)){
                return FFResult.success(StatusCodeEnum.SUCCESS, user.getAvatarUrl());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult updateMaintainAdminUser(String username, String password, String nPassword, String vPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userMapper.queryAccountUser(name);
        if(!nPassword.equals(vPassword)){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        if(!MD5Util.encode(password).equals(user.getUserPassword())){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        if(!userMapper.updateMaintainAdmin(username, MD5Util.encode(nPassword), user.getId())){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        return FFResult.success(StatusCodeEnum.SUCCESS);
    }

    @Override
    public FFResult addUser(User user) {
        user.setUserStatus(1);
        user.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        user.setUserPassword(MD5Util.encode(user.getUserPassword()));
        if(userMapper.addUser(user)){
            return FFResult.success(StatusCodeEnum.SUCCESS, user);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult updateAdminUser(User user) {
        user.setUserPassword(MD5Util.encode(user.getUserPassword()));
        if(userMapper.updateAdminUser(user)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult deleteAdminUser(Integer id) {
        if(userMapper.deleteAdminUser(id)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }
}
