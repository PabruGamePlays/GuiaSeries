package com.example.guiaseries.model;

public class ToDoModel extends TaskId{

    private String task, ep, ver;
    private int status;

    public String getTask(){
        return task;
    }

    public String getEp(){
        return ep;
    }
    public String getVer(){return ver;}

    public int getStatus(){
        return status;
    }
}
