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
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChain;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ArticlesActivityAdapter extends BaseAdapter{

    private ArrayList<ModelArticle> mArticles;
    private LayoutInflater inflater;


    public ArticlesActivityAdapter(ArrayList<ModelArticle> mArticles, Context c) {
        this.mArticles = mArticles;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(ArrayList<ModelArticle> mArticles) {
        this.mArticles= mArticles;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mArticles.size();
    }

    @Override
    public Object getItem(int position) {
        return mArticles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelArticle modelArticle= mArticles.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_article, null);

            holder.tvTitle = (TextView) v.findViewById(R.id.tv_custom_item_article_title);
            holder.tvDescription= (TextView) v.findViewById(R.id.tv_custom_item_article_description);
            holder.dvImage= (DraweeView) v.findViewById(R.id.dv_custom_item_article_image);

            v.setTag(holder);

        }else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvTitle.setText(modelArticle.getTitle());
        holder.tvDescription.setText(modelArticle.getTeaser());
        Uri imageUriArticle = Uri.parse(modelArticle.getCoverImage());
        holder.dvImage.setImageURI(imageUriArticle);

        return v;


    }

    private static class ViewHolder {

        TextView tvTitle;
        TextView tvDescription;
        DraweeView dvImage;

    }

}
