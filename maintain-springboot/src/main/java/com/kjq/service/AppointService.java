package com.kjq.service;

import com.kjq.POJO.Appoint;
import com.kjq.utils.FFResult;

import java.util.Map;

public interface AppointService {
    FFResult getAppoints(Integer page, Integer num, String name, String city, String skill, String time);

    FFResult updateStatus(Map<String, String> newAppoint);

    FFResult getTotal(Integer status);

    FFResult getListAppoint(Integer page, Integer num, Integer status);

    FFResult cancelAppoint(Integer id);

    FFResult tPrice(Integer id);

    FFResult mark(Integer id, Integer rate);

    FFResult getMaintainAppoints(Integer status, Integer page, Integer limit, String title);

    FFResult deleteAppoint(Integer id);

    FFResult updateAppoint(String content, String appointTime, Integer id);

    FFResult addAppoint(String content, String appointTime);

    FFResult addPrice(Integer id, Integer price);

    FFResult subAppoint(Integer id);
}
