package nt.tshape.automation.selenium.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoDataModel {
    public String id;
    public String created_at;
    public String updated_at;
    public String promoted_at;
    public String width;
    public String height;
    public String color;
    public Links links;
    public UsersDataModel user;
}
