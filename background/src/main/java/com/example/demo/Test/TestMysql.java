package com.example.demo.Test;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class TestMysql {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    List<Map<String, Object>> user;
    public void findByUsername() {
        //定义sql
        try {
            String sql = "INSERT INTO user VALUES (\"s00000004\", ?, ?,null );";

            template.update(sql, "王小淞", "3333");

        }catch (Exception e){
            System.out.println(e);
            System.out.println("Fuck");
        }
    }

    public static void main(String[] args) {
        TestMysql p=new TestMysql();
        p.findByUsername();
    }
}
