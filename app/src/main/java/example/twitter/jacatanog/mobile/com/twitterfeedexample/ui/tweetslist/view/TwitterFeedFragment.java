package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    private TweetListPresenter presenter;
    private TweetListAdapter tweetsAdapter;
    private LinearLayoutManager tweetLinearLayoutManager;

    public TwitterFeedFragment() {
        // Required empty public constructor
    }

    public static TwitterFeedFragment newInstance() {
        return new TwitterFeedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_twitter_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null){
            tweetsFeedRecycler = (RecyclerView) view.findViewById(R.id.rv_tweet_list);
            tweetsFeedRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                        if (tweetLinearLayoutManager.findLastVisibleItemPosition() >=
                                tweetsAdapter.getItemCount() - 2 /*start loading more tweets when two items away of the end*/) {
                            presenter.getTweetList();
                        }
                    }
                }
            });

            presenter = new TweetListPresenterImpl(this);
            tweetsAdapter = new TweetListAdapter();
            tweetLinearLayoutManager = new LinearLayoutManager(getContext());

            tweetsFeedRecycler.setAdapter(tweetsAdapter);
            tweetsFeedRecycler.setLayoutManager(tweetLinearLayoutManager);
            presenter.getTweetList();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.feed_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(presenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTweetList(List<Tweet> tweets) {
        tweetsAdapter.addTweets(tweets);
    }

    @Override
    public void showFilteredTweetList(List<Tweet> tweets) {
        tweetsAdapter.setTweets(tweets);
    }

    @Override
    public void showErrorLoadingTweetList(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG);
    }

    @Override
    public void showProgressLoader(boolean show) {

    }
}
