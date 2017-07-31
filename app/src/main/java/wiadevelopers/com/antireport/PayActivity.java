package wiadevelopers.com.antireport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wiadevelopers.com.antireport.util.IabHelper;
import wiadevelopers.com.antireport.util.IabResult;
import wiadevelopers.com.antireport.util.Inventory;
import wiadevelopers.com.antireport.util.Purchase;
import wiadevelopers.com.antireport.util.SkuDetails;

import static wiadevelopers.com.antireport.billing.sku_key;

public class PayActivity extends Activity
{


    private TextView textView;
    private IabHelper bhelper;
    private Button btnPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        textView = (TextView) findViewById(R.id.textview);
        textView.setTypeface(SplashScreenActivity.type);
        ((Button) findViewById(R.id.btnPay)).setTypeface(SplashScreenActivity.type);
        btnPay=(Button)findViewById(R.id.btnPay);

        bhelper = new IabHelper(PayActivity.this, billing.rsa_key);
        bhelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {
                if (result.isSuccess()) {

                    ArrayList<String> moreSkus = new ArrayList<String>();
                    moreSkus.add(sku_key);


                    ////


                    bhelper.queryInventoryAsync(true, moreSkus, new IabHelper.QueryInventoryFinishedListener() {
                        @Override
                        public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                            if (result.isSuccess()) {

                                SkuDetails details = inv.getSkuDetails(sku_key);
                                Toast.makeText(getApplicationContext(), "اطلاهات با موفقیت دریافت شد", Toast.LENGTH_LONG).show();
                            } else {x

                            }
                        }


                    });
                }
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bhelper.launchPurchaseFlow(PayActivity.this,billing.sku_key, 1001,
                        new IabHelper.OnIabPurchaseFinishedListener() {
                            @Override
                            public void onIabPurchaseFinished(IabResult result, Purchase info) {
                                if (result.isSuccess()) {

Toast.makeText(getApplicationContext(),"با تشکر از خرید شما",Toast.LENGTH_LONG).show();

                                }else{
                                    Toast.makeText(getApplicationContext(),"عملیات خرید با خطا مواجه شد!",Toast.LENGTH_LONG).show();

                                }

                            }
                        });
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bhelper.handleActivityResult(requestCode, resultCode, data);
    }
}