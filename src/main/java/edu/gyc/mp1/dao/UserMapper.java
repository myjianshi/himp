package edu.gyc.mp1.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gyc.mp1.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {



    IPage<User> selectUserPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
