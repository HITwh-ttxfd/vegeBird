package com.example.demo.Connector;
//author:孙宝臻
//date:2020-10-27————2020-10-28  开发完成
//function:兼职相关数据库操作

/**
 * change:
 * changeauthor:
 * changedate:
 */
import com.example.demo.Entity.RecommendTask;
import com.example.demo.Entity.Task;
import com.example.demo.Test.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.text.DecimalFormat;
import java.util.List;

public class TaskConnector {
    //    数据连接
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * @function:创建兼职
     * @param task 兼职类：需要传入所有信息:12个属性
     * @return  执行成功返回"Ok" / 执行失败返回错误报告
     * @throws Exception
     */
    public static String createAccount(Task task) throws Exception {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
//            给id赋值
            task.setId(getMaxTaskId());
            String sql = "INSERT INTO task VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            jdbcTemplate.update(sql,task.getId(),task.getTitle(),
                    task.getTerm(),task.getPay(),task.getBid(),task.getContent(),
                    task.getLink(),task.getOutType(),task.getOutTypeName(),
                    task.getInType(),task.getInTypeName(),task.getSource());
        }catch (Exception e)
        {
            return e.toString();
        }
        return "Ok";
    }

    /**
     * @function 查询兼职最大id
     * @aim 为了产生一个新的id
     * @return string——兼职的最大id+1,直接拿来给下一个元祖id赋值
     * @throws Exception
     */
    public static String getMaxTaskId() throws Exception {
        String result;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT MAX(id) FROM task";
            result = jdbcTemplate.queryForObject(sql, String.class);
        }catch (Exception e)
        {
            System.out.println("兼职查询最大id错误Error\n");
            System.out.println("错误信息为："+e);
            return e.toString();
        }
        if(result!=null)
        {
//            标准化格式，并且+1
            String numStr = result.substring(1);
            DecimalFormat countFormat = new DecimalFormat("00000000");
            result="t" + countFormat.format(Integer.parseInt(numStr)+1);
        }
        return result;
    }


    /**
     * @function 获取所有兼职
     * @param task 只传入大分类小分类  outType, inType
     * @return List<Task>  该分类下所有兼职
     * @throws Exception
     */
    public static List<Task> getAllTask(Task task) throws Exception {
        List<Task> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM  task  WHERE outType=? and inType=?;";
            RowMapper<Task> rowMapper = new BeanPropertyRowMapper<Task>(Task.class);
            result = jdbcTemplate.query(sql, rowMapper,task.getOutType(),task.getInType());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("获取所有兼职出现问题");
            return null;
        }
        if(result.isEmpty())
        {
            return null;
        }
        return result;
    }


    /**
     * @function 改变兼职信息
     * @param task 全传
     * @return String 成功返回"Ok"/失败返回错误信息
     * @throws Exception
     */
    public static String changeTask(Task task) throws Exception {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "UPDATE task SET title=?, term=?, pay=?, bid=?" +
                    ", content=?, link=?, outType=?, outTypeName=?, inType=?, inTypeName=?, source=? WHERE id=?;";
            jdbcTemplate.update(sql,task.getTitle(),
                    task.getTerm(),task.getPay(),task.getBid(),task.getContent(),
                    task.getLink(),task.getOutType(),task.getOutTypeName(),
                    task.getInType(),task.getInTypeName(),task.getSource(),task.getId());
        }catch (Exception e)
        {
            return e.toString();
        }
        return "Ok";
    }


    /**
     * @function 获取推荐的兼职信息
     * @param recommendTask 全传
     * @return List<RecommendTask>推荐兼职列表
     * @throws Exception
     */
    public static List<RecommendTask> getRecommandTask(RecommendTask recommendTask) throws Exception {
        List<RecommendTask> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM  recommend_task  WHERE outType=? and inType=?;";
            RowMapper<RecommendTask> rowMapper = new BeanPropertyRowMapper<RecommendTask>(RecommendTask.class);
            result = jdbcTemplate.query(sql, rowMapper,recommendTask.getOutType(),recommendTask.getInType());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("获取推荐的兼职信息出现问题");
            return null;
        }
        if(result.isEmpty())
        {
            return null;
        }
        return result;
    }


    /**
     * @function 获取大分类
     * @return List<Task> 只含有大分类和大分类的中文
     * @throws Exception
     */
    public static List<Task> getBriefClassification() throws Exception {
        List<Task> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT distinct outType,outTypeName FROM  task";
            RowMapper<Task> rowMapper = new BeanPropertyRowMapper<Task>(Task.class);
            result = jdbcTemplate.query(sql, rowMapper);
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("获取大分类出现问题");
            return null;
        }
        if(result.isEmpty())
        {
            return null;
        }
        return result;
    }


    /**
     * @function 获取小分类
     * @param outType  需要获得小分类的大分类
     * @return List<Task> 只含有小分类和小分类的中文
     * @throws Exception
     */
    public static List<Task> getDetailedClassification(String outType) throws Exception {
        List<Task> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT distinct inType,inTypeName FROM  task where outType=? ";
            RowMapper<Task> rowMapper = new BeanPropertyRowMapper<Task>(Task.class);
            result = jdbcTemplate.query(sql, rowMapper,outType);
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("获取小分类出现问题");
            return null;
        }
        if(result.isEmpty())
        {
            return null;
        }
        return result;
    }


}
