package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisteredActivity extends Activity {

    private Button mNextBut;
    private TextView mTvVerification;

    private String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        mEmail = getIntent().getExtras().getString(RegisterActivity.KEY_BUNDLE_MAIL_REGISTER_REGISTRER);

        mNextBut = (Button) findViewById(R.id.but_registered_activity_next);
        mTvVerification = (TextView) findViewById(R.id.tv_registered_activity_verification);

        String toDisp = getResources().getString(R.string.tv_registered_activity_verification_part_1) + " "
                + mEmail
                + getResources().getString(R.string.tv_registered_activity_verification_part_2);

        mTvVerification.setText(toDisp);

        mNextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
