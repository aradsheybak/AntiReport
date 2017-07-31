package wiadevelopers.com.antireport;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReturnMoneyActivity extends AppCompatActivity
{

    EditText edtNameAndFamily, edtPhone, edtPayId, edtExplain;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_money);

        edtNameAndFamily = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPayId = (EditText) findViewById(R.id.edtPayId);
        edtExplain = (EditText) findViewById(R.id.edtExplain);
        btnSend = (Button) findViewById(R.id.btnSend);

        edtNameAndFamily.setText("");
        edtPhone.setText("");
        edtPayId.setText("");
        edtExplain.setText("");

        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isEmpty(edtNameAndFamily) || isEmpty(edtPhone) || isEmpty(edtPayId) || isEmpty(edtExplain))
                    Toast.makeText(getApplicationContext(), "لطفا همه خانه ها را پر کنید", Toast.LENGTH_LONG).show();
                else
                {
                    String res = getText(edtNameAndFamily) + "\n" + getText(edtPhone) + "\n" + getText(edtPayId) + "\n" + getText(edtExplain);
                    intentMessageTelegram(res);

                }
            }
        });
    }

    private boolean isEmpty(EditText editText)
    {
        return editText.getText().toString().equalsIgnoreCase("");
    }

    private String getText(EditText editText)
    {
        return editText.getText().toString().trim();
    }

    void intentMessageTelegram(String msg)
    {
        //https://t.me/Poshtibani_Anti_Report
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = isAppAvailable(ReturnMoneyActivity.this, appName);
        if (isAppInstalled)
        {
            /*
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            myIntent.setPackage(appName);
            myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
            startActivity(Intent.createChooser(myIntent, "Share with"));*/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Poshtibani_Anti_Report"));
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