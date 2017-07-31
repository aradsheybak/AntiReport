package wiadevelopers.com.antireport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;



public class SplashScreenActivity extends Activity
{

    final Handler handler = new Handler();
    TextView txtJobi;
    ImageView imgJobi,imgJobi1;
    public static Typeface type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imgJobi = (ImageView)findViewById(R.id.splashImgJobi);
        imgJobi1 = (ImageView)findViewById(R.id.splashImgJobi1);
        txtJobi = (TextView)findViewById(R.id.splashtxtJobi);

        type = Typeface.createFromAsset(getAssets(),"fonts/iran.ttf");
        txtJobi.setTypeface(type);

        imgJobi1.setVisibility(View.GONE);
        imgJobi.animate().rotationY(2700).setDuration(5000).setInterpolator(new AccelerateDecelerateInterpolator());


        AlphaAnimation  animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(5000);
        txtJobi.setAnimation(animation);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, 6000);
    }


}