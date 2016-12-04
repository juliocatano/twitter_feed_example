package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import example.twitter.jacatanog.mobile.com.twitterfeedexample.R;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.model.UserStatus;
import example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.services.ApiClientTwitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TwitterFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwitterFeedFragment extends Fragment {


    public TwitterFeedFragment() {
        // Required empty public constructor
    }

    public static TwitterFeedFragment newInstance() {
        return new TwitterFeedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_twitter_feed, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiClientTwitter.TwitterApiInterface apiService = ApiClientTwitter.getApiService();
        Call<List<UserStatus>> statusesCall = apiService.getUserStatuses("juliocatano", "20");
        statusesCall.enqueue(new Callback<List<UserStatus>>() {
            @Override
            public void onResponse(Call<List<UserStatus>> call, Response<List<UserStatus>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TwitterFeedFragment.this.getActivity(), "Got response!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UserStatus>> call, Throwable t) {
                Toast.makeText(TwitterFeedFragment.this.getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
