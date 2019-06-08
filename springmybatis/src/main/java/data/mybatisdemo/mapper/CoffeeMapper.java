package data.mybatisdemo.mapper;

import data.mybatisdemo.model.Coffee;
import org.apache.ibatis.annotations.*;

@Mapper//mybatis
public interface CoffeeMapper {


    //sql
    @Insert("insert into t_coffee( name, price, create_time,update_time) " +
            "values (#{name}, #{price},now(),now())")
    //primary key
    @Options(useGeneratedKeys = true)
    int save(Coffee coffee);// return the count that has been infulenced

    //sql
    @Select("select * from t_coffee where id=#{id}")
    //result
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime")
    })
    Coffee findById(@Param("id") Long id);
}
