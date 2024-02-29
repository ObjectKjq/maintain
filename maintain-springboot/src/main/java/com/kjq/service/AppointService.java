package com.kjq.service;

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
}
