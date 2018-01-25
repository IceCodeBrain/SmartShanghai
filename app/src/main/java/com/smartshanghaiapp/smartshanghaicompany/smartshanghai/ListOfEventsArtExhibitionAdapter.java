package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEvent;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTicket;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ListOfEventsArtExhibitionAdapter extends BaseAdapter {

    private ArrayList<ModelEvent> events;
    private LayoutInflater inflater;
    private boolean contain;


    public ListOfEventsArtExhibitionAdapter(ArrayList<ModelEvent> events, Context c) {
        this.events = events;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Fresco.initialize(c);

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



        final ModelEvent event = events.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_event_art_exhibition, null);

            holder.tvNameEvent = (TextView) v.findViewById(R.id.tv_custom_list_of_events_art_exhibition_title);
            holder.dvImageEvent = (DraweeView) v.findViewById(R.id.dv_custom_list_of_events_art_exhibition_img);
            holder.tvDateHuman = (TextView) v.findViewById(R.id.tv_custom_list_of_events_art_exhibition_date);
            holder.tvDescription= (TextView) v.findViewById(R.id.tv_custom_list_of_events_art_exhibition_description);
            holder.tvDistanceEvent= (TextView) v.findViewById(R.id.tv_custom_list_of_events_art_exhibition_distance);
            holder.tvVenueName= (TextView) v.findViewById(R.id.tv_custom_list_of_events_art_exhibition_venue_name);
            holder.ivDistance = (ImageView) v.findViewById(R.id.iv_custom_list_of_events_art_exhibition_distance);


            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvNameEvent.setText(event.getName());
        holder.tvDateHuman.setText(event.getDate_human());
        holder.tvDescription.setText(event.getDescription_short_nohtml());
        holder.tvVenueName.setText(event.getVenue_name());
        String distance = distanceToVenueAnd(MainActivity.mXuser, MainActivity.mYuser,event.getMap_x(),event.map_y);
        if (!distance.equals("no data")){
            holder.tvDistanceEvent.setText(distance + " km");
        }else{
            holder.tvDistanceEvent.setVisibility(View.GONE);
            holder.ivDistance.setVisibility(View.GONE);
        }

        Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/events/thumbs/" + event.getPrevpix());
        holder.dvImageEvent.setImageURI(imageUri);


        return v;


    }

    private static class ViewHolder {

        TextView tvDateHuman;
        TextView tvNameEvent;
        TextView tvDescription;
        DraweeView dvImageEvent;
        TextView tvDistanceEvent;
        TextView tvVenueName;
        ImageView ivDistance;

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
        if (distanceKm > 1000){
            return "no data";
        }
        double distanceFormat = (int) Math.round(distanceKm * 100) / (double) 100;
        String distanceStr = Double.toString(distanceFormat);
        return distanceStr;
    }

}
