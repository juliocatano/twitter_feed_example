package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.services;

import java.util.List;

import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.model.UserStatus;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by juliocatano on 12/4/16.
 */

public class ApiClientTwitter {

    public interface TwitterApiInterface {
        @GET("statuses/user_timeline.json")
        Call<List<UserStatus>> getUserStatuses(@Query("screen_name") String user, @Query("count") String postCount);
    }

    private static final String TWITTER_BASE_API_URL = "https://api.twitter.com/1.1/";

    private static TwitterApiInterface apiService;

    public static TwitterApiInterface getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(TWITTER_BASE_API_URL)
                                                      .client(createTwitterHttpClient())
                                                      .addConverterFactory(GsonConverterFactory.create())
                                                      .build();
            apiService = retrofit.create(TwitterApiInterface.class);
        }
        return apiService;
    }

    private static OkHttpClient createTwitterHttpClient() {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer("1R2mg1HZwu1xVE5EP1yZniVBX", "g1v99RxM8KKAnJ3IJZslJwoRT9sdnTziYjelwEMn4RMt5Fgryy");
        return new OkHttpClient.Builder().addInterceptor(new SigningInterceptor(consumer)).build();
    }
}
