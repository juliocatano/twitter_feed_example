package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.presenter;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.twitter.sdk.android.core.models.Tweet;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import example.twitter.jacatanog.mobile.com.twitterfeedexample.R;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.interactor.TweetListInteractor;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.interactor.TweetListInteractorImpl;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.view.TweetListView;

/**
 * Implementation of a {@link TweetListPresenter}
 *
 * @author juliocatano
 */
public class TweetListPresenterImpl implements TweetListPresenter, TweetListInteractor.OnTweetsLoadedListener {

    private static final String DEFAULT_USER = "FinalFantasy";

    private String screenName;
    private TweetListView tweetListView;
    private TweetListInteractor tweetListInteractor;
    private List<Tweet> tweets;

    private boolean isGettingTweets = false;

    public TweetListPresenterImpl(TweetListView listView) {
        this.screenName = DEFAULT_USER;
        this.tweetListView = listView;
        this.tweetListInteractor = new TweetListInteractorImpl(this);
        this.tweets = new ArrayList<>();
    }

    public TweetListPresenterImpl(String screenName, TweetListView listView) {
        this.screenName = screenName;
        this.tweetListView = listView;
        this.tweetListInteractor = new TweetListInteractorImpl(this);
        this.tweets = new ArrayList<>();
    }

    public TweetListPresenterImpl(String screenName, TweetListView listView, TweetListInteractor interactor) {
        this.screenName = screenName;
        this.tweetListView = listView;
        this.tweetListInteractor = interactor;
        this.tweets = new ArrayList<>();
    }

    @Override
    public void onTweetsLoaded(List<Tweet> tweets) {
        isGettingTweets = false;
        this.tweets.addAll(tweets);
        tweetListView.showTweetList(tweets);
        tweetListView.showProgressLoader(false);
    }

    @Override
    public void onTweetsLoadedError(Throwable exception) {
        if (exception instanceof SocketTimeoutException) {
            tweetListView.showErrorLoadingTweetList(tweetListView.getContext().getString(R.string.socket_time_out_error_message));
        }
    }

    @Override
    public void getTweetList() {
        if (!isGettingTweets) {
            if (tweets.size() > 0) {
                tweetListInteractor.getNextPage(tweets.get(tweets.size() - 1).id, screenName);
            } else {
                tweetListInteractor.getTweetsForUser(screenName);
            }
            tweetListView.showProgressLoader(true);
            isGettingTweets = true;
        }
    }

    @Override
    public List<Tweet> getPresenterTweetList() {
        return tweets;
    }

    @Override
    public void onDestroy() {
        tweetListView = null;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        List<Tweet> filteredTweets = FluentIterable.from(tweets)
                                                   .filter(new Predicate<Tweet>() {
                                                       @Override
                                                       public boolean apply(Tweet input) {
                                                           return input.text.toLowerCase().contains(newText.toLowerCase());
                                                       }
                                                   }).toList();
        tweetListView.showFilteredTweetList(filteredTweets);
        return true;
    }
}
