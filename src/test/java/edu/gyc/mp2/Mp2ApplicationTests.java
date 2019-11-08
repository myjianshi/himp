package edu.gyc.mp2;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.gyc.mp2.dao.UserMapper;
import edu.gyc.mp2.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mp2ApplicationTests {
@Resource
private UserMapper userMapper;

@Test
public void save(){
    User user=new User();
    user.setName("梦露");
    user.setAge(38);

    userMapper.insert(user);

    Long id= userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getName,"梦露")).getId();
    System.out.println("Id: "+id);
}
    @Test
    public void logicDelById() {
      int rows=  userMapper.deleteById(1181068762742349826L);
        System.out.println("Del "+rows);
    }
    @Test
    public void all() {
        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery());
        System.out.println("users "+users);
    }

    @Test
    public void update(){
    User user=new User();
    user.setId(1181079290864848897L);
    user.setAge(42);
        System.out.println(userMapper.updateById(user));
    }

    @Test
    public void all1() {
        List<User> users = userMapper.myAllUsers(Wrappers.<User>lambdaQuery().gt(User::getAge,25)
                    .eq(User::getDeleted,0));
        System.out.println("users "+users);
    }
}
