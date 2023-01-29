package nt.tshape.automation.selenium.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Social {
    public String instagram_username;
    public String portfolio_url;
    public String twitter_username;
    public String paypal_email;
}
