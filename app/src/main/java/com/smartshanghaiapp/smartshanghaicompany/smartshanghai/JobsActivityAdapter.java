package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelArticle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelJob;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class JobsActivityAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<ModelJob> mJobs;
    private LayoutInflater inflater;


    public JobsActivityAdapter(ArrayList<ModelJob> mJobs, Context mContext) {
        this.mJobs = mJobs;
        this.mContext = mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(ArrayList<ModelJob> mJobs) {
        this.mJobs= mJobs;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mJobs.size();
    }

    @Override
    public Object getItem(int position) {
        return mJobs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelJob modelJob= mJobs.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_job, null);

            holder.tvTitle = (TextView) v.findViewById(R.id.tv_custom_jobs_title);
            holder.tvCompany_name= (TextView) v.findViewById(R.id.tv_jobs_company_name);
            holder.dvImage= (DraweeView) v.findViewById(R.id.iv_custom_jobs_image);

            v.setTag(holder);

        }else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvTitle.setText(modelJob.getTitle());
        holder.tvCompany_name.setText(modelJob.getCompany_name());
        if (!modelJob.getCompany_logo().equals("")) {
            holder.dvImage.setVisibility(View.VISIBLE);
            Uri imageUriJob= Uri.parse("http://www.smartshanghai.com/uploads/jobs/logos/" + modelJob.getCompany_logo());
            holder.dvImage.setImageURI(imageUriJob);
        } else {
            holder.dvImage.setVisibility(View.INVISIBLE);
        }
        return v;


    }

    private static class ViewHolder {

        TextView tvTitle;
        TextView tvCompany_name;
        DraweeView dvImage;

    }

}
