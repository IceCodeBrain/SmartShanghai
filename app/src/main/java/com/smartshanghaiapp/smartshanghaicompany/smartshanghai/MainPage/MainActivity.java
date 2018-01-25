package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MainPage;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.AppFeedbackActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.BookmarksActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory.Database;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DirectoryListing;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.FirstRunActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.FragmentLifeCycle;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Helper.InterfacePostFeedbackAPI;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.InterfacePostCheckUpdate;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelResponseUpdate;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.MoreActivity;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.R;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.TinyDB;

import io.fabric.sdk.android.Fabric;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends FragmentActivity implements EasyPermissions.PermissionCallbacks {


    private Button mButton1Feed;
    private Button mButton2Directory;
    private Button mButton3Events;
    private Button mButton4Tickets;
    private Button mButton5More;

    public static ImageView mImageFeed;
    public static ImageView mImageDirectory;
    public static ImageView mImageEvents;
    public static ImageView mImageTickets;
    public static ImageView mImageMore;

    public static TextView mTvFeed;
    public static TextView mTvDirectory;
    public static TextView mTvEvents;
    public static TextView mTvTickets;
    public static TextView mTvMore;

    private TextView tvTitleMain;

    private SharedPreferences mPreferences;

    private LocationManager lm;

    private boolean mNeedUpdate;

    private DatabaseDownloadTask downloadTask;

    private Calendar mCalendar;

    private Dialog mDialog;
    private Dialog mDialogRating;
    private View v;
    private RelativeLayout mRlLogoSmsh;

    private String mStringLinkUpdate;

    public static LinearLayout mLlBottomBar;
    public static LinearLayout mLlTopBar;

    private PagerAdapterMainActivity pageAdapter;

    private final static int MY_PERMISSIONS_REQUEST_LOCATION = 10;

    public static double mXuser;
    public static double mYuser;

    private int iPreventBug;

    private boolean mLocAuth;

    public static double mXGooglePosition;
    public static double mYGooglePosition;


    private ImageView mIvTopRight;

    private ArrayList<String> mTitles;

    private LocationListener locationListener;

    ViewPager mPager = null;

    private ArrayList<Integer> mImageRessources;

    public static boolean mConnectionAvailable;

    public final static String query_come_from_fav = "Key_bundle_fav_from_main";
    public final static String query_fav_id = "Key_bundle_fav_id";

    private Database mDatabase;

    private boolean mFirstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        Fresco.initialize(this);

        setContentView(R.layout.activity_main);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mFirstRun = mPreferences.getBoolean("firstrun", true);

        if (mFirstRun) {
            Intent i = new Intent(MainActivity.this, FirstRunActivity.class);
            startActivity(i);
        }
        pageAdapter = new PagerAdapterMainActivity(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.myViewPager);
        mPager.setAdapter(pageAdapter);

        iPreventBug = 0;
        if (!mFirstRun && checkOneWeekUpdate()) {

            SharedPreferences.Editor editor = mPreferences.edit();
            long yourmilliseconds = System.currentTimeMillis();
            editor.putLong("lastTimeCheckUpdate", yourmilliseconds);
            editor.apply();

            //    checkNewVersion(this);
// FOR NOW WE DISABLED THISFEATURE

        }


        mTitles = new ArrayList<String>();
        mTitles.add("Feed");
        mTitles.add("Directory");
        mTitles.add("Events");
        mTitles.add("Tickets");
        mTitles.add("Essentials");

        mImageRessources = new ArrayList<Integer>();
        mImageRessources.add(R.drawable.ic_bookmarks);
        mImageRessources.add(R.drawable.ic_favs_co);
        mImageRessources.add(R.drawable.blank);
        mImageRessources.add(R.drawable.blank);
        mImageRessources.add(R.drawable.blank);


        mImageFeed = (ImageView) findViewById(R.id.iv_main_activity_bar_feed);
        mImageDirectory = (ImageView) findViewById(R.id.iv_main_activity_bar_directory);
        mImageEvents = (ImageView) findViewById(R.id.iv_main_activity_bar_events);
        mImageTickets = (ImageView) findViewById(R.id.iv_main_activity_bar_ticket);
        mImageMore = (ImageView) findViewById(R.id.iv_main_activity_bar_more);

        mTvFeed = (TextView) findViewById(R.id.tv_main_activity_bar_feed);
        mTvDirectory = (TextView) findViewById(R.id.tv_main_activity_bar_directory);
        mTvEvents = (TextView) findViewById(R.id.tv_main_activity_bar_events);
        mTvTickets = (TextView) findViewById(R.id.tv_main_activity_bar_tickets);
        mTvMore = (TextView) findViewById(R.id.tv_main_activity_bar_more);

        tvTitleMain = (TextView) findViewById(R.id.frag_feed_smart_shanghai);

        mLlBottomBar = (LinearLayout) findViewById(R.id.ll_choose_frag_bar);

        mLlTopBar = (LinearLayout) findViewById(R.id.rl_main_top_bar);


        mIvTopRight = (ImageView) findViewById(R.id.iv_main_activity_top_right);
        mIvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPager.getCurrentItem() == 0) {
                    Intent i = new Intent(MainActivity.this, BookmarksActivity.class);
                    startActivity(i);
                } else if (mPager.getCurrentItem() == 1) {
                    TinyDB tinyDB = new TinyDB(getBaseContext());
                    ArrayList<String> fav = new ArrayList<>();
                    fav = tinyDB.getListString("favoriteListIdVenues");
                    if (fav.size() > 0) {
                        Intent i = new Intent(MainActivity.this, DirectoryListing.class);
                        i.putExtra(query_fav_id, -1); // -1 because no need of id
                        i.putExtra(query_come_from_fav, "Favorites");
                        i.putExtra("LISTING_FROM", "favorite");
                        startActivity(i);
                    } else {
                        Toast.makeText(getBaseContext(), "Your have no favorite venues", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        mRlLogoSmsh = (RelativeLayout) findViewById(R.id.rl_main_logo_smsh);
        mRlLogoSmsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MoreActivity.class);
                startActivity(i);
            }
        });


        mButton1Feed = (Button) findViewById(R.id.button1_feed);
        mButton1Feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(0);
            }
        });

        mButton2Directory = (Button) findViewById(R.id.button2_directory);
        mButton2Directory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(1);
            }
        });

        mButton3Events = (Button) findViewById(R.id.button3_events);
        mButton3Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(2);
            }
        });

        mButton4Tickets = (Button) findViewById(R.id.button4_tickets);
        mButton4Tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(3);
            }
        });

        mButton5More = (Button) findViewById(R.id.button5_more);
        mButton5More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(4);
            }
        });


        mPager.setOnPageChangeListener(pageChangeListener);

        if (isNetworkAvailable()) {
            mPager.setCurrentItem(0);
            mConnectionAvailable = true;

        } else {
            mPager.setCurrentItem(1);
            mConnectionAvailable = false;
        }


        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mXuser = location.getLatitude();
                mYuser = location.getLongitude();

                mXGooglePosition = mXuser - 0.00184;
                mYGooglePosition = mYuser + 0.004353;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

