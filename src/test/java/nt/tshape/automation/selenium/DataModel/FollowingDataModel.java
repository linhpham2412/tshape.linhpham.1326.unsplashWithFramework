package nt.tshape.automation.selenium.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FollowingDataModel {
    public List<FollowingDataModel> followingDataModelList;
}
