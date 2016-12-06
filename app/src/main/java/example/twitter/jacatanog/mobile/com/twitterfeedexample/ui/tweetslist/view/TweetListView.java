package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.view;

import android.content.Context;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * View that will be in charge of showing a list of tweets brought by any
 * {@link example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.presenter.TweetListPresenter}
 *
 * @author juliocatano
 */
public interface TweetListView {
    /**
     * shows a tweet list inside the view
     *
     * @param tweets tweets to be showed
     */
    void showTweetList(List<Tweet> tweets);

    /**
     * Shows a filtered list of the tweets
     * @param tweets list of tweets filtered
     */
    void showFilteredTweetList(List<Tweet> tweets);

    /**
     * Shows an error message as a {@link android.widget.TextView} when there was an error loading tweets
     *
     * @param errorMessage the message that should be displayed
     */
    void showErrorLoadingTweetList(String errorMessage);

    /**
     * Shows/Hides a progress loader when tweets are loading
     * @param show true to show the loader
     */
    void showProgressLoader(boolean show);

    /**
     * Gets the context of the view
     *
     * @return returns the context of an {@link android.view.View}
     */
    Context getContext();
}
