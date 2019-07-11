package com.pinyougou.test;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.UserMapper;
import com.pinyougou.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author Steven
 * @version 1.0
 * @description com.pinyougou.test
 * @date 2019-7-11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-*.xml")
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    //查询所有用户
    @Test
    public void testFindAll(){
        List<User> userList = userMapper.select(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("关羽");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("深圳黑马");

        //不忽略空值的保存
        //userMapper.insert(user);

        //忽略空值的保存
        userMapper.insertSelective(user);

        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(39);
        user.setUsername("关二哥");
        //不忽略空值-更新
        //userMapper.updateByPrimaryKey(user);

        //忽略空值-更新
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Test
    public void testUpdateByExample(){
        //修改的结果
        User record = new User();
        record.setBirthday(new Date());

        //修改的范围
        Example example = new Example(User.class);
        //创建高级查询条件对象
        Example.Criteria criteria = example.createCriteria();
        //组装查询条件
        criteria.andEqualTo("sex", "1");
        //updateExample（修改的结果，修改的范围）
        userMapper.updateByExampleSelective(record,example);
    }

    //根据id查询数据
    @Test
    public void testGetById(){
        User user = userMapper.selectByPrimaryKey(33);
        System.out.println(user);
    }

    //查询一个对象
    @Test
    public void testFindOne(){
        User where = new User();
        //where.setUsername("赵子龙");
        where.setSex("1");
        User user = userMapper.selectOne(where);
        System.out.println(user);
    }

    //pojo查询-where套路法
    @Test
    public void testGetByPojo(){
        User where = new User();
        where.setSex("0");
        List<User> userList = userMapper.select(where);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    //example查询-example套路法
    @Test
    public void testGetByExample(){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //组装查询条件
        criteria.andLike("username", "%张%");
        List<User> userList = userMapper.selectByExample(example);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    //查询记录数
    @Test
    public void testGetCount(){
        User where = new User();
        where.setSex("0");
        int count = userMapper.selectCount(where);
        System.out.println("女生总共用：" + count);
    }

    //删除
    @Test
    public void testDelete(){
        userMapper.deleteByPrimaryKey(31);
    }

    //分页查询
    @Test
    public void testGetPage(){
        //设置分页条件
        PageHelper.startPage(1, 5);
        //查询数据
        List<User> userList = userMapper.select(null);

        PageInfo<User> info = new PageInfo<>(userList);
        System.out.println("总记录数：" + info.getTotal());
        System.out.println("总页数：" + info.getPages());
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
