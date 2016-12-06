package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.interactor;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Interactor that brings tweets for a user
 *
 * @author juliocatano
 */

public interface TweetListInteractor {

    interface OnTweetsLoadedListener {
        void onTweetsLoaded(List<Tweet> tweets);

        void onTweetsLoadedError(Throwable exception);
    }

    /**
     * Method that gets the tweets for a certain user using the Screen Name
     *
     * @param screenName Screen Name of the user that we want to bring the tweets
     */
    void getTweetsForUser(String screenName);

    /**
     * Method that fetchs more tweets using the latest id obtained in last request
     * @param maxId id of the last tweet obtained in the previous call
     * @param screenName Screen Name of the user I want to retreive tweets
     */
    void getNextPage(long maxId, String screenName);

}
