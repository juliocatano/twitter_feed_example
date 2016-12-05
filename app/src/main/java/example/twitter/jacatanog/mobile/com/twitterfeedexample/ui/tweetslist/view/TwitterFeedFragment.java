package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.List;

import example.twitter.jacatanog.mobile.com.twitterfeedexample.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link Fragment} that shows the tweets of a certain user
 */
public class TwitterFeedFragment extends Fragment implements TweetListView {


    public TwitterFeedFragment() {
        // Required empty public constructor
    }

    public static TwitterFeedFragment newInstance() {
        return new TwitterFeedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_twitter_feed, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showTweetList(List<Tweet> tweets) {

    }

    @Override
    public void showErrorLoadingTweetList(String errorMessage) {

    }

    @Override
    public void showProgressLoader(boolean show) {

    }
}
