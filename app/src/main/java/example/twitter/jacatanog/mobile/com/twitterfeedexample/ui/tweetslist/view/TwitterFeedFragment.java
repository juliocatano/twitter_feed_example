package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import example.twitter.jacatanog.mobile.com.twitterfeedexample.R;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.presenter.TweetListPresenter;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.presenter.TweetListPresenterImpl;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.view.adapter.TweetListAdapter;

/**
 * {@link Fragment} that shows the tweets of a certain user
 */
public class TwitterFeedFragment extends Fragment implements TweetListView {

    //Views
    private RecyclerView tweetsFeedRecycler;
    private TextView errorMessageTextView;

    private TweetListPresenter presenter;

    public TwitterFeedFragment() {
        // Required empty public constructor
    }

    public static TwitterFeedFragment newInstance() {
        return new TwitterFeedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twitter_feed, container, false);

        tweetsFeedRecycler = (RecyclerView) view.findViewById(R.id.rv_tweet_list);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TweetListPresenterImpl(this);
        presenter.getTweetList();
    }

    @Override
    public void showTweetList(List<Tweet> tweets) {
        TweetListAdapter adapter = new TweetListAdapter(tweets);
        tweetsFeedRecycler.setAdapter(adapter);
        tweetsFeedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void showErrorLoadingTweetList(String errorMessage) {
        errorMessageTextView.setText(errorMessage);
    }

    @Override
    public void showProgressLoader(boolean show) {

    }
}
