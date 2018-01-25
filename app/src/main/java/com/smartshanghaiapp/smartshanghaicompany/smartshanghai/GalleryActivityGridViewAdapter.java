package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;

import java.util.ArrayList;

/**
 * Created by Pithou on 28/04/2016.
 */
public class GalleryActivityGridViewAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList<String> data = new ArrayList<String>();

    public GalleryActivityGridViewAdapter(Context context, int layoutResourceId, ArrayList<String> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        Fresco.initialize(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.dvImage = (DraweeView) row.findViewById(R.id.dv_custom_item_gridview_gallery_image_item);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        String item = data.get(position);



        Uri imageUri = Uri.parse("http://images.smartchina.com/image.php?id=" + item + "&width=150");
        holder.dvImage.setImageURI(imageUri);


        return row;
    }

    static class ViewHolder {
        DraweeView dvImage;
    }

}
