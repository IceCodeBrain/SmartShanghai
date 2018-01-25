package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChain;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class NameActivityAdapter extends BaseAdapter{

    private ArrayList<String> mLetters;
    private LayoutInflater inflater;


    public NameActivityAdapter(ArrayList<String> mLetters, Context c) {
        this.mLetters = mLetters;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(ArrayList<String> mLetters) {
        this.mLetters = mLetters;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mLetters.size();
    }

    @Override
    public Object getItem(int position) {
        return mLetters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final String letter = mLetters.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_letter, null);

            holder.tvLetter = (TextView) v.findViewById(R.id.tv_custom_item_letter);

            v.setTag(holder);

        }else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvLetter.setText(letter);


        return v;


    }

    private static class ViewHolder {

        TextView tvLetter;

    }

}
