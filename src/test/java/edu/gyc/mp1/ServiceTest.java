package edu.gyc.mp1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.gyc.mp1.dao.UserMapper;
import edu.gyc.mp1.model.User;
import edu.gyc.mp1.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Resource
private UserService userService;

    @Test
    public void getOne() {
     User user=   userService.getOne(Wrappers.<User>lambdaQuery().between(User::getAge, 20,35),true);


        System.out.println(user);

    }
    @Test
    public void Batch() {
        User user1 = new User();
        user1.setName("徐睿1");
        user1.setAge(38);

        User user2 = new User();
        user2.setName("汤唯1");
        user2.setId(1123344L);
        user2.setAge(39);
       boolean saveBatch=userService.saveOrUpdateBatch(Arrays.asList(user1,user2));
        System.out.println("SaveBatch "+saveBatch);

    }
    @Test
    public void chain(){
    List<User>users=    userService.lambdaQuery().gt(User::getAge,25).like(User::getName,"睿").list();

        userService.lambdaQuery().gt(User::getAge, 25).list();

    users.forEach(System.out::println);
    }
    @Test
    public void chainUpdate(){
        boolean update=userService.lambdaUpdate().eq(User::getAge,27).set(User::getAge,38).update();
        System.out.println("Chain update "+update);


    }

    @Test
    public void chainRemove() {
        boolean remove=userService.lambdaUpdate().eq(User::getAge,38).remove();
        System.out.println("Chain remove "+remove);
    }
}
