package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelAd;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelVenue;

import java.util.ArrayList;

/**
 * Created by Pithou on 14/04/2016.
 */
public class DirectoryListingVenuesAdapter extends BaseAdapter {

    private boolean mLocAuth;
    private ArrayList<ModelVenue> venues;
    private LayoutInflater inflater;
    private ArrayList<ModelAd> mAds;
    private int adNumber;
    private Context ctx;
    private int position2;
    private boolean isFromNearby;

    public final static String KEY_BUNDLE_WEB_ADDRESS_AD_DIR_LISTING = "key bundle web address ad dir listing";


    public DirectoryListingVenuesAdapter(ArrayList<ModelVenue> venues, Context c, boolean locAuth, ArrayList<ModelAd> mAds, boolean isFromNearby) {
        this.venues = venues;
        this.mLocAuth = locAuth;
        this.adNumber = 0;
        this.mAds = mAds;
        this.ctx = c;
        this.isFromNearby = isFromNearby;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Fresco.initialize(c);
        position2 = 1;

    }


    public void updateList(ArrayList<ModelVenue> venues) {
        this.venues = venues;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return venues.size();
    }

    @Override
    public Object getItem(int position) {
        return venues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelVenue venue = venues.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_venue_item, null);

            holder.tvNameVenue = (TextView) v.findViewById(R.id.tv_directory_listing_venue_name);
            holder.dvImageVenue = (DraweeView) v.findViewById(R.id.dv_directory_listing_custom_item_image);
            holder.tvDistance = (TextView) v.findViewById(R.id.tv_directory_listing_distance);
            holder.tvAddress = (TextView) v.findViewById(R.id.tv_directory_listing_address_short);

//            holder.tvNumberOfReview = (TextView) v.findViewById(R.id.tv_custom_item_venue_number_of_review);

//            holder.rbRateVenue = (RatingBar) v.findViewById(R.id.rb_custom_item_venue_rate_of_venue);

            holder.rlDataDistance = (RelativeLayout) v.findViewById(R.id.rl_directory_listing_distance_data);

            holder.ivDistance = (ImageView) v.findViewById(R.id.iv_directory_listing_location);

            holder.tvPrice1 = (TextView) v.findViewById(R.id.tv_directory_listing_price1);
            holder.tvPrice2 = (TextView) v.findViewById(R.id.tv_directory_listing_price2);
            holder.tvPrice3 = (TextView) v.findViewById(R.id.tv_directory_listing_price3);
            holder.tvPrice4 = (TextView) v.findViewById(R.id.tv_directory_listing_price4);
            holder.tvPrice5 = (TextView) v.findViewById(R.id.tv_directory_listing_price5);

            holder.llPrice = (LinearLayout) v.findViewById(R.id.ll_custom_item_venue_price);

            holder.dvAd = (DraweeView) v.findViewById(R.id.dv_directory_listing_custom_item_ad);

            holder.rlClosed = (RelativeLayout) v.findViewById(R.id.rl_directory_listing_custom_item_closed);


            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        /* ADS ARE DESACTIVATED IN THE DIRECTORY LISTINGS


        if (mAds != null && mAds.size() > 0 && mAds.get(0).getAdfilename() != null) {
            if ((position % 6) == 0 && (position != 0)) {

                position2 = getAdNumberFromPosition(position);

                holder.dvAd.setVisibility(View.VISIBLE);
                String str = "http://www.smartshanghai.com/eximg/" + mAds.get(position2).getAdfilename();
                Uri adUri = Uri.parse(str);
                holder.dvAd.setImageURI(adUri);
                holder.dvAd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mAds.get(position2).getAdlink().equals("") && mAds.get(position2).getAdlink() != null) {
                            String url = mAds.get(position2).getAdlink();
                            if (!url.startsWith("https://") && !url.startsWith("http://")) {
                                url = "http://" + url;
                            }
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setData(Uri.parse(url));
                            ctx.startActivity(i);
                        }
                    }
                });

            } else {
                holder.dvAd.setVisibility(View.GONE);
            }
        } else {
            holder.dvAd.setVisibility(View.GONE);
        }

        */

