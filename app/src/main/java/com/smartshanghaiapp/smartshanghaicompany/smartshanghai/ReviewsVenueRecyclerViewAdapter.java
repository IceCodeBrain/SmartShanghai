package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelReviewVenue;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ReviewsVenueRecyclerViewAdapter extends RecyclerView.Adapter<ReviewsVenueRecyclerViewAdapter.ReviewsVenueHolder> {

    private ArrayList<ModelReviewVenue> reviews;
    private LayoutInflater inflater;

    public static class ReviewsVenueHolder extends RecyclerView.ViewHolder {

        RatingBar rbTotalRating;
        RatingBar rbValueForMoney;
        RatingBar rbTheFood;
        RatingBar rbTheDrinks;
        RatingBar rbTheService;
        RatingBar rbAtmosphere;
        RatingBar rbADate;
        RatingBar rbWithFamily;
        RatingBar rbBusinessDinner;
        RatingBar rbWithFriends;
        TextView tvRevQuote;
        TextView tvUsernameAndVenueName;
        TextView tvrevTxt;
        TextView tvDate;
        DraweeView dvAvatar;

        public ReviewsVenueHolder(View itemView) {
            super(itemView);

            tvRevQuote = (TextView) itemView.findViewById(R.id.tv_custom_item_review_venue_rev_quote);
            tvUsernameAndVenueName = (TextView) itemView.findViewById(R.id.tv_custom_item_review_venue_username_and_venue_name);
            tvrevTxt = (TextView) itemView.findViewById(R.id.tv_custom_item_review_venue_rev_txt);
            tvDate = (TextView) itemView.findViewById(R.id.tv_custom_item_review_venue_date);

            rbTotalRating = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_total_rating);
            rbValueForMoney = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_value_for_money);
            rbTheFood = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_the_food);
            rbTheDrinks = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_the_drinks);
            rbTheService = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_the_service);
            rbAtmosphere = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_atmosphere);
            rbADate = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_good_for_date);
            rbWithFamily = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_good_for_family);
            rbBusinessDinner = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_good_for_business);
            rbWithFriends = (RatingBar) itemView.findViewById(R.id.rb_custom_item_review_venue_good_for_friends);

            dvAvatar = (DraweeView) itemView.findViewById(R.id.dv_custom_item_review_venue_avatar);
        }
    }

    public ReviewsVenueRecyclerViewAdapter(ArrayList<ModelReviewVenue> reviews) {
        this.reviews = reviews;
    }

    @Override
    public ReviewsVenueHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item_review_venue, parent, false);

        ReviewsVenueHolder reviewVenueHolder = new ReviewsVenueHolder(view);
        return reviewVenueHolder;
    }

    @Override
    public void onBindViewHolder(ReviewsVenueHolder holder, int position) {

        holder.tvRevQuote.setText(reviews.get(position).getRev_quote());
        holder.tvUsernameAndVenueName.setText(reviews.get(position).getUsername() + " @ " + reviews.get(position).getName_en());
        holder.tvrevTxt.setText(reviews.get(position).getRev_txt());
        holder.tvDate.setText(reviews.get(position).getRev_time());

        holder.rbTotalRating.setRating(reviews.get(position).getRev_totalrating());
        holder.rbValueForMoney.setRating(reviews.get(position).getRev_value());
        holder.rbTheFood.setRating(reviews.get(position).getRev_food());
        holder.rbTheDrinks.setRating(reviews.get(position).getRev_drinks());
        holder.rbTheService.setRating(reviews.get(position).getRev_service());
        holder.rbAtmosphere.setRating(reviews.get(position).getRev_decor());
        holder.rbADate.setRating(reviews.get(position).getRev_goodfordate());
        holder.rbWithFamily.setRating(reviews.get(position).getRev_goodforfamily());
        holder.rbBusinessDinner.setRating(reviews.get(position).getRev_goodforbusiness());
        holder.rbWithFriends.setRating(reviews.get(position).getRev_goodforfriends());

        if (reviews.get(position).getAvatar() != null && !reviews.get(position).getAvatar().equals("")) {
            Uri uriAvatar = Uri.parse("http://www.smartshanghai.com/uploads/avatar/" + reviews.get(position).getAvatar());
            holder.dvAvatar.setImageURI(uriAvatar);
        }else{
            holder.dvAvatar.setImageResource(R.drawable.ic_avatar_placeholder);
        }

    }

    public void addItem(ModelReviewVenue modelReviewVenue, int index) {
        reviews.add(modelReviewVenue);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        reviews.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }


}
