package com.repumart.mysearch.Search.SearchModel;

public class Result
{
    private boolean success;

    private Reputations reputations;

    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return this.success;
    }
    public void setReputations(Reputations reputations){
        this.reputations = reputations;
    }
    public Reputations getReputations(){
        return this.reputations;
    }
}
