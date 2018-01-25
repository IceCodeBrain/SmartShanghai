package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.FragmentLifeCycle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceTicketsAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfTickets;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTicket;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.OnlineFrameActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.R;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class FragmentTickets extends Fragment implements FragmentLifeCycle, Callback<ModelListOfTickets> {


    private ArrayList<ModelTicket> mTickets;
    private ModelListOfTickets mListOfTickets;
    private ListView mTicketsListview;
    private FragmentTicketsAdapter mAdapter;

    private ProgressBar mProgressBarTickets;

    public final static String KEY_BUNDLE_WEB_ADDRESS = "Key_bundle_web_address";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_tickets, container, false);
        mProgressBarTickets = (ProgressBar) view.findViewById(R.id.load_frag_ticket);


        mTickets = new ArrayList<ModelTicket>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.smartticket.cn/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceTicketsAPI api = retrofit.create(InterfaceTicketsAPI.class);


        Call<ModelListOfTickets> callListOfTickets = api.getTicketsList();
        callListOfTickets.enqueue(this);


        mTicketsListview = (ListView) view.findViewById(R.id.lv_fragment_tickets);


        mTicketsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = "http://www.smartshanghai.com/smartticket/" + mTickets.get(position).getShow_urlname() + "?source=android";
                Intent i = new Intent(getActivity(), OnlineFrameActivity.class);
                i.putExtra(KEY_BUNDLE_WEB_ADDRESS, url);
                startActivity(i);

            }
        });


        return view;
    }


    @Override
    public void onPauseFragment() {


    }


    @Override
    public void onResumeFragment() {


    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public void onResponse(Response<ModelListOfTickets> response, Retrofit retrofit) {
        if (mProgressBarTickets != null) {
            mProgressBarTickets.setVisibility(View.GONE);
        }
        response.body();

        if (response.body() != null) {


            for (int i = 0; i < response.body().data.size(); i++) {
                mTickets.add(new ModelTicket(
                        response.body().data.get(i).show_name,
                        response.body().data.get(i).show_prevpix,
                        response.body().data.get(i).show_urlname,
                        response.body().data.get(i).show_datetime,
                        response.body().data.get(i).name_en

                ));
            }

        }
        if (isAdded()) {
            if (getActivity() != null) {

                mAdapter = new FragmentTicketsAdapter(mTickets, getActivity().getApplicationContext());
                mTicketsListview.setAdapter(mAdapter);
            }
        }

    }

    @Override
    public void onFailure(Throwable t) {

        if (mProgressBarTickets != null) {
            mProgressBarTickets.setVisibility(View.GONE);
        }

    }


}
