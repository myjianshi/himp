package edu.gyc.mp1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import edu.gyc.mp1.dao.UserMapper;
import edu.gyc.mp1.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArTest {
    @Resource
private UserMapper userMapper;

    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }
    @Test
    public void insert() {
       User user=new User();
       user.setName("张颖");
       user.setAge(36);
       user.setEmail("lslover@qq.com");
       user.setCreateTime(LocalDateTime.now());
       user.setManagerId(1088248166370832385L);
       user.setCreateTime(LocalDateTime.now());
        boolean r=user.insert();
        System.out.println("Insert "+user+r);


    }

    @Test
    public void select1() {
        User user=new User();
        user.setName("徐睿");
        User user1=user.selectOne(new QueryWrapper<User>().setEntity(user));
        System.out.println("Select "+user1);

        long id=user1.getId();
        user.setId(id);
        user.setAge(38);
        boolean b = user.updateById();
        System.out.println("Update "+b);

    }

    @Test
    public void insertUpdate() {
        User user=new User();
        user.setName("苏小小");
        user.setAge(36);
        user.setEmail("lslover@qq.com");
        user.setCreateTime(LocalDateTime.now());
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        boolean r=user.insertOrUpdate();
        System.out.println("Insert "+user+r);


    }

    @Test
    public void insertUpdate1() {
        User user=new User();
        user.setName("苏晓晓");
        user.setAge(36);
        user.setEmail("lslover@qq.com");
      user.setId(1180843418437394434L);
        boolean r=user.insertOrUpdate();
        System.out.println("Insert "+user+r);


    }
}