        holder.tvNameVenue.setText(venue.getName());
        holder.tvAddress.setText(venue.getAddress_en());

        if (venue.getClosed() == 1) {
            holder.rlClosed.setVisibility(View.VISIBLE);
        } else {
            holder.rlClosed.setVisibility(View.GONE);
        }


        if (mLocAuth) {
            if (!isFromNearby)
                if (venue.getDistance().equals("no data")) {
                    holder.rlDataDistance.setVisibility(View.INVISIBLE);
                } else {
                    holder.tvDistance.setText(venue.getDistance() + " km");
                    holder.rlDataDistance.setVisibility(View.VISIBLE);
                    holder.tvDistance.setTextColor(ctx.getResources().getColor(R.color.colorSmartShanghaiBlue));
                    holder.ivDistance.setImageResource(R.drawable.ic_arrow_blue_collection);
                }
            else {
                holder.rlDataDistance.setVisibility(View.VISIBLE);
                holder.tvDistance.setText(venue.getDistance() + " km");
                holder.tvDistance.setTextColor(ctx.getResources().getColor(R.color.greenVenueCose));
                holder.ivDistance.setImageResource(R.drawable.ic_venue_close);
            }
        } else {
            holder.rlDataDistance.setVisibility(View.INVISIBLE);
        }


        Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/venues/thumbs/" + venue.getThumb());
        holder.dvImageVenue.setImageURI(imageUri);

//        if (venue.getReviews_count() == 0) {
//            holder.rbRateVenue.setVisibility(View.GONE);
//            holder.tvNumberOfReview.setVisibility(View.GONE);
//        } else {
//            holder.rbRateVenue.setVisibility(View.VISIBLE);
//            holder.rbRateVenue.setRating(venue.getReviews_average());
//            holder.tvNumberOfReview.setVisibility(View.VISIBLE);
//            holder.tvNumberOfReview.setText(String.valueOf(venue.getReviews_count()) + " reviews");
//        }

        if (venue.getPrice() != 0) {
            int price = venue.getPrice();
            if (price > 0) {
                holder.tvPrice1.setTextColor(v.getResources().getColor(R.color.colorBlackPrice));
                if (price > 1) {
                    holder.tvPrice2.setTextColor(v.getResources().getColor(R.color.colorBlackPrice));
                    if (price > 2) {
                        holder.tvPrice3.setTextColor(v.getResources().getColor(R.color.colorBlackPrice));
                        if (price > 3) {
                            holder.tvPrice4.setTextColor(v.getResources().getColor(R.color.colorBlackPrice));
                            if (price > 4) {
                                holder.tvPrice5.setTextColor(v.getResources().getColor(R.color.colorBlackPrice));
                            }
                        }
                    }
                }
            }
        } else {
            holder.llPrice.setVisibility(View.GONE);
        }

        return v;


    }

    private static class ViewHolder {

        TextView tvNameVenue;
        TextView tvAddress;
        TextView tvDistance;
        DraweeView dvImageVenue;

//        TextView tvNumberOfReview;

//        RatingBar rbRateVenue;

        RelativeLayout rlDataDistance;
        LinearLayout llPrice;

        TextView tvPrice1;
        TextView tvPrice2;
        TextView tvPrice3;
        TextView tvPrice4;
        TextView tvPrice5;

        DraweeView dvAd;

        ImageView ivDistance;

        RelativeLayout rlClosed;

    }


    private int getAdNumberFromPosition(int position) {
        if (position == 6 || (position % 18) == 6) {
            return 0;
        } else if (position == 12 || (position % 18) == 12) {
            return 1;
        } else {
            return 2;
        }

    }

}
