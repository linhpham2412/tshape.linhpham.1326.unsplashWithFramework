package nt.tshape.automation.selenium.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {
    public String self;
    public String html;
    public String photos;
    public String likes;
    public String portfolio;
    public String following;
    public String followers;
}
