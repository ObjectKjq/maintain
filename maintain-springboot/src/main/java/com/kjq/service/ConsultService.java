package com.kjq.service;

import com.kjq.POJO.Consult;
import com.kjq.utils.FFResult;

public interface ConsultService {
    FFResult getConsults(Integer page, Integer limit);

    FFResult getAdminConsults(Integer page, Integer limit);

    FFResult addConsult(String content, Integer appointId, Integer id);

    FFResult getListConsult();

    FFResult deleteConsult(Integer id);

    FFResult reply(Consult consult);
}