// DEAL WITH THE POP UP ASKING TO RATE THE APP
        int countLaunch = 0;
        countLaunch = mPreferences.getInt("countLaunch", 0);
        countLaunch++;
        mPreferences.edit().putInt("countLaunch", countLaunch).commit();
        if (countLaunch == 10) {
            displayDialogRate(v);
        }


    }


    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        int currentPosition = 0;


        @Override
        public void onPageSelected(int newPosition) {


            FragmentLifeCycle fragmentToHide = (FragmentLifeCycle) pageAdapter.getItem(currentPosition);
            fragmentToHide.onPauseFragment();

            FragmentLifeCycle fragmentToShow = (FragmentLifeCycle) pageAdapter.getItem(newPosition);
            fragmentToShow.onResumeFragment();

            currentPosition = newPosition;

            tvTitleMain.setText(mTitles.get(currentPosition));
            mIvTopRight.setImageResource(mImageRessources.get(currentPosition));

            setCurrentImageAndColor(currentPosition);

        }


        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    };


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirstRun = mPreferences.getBoolean("firstrun", true);
        if (!mFirstRun) {
            if (iPreventBug < 3) {
                locationTask();
            } else if (checkLocationPermission()) {
                locationTask();
            }
        }

        // DOWNLOAD THE DB
        if (checkWifi() && checkOneWeekDb() && !mFirstRun) {
            SharedPreferences.Editor editor = mPreferences.edit();
            long myTime = System.currentTimeMillis();
            editor.putLong("lastTimeDBDonwload", myTime);
            editor.apply();


            downloadTask = new DatabaseDownloadTask(MainActivity.this);
            downloadTask.execute(null, null, null);
        }

    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(MY_PERMISSIONS_REQUEST_LOCATION)
    public void locationTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Have permission, do the thing!

            if (lm.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 15000, 0, locationListener);
            }

            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean("LocationAuth", true);
            editor.apply();

        } else {
            // Ask for one permission
            if (iPreventBug < 1) {
                EasyPermissions.requestPermissions(this, "this app needs the location to show you nearby venues",
                        MY_PERMISSIONS_REQUEST_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);

                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("LocationAuth", false);
                editor.apply();
                iPreventBug++;
            }


        }
    }

    private boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = getApplication().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public void setPagerToTickets() {
        if (mPager != null) {
            mPager.setCurrentItem(3);
        }
    }

    public void setPagerToEssentials() {
        if (mPager != null) {
            mPager.setCurrentItem(4);
        }
    }

    private boolean checkOneWeekDb() {
        long lastTimeMill = mPreferences.getLong("lastTimeDBDonwload", 0);
        long today = System.currentTimeMillis();
        long difference = today - lastTimeMill;
        if (difference > 604800000) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkOneWeekUpdate() {
        long lastTimeMill = mPreferences.getLong("lastTimeCheckUpdate", 0);
        long today = System.currentTimeMillis();
        long difference = today - lastTimeMill;
        if (difference > 604800000) {
            return true;
        } else {
            return false;
        }
    }

    private Dialog createCustomDialogAskUpdate() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_layout_ask_update_app);

        Button butCancel = (Button) dialog.findViewById(R.id.but_dialog_ask_update_cancel);
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    private Dialog createCustomDialogAskRating() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog_ask_rating);


        RelativeLayout rlRatingYes = (RelativeLayout) dialog.findViewById(R.id.rl_custom_dialog_yes);
        RelativeLayout rlRatingNo = (RelativeLayout) dialog.findViewById(R.id.rl_custom_dialog_no);
        RelativeLayout rlRatingDontAsk = (RelativeLayout) dialog.findViewById(R.id.rl_custom_dialog_dont_ask_again);

        rlRatingYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" + getApplication().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                }
                dialog.dismiss();
            }
        });

        rlRatingNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AppFeedbackActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });


        rlRatingDontAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public void displayDialogRate(View view) {
        mDialogRating = createCustomDialogAskRating();
        mDialogRating.show();
    }


    private void checkNewVersion(Context ctx) {
        String versionNumber = "no version detected";
        try {
            versionNumber = ctx.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.smartshanghai.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePostCheckUpdate apiUpdate = retrofit.create(InterfacePostCheckUpdate.class);
        Call<ModelResponseUpdate> callUpdate = apiUpdate.postUpdateRequest(
                getResources().getString(R.string.key_webservices_android),
                versionNumber
        );

        callUpdate.enqueue(new Callback<ModelResponseUpdate>() {
                               @Override
                               public void onResponse(Response<ModelResponseUpdate> response, Retrofit retrofit) {
                                   response.body();
                                   response.message();

                                   if (response.body().getData().isMostRecent() == false) {
                                       mNeedUpdate = true;
                                       mStringLinkUpdate = response.body().getData().getAbsoluteFilePath();

                                       mDialog = createCustomDialogAskUpdate();
                                       mDialog.show();

                                   }
                               }

                               @Override
                               public void onFailure(Throwable t) {
                                   mNeedUpdate = false;
                               }
                           }

        );

    }

    private boolean checkWifi() {
        ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiCheck = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiCheck.isConnected();
    }


    private class DatabaseDownloadTask extends AsyncTask<Void, Void, Void> {
        private Context myContext;
        private boolean mDbExist;
        private boolean mCheckDbExist;
        private boolean mCheckDLdb;
        private boolean mCheckCopyDb;
        private boolean mDbUpdated;

        public DatabaseDownloadTask(Context context) {
            this.myContext = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(myContext);


            Database myDb = new Database(myContext);
            mDbExist = myDb.checkDataBase();


            mCheckDbExist = myDb.checkDataBase();

            mCheckDLdb = myDb.downloadDatabase(myContext);
            mCheckDbExist = myDb.checkDataBase();

            if (mCheckDLdb) {
                myDb.deleteDatabase(myContext);
                mDbExist = myDb.checkDataBase();
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
            Toast.makeText(myContext, "Database trying to update...", Toast.LENGTH_LONG).show();
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPreExecute();
            SharedPreferences.Editor editor = mPreferences.edit();

            if (mDbExist && mDbUpdated) {
                Toast.makeText(myContext, "Database Updated", Toast.LENGTH_LONG).show();
                long yourmilliseconds = System.currentTimeMillis();
                editor.putLong("lastTimeDBDonwload", yourmilliseconds);
            }

            editor.putBoolean("dbIsUsable", mDbExist);
            editor.apply();

        }

    }

    private void setCurrentImageAndColor(int current) {
        mImageFeed.setImageResource(R.drawable.ic_feed);
        mImageDirectory.setImageResource(R.drawable.ic_directory);
        mImageEvents.setImageResource(R.drawable.ic_events);
        mImageTickets.setImageResource(R.drawable.ic_tickets);
        mImageMore.setImageResource(R.drawable.ic_more);
        mTvFeed.setTextColor(getResources().getColor(R.color.greyMainActivityNoSelected));
        mTvDirectory.setTextColor(getResources().getColor(R.color.greyMainActivityNoSelected));
        mTvEvents.setTextColor(getResources().getColor(R.color.greyMainActivityNoSelected));
        mTvTickets.setTextColor(getResources().getColor(R.color.greyMainActivityNoSelected));
        mTvMore.setTextColor(getResources().getColor(R.color.greyMainActivityNoSelected));

        if (current == 0) {
            mImageFeed.setImageResource(R.drawable.ic_feedfocused);
            mTvFeed.setTextColor(getResources().getColor(R.color.colorSmartShanghaiBlue));
        } else if (current == 1) {
            mImageDirectory.setImageResource(R.drawable.ic_directoryfocused);
            mTvDirectory.setTextColor(getResources().getColor(R.color.colorSmartShanghaiBlue));
        } else if (current == 2) {
            mImageEvents.setImageResource(R.drawable.ic_eventfocused);
            mTvEvents.setTextColor(getResources().getColor(R.color.colorSmartShanghaiBlue));
        } else if (current == 3) {
            mImageTickets.setImageResource(R.drawable.ic_ticketsfocused);
            mTvTickets.setTextColor(getResources().getColor(R.color.colorSmartShanghaiBlue));
        } else if (current == 4) {
            mImageMore.setImageResource(R.drawable.ic_morefocused);
            mTvMore.setTextColor(getResources().getColor(R.color.colorSmartShanghaiBlue));
        }


    }

}
