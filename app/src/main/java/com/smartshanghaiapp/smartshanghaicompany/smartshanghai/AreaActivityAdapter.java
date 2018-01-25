package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChildrenArea;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTicket;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class AreaActivityAdapter extends BaseAdapter {

    private ArrayList<ModelChildrenArea> childrenAreas;
    private LayoutInflater inflater;


    public AreaActivityAdapter(ArrayList<ModelChildrenArea> childrenAreas, Context c) {
        this.childrenAreas = childrenAreas;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(ArrayList<ModelChildrenArea> childrenAreas) {
        this.childrenAreas = childrenAreas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return childrenAreas.size();
    }

    @Override
    public Object getItem(int position) {
        return childrenAreas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelChildrenArea childrenArea = childrenAreas.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_area_children, null);

            holder.tvNameChild = (TextView) v.findViewById(R.id.tv_custom_item_name_childrens_area);
            holder.ivAreaArrow = (ImageView) v.findViewById(R.id.iv_custom_item_name_childrens_area_arrow);

            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }


        if (childrenArea.getId() == -1) {
            holder.tvNameChild.setTextSize(23);
            holder.tvNameChild.setTypeface(null, Typeface.BOLD);
            holder.ivAreaArrow.setVisibility(View.GONE);
        } else {
            holder.tvNameChild.setTextSize(17);
            holder.tvNameChild.setTypeface(null, Typeface.NORMAL);
            holder.ivAreaArrow.setVisibility(View.VISIBLE);
        }

        holder.tvNameChild.setText(childrenArea.getName());


        return v;


    }

    private static class ViewHolder {

        TextView tvNameChild;
        ImageView ivAreaArrow;

    }

}
