package ottplatform.show.com;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MyApp extends Application {

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;
    static SQLiteDatabase database;


    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences("my", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        database=openOrCreateDatabase("my",MODE_PRIVATE,null);
        database.execSQL("create table if not exists Watchlist(id integer primary key autoincrement, video_path text)");
    }

    public static void setvideo(String s) {
        editor.putString("s", s).commit();
    }

    public static String getvideo() {
        return sharedPreferences.getString("s", "");
    }

    public static void setTitle(String t) {
        editor.putString("t", t).commit();
    }

    public static String getTitle() {
        return sharedPreferences.getString("t", "");
    }


    public static void setMyList(ArrayList<MovieDataItem> movieDataItems) {
        Gson gson = new Gson();
        String arraydata = gson.toJson(movieDataItems);
        editor.putString("a", arraydata).commit();
    }

    public static ArrayList<MovieDataItem> getMyList() {
        String ardata = sharedPreferences.getString("a", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<MovieDataItem>>() {}.getType();
        ArrayList<MovieDataItem> movieDataItems = gson.fromJson(ardata, type);
        return movieDataItems;
    }


    public static void setmypos(int pos) {
        editor.putInt("pos", pos).commit();
    }

    public static int getmypos() {
        return sharedPreferences.getInt("pos", 0);
    }


}