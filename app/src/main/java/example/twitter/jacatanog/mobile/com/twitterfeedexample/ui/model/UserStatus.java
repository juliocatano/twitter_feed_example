package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juliocatano on 12/4/16.
 */

public class UserStatus {
    @Expose
    @SerializedName("created_at")
    private String createdAt;
    @Expose
    private String text;
    @Expose
    private Entities entities;

    public UserStatus (String createdAt, String text, Entities entities) {
        this.createdAt = createdAt;
        this.text = text;
        this.entities = entities;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }
}
