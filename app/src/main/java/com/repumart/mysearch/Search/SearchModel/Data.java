package com.repumart.mysearch.Search.SearchModel;

public class Data {
    private int vendor_id;

    private String username;

    private String email;

    private String phone;

    private int rank_id;

    private int score;

    private String created_at;

    private String updated_at;

    private String twitter_handle;

    private String facebook_username;

    public void setVendor_id(int vendor_id){
        this.vendor_id = vendor_id;
    }
    public int getVendor_id(){
        return this.vendor_id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setRank_id(int rank_id){
        this.rank_id = rank_id;
    }
    public int getRank_id(){
        return this.rank_id;
    }
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public String getCreated_at(){
        return this.created_at;
    }
    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }
    public String getUpdated_at(){
        return this.updated_at;
    }
    public void setTwitter_handle(String twitter_handle){
        this.twitter_handle = twitter_handle;
    }
    public String getTwitter_handle(){
        return this.twitter_handle;
    }
    public void setFacebook_username(String facebook_username){
        this.facebook_username = facebook_username;
    }
    public String getFacebook_username(){
        return this.facebook_username;
    }
}

