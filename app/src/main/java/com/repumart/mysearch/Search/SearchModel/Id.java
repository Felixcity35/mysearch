package com.repumart.mysearch.Search.SearchModel;

import java.util.List;

public class Id
{
    private int reputation;

    private List<Integer> sentiments;

    public void setReputation(int reputation){
        this.reputation = reputation;
    }
    public int getReputation(){
        return this.reputation;
    }
    public void setSentiments(List<Integer> sentiments){
        this.sentiments = sentiments;
    }
    public List<Integer> getSentiments(){
        return this.sentiments;
    }
}
