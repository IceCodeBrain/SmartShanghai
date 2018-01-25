package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentFirstRun4 extends Fragment implements FragmentLifeCycle {

    private TextView tvStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_first_run_4, container, false);

        tvStart = (TextView) view.findViewById(R.id.but_first_run_start);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
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


}
