package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCuisine;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class CuisineActivityAdapter extends BaseAdapter{

    private ArrayList<ModelCuisine> mCuisines;
    private LayoutInflater inflater;


    public CuisineActivityAdapter(ArrayList<ModelCuisine> mCuisines, Context c) {
        this.mCuisines = mCuisines;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(ArrayList<ModelCuisine> mCuisines) {
        this.mCuisines = mCuisines;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCuisines.size();
    }

    @Override
    public Object getItem(int position) {
        return mCuisines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelCuisine modelCuisine = mCuisines.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_cuisine, null);

            holder.tvNameCuisine = (TextView) v.findViewById(R.id.tv_custom_item_name_cuisine);

            v.setTag(holder);

        }else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvNameCuisine.setText(modelCuisine.getName());


        return v;


    }

    private static class ViewHolder {

        TextView tvNameCuisine;

    }

}
