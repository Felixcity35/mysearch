package com.repumart.mysearch.Search.SearchModel;

import java.util.List;

public class Rank
{
    private boolean success;

    private List<Data> data;

    private Result result;

    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return this.success;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public void setResult(Result result){
        this.result = result;
    }
    public Result getResult(){
        return this.result;
    }
}
