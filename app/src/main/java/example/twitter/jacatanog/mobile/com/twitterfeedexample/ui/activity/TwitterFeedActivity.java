package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.twitter.jacatanog.mobile.com.twitterfeedexample.R;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.fragment.TwitterFeedFragment;

public class TwitterFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_feed);

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.fragment_container, new TwitterFeedFragment())
                                   .commit();

    }
}
