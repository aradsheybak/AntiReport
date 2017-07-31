package wiadevelopers.com.antireport;

import android.app.Application;
import android.content.SharedPreferences;


public class G extends Application
{

    public static String MY_PREFS_NAME="isPayToUnBlock";
    public static String KEY = "isAllow";

    @Override
    public void onCreate()
    {
        super.onCreate();
    }
}
