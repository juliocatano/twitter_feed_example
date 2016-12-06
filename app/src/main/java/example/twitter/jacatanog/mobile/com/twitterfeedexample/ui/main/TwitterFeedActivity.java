package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.R;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.view.TwitterFeedFragment;
import io.fabric.sdk.android.Fabric;

public class TwitterFeedActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "1R2mg1HZwu1xVE5EP1yZniVBX";
    private static final String TWITTER_SECRET = "g1v99RxM8KKAnJ3IJZslJwoRT9sdnTziYjelwEMn4RMt5Fgryy";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_twitter_feed);
        setSupportActionBar((Toolbar) findViewById(R.id.feed_toolbar));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.fragment_container, new TwitterFeedFragment())
                                       .commit();
        }
    }
}
