package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by juliocatano on 12/4/16.
 */
public class Entities {

    @Expose
    List<String> urls;

    public Entities(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
