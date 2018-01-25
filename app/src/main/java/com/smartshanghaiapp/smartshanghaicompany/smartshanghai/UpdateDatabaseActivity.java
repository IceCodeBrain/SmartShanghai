package com.smartshanghaiapp.smartshanghaicompany.smartshanghai;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateDatabaseActivity extends Activity {

    private Button mButtonUpdate;
    private DatabaseDownloadTask downloadTask;
    private SharedPreferences mPreferences;
    private Calendar mCalendar;

    private boolean mDbExist;
    private boolean mCheckDbExist;
    private boolean mCheckDLdb;
    private boolean mCheckCopyDb;
    private boolean mDbUpdated;

    private Button mButCancel;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_database);
        mCalendar = Calendar.getInstance();

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        mButtonUpdate = (Button) findViewById(R.id.but_update_activity_update_databasse);
        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkAvailable()) {
                    downloadTask = new DatabaseDownloadTask(UpdateDatabaseActivity.this);
                    downloadTask.execute(null, null, null);

                    SharedPreferences.Editor editor = mPreferences.edit();
                    Date day1 = mCalendar.getTime();

                    editor.putString("LastUpdate", day1.toString().substring(0, 10));
                    editor.apply();
                } else {
                    Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_LONG).show();
                }

            }
        });

        mButCancel = (Button) findViewById(R.id.but_update_activity_cancel);
        mButCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private class DatabaseDownloadTask extends AsyncTask<Void, Void, Void> {
        private Context myContext;
        private ProgressDialog progressDialog;

        public DatabaseDownloadTask(Context context) {
            this.myContext = context;
        }


        @Override
        protected Void doInBackground(Void... params) {


            Database myDb = new Database(myContext);
            mDbExist = false;


            mCheckDbExist = myDb.checkDataBase();

            mCheckDLdb = myDb.downloadDatabase(myContext);
            mCheckDbExist = myDb.checkDataBase();

            if (mCheckDLdb) {
                myDb.deleteDatabase(myContext);
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("dbIsUsable", mDbExist);
                editor.apply();

                mCheckDbExist = myDb.checkDataBase();

                mCheckCopyDb = myDb.copyServerDatabase(myContext);
                mCheckDbExist = myDb.checkDataBase();

                mDbUpdated = true;
            }

            if (mCheckDbExist) {
                mDbExist = true;

            } else {
                mDbExist = false;
            }


            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(myContext);
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPreExecute();
            progressDialog.dismiss();
            SharedPreferences.Editor editor = mPreferences.edit();

            if (mDbExist && mDbUpdated) {
                    Toast.makeText(myContext, "Database Updated", Toast.LENGTH_LONG).show();
                    long yourmilliseconds = System.currentTimeMillis();
                    editor.putLong("lastTimeDBDonwload", yourmilliseconds);
            } else {
                Toast.makeText(myContext, "Problem updating the database", Toast.LENGTH_LONG).show();
            }

            editor.putBoolean("dbIsUsable", mDbExist);
            editor.apply();



        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
