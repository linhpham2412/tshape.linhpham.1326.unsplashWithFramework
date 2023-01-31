package nt.tshape.automation.selenium.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionDataModel {
    public String id;
    public String title;
    public String description;
    public String published_at;
    public String last_collected_at;
    public String updated_at;
    public String total_photos;
    public boolean isPrivate;
    public String share_key;
    public PhotoDataModel cover_photo;
    public UsersDataModel user;
    public Links links;
}
