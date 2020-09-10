package s.a.sampaylib;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import s.a.sampay.NegativeAmountException;
import s.a.sampay.PaymentCallback;
import s.a.sampay.PaymentDialog;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startLibrary(View view) {
        try {
            new PaymentDialog()
                    .setLink("https://samafricaonline.com/sam_pay/public/deliverypay/")
                    .setReceiver("+260973767938")
                    .setSender("+260977209444")
                    .setAmount("1.2")
                    .setPaymentCallback(new PaymentCallback() {
                        @Override
                        public void onFailureListener(final String msg) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        @Override
                        public void onSuccessListener(final String msg) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .show(MainActivity.this);
        }
        catch (NegativeAmountException e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

}

