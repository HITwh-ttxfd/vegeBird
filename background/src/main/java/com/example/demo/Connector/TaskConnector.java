package com.example.demo.Connector;
//author:孙宝臻
//date:2020-10-27
//function:兼职相关数据库操作

import com.example.demo.Entity.Task;
import com.example.demo.Test.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.DecimalFormat;

public class TaskConnector {
    //    数据连接
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

//  function:创建兼职
//  传入参数：兼职类：需要传入所有信息:12个属性
//  返回值：执行成功返回"Ok" / 执行失败返回错误报告
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

//  function:查询兼职最大id
//  返回值：string——兼职的最大id+1,直接拿来给下一个元祖id赋值
//    aim:为了产生一个新的id
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

    public static void main(String[] args) throws Exception {
        Task task=new Task();
        task.setLink("1");
        task.setInType("mmp");
        task.setOutType("fuck");
        System.out.println(createAccount(task));
    }
}
