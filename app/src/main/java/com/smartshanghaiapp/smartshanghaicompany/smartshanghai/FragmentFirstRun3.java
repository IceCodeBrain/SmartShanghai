package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentFirstRun3 extends Fragment implements FragmentLifeCycle {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_first_run_3, container, false);




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
