package wiadevelopers.com.antireport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView imgRepairReport = (TextView) findViewById(R.id.txtRepairReport);
        TextView imgReturnMoney = (TextView) findViewById(R.id.txtReturnMoney);
        TextView imgActive = (TextView) findViewById(R.id.txtActive);
        TextView imgAntiPort = (TextView) findViewById(R.id.txtAntiReport);
        TextView about = (TextView) findViewById(R.id.textView5);
        TextView about1 = (TextView) findViewById(R.id.textView5s);


        imgRepairReport.setTypeface(SplashScreenActivity.type);
        imgReturnMoney.setTypeface(SplashScreenActivity.type);
        imgActive.setTypeface(SplashScreenActivity.type);
        imgAntiPort.setTypeface(SplashScreenActivity.type);
        about.setTypeface(SplashScreenActivity.type);
        about1.setTypeface(SplashScreenActivity.type);



        about.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                intentMessageTelegram("");
            }
        });



        imgRepairReport.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isAllow())
                    startActivity(new Intent(MainActivity.this, RepairReportActivity.class));
            }
        });

        imgReturnMoney.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isAllow())
                    startActivity(new Intent(MainActivity.this, ReturnMoneyActivity.class));
            }
        });

        imgActive.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //if (isAllow())
                startActivity(new Intent(MainActivity.this, PayActivity.class));
            }
        });

        imgAntiPort.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isAllow())
                    startActivity(new Intent(MainActivity.this, AntiReportActivity.class));
            }
        });
    }


    private boolean isAllow()
    {
        SharedPreferences prefs = getSharedPreferences(G.MY_PREFS_NAME, MODE_PRIVATE);
        boolean kos = prefs.getBoolean(G.KEY, false);
        if (kos == false)
            Toast.makeText(getApplicationContext(), "برای فعال سازی این قسمت باید ابتد آن را خریداری نمایید", Toast.LENGTH_LONG).show();
        return kos;
    }

    void intentMessageTelegram(String msg)
    {
        //https://t.me/Poshtibani_Anti_Report
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = isAppAvailable(MainActivity.this, appName);
        if (isAppInstalled)
        {
            /*
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            myIntent.setPackage(appName);
            myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
            startActivity(Intent.createChooser(myIntent, "Share with"));*/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/Official_Anti_Report"));
            startActivity(browserIntent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "اپلیکیشن تلگرام یافت نشد", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isAppAvailable(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (Exception e)
        {

            return false;
        }
    }
}