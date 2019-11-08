package edu.gyc.mp1;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
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
import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mp1ApplicationTests {
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
       user.setName("柳如是");
       user.setAge(28);
       user.setEmail("zhm@qq.com");
       user.setCreateTime(LocalDateTime.now());
       user.setManagerId(1088248166370832385L);
      // user.setCreateTime(new LocalDateTime("1978-9-9"));

        userMapper.insert(user);
        select();

    }

    @Test
    public void del() {


        HashMap<String, Object> delmap = new HashMap<>();
        delmap.put("age", 50);

        userMapper.deleteByMap(delmap);
        select();

    }
    @Test
    public void del1() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 39).eq("email", "zhm@qq.com");

        userMapper.delete(queryWrapper);
        select();

    }
    @Test
    public void update() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("name","柳如是");

        User user=new User();
        user.setAge(28);
        user.setEmail("lrs2019@qq.com");

        userMapper.update(user, userUpdateWrapper);
        select();

    }

    @Test
    public void update1() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("user_id","1180124479407382530")

                .set("age", 28);

        userMapper.update(null, userUpdateWrapper);
        select();

    }

    @Test
    public void update2() {

        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.<User>lambdaUpdate();
        lambdaUpdateWrapper.eq(User::getId, 1180124479407382530L)
                .set(User::getAge, 18)
                .set(User::getEmail, "zhm18@qq.com");

        userMapper.update(null, lambdaUpdateWrapper);
        select();

    }

    @Test
    public void updateChain() {
        LambdaUpdateChainWrapper<User> lambdaUpdateWrapper = new LambdaUpdateChainWrapper<>(userMapper);
      boolean update=  lambdaUpdateWrapper.eq(User::getId, 1180124479407382530L)
                .set(User::getAge, 18)
                .set(User::getEmail, "zhm18@qq.com")
                .update();
        System.out.println("Update "+update);

        select();

    }
    @Test
    public void select1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }
    @Test
    public void select2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").between("age", 20,40).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeLeft("name", "敏").le("age", 40).orderByDesc("age")
                .orderByAsc("user_id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
                .inSql("manager_id", "select user_id from user where name like '王%'");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20)
                .isNotNull("email"));

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select7() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(qw -> qw.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select8() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30,31,38));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","敏").gt("age", 20)
               .select("user_id","name","age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }


    @Test
    public void select10() {
        String name = "";
        String email = "@";
        mycondition(name,email);

    }
    private void mycondition(String name, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
     /*   if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotEmpty(email)) {
            queryWrapper.like("email", email);
        }

      */

        queryWrapper.like(StringUtils.isNotEmpty(name), "name", name)
                .like(StringUtils.isNotEmpty(email), "email", email);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select11() {
        User whereUser=new User();
        // whereUser.setName("敏");
        whereUser.setAge(39);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(whereUser);
        //  queryWrapper.like("name","敏").gt("age", 20)
        // .select("user_id","name","age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select12() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        HashMap<String, Object> map = new HashMap<>();
        map.put("name","周慧敏");
        map.put("age",38);
       // queryWrapper.allEq(map,false);
        queryWrapper.allEq((k,v)->!k.equals("name"),map);
        //  queryWrapper.like("name","敏").gt("age", 20)
        // .select("user_id","name","age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select13() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("name","敏").gt("age",30)
            .select("user_id","name");


        List<Map<String ,Object>> users = userMapper.selectMaps(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void select14() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age","manager_id")
                .groupBy("manager_id").having("sum(age)<{0}",500);

        List<Map<String ,Object>> users = userMapper.selectMaps(queryWrapper);
        users.forEach(System.out::println);

    }


    @Test
    public void select15() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.gt("age", 30);

        Integer n = userMapper.selectCount(queryWrapper);
        System.out.println("Total count: "+n);

    }

    @Test
    public void selectPage() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 30);
      Page<User>page=   new Page<User>(1,2,false);

        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("Total page num:" + iPage.getPages());
        System.out.println("Total data num:" + iPage.getTotal());
        Integer n = userMapper.selectCount(queryWrapper);

        List<User> list=iPage.getRecords();
        list.forEach(System.out::println);

    }
}
