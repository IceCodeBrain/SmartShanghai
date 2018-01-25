package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage;

import android.content.Context;
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
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTicket;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.R;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class FragmentTicketsAdapter extends BaseAdapter{

    private String BASE_URL_IMAGES = "http://www.smartshanghai.com/uploads/smartticket/largethumbs/";
    private ArrayList<ModelTicket> tickets;
    private LayoutInflater inflater;


    public FragmentTicketsAdapter(ArrayList<ModelTicket> tickets, Context c) {
        this.tickets = tickets;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Fresco.initialize(c);
    }

    public void updateList(ArrayList<ModelTicket> tickets) {
        this.tickets = tickets;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public Object getItem(int position) {
        return tickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final ViewHolder holder;

        final ModelTicket ticket = tickets.get(position);


        if (v == null) {
            holder = new ViewHolder();

            v = inflater.inflate(R.layout.custom_item_ticket, null);

            holder.tvNameTicket = (TextView) v.findViewById(R.id.tv_custom_item_ticket_name);
            holder.dvImageTicket = (SimpleDraweeView) v.findViewById(R.id.iv_custom_ticket);
            holder.tvNamevenue= (TextView) v.findViewById(R.id.tv_custom_item_ticket_venue_name);
            holder.tvDate = (TextView) v.findViewById(R.id.tv_custom_item_ticket_date);

            v.setTag(holder);

        }else {
            holder = (ViewHolder) v.getTag();
        }


        holder.tvNameTicket.setText(ticket.getShow_name());

        Uri imageUri = Uri.parse("http://www.smartticket.cn/uploads/smartticket/thumbs/" + ticket.getShow_prevpix());
        holder.dvImageTicket.setImageURI(imageUri);

        holder.tvNamevenue.setText(" @ " + ticket.getName_en());
        holder.tvDate.setText(ticket.getShow_datetime());




        return v;


    }

    private static class ViewHolder {

        DraweeView dvImageTicket;
        TextView tvNameTicket;
        TextView tvNamevenue;
        TextView tvDate;

    }

}
