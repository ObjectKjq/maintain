package com.kjq.mapper;

import com.kjq.POJO.Sort;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SortMapper {
    List<Sort> getSorts();
}
