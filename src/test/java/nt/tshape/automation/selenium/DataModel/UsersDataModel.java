package nt.tshape.automation.selenium.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersDataModel {
    public String name;
    public String job;
    public String id;
    public String createdAt;
}
