package com.example.demo.Controller;

import com.example.demo.Connector.TaskConnector;
import com.example.demo.Entity.Task;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
//author:孙宝臻
//date:2020-10-27
//function:兼职信息发布api

@RestController//            @RestController的作用等同于@Controller + @ResponseBody
@CrossOrigin //              解决跨域问题
public class TaskController {
//    产生一条兼职信息
//    需要传入task所有东西，除了id
    @CrossOrigin
    @PostMapping("/createTask")
    public String createTask(@RequestBody JSONObject json) throws Exception {
        JSONObject jsonobject=  json.getJSONObject("task");
        Task task= (Task) JSONObject.toBean(jsonobject,Task.class);
        try{
            TaskConnector.createAccount(task);
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return "nihao";
    }
//    测试函数
    @CrossOrigin
    @PostMapping("/TaskTest")
    public String Test() throws Exception {
        return "正常运转";
    }
}
