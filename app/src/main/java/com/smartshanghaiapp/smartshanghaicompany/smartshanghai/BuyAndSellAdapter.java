package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelArticle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelBuyAndSell;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class BuyAndSellAdapter extends BaseAdapter {

    private ArrayList<ModelBuyAndSell> mBuyAndSell;
    private LayoutInflater inflater;


    public BuyAndSellAdapter(ArrayList<ModelBuyAndSell> mBuyAndSell, Context c) {
        this.mBuyAndSell = mBuyAndSell;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(ArrayList<ModelBuyAndSell> mBuyAndSell) {
        this.mBuyAndSell = mBuyAndSell;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mBuyAndSell.size();
    }

    @Override
    public Object getItem(int position) {
        return mBuyAndSell.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelBuyAndSell modelBuyAndSell = mBuyAndSell.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_buy_and_sell, null);

            holder.tvTitle = (TextView) v.findViewById(R.id.tv_custom_item_buy_and_sell_title);
            holder.tvTimeAgo = (TextView) v.findViewById(R.id.tv_custom_item_buy_and_sell_time_ago);
            holder.tvPrice = (TextView) v.findViewById(R.id.tv_custom_item_buy_and_sell_price);
            holder.dvImage = (DraweeView) v.findViewById(R.id.dv_custom_buy_and_sell_image);

            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvTitle.setText(modelBuyAndSell.getTitle());
        holder.tvPrice.setText(modelBuyAndSell.getPrice());
        holder.tvTimeAgo.setText(modelBuyAndSell.getDatePosted());
        if (!modelBuyAndSell.getThumb().equals("")) {
            Uri imageUriBuyANdSell= Uri.parse("http://www.smartshanghai.com/uploads/buyandsell/thumbs/" + modelBuyAndSell.getThumb());
            holder.dvImage.setImageURI(imageUriBuyANdSell);
        }
        return v;


    }

    private static class ViewHolder {

        TextView tvTitle;
        TextView tvTimeAgo;
        TextView tvPrice;
        DraweeView dvImage;

    }

}
