package com.example.demo.Controller;

import com.example.demo.Connector.CourseConnector;
import com.example.demo.Connector.TaskConnector;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.RecommendTask;
import com.example.demo.Entity.Result;
import com.example.demo.Entity.Task;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
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

    //    获取所有兼职
    //    需要大小分类英文
    @CrossOrigin
    @PostMapping("/getAllTask")
    public Result getAllTask(@RequestBody JSONObject json) throws Exception {
        JSONObject jsonobject=  json.getJSONObject("task");
        Task task = (Task) JSONObject.toBean(jsonobject,Task.class);
//        初始化结果
        Result result=new Result();
        try{
            List<Task> tasks=TaskConnector.getAllTask(task);
            result.setData(tasks);
        }catch (Exception e)
        {
            result.setSucccess(false);
            result.setMessage("查询兼职失败");
            result.setData(null);
            return result;
        }
        result.setSucccess(true);
        result.setMessage("查询兼职成功");

        return result;
    }

    //    获取推荐的兼职信息
    //    需要大小分类英文
    @CrossOrigin
    @PostMapping("/getRecommandTask")
    public Result getRecommandTask(@RequestBody JSONObject json) throws Exception {
        JSONObject jsonobject=  json.getJSONObject("recommandtask");
        RecommendTask recommendTask = (RecommendTask) JSONObject.toBean(jsonobject,RecommendTask.class);
//        初始化结果
        Result result=new Result();
        try{
            List<RecommendTask> recommendTasks=TaskConnector.getRecommandTask(recommendTask);
            result.setData(recommendTasks);
        }catch (Exception e)
        {
            result.setSucccess(false);
            result.setMessage("查询推荐的兼职失败");
            result.setData(null);
            return result;
        }
        result.setSucccess(true);
        result.setMessage("查询推荐的兼职成功");
        return result;
    }

//    测试函数
    @CrossOrigin
    @GetMapping("/TaskTest")
    public String Test() throws Exception {
        return "正常运转";
    }
}
