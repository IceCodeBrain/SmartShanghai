package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEvent;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTicket;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ListOfEventsAdapter extends BaseAdapter {

    private String BASE_URL_IMAGES = "http://www.smartshanghai.com/uploads/smartticket/largethumbs/";
    private ArrayList<ModelEvent> events;
    private LayoutInflater inflater;
    private ArrayList<String> listOfStringDateAlreadyDisplayed;
    private boolean contain;
    private int mCount;
    private boolean fromFeed;
    private String image;
    private String title;
    private boolean mNeedTopDate;


    public ListOfEventsAdapter(ArrayList<ModelEvent> events, Context c, boolean fromFeed, String image, String title, boolean mNeedTopDate) {
        this.events = events;
        this.mCount = 0;
        this.fromFeed = fromFeed;
        this.image = image;
        this.title = title;
        this.mNeedTopDate = mNeedTopDate;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Fresco.initialize(c);
        listOfStringDateAlreadyDisplayed = new ArrayList<String>();


    }

    public void updateList(ArrayList<ModelTicket> tickets) {
        this.events = events;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final ViewHolder holder;

        events = removeUselessesDates(events);


        final ModelEvent event = events.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_event, null);

            holder.tvNameEvent = (TextView) v.findViewById(R.id.tv_custom_list_of_events_disp_name_of_event);
            holder.tvVenueName = (TextView) v.findViewById(R.id.tv_custom_list_of_events_disp_venue_name);
            holder.tvDescription = (TextView) v.findViewById(R.id.tv_custom_list_of_events_disp_description);
            holder.tvDistance = (TextView) v.findViewById(R.id.tv_custom_list_of_events_disp_distance_venue);
            holder.tvDateFeatured = (TextView) v.findViewById(R.id.tv_custom_list_of_events_disp_date_start);
            holder.dvImageEvent = (SimpleDraweeView) v.findViewById(R.id.iv_custom_list_of_events_disp_img);
            holder.rlEventDate = (RelativeLayout) v.findViewById(R.id.rl_custom_list_of_events_disp_date_event_start);
            holder.llEvent = (LinearLayout) v.findViewById(R.id.ll_custom_list_of_events);

            holder.llEventdisp = (LinearLayout) v.findViewById(R.id.ll_event_item_event_disp);

            holder.dvTopFromFeed = (DraweeView) v.findViewById(R.id.dv_event_item_top_image_from_feed);
            holder.tvTopTitleFromFeed = (TextView) v.findViewById(R.id.tv_event_item_top_image_from_feed_title);
            holder.rlTopFromFeed = (RelativeLayout) v.findViewById(R.id.rl_event_item_top_image_from_feed);

            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.llEventdisp.setVisibility(View.GONE);
        holder.rlTopFromFeed.setVisibility(View.GONE);

        if (position == 0 && fromFeed) { // CASE FROMFEED FIRST IMAGE
            holder.llEventdisp.setVisibility(View.GONE);
            holder.rlTopFromFeed.setVisibility(View.VISIBLE);
            Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/feed/" + image);
            holder.dvTopFromFeed.setImageURI(imageUri);
            holder.tvTopTitleFromFeed.setText(title);
        } else {

            holder.llEventdisp.setVisibility(View.VISIBLE);
            holder.rlTopFromFeed.setVisibility(View.GONE);

            if (mCount % 2 == 0 || mCount == 0) {
                holder.llEvent.setBackgroundColor(Color.parseColor("#EDEDED"));
            } else {
                holder.llEvent.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            mCount++;

            holder.tvNameEvent.setText(event.getName());
            holder.tvVenueName.setText(event.getVenue_name());
            String distance = distanceToVenueAnd(event.getMap_x(), event.getMap_y(), MainActivity.mXGooglePosition, MainActivity.mYGooglePosition);
            if (!distance.equals("no data")) {
                holder.tvDistance.setText(" (" + distance + "km)");
            } else {
                holder.tvDistance.setVisibility(View.GONE);
            }


            holder.tvDescription.setText(event.getDescription_short_nohtml());


            if (event.getDate_featured() != null && mNeedTopDate == true) {
                holder.rlEventDate.setVisibility(View.VISIBLE);
                holder.tvDateFeatured.setVisibility(View.VISIBLE);
                holder.tvDateFeatured.setText(event.getDate_featured());
            } else {
                holder.rlEventDate.setVisibility(View.GONE);
                holder.tvDateFeatured.setVisibility(View.GONE);

            }


            Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/events/thumbs/" + event.getPrevpix());
            holder.dvImageEvent.setImageURI(imageUri);

        }
        return v;


    }

    private static class ViewHolder {

        TextView tvDateFeatured;
        TextView tvNameEvent;
        TextView tvVenueName;
        TextView tvDistance;
        DraweeView dvImageEvent;
        RelativeLayout rlEventDate;
        TextView tvDescription;
        LinearLayout llEvent;
        LinearLayout llEventdisp;

        DraweeView dvTopFromFeed;
        RelativeLayout rlTopFromFeed;
        TextView tvTopTitleFromFeed;

    }


    public ArrayList<ModelEvent> removeUselessesDates(ArrayList<ModelEvent> listOfModelEvents) {
        ArrayList<String> alreadyThere = new ArrayList<String>();
        for (int i = 0; i < listOfModelEvents.size(); i++) {
            if (alreadyThere.contains(listOfModelEvents.get(i).getDate_featured())) {
                listOfModelEvents.get(i).setDate_featured(null);
            } else {
                alreadyThere.add(listOfModelEvents.get(i).getDate_featured());
            }
        }
        return listOfModelEvents;
    }

    private String distanceToVenueAnd(double venX, double venY, double userX, double userY) {
        Location locVen = new Location("");
        locVen.setLatitude(venX);
        locVen.setLongitude(venY);
        Location locUser = new Location("");
        locUser.setLatitude(userX);
        locUser.setLongitude(userY);
        double distance = locUser.distanceTo(locVen);
        double distanceKm = distance / 1000;
        if (distanceKm > 1000) {
            return "no data";
        }
        double distanceFormat = (int) Math.round(distanceKm * 100) / (double) 100;
        String distanceStr = Double.toString(distanceFormat);
        return distanceStr;
    }

}
