package nt.tshape.automation.selenium.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FollowingDataModel {
    public String id;
    public String username;
    public Boolean followed_by_user;
}
