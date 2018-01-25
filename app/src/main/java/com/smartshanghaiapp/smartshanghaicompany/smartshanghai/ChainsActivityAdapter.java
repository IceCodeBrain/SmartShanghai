package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChain;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChildrenArea;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ChainsActivityAdapter extends BaseAdapter{

    private ArrayList<ModelChain> mChains;
    private LayoutInflater inflater;


    public ChainsActivityAdapter(ArrayList<ModelChain> mChains, Context c) {
        this.mChains = mChains;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(ArrayList<ModelChain> mChains) {
        this.mChains = mChains;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mChains.size();
    }

    @Override
    public Object getItem(int position) {
        return mChains.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelChain modelChain = mChains.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_chain, null);

            holder.tvNameChain = (TextView) v.findViewById(R.id.tv_custom_item_name_chain);

            v.setTag(holder);

        }else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvNameChain.setText(modelChain.getName());


        return v;


    }

    private static class ViewHolder {

        TextView tvNameChain;

    }

}
