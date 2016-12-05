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

        void onTweetsLoadedError(String errorMessage);
    }

    /**
     * Method that gets the tweets for a certain user using the Screen Name
     *
     * @param screenName Screen Name of the user that we want to bring the tweets
     * @param listener   {@link OnTweetsLoadedListener} that will be called by the interactor
     *                   when tweets are loaded.
     */
    void getTweetsForUser(String screenName, OnTweetsLoadedListener listener);

}
