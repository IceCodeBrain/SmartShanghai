package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.DatabaseDirectory;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelBuyAndSellCategory;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChain;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelChildrenArea;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelCuisine;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEssentials;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEvent;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelEventInfoFromDB;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelTagIdName;
import com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models.ModelVenue;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.DeflaterOutputStream;

/**
 * Created by Pithou on 13/04/2016.
 */
public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "smsh.sqlite";
    private static final String DB_PATH = "/data/data/com.smartshanghaiapp.smartshanghaicompany.smartshanghai/databases/";
    private SQLiteDatabase mDatabase;
    private static String TAG = "testDB4";
    private static final String URL_DL_DB = "http://www.smartshanghai.com/apps/updates/db/smsh-v3.sqlite";


    private static Database INSTANCE;
    private SQLiteDatabase mDb;
    private final Context mContext;


    public Database(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    public boolean databaseExist() {
        File dbtest = new File(DB_PATH);
        if (dbtest.exists()) {
            return true;
        } else {
            return false;
        }
    }


    public void deleteDatabase(Context context) {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            context.deleteDatabase(DB_NAME);
            Log.d(TAG, "Database has been deleted");
        } else {
            Log.d(TAG, "Database is already deleted");
            //do nothing - database already erased}
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            File file = new File(myPath);
            if (file.exists()) {
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            }

        } catch (SQLiteException e) {
            //database does't exist yet.
            Log.d(TAG, "Database doesn't exist");
        }
        if (checkDB != null) {
            checkDB.close();
            Log.d(TAG, "Database exist");
        } else {
            Log.d(TAG, "Database doesn't exist");

        }
        return checkDB != null ? true : false;
    }


    @Override
    public synchronized void close() {
        if (mDatabase != null)
            mDatabase.close();
        super.close();
    }


    public static boolean downloadDatabase(Context context) {
        try {
            Log.d(TAG, "downloading database");
            URL url = new URL(URL_DL_DB);
                        /* Open a connection to that URL. */
            URLConnection ucon = url.openConnection();
                        /*
                         * Define InputStreams to read from the URLConnection.
                         */
            int fileLength = ucon.getContentLength();
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
                        /*
                         * Read bytes to the Buffer until there is nothing more to read(-1).
                         */

            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;

            while ((current = bis.read()) != -1) {
                try {
                    baf.append((byte) current);
                } catch (Exception e){
                    Log.d(TAG, "error");
                    return false;
                }
            }

                        /* Convert the Bytes read to a String. */
            FileOutputStream fos = null;
            // Select storage location
            fos = context.openFileOutput("smsh.sqlite", Context.MODE_PRIVATE);

            fos.write(baf.toByteArray());
            fos.close();
            Log.d(TAG, "downloaded");
        } catch (IOException e) {
            Log.d(TAG, "error null pointer 1  " + e);
            return false;
        } catch (NullPointerException e) {
            Log.d(TAG, "error 2  " + e);

            return false;
        } catch (Exception e) {
            Log.d(TAG, "error 3  " + e);

            return false;
        }
        return true;
    }


    public boolean copyServerDatabase(Context context) {
        // by calling this line an empty database will be created into the default system path
        // of this app - we will then overwrite this with the database from the server

        SQLiteOpenHelper soh = new Database(context);
        SQLiteDatabase db = soh.getReadableDatabase();
        db.close();


        OutputStream os = null;
        InputStream is = null;
        try {
            Log.d(TAG, "Copying DB from server version into app");
            is = context.openFileInput("smsh.sqlite");
            os = new FileOutputStream("/data/data/com.smartshanghaiapp.smartshanghaicompany.smartshanghai/databases/smsh.sqlite"); // something weird here

            copyFile(os, is);
        } catch (Exception e) {
            Log.e(TAG, "Server Database was not found - did it download correctly?", e);
            return false;
        } finally {
            try {
                //Close the streams
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "failed to close databases");
                return false;
            }
        }


        Log.d(TAG, "Done Copying DB from server");
        return true;
    }


    private void copyFile(OutputStream os, InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        os.flush();
    }


    public void copyDataBaseFromAsset(Context ctx) throws IOException {
        SQLiteOpenHelper soh = new Database(ctx);
        SQLiteDatabase db = soh.getReadableDatabase();
        db.close();

        //Open your local db as the input stream
        InputStream myInput = ctx.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        Log.d(TAG, "Done Copying DB from assets");

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ModelVenue getAModelVenueFromId(int idVenue) {
        final String TABLE_NAME = "venues";
        String selectQuery = "SELECT name, address_en, address_cn, phone, hours, metro_station_id, cards, website,description, price, thumb,reviews_count,reviews_average, metro_distance_time, closed, map_x, map_y, chope_widget_url FROM venues WHERE smsh_id = " + idVenue;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            ModelVenue venue = new ModelVenue();
            venue.setName(cursor.getString(0));
            venue.setAddress_en(cursor.getString(1));
            venue.setAddress_cn(cursor.getString(2));
            venue.setPhone(cursor.getString(3));
            venue.setHours(cursor.getString(4));
            venue.setMetro_station_id(cursor.getInt(5));
            venue.setCard(cursor.getString(6));
            venue.setWebsite(cursor.getString(7));
            venue.setDescription(cursor.getString(8));
            venue.setPrice(cursor.getInt(9));
            venue.setThumb(cursor.getString(10));
            venue.setReviews_count(cursor.getInt(11));
            venue.setReviews_average(cursor.getFloat(12));
            venue.setMetro_distance_time(cursor.getInt(13));
            venue.setClosed(cursor.getInt(14));
            venue.setMap_x(cursor.getDouble(15));
            venue.setMap_y(cursor.getDouble(16));
            venue.setChope_widget_url(cursor.getString(17));
            return venue;

        }
        cursor.close();
        return null;
    }


    public ModelVenue getAModelVenuePositionFromId(int idVenue) {
        final String TABLE_NAME = "venues";
        String selectQuery = "SELECT smsh_id, name, address_en, map_x, map_y FROM 'venues' WHERE smsh_id = " + idVenue;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            ModelVenue venue = new ModelVenue();
            venue.setId(cursor.getInt(0));
            venue.setName(cursor.getString(1));
            venue.setAddress_en(cursor.getString(2));
            venue.setMap_x(cursor.getDouble(3));
            venue.setMap_y(cursor.getDouble(4));

            return venue;

        }
        cursor.close();
        return null;
    }

    public String getNameOfMetroStationFromIdVenue(int idVenue) {
        final String TABLE_NAME = "venues";
        String selectQuery = "SELECT name_en FROM metro_stations WHERE smsh_id = " + idVenue;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            String result;
            result = cursor.getString(0);
            return result;
        }
        cursor.close();
        return null;
    }

    public boolean checkIfVenueExist(int idVenue) {
        String selectQuery = "SELECT * FROM 'venues' WHERE smsh_id =" + idVenue;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 1){
            return true;
        } else {
            return false;
        }
    }


    public ArrayList<ModelVenue> getModelVenuesCloseWithId(double x, double y, int idParent) {
        double ecartMax = 1.0;
        double xMin = x - ecartMax;
        double xMax = x + ecartMax;
        double yMin = y - ecartMax;
        double yMax = y + ecartMax;

        String selectQuery;
        if (!isInShanghai(x,y)) {
            selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id= " + idParent + " AND closed = 0 LIMIT 20000";

        } else {
            selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id= " + idParent + " AND map_x > " + xMin + " AND map_x < " + xMax + " AND map_y > " + yMin + " AND map_y < " + yMax + " AND closed = 0 LIMIT 20000";
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> data = new ArrayList<ModelVenue>();
        if (cursor.moveToFirst()) {
            do {

                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setDescription(cursor.getString(2));
                venue.setAddress_en(cursor.getString(3));
                venue.setMap_x(cursor.getDouble(4));
                venue.setMap_y(cursor.getDouble(5));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(4), cursor.getDouble(5), x, y));
                venue.setPrice(cursor.getInt(6));
                venue.setThumb(cursor.getString(7));
                venue.setReviews_count(cursor.getInt(8));
                venue.setReviews_average(cursor.getFloat(9));
                venue.setClosed(cursor.getInt(10));
                data.add(venue);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public ArrayList<ModelVenue> getModelVenuesCloseOfCoord(double x, double y, int smshId) {
        double ecartMax = 0.004; // environt 500m
        double xMin = x - ecartMax;
        double xMax = x + ecartMax;
        double yMin = y - ecartMax;
        double yMax = y + ecartMax;

        String selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues WHERE map_x > " + xMin + " AND map_x < " + xMax + " AND map_y > " + yMin + " AND map_y < " + yMax + " AND closed = 0 AND smsh_id != " + smshId + " LIMIT 20000";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> data = new ArrayList<ModelVenue>();
        if (cursor.moveToFirst()) {
            do {

                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setDescription(cursor.getString(2));
                venue.setAddress_en(cursor.getString(3));
                venue.setMap_x(cursor.getDouble(4));
                venue.setMap_y(cursor.getDouble(5));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(4), cursor.getDouble(5), x, y));
                venue.setPrice(cursor.getInt(6));
                venue.setThumb(cursor.getString(7));
                venue.setReviews_count(cursor.getInt(8));
                venue.setReviews_average(cursor.getFloat(9));
                venue.setClosed(cursor.getInt(10));
                data.add(venue);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }



    public ArrayList<ModelVenue> getModelVenuesFromAreaWithId(double x, double y, int idParent) {
        String selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id= " + idParent;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> data = new ArrayList<ModelVenue>();
        if (cursor.moveToFirst()) {
            do {

                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setDescription(cursor.getString(2));
                venue.setAddress_en(cursor.getString(3));
                venue.setMap_x(cursor.getDouble(4));
                venue.setMap_y(cursor.getDouble(5));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(4), cursor.getDouble(5), x, y));
                venue.setPrice(cursor.getInt(6));
                venue.setThumb(cursor.getString(7));
                venue.setReviews_count(cursor.getInt(8));
                venue.setReviews_average(cursor.getFloat(9));
                venue.setClosed(cursor.getInt(10));
                data.add(venue);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public ArrayList<ModelVenue> getModelVenuesRecentlyOpened(double x, double y, int idRecentlyOpened) {
        String selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id= " + idRecentlyOpened;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> data = new ArrayList<ModelVenue>();
        if (cursor.moveToFirst()) {
            do {

                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setDescription(cursor.getString(2));
                venue.setAddress_en(cursor.getString(3));
                venue.setMap_x(cursor.getDouble(4));
                venue.setMap_y(cursor.getDouble(5));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(4), cursor.getDouble(5), x, y));
                venue.setPrice(cursor.getInt(6));
                venue.setThumb(cursor.getString(7));
                venue.setReviews_count(cursor.getInt(8));
                venue.setReviews_average(cursor.getFloat(9));
                venue.setClosed(cursor.getInt(10));
                data.add(venue);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }


    public ArrayList<ModelVenue> getModelVenuesFromChainWithId(double x, double y, int idParent) {
        String selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id= " + idParent;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> data = new ArrayList<ModelVenue>();
        if (cursor.moveToFirst()) {
            do {

                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setDescription(cursor.getString(2));
                venue.setAddress_en(cursor.getString(3));
                venue.setMap_x(cursor.getDouble(4));
                venue.setMap_y(cursor.getDouble(5));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(4), cursor.getDouble(5), x, y));
                venue.setPrice(cursor.getInt(6));
                venue.setThumb(cursor.getString(7));
                venue.setReviews_count(cursor.getInt(8));
                venue.setReviews_average(cursor.getFloat(9));
                venue.setClosed(cursor.getInt(10));
                data.add(venue);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public ArrayList<ModelVenue> getModelVenuesFromCuisineWithId(double x, double y, int idParent) {
        String selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id= " + idParent;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> data = new ArrayList<ModelVenue>();
        if (cursor.moveToFirst()) {
            do {

                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setDescription(cursor.getString(2));
                venue.setAddress_en(cursor.getString(3));
                venue.setMap_x(cursor.getDouble(4));
                venue.setMap_y(cursor.getDouble(5));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(4), cursor.getDouble(5), x, y));
                venue.setPrice(cursor.getInt(6));
                venue.setThumb(cursor.getString(7));
                venue.setReviews_count(cursor.getInt(8));
                venue.setReviews_average(cursor.getFloat(9));
                venue.setClosed(cursor.getInt(10));
                data.add(venue);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public ModelEventInfoFromDB getEventInfoDB(int idVenue) {
        String selectQuery = "SELECT name, address_en, address_cn, phone FROM 'venues' WHERE smsh_id =" + idVenue;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            ModelEventInfoFromDB modelEventInfoFromDB = new ModelEventInfoFromDB();
            modelEventInfoFromDB.setName(cursor.getString(0));
            modelEventInfoFromDB.setAddress_en(cursor.getString(1));
            modelEventInfoFromDB.setAddress_cn(cursor.getString(2));
            modelEventInfoFromDB.setPhone(cursor.getString(3));
            return modelEventInfoFromDB;
        }
        cursor.close();
        return null;
    }

    public ArrayList<ModelVenue> getModelVenuesFromLetterWithId(double x, double y, String letter) {
        String selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues WHERE name LIKE '" + letter + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> data = new ArrayList<ModelVenue>();
        if (cursor.moveToFirst()) {
            do {

                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setDescription(cursor.getString(2));
                venue.setAddress_en(cursor.getString(3));
                venue.setMap_x(cursor.getDouble(4));
                venue.setMap_y(cursor.getDouble(5));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(4), cursor.getDouble(5), x, y));
                venue.setPrice(cursor.getInt(6));
                venue.setThumb(cursor.getString(7));
                venue.setReviews_count(cursor.getInt(8));
                venue.setReviews_average(cursor.getFloat(9));
                venue.setClosed(cursor.getInt(10));
                data.add(venue);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }


    public ArrayList<ModelVenue> getModelVenuesFavorites(double x, double y, ArrayList<String> ids) {
        String selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues WHERE";
        for (int i = 0; i < ids.size(); i++) {
            if (i == 0) {
                selectQuery = selectQuery + " smsh_id = " + ids.get(i);
            } else {
                selectQuery = selectQuery + " OR smsh_id = " + ids.get(i);
            }
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> data = new ArrayList<ModelVenue>();
        if (cursor.moveToFirst()) {
            do {

                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setDescription(cursor.getString(2));
                venue.setAddress_en(cursor.getString(3));
                venue.setMap_x(cursor.getDouble(4));
                venue.setMap_y(cursor.getDouble(5));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(4), cursor.getDouble(5), x, y));
                venue.setPrice(cursor.getInt(6));
                venue.setThumb(cursor.getString(7));
                venue.setReviews_count(cursor.getInt(8));
                venue.setReviews_average(cursor.getFloat(9));
                venue.setClosed(cursor.getInt(10));
                data.add(venue);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }


    public ArrayList<ModelTagIdName> getSubCategoriesId(int idCatParent) {
        String selectQuery = "SELECT tag_id,tag_name FROM tags LEFT JOIN tag_relationships ON tag_relationships.tag_id=tags.id WHERE parent_tag_id= " + idCatParent + " AND grandparent_tag_id= 1 ORDER BY tag_name";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelTagIdName> result = new ArrayList<ModelTagIdName>();
        if (cursor.moveToFirst()) {
            do {

                ModelTagIdName tagIdName = new ModelTagIdName();
                tagIdName.setTag_id(cursor.getInt(0));
                tagIdName.setTag_name(cursor.getString(1));
                result.add(tagIdName);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public ArrayList<ModelCuisine> getSubCuisines(int idCatParent) {
        String selectQuery = "SELECT tag_id,tag_name FROM tags LEFT JOIN tag_relationships ON tag_relationships.tag_id=tags.id WHERE parent_tag_id= " + idCatParent + " ORDER BY tag_name";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelCuisine> result = new ArrayList<ModelCuisine>();
        if (cursor.moveToFirst()) {
            do {

                ModelCuisine cuisine = new ModelCuisine();
                cuisine.setId(cursor.getInt(0));
                cuisine.setName(cursor.getString(1));
                result.add(cuisine);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }


    public ArrayList<ModelChildrenArea> getChildAreaItems(int idchildAreaId) {
        String selectQuery = "SELECT tags.id, tag_name FROM tags LEFT JOIN tag_relationships ON tag_relationships.tag_id = tags.id WHERE tag_relationships.parent_tag_id='" + idchildAreaId + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelChildrenArea> result = new ArrayList<ModelChildrenArea>();
        if (cursor.moveToFirst()) {
            do {
                ModelChildrenArea modelChildrenArea = new ModelChildrenArea();
                modelChildrenArea.setId(cursor.getInt(0));
                modelChildrenArea.setName(cursor.getString(1));
                result.add(modelChildrenArea);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public ArrayList<ModelChain> getChainsItems(int idChain) {
        String selectQuery = "SELECT tags.id, tag_name FROM tags LEFT JOIN tag_relationships ON tag_relationships.tag_id = tags.id WHERE tag_relationships.parent_tag_id= " + idChain + " ORDER BY tag_name";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelChain> result = new ArrayList<ModelChain>();
        if (cursor.moveToFirst()) {
            do {
                ModelChain modelChain = new ModelChain();
                modelChain.setId(cursor.getInt(0));
                modelChain.setName(cursor.getString(1));
                result.add(modelChain);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public ArrayList<ModelBuyAndSellCategory> getCategoriesBuyAndSell() {
        String selectQuery = "SELECT * FROM tags LEFT JOIN tag_relationships ON tag_relationships.tag_id = tags.id WHERE tag_relationships.parent_tag_id=3";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelBuyAndSellCategory> result = new ArrayList<ModelBuyAndSellCategory>();
        if (cursor.moveToFirst()) {
            do {
                ModelBuyAndSellCategory modelBuyAndSellCategory = new ModelBuyAndSellCategory();
                modelBuyAndSellCategory.setId(cursor.getInt(0));
                modelBuyAndSellCategory.setTag_name(cursor.getString(1));
                modelBuyAndSellCategory.setTag_id(cursor.getInt(7));
                result.add(modelBuyAndSellCategory);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public ArrayList<ModelVenue> getModelVenuesCloseWithSubId(double x, double y, int idParent, int subId) {
        double ecartMax = 1.0;
        double xMin = x - ecartMax;
        double xMax = x + ecartMax;
        double yMin = y - ecartMax;
        double yMax = y + ecartMax;
        String selectQuery = "";

        if(isInShanghai(x,y)) {
            //if the person is in SHanghai now
            selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id IN ('" + idParent + "','" + subId + "') AND closed='0' AND map_x > " + xMin + " AND map_x < " + xMax + " AND map_y > " + yMin + " AND map_y < " + yMax + " GROUP BY venues.id HAVING COUNT (venues.id) = 2 LIMIT 1000";
        }
        else{
            // The person is abroad
            selectQuery = "SELECT smsh_id,name,description,address_en,map_x,map_y,price,thumb,reviews_count,reviews_average, closed FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id IN ('" + idParent + "','" + subId + "') AND closed='0'  GROUP BY venues.id HAVING COUNT (venues.id) = 2 LIMIT 1000";

        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> result = new ArrayList<ModelVenue>();

        if (cursor.moveToFirst()) {
            do {
                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setDescription(cursor.getString(2));
                venue.setAddress_en(cursor.getString(3));
                venue.setMap_x(cursor.getDouble(4));
                venue.setMap_y(cursor.getDouble(5));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(4), cursor.getDouble(5), x, y));
                venue.setPrice(cursor.getInt(6));
                venue.setThumb(cursor.getString(7));
                venue.setReviews_count(cursor.getInt(8));
                venue.setReviews_average(cursor.getFloat(9));
                venue.setClosed(cursor.getInt(10));
                result.add(venue);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public ArrayList<ModelVenue> getModelVenueCloseWithPositionMarkers(int idParent, int subId, double xMaxDistance, double yMaxDistance, double screenCenterX, double screenCenterY) {
        double xMin = screenCenterX - xMaxDistance;
        double xMax = screenCenterX + xMaxDistance;
        double yMin = screenCenterY - yMaxDistance;
        double yMax = screenCenterY + yMaxDistance;
        String selectQuery = null;
        if (subId == 0) {
            // NO SUB CATEGORIES
            selectQuery = "SELECT smsh_id,name,address_en,map_x,map_y FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id= " + idParent + " AND map_x > " + xMin + " AND map_x < " + xMax + " AND map_y > " + yMin + " AND map_y < " + yMax + "  LIMIT 500";

        } else if (subId > 0) {
            // THERE IS A SUB CATEGORY
            selectQuery = "SELECT smsh_id,name,address_en,map_x,map_y FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id IN ('" + idParent + "','" + subId + "') AND closed='0' AND map_x > " + xMin + " AND map_x < " + xMax + " AND map_y > " + yMin + " AND map_y < " + yMax + " GROUP BY venues.id HAVING COUNT (venues.id) = 2 LIMIT 1000";
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<ModelVenue> result = new ArrayList<ModelVenue>();

        if (cursor.moveToFirst()) {
            do {
                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setAddress_en(cursor.getString(2));
                venue.setMap_x(cursor.getDouble(3));
                venue.setMap_y(cursor.getDouble(4));

                result.add(venue);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public ArrayList<ModelVenue> getModelVenueCloseWithPositionMarkersBaiduMap(int idParent, int subId, double xMaxDistance, double yMaxDistance, double screenCenterX, double screenCenterY) {
        double xMin = screenCenterX - xMaxDistance;
        double xMax = screenCenterX + xMaxDistance;
        double yMin = screenCenterY - yMaxDistance;
        double yMax = screenCenterY + yMaxDistance;
        String selectQuery = null;
        if (subId == 0) {
            // NO SUB CATEGORIES
            selectQuery = "SELECT smsh_id,name,address_en,map_x,map_y FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id= " + idParent + " AND map_x > " + xMin + " AND map_x < " + xMax + " AND map_y > " + yMin + " AND map_y < " + yMax + "  LIMIT 500";

        } else if (subId > 0) {
            // THERE IS A SUB CATEGORY
            selectQuery = "SELECT smsh_id,name,address_en,map_x,map_y FROM venues LEFT JOIN venues_tags ON venues_tags.venue_id=venues.smsh_id WHERE venues_tags.tag_id IN ('" + idParent + "','" + subId + "') AND closed='0' AND map_x > " + xMin + " AND map_x < " + xMax + " AND map_y > " + yMin + " AND map_y < " + yMax + " GROUP BY venues.id HAVING COUNT (venues.id) = 2 LIMIT 1000";
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<ModelVenue> result = new ArrayList<ModelVenue>();

        if (cursor.moveToFirst()) {
            do {
                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setAddress_en(cursor.getString(2));
                venue.setMap_x(cursor.getDouble(3));
                venue.setMap_y(cursor.getDouble(4));

                result.add(venue);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public ArrayList<ModelEssentials> getModelsEssentials(int essentialId) {
        String selectQuery = "SELECT  title,description, smsh_venue_id FROM essentials_entries WHERE essentials_id =" + essentialId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelEssentials> result = new ArrayList<ModelEssentials>();
        if (cursor.moveToFirst()) {
            do {
                ModelEssentials modelEssentials= new ModelEssentials();
                modelEssentials.setTitle(cursor.getString(0));
                modelEssentials.setDescription(cursor.getString(1));
                modelEssentials.setSmsh_venue_id(cursor.getInt(2));
                result.add(modelEssentials);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }


    public ArrayList<ModelVenue> searchDatabaseForVenues(String search, Double x, Double y) {
        String selectQuery = null;

        SQLiteDatabase db = this.getReadableDatabase();
        search = search.replaceAll("\"",""); // this should take off any "
        selectQuery = "SELECT smsh_id,name,address_en,map_x,map_y,reviews_average,reviews_count,price, closed FROM venues WHERE name LIKE \"%" + search + "%\" ORDER BY name";
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<ModelVenue> result = new ArrayList<ModelVenue>();

        if (cursor.moveToFirst()) {
            do {
                ModelVenue venue = new ModelVenue();
                venue.setId(cursor.getInt(0));
                venue.setName(cursor.getString(1));
                venue.setAddress_en(cursor.getString(2));
                venue.setMap_x(cursor.getDouble(3));
                venue.setMap_y(cursor.getDouble(4));
                venue.setReviews_average(cursor.getFloat(5));
                venue.setReviews_count(cursor.getInt(6));
                venue.setDistance(distanceToVenueAnd(cursor.getDouble(3), cursor.getDouble(4), x, y));
                venue.setPrice(cursor.getInt(7));
                venue.setClosed(cursor.getInt(8));


                result.add(venue);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    private String distanceToVenueAnd(double venX, double venY, double userX, double userY) {
        if (userX == 0.0 && userY == 0.0){
            return "no data";
        }
        Location locVen = new Location("");
        locVen.setLatitude(venX);
        locVen.setLongitude(venY);
        Location locUser = new Location("");
        locUser.setLatitude(userX);
        locUser.setLongitude(userY);
        double distance = locUser.distanceTo(locVen);
        double distanceKm = distance / 1000;
        if (distanceKm > 1000) {
            return "no data";
        }
        double distanceFormat = (int) Math.round(distanceKm * 100) / (double) 100;
        String distanceStr = Double.toString(distanceFormat);
        return distanceStr;
    }

    private boolean isInShanghai (double x, double y){
        if (x > 28.0 && x < 34.0 && y > 118 && y < 123 ){
            return true;
        }
        else return false;
    }

}
