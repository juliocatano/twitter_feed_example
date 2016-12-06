package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.presenter;

import android.support.v7.widget.SearchView;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Presenter that will process data brought by the
 * {@link example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.interactor.TweetListInteractor}
 * and that will tell the view how to present it.
 *
 * @author juliocatano
 */
public interface TweetListPresenter extends SearchView.OnQueryTextListener {

    /**
     * Method that loads the tweet list and tells the view what to do with it
     */
    void getTweetList();

    /**
     * Getter for the tweet list that we are getting
     * @return Tweet list contained by the presenter
     */
    List<Tweet> getPresenterTweetList();

    /**
     * Free's the presenter resources
     */
    void onDestroy();

    @Override
    boolean onQueryTextSubmit(String query);

    @Override
    boolean onQueryTextChange(String newText);
}
