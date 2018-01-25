package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChildrenArea;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEssentials;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class MoreListAdapter extends BaseAdapter {

    private ArrayList<ModelEssentials> essentials;
    private LayoutInflater inflater;


    public MoreListAdapter(ArrayList<ModelEssentials> essentials, Context c) {
        this.essentials = essentials;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(ArrayList<ModelEssentials> essentials) {
        this.essentials = essentials;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return essentials.size();
    }

    @Override
    public Object getItem(int position) {
        return essentials.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelEssentials essential= essentials.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_essentials, null);

            holder.tvTitle= (TextView) v.findViewById(R.id.tv_custom_item_essential_title);
            holder.tvDescription= (TextView) v.findViewById(R.id.tv_custom_item_essential_description);
            holder.tvViewListing= (TextView) v.findViewById(R.id.tv_custom_item_essential_button_view_listing);

            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvTitle.setText(essential.getTitle());
        holder.tvDescription.setText(essential.getDescription());
        if (essential.getSmsh_venue_id() > 0){
            holder.tvViewListing.setVisibility(View.VISIBLE);
        }else{
            holder.tvViewListing.setVisibility(View.GONE);
        }


        return v;


    }

    private static class ViewHolder {

        TextView tvTitle;
        TextView tvDescription;
        TextView tvViewListing;

    }

}
