package com.example.demo.Connector;
//author:孙宝臻
//date:2020-10-27————2020-10-28  开发完成
//function:兼职相关数据库操作

/**
 * change:
 * changeauthor:
 * changedate:
 */
import com.example.demo.Entity.Course;
import com.example.demo.Test.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;

public class CourseConnector {
    //    数据连接
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * @function 获取相关教程
     * @param course 传入课程的大分类和小分类
     * @return List<Course>
     * @throws Exception
     */
    public static List<Course> getCourse(Course course) throws Exception {
        List<Course> result=null;
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
            String sql = "SELECT * FROM  course  WHERE outType=? and inType=?;";
            RowMapper<Course> rowMapper = new BeanPropertyRowMapper<Course>(Course.class);
            result = jdbcTemplate.query(sql, rowMapper,course.getOutType(),course.getInType());
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("获取相关教程出现问题");
            return null;
        }
        if(result.isEmpty())
        {
            return null;
        }
        return result;
    }


}
