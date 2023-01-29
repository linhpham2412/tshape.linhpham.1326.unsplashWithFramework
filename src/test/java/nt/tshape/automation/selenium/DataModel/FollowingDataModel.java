package nt.tshape.automation.selenium.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FollowingDataModel{
    public String id;
    public String updated_at;
    public String username;
    public String name;
    public String first_name;
    public String last_name;
    public String twitter_username;
    public String portfolio_url;
    public String bio;
    public String location;
    public Links links;
    public Profile_Image profile_images;
    public String instagram_username;
    public int total_collections;
    public int total_likes;
    public int total_photos;
    public Boolean accepted_tos;
    public Boolean for_hire;
    public Social social;
    public Boolean followed_by_user;
}
