package com.kjq.mapper;

import com.kjq.POJO.Sort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SortMapper {
    List<Sort> getSorts();

    @Select("select * from sort where sort_status = 1 limit #{page}, #{limit}")
    List<Sort> getAdminSorts(Integer page, Integer limit);
}
