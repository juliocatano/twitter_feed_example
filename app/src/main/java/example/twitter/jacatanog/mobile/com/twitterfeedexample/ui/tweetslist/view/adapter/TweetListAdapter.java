package example.twitter.jacatanog.mobile.com.twitterfeedexample.ui.tweetslist.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import example.twitter.jacatanog.mobile.com.twitterfeedexample.R;

/**
 * Adapter that paints tweets obtained from the server
 *
 * @author juliocatano
 */
public class TweetListAdapter extends RecyclerView.Adapter<TweetListAdapter.TweetViewHolder> {

    private List<Tweet> tweetList;

    public TweetListAdapter(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_tweet, parent, false);
        return new TweetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        holder.onBind(tweetList.get(position));
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    public class TweetViewHolder extends RecyclerView.ViewHolder {

        private ImageView profilePicture;
        private TextView userName;
        private TextView tweetContent;
        private TextView tweetContentLink;
        private ImageView tweetContentImage;

        public TweetViewHolder(View itemView) {
            super(itemView);
            profilePicture = (ImageView) itemView.findViewById(R.id.im_user_profile_picture);
            userName = (TextView) itemView.findViewById(R.id.tv_user_name);
            tweetContent = (TextView) itemView.findViewById(R.id.tv_tweet_text);
            tweetContentLink = (TextView) itemView.findViewById(R.id.tv_media_url);
            tweetContentImage = (ImageView) itemView.findViewById(R.id.im_user_image_content);
        }

        public void onBind(Tweet tweet) {
            Context context = itemView.getContext();
            Picasso.with(context).load(tweet.user.profileImageUrlHttps).into(profilePicture);
            userName.setText(tweet.user.screenName);
            tweetContent.setText(tweet.text);

            if (tweet.entities != null) {
                if (tweet.entities.media != null && tweet.entities.media.size() > 0) {
                    if (tweet.entities.media.get(0).type.equals("photo")) {
                        tweetContentLink.setVisibility(View.GONE);
                        tweetContentImage.setVisibility(View.VISIBLE);
                        Picasso.with(context).load(tweet.entities.media.get(0).mediaUrl).into(tweetContentImage);
                    } else {
                        tweetContentLink.setVisibility(View.VISIBLE);
                        tweetContentImage.setVisibility(View.GONE);
                        tweetContentLink.setText(tweet.entities.media.get(0).mediaUrl);
                    }
                }
            }
        }
    }
}
