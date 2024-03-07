package com.kjq.service;


import com.kjq.POJO.Scope;
import com.kjq.utils.FFResult;

public interface ScopeService {
    FFResult getScopes(Integer page, Integer limit);

    FFResult addScope(Scope scope);

    FFResult updateScope(Scope scope);

    FFResult deleteScope(Integer id);
}
