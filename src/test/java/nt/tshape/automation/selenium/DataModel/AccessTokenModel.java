package nt.tshape.automation.selenium.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenModel {
    public String access_token;
    public String token_type;
    public String refresh_token;
    public String scope;
    public String created_at;
}
