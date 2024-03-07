package com.kjq.service;

import com.kjq.POJO.Sort;
import com.kjq.utils.FFResult;

public interface SortService {
    FFResult getSorts();

    FFResult getAdminSorts(Integer page, Integer limit);

    FFResult getSortList();

    FFResult addAdminSort(Sort sort);

    FFResult updateAdminSort(Sort sort);

    FFResult deleteAdminSort(Integer id);
}
