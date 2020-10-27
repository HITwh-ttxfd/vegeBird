package com.example.demo.Connector;

import com.example.demo.Entity.User;
import com.example.demo.Test.JDBCUtils;
import com.example.demo.Test.TestMysql;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
            // 查询数据的SQL语句
            String sql = "INSERT INTO user VALUES (?,?,?,?);";
            jdbcTemplate.update(sql, user.getId(),user.getUsername(),user.getPassword(),user.getAvatar());
        }catch (Exception e)
        {
            return e.toString();
        }
        return "Ok";
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
//  传入参数：用户类：用户账号、用户密码（其他不传）
//  返回值：执行成功返回"Ok" / 执行失败返回错误报告
    public static String loginUser(User user) throws Exception {
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


    public void findByUsername() {
        //定义sql
        try {
//            String sql = "INSERT INTO user VALUES (\"s00000004\", ?, ?,null );";
            String sql="select * from user";
//            template.update(sql, "王小淞", "3333");
//            user = template.queryForList(sql);
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Fuck");
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
//            user.toString();
        }
    }


    public static void main(String[] args) throws Exception {
//        UserConnector p=new UserConnector();
//        test07();
        User user=new User();
//        System.out.println(createAccount(user));
    }
}
