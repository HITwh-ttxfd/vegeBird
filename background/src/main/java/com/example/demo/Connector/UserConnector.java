package com.example.demo.Connector;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Test.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
//author:孙宝臻
//date:2020-10-27
//function:用户相关数据库操作

public class UserConnector {
//    数据连接
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

//  function:创建账号
//  传入参数：用户类：用户id，用户名，用户密码，（不需要头像）
//  返回值：执行成功返回"Ok" / 执行失败返回错误报告
    public static String createAccount(User user) throws Exception {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            user.setId(getMaxUserId());
            // 查询数据的SQL语句
            String sql = "INSERT INTO user VALUES (?,?,?,?);";
            jdbcTemplate.update(sql, user.getId(),user.getUsername(),user.getPassword(),user.getAvatar());
        }catch (Exception e)
        {
            return e.toString();
        }
        return "Ok";
    }

//  function:查询用户最大id
//  返回值：string——用户的最大id+1,直接拿来给下一个元组id赋值
//    aim:为了产生一个新的id
    public static String getMaxUserId() throws Exception {
        String result;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT MAX(id) FROM user ";
            result = jdbcTemplate.queryForObject(sql, String.class);
        }catch (Exception e)
        {
            System.out.println("用户查询最大id错误Error\n");
            System.out.println("错误信息为："+e);
            return e.toString();
        }
        if(result!=null)
        {
//            标准化格式，并且+1
            String numStr = result.substring(1);
            DecimalFormat countFormat = new DecimalFormat("00000000");
            result="s" + countFormat.format(Integer.parseInt(numStr)+1);
        }
        return result;
    }

//  function:修改账号简要信息
//  传入参数：用户类：用户id（其他不是必须，改啥传啥）
//  返回值：执行成功返回"Ok" / 执行失败返回错误报告
    public static String changeAccount(User user) throws Exception {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "UPDATE user SET username=?, password=?, avatar=? WHERE id=?;";
            jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getAvatar(),user.getId());
        }catch (Exception e)
        {
            return e.toString();
        }
        return "Ok";
    }

//  function:登录校验
//  传入参数：用户类：用户名、用户密码（其他不传）
//  返回值：执行成功返回id / 执行失败返回错误报告
    public static String loginUser(User user) throws Exception {
        List<User> result;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM user WHERE username=?;";
//            设置返回值类型
            RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
             result = jdbcTemplate.query(sql, rowMapper,user.getUsername());//最后一个参数为username值
        }catch (Exception e)
        {
            return e.toString();
        }
        for (User user1 : result) {
//            密码正确，返回id
            if(user1.getPassword().equals(user.getPassword()))
            {
                return user1.getId();
            }
//            有此帐号，但密码错误，返回对应信息"密码错误"，状态1
            else {
                return "1";
            }
        }
//            没有此帐号，返回对应信息"查无此账号"，状态0
        return "0";
    }

//  function:获取用户详细信息
//  传入参数：用户类：只传id
//  返回值：执行成功返回"Ok" / 执行失败返回错误报告
    public static List<UserInfo> getUserDetailedInformation(UserInfo user) throws Exception {
        List<UserInfo> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM user_info  WHERE id=?;";
            //            设置返回值类型
            RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<UserInfo>(UserInfo.class);
            result = jdbcTemplate.query(sql, rowMapper,user.getId());//最后一个参数为username值
        }catch (Exception e)
        {
            System.out.println("获取用户详细信息出现问题");
        }
        return result;
    }

//  function:改变用户详细信息(包含一开始没有用户信息的创建和有了信息的更改)
//  传入参数：用户类：id是必须的，别的随意
//  返回值：执行成功返回"Ok" / 执行失败返回错误报告
    public static String changeUserInformation(UserInfo user) throws Exception {
        try{
            String sql;
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            if(getUserDetailedInformation(user)==null)
            {
                sql = "INSERT INTO user_info VALUES (?,?,?,?,?,?,?);";
                jdbcTemplate.update(sql, user.getId(),user.getAge(),user.getBirth(),
                user.getSex(),user.getIntroduction(),user.getEducation(),user.getExperience());
            }
            else
            {
                sql = "UPDATE user_info SET age=?, birth=?, sex=?, introduction=?, education=?, experience=? WHERE id=?;";
                jdbcTemplate.update(sql,user.getAge(),user.getBirth(),user.getSex(),
                        user.getIntroduction(),user.getEducation(),user.getExperience(),user.getId());
            }
        }catch (Exception e)
        {
            System.out.println("改变用户详细信息出现问题");
            return e.toString();
        }
        return "Ok";
    }

    public void findByUsername() {
        //定义sql
        try {
//            String sql = "INSERT INTO user VALUES (\"s00000004\", ?, ?,null );";
            String sql="select * from user";
//            template.update(sql, "王小淞", "3333");
//            user = template.queryForList(sql);
        }catch (Exception e){
            System.out.println(e);
        }
//        user.
    }

    // query使用BeanPropertyRowMapper做映射返回对象
    public static void test07() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

        // 查询数据的SQL语句
        String sql = "SELECT * FROM user;";
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

        for (User user : list) {
            System.out.println(user);
            user.toString();
//            user.toString();
        }
    }


    public static void main(String[] args) throws Exception {
//        UserConnector p=new UserConnector();
//        test07();
//        User user=new User();
//        user.setUsername("王一淞");
//        user.setPassword("1");
//        System.out.println(loginUser(user));
//        user.setUsername("王琪淞");
//        System.out.println(loginUser(user));
//        user.setPassword("123456");
//        System.out.println(loginUser(user));
        UserInfo userInfo=new UserInfo();
        userInfo.setId("s00000001");
        System.out.println(getUserDetailedInformation(userInfo));
//        System.out.println(createAccount(user));
//        System.out.println(getMaxUserId());
    }
}
