package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCollectionResult;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class CollectionAdapter extends BaseAdapter {

    private ArrayList<ModelCollectionResult> collectionResults;
    private LayoutInflater inflater;
    private SharedPreferences mSharedPreferences;
    private boolean mLocAuth;

    public CollectionAdapter(ArrayList<ModelCollectionResult> collectionResults, Context c) {
        this.collectionResults = collectionResults;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Fresco.initialize(c);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        mLocAuth = mSharedPreferences.getBoolean("LocationAuth", false);


    }

    public void updateList(ArrayList<ModelCollectionResult> collectionResults) {
        this.collectionResults = collectionResults;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return collectionResults.size();
    }

    @Override
    public Object getItem(int position) {
        return collectionResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final ViewHolder holder;

        final ModelCollectionResult collectionResult = collectionResults.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_collection_item, null);

            holder.tvNameVenue = (TextView) v.findViewById(R.id.tv_custom_item_collection_item_name);
            holder.tvDistance = (TextView) v.findViewById(R.id.tv_custom_item_collection_item_distance);
            holder.tvAddressEn = (TextView) v.findViewById(R.id.tv_custom_item_collection_item_address_english);
            holder.tvAddressCn = (TextView) v.findViewById(R.id.tv_custom_item_collection_address_chinese);
            holder.tvDescriptionVenue = (TextView) v.findViewById(R.id.tv_custom_item_collection_item_description);
            holder.dvImageVenue = (DraweeView) v.findViewById(R.id.dv_custom_item_collection_item_image);
            holder.llLoc = (LinearLayout) v.findViewById(R.id.ll_custom_item_collection_item_distance);
            holder.llType0 = (LinearLayout) v.findViewById(R.id.ll_custom_item_collection_item_venue_middle);

            holder.llType1 = (LinearLayout) v.findViewById(R.id.ll_custom_item_collection_item_top_page);
            holder.tvTopTitle = (TextView) v.findViewById(R.id.tv_custom_item_collection_item_top_page_title);
            holder.tvTopDescription= (TextView) v.findViewById(R.id.tv_custom_item_collection_item_top_page_description);
            holder.tvTopDate = (TextView) v.findViewById(R.id.tv_custom_item_collection_item_top_page_date);
            holder.dvTopImage= (DraweeView) v.findViewById(R.id.dv_custom_item_collection_item_top_page_image);

            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.llType0.setVisibility(View.GONE);
        holder.llType1.setVisibility(View.GONE);


        if (collectionResult.getType() == 0) {
            holder.llType0.setVisibility(View.VISIBLE);
            holder.tvNameVenue.setText(collectionResult.getVenueName());
            holder.tvDistance.setText(collectionResult.getDisatance() + " km");
            holder.tvDescriptionVenue.setText(collectionResult.getVenueTagDescription());
            Uri imageUri = Uri.parse("http://www.smartshanghai.com/uploads/venues/tags/" + collectionResult.getVenueTagThumb());
            holder.dvImageVenue.setImageURI(imageUri);
            holder.tvAddressEn.setText(collectionResult.getAddressEnglish());
            holder.tvAddressCn.setText(collectionResult.getAddressChinese());
            if (mLocAuth && collectionResult.getDisatance().equals("no data") == false) {
                holder.llLoc.setVisibility(View.VISIBLE);
            } else {
                holder.llLoc.setVisibility(View.GONE);
            }
        } else if (collectionResult.getType() == 1) { // top of the page
            holder.llType1.setVisibility(View.VISIBLE);
            holder.tvTopTitle.setText(collectionResult.getVenueName());
            holder.tvTopDescription.setText(collectionResult.getVenueTagDescription());
            holder.tvTopDate.setText(collectionResult.getAddressEnglish()); // because we match two differnets
            Uri imageUri = Uri.parse(collectionResult.getVenueTagThumb());
            holder.dvTopImage.setImageURI(imageUri);
        }


        return v;


    }

    private static class ViewHolder {

        TextView tvNameVenue;
        TextView tvDistance;
        DraweeView dvImageVenue;
        TextView tvAddressEn;
        TextView tvAddressCn;
        TextView tvDescriptionVenue;
        LinearLayout llLoc;
        LinearLayout llType0;

        LinearLayout llType1;
        TextView tvTopTitle;
        TextView tvTopDescription;
        TextView tvTopDate;
        DraweeView dvTopImage;


    }


}
