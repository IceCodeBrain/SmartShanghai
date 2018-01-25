package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AskSignInActivity extends Activity {

    private Button mButSignIn;
    private Button mButRegister;
    private Button mButReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_sign_in);

        mButRegister = (Button) findViewById(R.id.but_ask_sign_in_register);
        mButSignIn= (Button) findViewById(R.id.but_ask_sign_in_sign_in);
        mButReturn= (Button) findViewById(R.id.but_ask_sign_in_cancel);

        mButSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AskSignInActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        mButRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AskSignInActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        mButReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
