package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.interactor;

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

    private static final int TWEETS_PER_PAGE = 20;

    private StatusesService statusesService;
    private OnTweetsLoadedListener listener;

    public TweetListInteractorImpl(OnTweetsLoadedListener listener) {
        this.statusesService = TwitterCore.getInstance().getApiClient().getStatusesService();
        this.listener = listener;
    }

    @Override
    public void getTweetsForUser(String screenName) {
        Call<List<Tweet>> call = statusesService.userTimeline(null, screenName, TWEETS_PER_PAGE, null, null, null, null, null, null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.isSuccessful()) {
                    listener.onTweetsLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                listener.onTweetsLoadedError(t);
            }
        });
    }

    @Override
    public void getNextPage(long maxId, String screenName) {
        Call<List<Tweet>> call = statusesService.userTimeline(null, screenName, TWEETS_PER_PAGE, null, maxId, null, null, null, null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.isSuccessful()) {
                    listener.onTweetsLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                listener.onTweetsLoadedError(t);
            }
        });

    }
}
