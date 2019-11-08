package edu.gyc.mp2.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import edu.gyc.mp2.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
@Select("select * from user ${ew.customSqlSegment}")
    List<User> myAllUsers(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

}
