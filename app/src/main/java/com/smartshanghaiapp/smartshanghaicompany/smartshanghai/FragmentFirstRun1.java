package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

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

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfaceTicketsAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.FragmentTicketsAdapter;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage.MainActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelListOfTickets;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTicket;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class FragmentFirstRun1 extends Fragment implements FragmentLifeCycle {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_first_run_1, container, false);




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




}
