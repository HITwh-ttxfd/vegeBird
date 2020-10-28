package com.example.demo.Connector;

import com.example.demo.Entity.Record;
import com.example.demo.Entity.Task;
import com.example.demo.Entity.User;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Test.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
//author:   孙宝臻
//date:     2020-10-27————2020-10-28
//function: 用户相关数据库操作
//mark:     获取用户历史记录存在问题，待修正
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
//  传入参数：用户信息类：只传id
//  返回值：执行成功返回——用户信息类——该id对应的用户的详细信息 / 执行失败返回空，并报错
    public static UserInfo getUserDetailedInformation(UserInfo user) throws Exception {
        List<UserInfo> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM user_info  WHERE id=?;";
            RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<UserInfo>(UserInfo.class);
            result = jdbcTemplate.query(sql, rowMapper,user.getId());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("获取用户详细信息出现问题");
            return null;
        }
        if(result.isEmpty())
        {
            return null;
        }
        return result.get(0);
    }

//  function:改变用户详细信息(包含一开始没有用户信息的创建和有了信息的更改)
//  传入参数：用户信息类：id是必须的，别的都要传
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

//  function:获取用户简要信息（用户名、密码、头像）
//  传入参数：用户类：id是必须的，别的不传
//  返回值：执行成功返回用户类 / 执行失败返回空，并报错
    public static User getUserBrieflyInformation(User user) throws Exception {
    List<User> result=null;
    try{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM user  WHERE id=?;";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        result = jdbcTemplate.query(sql, rowMapper,user.getId());
    }catch (Exception e)
    {
        System.out.println(e);
        System.out.println("获取用户简要信息出现问题");
    }
    if(result.isEmpty())
    {
        return null;
    }
    return result.get(0);
    }


//  function:生成用户历史记录
//  传入参数：记录类：两个都要传
//  返回值：执行成功返回"Ok" / 执行失败返回错误信息
    public static String createUserHistory(Record record) throws Exception {
        if(selectUserHistory(record))
        {
            return "Ok";
        }
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "INSERT INTO history VALUES (?,?);";
            jdbcTemplate.update(sql,record.getUserId(),record.getTaskId());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("生成用户历史记录出现问题");
        }
        return "Ok";
    }

//  function:用户历史记录查重
//  传入参数：记录类：两个都要传
//  返回值：重复返回true，不重复返回false
    public static boolean selectUserHistory(Record record) throws Exception {
        List<Record> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM history  WHERE user_id=? and task_id=?;";
            RowMapper<Record> rowMapper = new BeanPropertyRowMapper<Record>(Record.class);
            result = jdbcTemplate.query(sql, rowMapper,record.getUserId(),record.getTaskId());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("用户历史记录查重出现问题");
        }
        if(result.isEmpty())
        {
            return false;
        }
        return true;
    }

//  function:获取用户历史记录
//  传入参数：用户类：只传用户id
//  返回值：兼职列表 / 执行失败返回空并报错
    public static List<Task> getUserHistory(User user) throws Exception {
        List<Task> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM  task  WHERE id in (select distinct task_id from history where user_id=?);";
            RowMapper<Task> rowMapper = new BeanPropertyRowMapper<Task>(Task.class);
            result = jdbcTemplate.query(sql, rowMapper,user.getId());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("获取用户历史记录出现问题");
            return null;
        }
        if(result.isEmpty())
        {
            return null;
        }
        return result;
    }

//  function:生成用户收藏
//  传入参数：记录类：两个都要传
//  返回值：执行成功返回"Ok" / 执行失败返回错误信息
    public static String createUserCollection(Record record) throws Exception {
    if(selectUserCollection(record))
    {
        return "Ok";
    }
    try{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "INSERT INTO collection VALUES (?,?);";
        jdbcTemplate.update(sql,record.getUserId(),record.getTaskId());
    }catch (Exception e)
    {
        System.out.println(e);
        System.out.println("生成用户收藏记录出现问题");
    }
    return "Ok";
}

//  function:用户收藏记录查重
//  传入参数：记录类：两个都要传
//  返回值：重复返回true，不重复返回false
    public static boolean selectUserCollection(Record record) throws Exception {
        List<Record> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM collection  WHERE user_id=? and task_id=?;";
            RowMapper<Record> rowMapper = new BeanPropertyRowMapper<Record>(Record.class);
            result = jdbcTemplate.query(sql, rowMapper,record.getUserId(),record.getTaskId());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("用户收藏记录查重出现问题");
        }
        if(result.isEmpty())
        {
            return false;
        }
        return true;
    }

    /**
     * function:获取用户收藏记录
     * @param user 用户类：只传用户id
     * @return 兼职列表 / 执行失败返回空并报错
     * @throws Exception
     */
    public static List<Task> getUserCollection(User user) throws Exception {
        List<Task> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM  task  WHERE id in (select distinct task_id from collection where user_id=?);";
            RowMapper<Task> rowMapper = new BeanPropertyRowMapper<Task>(Task.class);
            result = jdbcTemplate.query(sql, rowMapper,user.getId());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("获取用户收藏记录出现问题");
            return null;
        }
        if(result.isEmpty())
        {
            return null;
        }
        return result;
    }

    /**
     *   function:删除用户收藏记录
     *   传入参数：记录类：两个都要传
     *   返回值：执行成功返回"Ok" / 执行失败返回错误信息
     */
    public static String deleteUserCollection(Record record) throws Exception {
//        如果数据库没这条信息，那么返回成功
        if(!selectUserHistory(record))
        {
            return "Ok";
        }
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "DELETE FROM collection WHERE user_id=? and task_id=?;";
            jdbcTemplate.update(sql,record.getUserId(),record.getTaskId());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("删除用户历史记录出现问题");
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
        User user=new User();
        user.setId("s00000008");
//        user.setUsername("王一淞");
//        user.setPassword("1");
//        System.out.println(loginUser(user));
//        user.setUsername("王琪淞");
//        System.out.println(loginUser(user));
//        user.setPassword("123456");
////        System.out.println(loginUser(user));
//        UserInfo userInfo=new UserInfo();
//        userInfo.setId("s00000002");
//        Record record=new Record("s00000001","t00000001");
        System.out.println(getUserHistory(user));
//        System.out.println(createAccount(user));
//        System.out.println(getMaxUserId());
    }
}
