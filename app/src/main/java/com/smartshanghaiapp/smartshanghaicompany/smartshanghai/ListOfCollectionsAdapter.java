package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCollection;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTicket;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ListOfCollectionsAdapter extends BaseAdapter{

    private String BASE_URL_IMAGES = "http://www.smartshanghai.com/uploads/smartticket/largethumbs/";
    private ArrayList<ModelCollection> collections;
    private LayoutInflater inflater;


    public ListOfCollectionsAdapter(ArrayList<ModelCollection> collections, Context c) {
        this.collections = collections;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Fresco.initialize(c);
    }

    public void updateList(ArrayList<ModelTicket> tickets) {
        this.collections = collections;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return collections.size();
    }

    @Override
    public Object getItem(int position) {
        return collections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelCollection collection = collections.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_collection, null);

            holder.tvNameCollection = (TextView) v.findViewById(R.id.tv_custom_disp_name_of_collection);
            holder.dvImagecollection = (SimpleDraweeView) v.findViewById(R.id.dv_custom_collection);
            holder.tvSubtitleCollection = (TextView) v.findViewById(R.id.tv_custom_disp_subname_of_collection);

            v.setTag(holder);

        }else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvNameCollection.setText(collection.getTitle());
        holder.tvSubtitleCollection.setText(collection.getTeaser());

        Uri imageUri = Uri.parse(collection.getThumb());
        holder.dvImagecollection.setImageURI(imageUri);




        return v;


    }

    private static class ViewHolder {

        TextView tvNameCollection;
        TextView tvSubtitleCollection;
        DraweeView dvImagecollection;

    }

}
