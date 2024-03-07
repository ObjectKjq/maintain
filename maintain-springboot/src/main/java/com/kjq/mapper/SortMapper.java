package com.kjq.mapper;

import com.kjq.POJO.Sort;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SortMapper {
    List<Sort> getSorts();

    @Select("select * from sort where sort_status = 1 limit #{page}, #{limit}")
    List<Sort> getAdminSorts(Integer page, Integer limit);

    @Select("select * from sort where sort_status = 1")
    List<Sort> getSortList();

    @Select("select id from sort where content = #{sort} and sort_status = 1")
    Integer getNameId(String sort);

    @Select("select count(*) from sort where sort_status = 1")
    Integer getTotal();

    @Insert("insert into sort(create_time, sort_status, content) value (#{createTime}, #{sortStatus}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean addAdminSort(Sort sort);

    @Update("update sort set content = #{content} where id = #{id}")
    boolean updateAdminSort(Sort sort);

    @Update("update sort set sort_status = 0 where id = #{id}")
    boolean deleteAdminSort(Integer id);
}
