package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfacePostBookmarkAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentFeed;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCollection;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelFeedItem;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponsePostReview;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTicket;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ListOfFeedItemsAdapter extends BaseAdapter {

    private String BASE_URL_IMAGES = "http://www.smartshanghai.com/uploads/smartticket/largethumbs/";
    private ArrayList<ModelFeedItem> feedItems;
    private LayoutInflater inflater;
    private Context ctx;
    private int userId;
    private MainActivity mMainActivity;
    private SharedPreferences mSharedPreferences;


    public ListOfFeedItemsAdapter(ArrayList<ModelFeedItem> feedItems, Context c, int userId, MainActivity mMainActivity) {
        this.feedItems = feedItems;
        this.ctx = c;
        this.userId = userId;
        this.mMainActivity = mMainActivity;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Fresco.initialize(c);
    }

    public void updateList(ArrayList<ModelFeedItem> feedItems) {
        this.feedItems = feedItems;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int position) {
        return feedItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelFeedItem feedItem = feedItems.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_feed_item, null);


            holder.rlId1 = (RelativeLayout) v.findViewById(R.id.rl_fragment_feed_custom_item_article_id_1);
            holder.tvId1ArticleName = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_1_title);
            holder.tvId1TimeAgo = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_article_id_1_time_ago);
            holder.tvId1ArticleDescriptionMeta2 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_1_description_meta2);
            holder.dvId1ArticleImage = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_1_image_article);
            holder.rlId1Bookmarks = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_1_bookmark);
            holder.ivId1Bookmark = (ImageView) v.findViewById(R.id.iv_custom_item_feed_item_id_1_bookmark);
            holder.tvId1Bookmark = (TextView) v.findViewById(R.id.tv_custom_item_feed_item_id_1_bookmark);
            holder.rlId1Share = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_1_share);

            holder.rlId2 = (RelativeLayout) v.findViewById(R.id.rl_fragment_feed_custom_item_ticket_id_2);
            holder.tvId2TimeAgo = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_ticket_id_2_time_ago);
            holder.tvId2Title = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_2_title);
            holder.tvId2DescriptionMeta1 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_2_description_meta1);
            holder.tvId2DateMeta2 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_2_date_meta2);
            holder.tvId2PlaceMeta3 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_2_place_meta3);
            holder.dvId2TicketImage = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_2_image);
            holder.rlId2Bookmarks = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_2_bookmark);
            holder.ivId2Bookmark = (ImageView) v.findViewById(R.id.iv_custom_item_feed_item_id_2_bookmark);
            holder.tvId2Bookmark = (TextView) v.findViewById(R.id.tv_custom_item_feed_item_id_2_bookmark);
            holder.tvId2AllShows = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_2_all_shows);
            holder.tvId2BuyTickets = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_2_buy_tickets);
            holder.rlId2Share = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_2_share);

            holder.rlId3 = (RelativeLayout) v.findViewById(R.id.rl_fragment_feed_custom_item_venue_user_quote_id_3);
            holder.tvId3topMeta1 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_3_top_meta1);
            holder.dvId3Venue1meta2 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_3_venue_img_meta_2);
            holder.dvId3Venue2meta7 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_3_venue2_img_meta_6);
            holder.dvId3Venue3meta12 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_3_venue3_img_meta_10);
            holder.tvId3Venue1NameMeta3 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_3_venue_name_meta_3);
            holder.tvId3Venue2NameMeta8 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_3_venue2_name_meta_7);
            holder.tvId3Venue3NameMeta13 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_3_venue3_name_meta_11);
            holder.rbId3Venue1rateMeta4 = (RatingBar) v.findViewById(R.id.rb_fragment_feed_custom_item_id_3_venue1_meta4);
            holder.rbId3Venue2rateMeta9 = (RatingBar) v.findViewById(R.id.rb_fragment_feed_custom_item_id_3_venue2_meta8);
            holder.rbId3Venue3rateMeta14 = (RatingBar) v.findViewById(R.id.rb_fragment_feed_custom_item_id_3_venue3_meta12);
            holder.tvId3Venue1reviewMeta5 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_3_review_venue1_meta5);
            holder.tvId3Venue2reviewMeta10 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_3_review_venue2_meta9);
            holder.tvId3Venue3reviewMeta15 = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_3_review_venue3_meta13);
            holder.tvId3timeAgo = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_review_id_3_time_ago);
            holder.llId3Review1 = (LinearLayout) v.findViewById(R.id.ll_fragment_feed_custom_item_id_3_review1);
            holder.llId3Review2 = (LinearLayout) v.findViewById(R.id.ll_fragment_feed_custom_item_id_3_review2);
            holder.llId3Review3 = (LinearLayout) v.findViewById(R.id.ll_fragment_feed_custom_item_id_3_review3);
            holder.butId3AllReviews = (Button) v.findViewById(R.id.but_fragment_feed_custom_item_id_3_all_reviews);


            holder.rlId4 = (RelativeLayout) v.findViewById(R.id.rl_fragment_feed_custom_item_party_pix_id_4);
            holder.tvId4timeAgo = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_party_pix_id_4_time_ago);
            holder.tvId4title = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_4_title);
            holder.dvId4Pic1 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_4_picture1);
            holder.dvId4Pic2 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_4_picture2);
            holder.dvId4Pic3 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_4_picture3);
            holder.dvId4Pic4 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_4_picture4);
            holder.dvId4Pic5 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_4_picture5);
            holder.dvId4Pic6 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_4_picture6);
            holder.dvId4Pic7 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_4_picture7);
            holder.dvId4Pic8 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_4_picture8);
            holder.dvId4Pic9 = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_4_picture9);
            holder.rlId4Bookmarks = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_4_bookmark);
            holder.ivId4Bookmark = (ImageView) v.findViewById(R.id.iv_custom_item_feed_item_id_4_bookmark);
            holder.tvId4Bookmark = (TextView) v.findViewById(R.id.tv_custom_item_feed_item_id_4_bookmark);
            holder.rlId4Share = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_4_share);


            holder.rlId5 = (RelativeLayout) v.findViewById(R.id.rl_fragment_feed_custom_item_collection_id_5);
            holder.tvId5PublishedTime = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_collection_id_5_time_ago);
            holder.tvId5title = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_5_colletion_title);
            holder.dvId5CollectionImage = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_5_image_collection);
            holder.rlId5Bookmarks = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_5_bookmark);
            holder.ivId5Bookmark = (ImageView) v.findViewById(R.id.iv_custom_item_feed_item_id_5_bookmark);
            holder.tvId5Bookmark = (TextView) v.findViewById(R.id.tv_custom_item_feed_item_id_5_bookmark);
            holder.rlId5Share = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_5_share);
            holder.tvId5subtitle = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_5_colletion_subtitle);

            holder.rlId6 = (RelativeLayout) v.findViewById(R.id.rl_fragment_feed_custom_item_ad_id_6);
            holder.tvId6TopAdvertisement = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_6_ad);
            holder.dvId6ImageAdvertisement = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_6_advertisement);

            holder.rlId7 = (RelativeLayout) v.findViewById(R.id.rl_fragment_feed_custom_item_collection_event_id_7);
            holder.tvId7PublishedTime = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_collection_event_id_7_time_ago);
            holder.tvId7title = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_7_colletion_event_title);
            holder.dvId7CollectionImage = (DraweeView) v.findViewById(R.id.dv_fragment_feed_custom_item_id_7_image_collection_event);
            holder.rlId7Bookmarks = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_7_bookmark);
            holder.ivId7Bookmark = (ImageView) v.findViewById(R.id.iv_custom_item_feed_item_id_7_bookmark);
            holder.tvId7Bookmark = (TextView) v.findViewById(R.id.tv_custom_item_feed_item_id_7_bookmark);
            holder.rlId7Share = (RelativeLayout) v.findViewById(R.id.rl_custom_item_feed_item_id_7_share);
            holder.tvId7subtitle = (TextView) v.findViewById(R.id.tv_fragment_feed_custom_item_id_7_colletion_event_subtitle);


            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }


        holder.rlId1.setVisibility(View.GONE);
        holder.rlId2.setVisibility(View.GONE);
        holder.rlId3.setVisibility(View.GONE);
        holder.rlId4.setVisibility(View.GONE);
        holder.rlId5.setVisibility(View.GONE);
        holder.rlId6.setVisibility(View.GONE);
        holder.rlId7.setVisibility(View.GONE);
        if (feedItem.getType_id() <= 7) {

            if (feedItem.getType_id() == 1) {
                holder.rlId1.setVisibility(View.VISIBLE);
                holder.tvId1TimeAgo.setText(feedItem.getDate_timeago());
                holder.tvId1ArticleName.setText(feedItem.getTitle());
                holder.tvId1ArticleDescriptionMeta2.setText(feedItem.getMeta2());
                Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/feed/" + feedItem.getThumb());
                holder.dvId1ArticleImage.setImageURI(imageUri);
                if (!feedItem.isBookmarked()) {
                    holder.ivId1Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                    holder.tvId1Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                } else {
                    holder.ivId1Bookmark.setImageResource(R.drawable.ic_bookmarked);
                    holder.tvId1Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                }
                holder.rlId1Bookmarks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!feedItem.isBookmarked()) {
                            if (clickBookmark(feedItem.getId(), ctx)) {
                                holder.ivId1Bookmark.setImageResource(R.drawable.ic_bookmarked);
                                holder.tvId1Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                                feedItem.setIsBookmarked(true);
                            }
                        } else {
                            clickUnBookmark(feedItem.getId(), ctx, userId);
                            holder.ivId1Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                            holder.tvId1Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                            feedItem.setIsBookmarked(false);
                        }

                    }

                });
                holder.rlId1Share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickShare(feedItem, "Share a wire");
                    }
                });

            } else if (feedItem.getType_id() == 2) {
                holder.rlId2.setVisibility(View.VISIBLE);
                holder.tvId2TimeAgo.setText(feedItem.getDate_timeago());
                holder.tvId2Title.setText(feedItem.getTitle());
                holder.tvId2DescriptionMeta1.setText(feedItem.getMeta1());
                holder.tvId2DateMeta2.setText(feedItem.getMeta2());
                holder.tvId2PlaceMeta3.setText(feedItem.getMeta3());
                Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/feed/" + feedItem.getThumb());
                holder.dvId2TicketImage.setImageURI(imageUri);
                if (!feedItem.isBookmarked()) {
                    holder.ivId2Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                    holder.tvId2Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                } else {
                    holder.ivId2Bookmark.setImageResource(R.drawable.ic_bookmarked);
                    holder.tvId2Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                }
                holder.tvId2AllShows.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mMainActivity != null) {
                            mMainActivity.setPagerToTickets();
                        }
                    }
                });


                holder.rlId2Bookmarks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!feedItem.isBookmarked()) {
                            if (clickBookmark(feedItem.getId(), ctx)) {
                                holder.ivId2Bookmark.setImageResource(R.drawable.ic_bookmarked);
                                holder.tvId2Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                                feedItem.setIsBookmarked(true);
                            }
                        } else {
                            clickUnBookmark(feedItem.getId(), ctx, userId);
                            holder.ivId2Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                            holder.tvId2Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                            feedItem.setIsBookmarked(false);
                        }
                    }
                });
                holder.rlId2Share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickShare(feedItem, "Share tickets");
                    }
                });


            } else if (feedItem.getType_id() == 3) {
                holder.rlId3.setVisibility(View.VISIBLE);
                holder.tvId3timeAgo.setText(feedItem.getDate_timeago());
                holder.tvId3topMeta1.setText(feedItem.getMeta1() + " Users");
                Uri imageUri = Uri.parse(feedItem.getMeta2());
                holder.dvId3Venue1meta2.setImageURI(imageUri);
                holder.tvId3Venue1NameMeta3.setText("@ " + feedItem.getMeta3());
                holder.rbId3Venue1rateMeta4.setRating(Float.parseFloat(feedItem.getMeta4()));
                holder.tvId3Venue1reviewMeta5.setText(feedItem.getMeta5());
                imageUri = Uri.parse(feedItem.getMeta7());
                holder.dvId3Venue2meta7.setImageURI(imageUri);
                holder.tvId3Venue2NameMeta8.setText("@ " + feedItem.getMeta8());
                holder.rbId3Venue2rateMeta9.setRating(Float.parseFloat(feedItem.getMeta9()));
                holder.tvId3Venue2reviewMeta10.setText(feedItem.getMeta10());
                imageUri = Uri.parse(feedItem.getMeta12());
                holder.dvId3Venue3meta12.setImageURI(imageUri);
                holder.tvId3Venue3NameMeta13.setText("@ " + feedItem.getMeta13());
                holder.rbId3Venue3rateMeta14.setRating(Float.parseFloat(feedItem.getMeta14()));
                holder.tvId3Venue3reviewMeta15.setText(feedItem.getMeta15());
                holder.llId3Review1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickReview(feedItem.getMeta6());
                    }
                });
                holder.llId3Review2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickReview(feedItem.getMeta11());
                    }
                });
                holder.llId3Review3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickReview(feedItem.getMeta16());
                    }
                });
                holder.butId3AllReviews.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickReview("0");
                    }
                });


            } else if (feedItem.getType_id() == 4) {
                holder.rlId4.setVisibility(View.VISIBLE);
                holder.tvId4timeAgo.setText(feedItem.getDate_timeago());
                holder.tvId4title.setText(feedItem.getTitle() + " @ " + feedItem.getMeta2());

                String sizeWanted = "200";
                String strPictures = feedItem.getMeta3();
                if (feedItem.getMeta3() != null) {
                    String[] strSeparated = strPictures.split(",");
                    Uri imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + strSeparated[0] + "&width=" + sizeWanted);
                    holder.dvId4Pic1.setImageURI(imageUri);
                    imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + strSeparated[1] + "&width=" + sizeWanted);
                    holder.dvId4Pic2.setImageURI(imageUri);
                    imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + strSeparated[2] + "&width=" + sizeWanted);
                    holder.dvId4Pic3.setImageURI(imageUri);
                    imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + strSeparated[3] + "&width=" + sizeWanted);
                    holder.dvId4Pic4.setImageURI(imageUri);
                    imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + strSeparated[4] + "&width=" + sizeWanted);
                    holder.dvId4Pic5.setImageURI(imageUri);
                    imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + strSeparated[5] + "&width=" + sizeWanted);
                    holder.dvId4Pic6.setImageURI(imageUri);
                    imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + strSeparated[6] + "&width=" + sizeWanted);
                    holder.dvId4Pic7.setImageURI(imageUri);
                    imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + strSeparated[7] + "&width=" + sizeWanted);
                    holder.dvId4Pic8.setImageURI(imageUri);
                    imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + strSeparated[8] + "&width=" + sizeWanted);
                    holder.dvId4Pic9.setImageURI(imageUri);
                }
                if (!feedItem.isBookmarked()) {
                    holder.ivId4Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                    holder.tvId4Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                } else {
                    holder.ivId4Bookmark.setImageResource(R.drawable.ic_bookmarked);
                    holder.tvId4Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                }
                holder.rlId4Bookmarks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!feedItem.isBookmarked()) {
                            if (clickBookmark(feedItem.getId(), ctx)) {
                                holder.ivId4Bookmark.setImageResource(R.drawable.ic_bookmarked);
                                holder.tvId4Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                                feedItem.setIsBookmarked(true);
                            }
                        } else {
                            clickUnBookmark(feedItem.getId(), ctx, userId);
                            holder.ivId4Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                            holder.tvId4Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                            feedItem.setIsBookmarked(false);
                        }
                    }
                });
                holder.rlId4Share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickShare(feedItem, "Share gallery");
                    }
                });


            } else if (feedItem.getType_id() == 5) {
                holder.rlId5.setVisibility(View.VISIBLE);
                holder.tvId5PublishedTime.setText(feedItem.getDate_timeago());
                holder.tvId5title.setText(feedItem.getTitle());
                Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/feed/" + feedItem.getThumb());
                holder.dvId5CollectionImage.setImageURI(imageUri);
                holder.tvId5subtitle.setText(feedItem.getLast_edited());
                if (!feedItem.isBookmarked()) {
                    holder.ivId5Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                    holder.tvId5Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                } else {
                    holder.ivId5Bookmark.setImageResource(R.drawable.ic_bookmarked);
                    holder.tvId5Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                }
                holder.rlId5Bookmarks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!feedItem.isBookmarked()) {
                            if (clickBookmark(feedItem.getId(), ctx)) {
                                holder.ivId5Bookmark.setImageResource(R.drawable.ic_bookmarked);
                                holder.tvId5Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                                feedItem.setIsBookmarked(true);
                            }
                        } else {
                            clickUnBookmark(feedItem.getId(), ctx, userId);
                            holder.ivId5Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                            holder.tvId5Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                            feedItem.setIsBookmarked(false);
                        }
                    }
                });
                holder.rlId5Share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickShare(feedItem, "Share collection");
                    }
                });

            } else if (feedItem.getType_id() == 6) {
                if (feedItem.getEntry_id() > 0) {
                    holder.rlId6.setVisibility(View.VISIBLE);
                    Uri imageUri = Uri.parse("http://www.smartshanghai.com/mar/" + feedItem.getEntry_id() + "?source=2");
                    holder.dvId6ImageAdvertisement.setImageURI(imageUri);
                }


            } else if (feedItem.getType_id() == 7) {
                holder.rlId7.setVisibility(View.VISIBLE);
                holder.tvId7PublishedTime.setText(feedItem.getDate_timeago());
                holder.tvId7title.setText(feedItem.getTitle());
                Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/feed/" + feedItem.getThumb());
                holder.dvId7CollectionImage.setImageURI(imageUri);
                holder.tvId7subtitle.setText(feedItem.getLast_edited());
                if (!feedItem.isBookmarked()) {
                    holder.ivId7Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                    holder.tvId7Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                } else {
                    holder.ivId7Bookmark.setImageResource(R.drawable.ic_bookmarked);
                    holder.tvId7Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                }
                holder.rlId7Bookmarks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!feedItem.isBookmarked()) {
                            if (clickBookmark(feedItem.getId(), ctx)) {
                                holder.ivId7Bookmark.setImageResource(R.drawable.ic_bookmarked);
                                holder.tvId7Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreenBookmark));
                                feedItem.setIsBookmarked(true);
                            }
                        } else {
                            clickUnBookmark(feedItem.getId(), ctx, userId);
                            holder.ivId7Bookmark.setImageResource(R.drawable.ic_feedicons_03);
                            holder.tvId7Bookmark.setTextColor(ctx.getResources().getColor(R.color.colorGreyFeedBookmark));
                            feedItem.setIsBookmarked(false);
                        }

                    }
                });
                holder.rlId7Share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickShare(feedItem, "Share collection");
                    }
                });

            } else {

            }
        }


        return v;


    }

    private static class ViewHolder {

        RelativeLayout rlId1;
        DraweeView dvId1ArticleImage;
        TextView tvId1ArticleName;
        TextView tvId1ArticleDescriptionMeta2;
        TextView tvId1TimeAgo;
        RelativeLayout rlId1Bookmarks;
        RelativeLayout rlId1Share;
        ImageView ivId1Bookmark;
        TextView tvId1Bookmark;

        RelativeLayout rlId2;
        DraweeView dvId2TicketImage;
        TextView tvId2TimeAgo;
        TextView tvId2Title;
        TextView tvId2DescriptionMeta1;
        TextView tvId2DateMeta2;
        TextView tvId2PlaceMeta3;
        RelativeLayout rlId2Bookmarks;
        RelativeLayout rlId2Share;
        ImageView ivId2Bookmark;
        TextView tvId2Bookmark;
        TextView tvId2BuyTickets;
        TextView tvId2AllShows;


        RelativeLayout rlId3;
        TextView tvId3topMeta1;
        TextView tvId3timeAgo;
        DraweeView dvId3Venue1meta2;
        DraweeView dvId3Venue2meta7;
        DraweeView dvId3Venue3meta12;
        TextView tvId3Venue1NameMeta3;
        TextView tvId3Venue2NameMeta8;
        TextView tvId3Venue3NameMeta13;
        TextView tvId3Venue1reviewMeta5;
        TextView tvId3Venue2reviewMeta10;
        TextView tvId3Venue3reviewMeta15;
        RatingBar rbId3Venue1rateMeta4;
        RatingBar rbId3Venue2rateMeta9;
        RatingBar rbId3Venue3rateMeta14;
        LinearLayout llId3Review1;
        LinearLayout llId3Review2;
        LinearLayout llId3Review3;
        Button butId3AllReviews;

        RelativeLayout rlId4;
        TextView tvId4timeAgo;
        DraweeView dvId4Pic1;
        DraweeView dvId4Pic2;
        DraweeView dvId4Pic3;
        DraweeView dvId4Pic4;
        DraweeView dvId4Pic5;
        DraweeView dvId4Pic6;
        DraweeView dvId4Pic7;
        DraweeView dvId4Pic8;
        DraweeView dvId4Pic9;
        TextView tvId4title;
        RelativeLayout rlId4Bookmarks;
        RelativeLayout rlId4Share;
        ImageView ivId4Bookmark;
        TextView tvId4Bookmark;


        RelativeLayout rlId5;
        TextView tvId5PublishedTime;
        DraweeView dvId5CollectionImage;
        TextView tvId5title;
        TextView tvId5subtitle;
        RelativeLayout rlId5Bookmarks;
        RelativeLayout rlId5Share;
        ImageView ivId5Bookmark;
        TextView tvId5Bookmark;


        RelativeLayout rlId6;
        TextView tvId6TopAdvertisement;
        DraweeView dvId6ImageAdvertisement;

        RelativeLayout rlId7;
        TextView tvId7PublishedTime;
        DraweeView dvId7CollectionImage;
        TextView tvId7title;
        TextView tvId7subtitle;
        RelativeLayout rlId7Bookmarks;
        RelativeLayout rlId7Share;
        ImageView ivId7Bookmark;
        TextView tvId7Bookmark;


    }


    private boolean clickBookmark(int idClicked, Context ctx) {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        userId = mSharedPreferences.getInt("userID", 0);

        if (userId > 0) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://smartshanghai.com/api/feed/bookmarks/add")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfacePostBookmarkAPI api = retrofit.create(InterfacePostBookmarkAPI.class);
            String feedId = String.valueOf(idClicked);
            String.valueOf(userId);

            String url = "?key=" + "abc123iphone" + "&feedId=" + feedId + "&userId=" + userId;

            Call<ModelResponsePostReview> callToPostBookmark = api.postBookmarks(url);

            callToPostBookmark.enqueue(new Callback<ModelResponsePostReview>() {
                @Override
                public void onResponse(Response<ModelResponsePostReview> response, Retrofit retrofit) {
                    response.body();

                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
            return true;
        } else {
            Intent i = new Intent(ctx, AskSignInActivity.class);
            ctx.startActivity(i);
            return false;
        }


    }

    private void clickUnBookmark(int idClicked, Context ctx, int userId) {

        if (userId > 0) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://smartshanghai.com/api/feed/bookmarks/remove")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfacePostBookmarkAPI api = retrofit.create(InterfacePostBookmarkAPI.class);
            String feedId = String.valueOf(idClicked);
            String.valueOf(userId);

            String url = "?key=" + "abc123iphone" + "&feedId=" + feedId + "&userId=" + userId;

            Call<ModelResponsePostReview> callToPostBookmark = api.postBookmarks(url);

            callToPostBookmark.enqueue(new Callback<ModelResponsePostReview>() {
                @Override
                public void onResponse(Response<ModelResponsePostReview> response, Retrofit retrofit) {
                    response.body();


                }

                @Override
                public void onFailure(Throwable t) {
                }
            });

        }


    }

    private void clickShare(ModelFeedItem modelFeedItem, String cat) {
        String toBeShared;
        if (cat.equals("Share a wire")) { // ADD ANDROID
            toBeShared = modelFeedItem.getShare_phrase() + modelFeedItem.getShare_title() + " " + modelFeedItem.getShare_url() + "?source=android";
        } else {
            toBeShared = modelFeedItem.getShare_phrase() + modelFeedItem.getShare_title() + " " + modelFeedItem.getShare_url();

        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, toBeShared);
        ctx.startActivity(Intent.createChooser(intent, cat));
    }

    private void clickReview(String revClicked) {
        Intent i = new Intent(ctx, ReviewsActivity.class);
        i.putExtra("key_bundle_first_review", revClicked);
        ctx.startActivity(i);
    }

}
