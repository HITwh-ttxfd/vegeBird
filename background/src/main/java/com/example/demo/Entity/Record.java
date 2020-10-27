package com.example.demo.Entity;
//author:孙宝臻
//date:2020-10-26
//function:历史记录和收藏都用这个
public class Record {
//    用户id
    private String userId;
//    兼职id
    private String taskId;

    public Record() {
    }

    public Record(String userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Record{" +
                "userId='" + userId + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
