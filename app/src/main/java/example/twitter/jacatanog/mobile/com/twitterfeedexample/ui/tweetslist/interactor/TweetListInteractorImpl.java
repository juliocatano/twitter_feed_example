package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.interactor;

import android.widget.Toast;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that brings tweets for a user using twitter SDK
 *
 * @author juliocatano
 */

public class TweetListInteractorImpl implements TweetListInteractor {

    private static final String NO_TWEETS_LOADED = "Tweets not loaded";

    @Override
    public void getTweetsForUser(String screenName, final OnTweetsLoadedListener listener) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.userTimeline(null, "juliocatano", 20, null, null, null, null, null, null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.isSuccessful()) {
                    listener.onTweetsLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                listener.onTweetsLoadedError(NO_TWEETS_LOADED);
            }
        });
    }
}
