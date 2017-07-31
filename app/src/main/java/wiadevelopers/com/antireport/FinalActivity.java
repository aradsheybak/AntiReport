package wiadevelopers.com.antireport;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class FinalActivity extends Activity
{
    TextView txtTExPLAIN, txtTPRICE, txtTNUM;
    Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        txtTExPLAIN = (TextView) findViewById(R.id.txtTExplain);
        txtTPRICE = (TextView) findViewById(R.id.txtTPRICE);
        txtTNUM = (TextView) findViewById(R.id.txtTNUM);
        btnComplete = (Button) findViewById(R.id.btnComplete);


        txtTExPLAIN.setTypeface(SplashScreenActivity.type);
        txtTPRICE.setTypeface(SplashScreenActivity.type);
        txtTNUM.setTypeface(SplashScreenActivity.type);
        btnComplete.setTypeface(SplashScreenActivity.type);





        try
        {
            JSONObject jsonObject = new JSONObject(getIntent().getStringExtra("tdata"));
            txtTExPLAIN.setText(jsonObject.getString("texplain"));
            txtTPRICE.setText(jsonObject.getString("tprice"));
            txtTNUM.setText(jsonObject.getString("tnum"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


        btnComplete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });


    }
}