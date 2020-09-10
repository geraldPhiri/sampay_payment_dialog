package s.a.sampay;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.view.WindowManager;

import android.widget.EditText;

import androidx.annotation.Nullable;
import okhttp3.*;

import java.io.Serializable;


public class PaymentDialog extends Activity implements PaymentBase{
    static private View contentView;

    static private String link;
    static private String amount;
    static private String sender, receiver;

    static private PaymentCallback paymentCallback;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        contentView=getLayoutInflater().inflate(R.layout.dialog_1,null,false);
        setContentView(contentView);

        //initFields();


    }


    //ToDo:uncomment if fields are no longer static
    /*private void initFields() {
        link=getIntent().getStringExtra(STRING_LINK);
        amount=getIntent().getStringExtra(STRING_AMOUNT);
        sender=getIntent().getStringExtra(STRING_SENDER);
        receiver=getIntent().getStringExtra(STRING_RECEIVER);

        //paymentCallback=(PaymentCallback)getIntent().getSerializableExtra(STRING_PAYMENT_CALLBACK);;
    }*/


    @Override
    public PaymentBase onNegativeButtonClick(View view) {
        finish();
        return this;
    }

    @Override
    public PaymentBase onPositiveButtonClick(View view) {
        EditText pin=findViewById(R.id.rald);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("dguy", receiver)
                .addFormDataPart("jobid","1")
                .addFormDataPart("sender", sender)
                .addFormDataPart("amount", PaymentDialog.this.amount)
                .addFormDataPart("pin", pin.getText().toString())
                .build();


        final Request request = new Request.Builder()
                .url(link)
                .post(requestBody)
                .build();


        new OkHttpClient().newCall(request).enqueue(paymentCallback);


        return this;
    }


    @Override
    public final PaymentBase setPaymentCallback(PaymentCallback paymentCallback) {
        this.paymentCallback=paymentCallback;
        return this;
    }//setPaymentCallback


    @Override
    public PaymentBase setLink(final String link) {
        this.link=link;
        return this;
    }//setLink


    @Override
    public PaymentBase setAmount(String amount) throws NegativeAmountException{
        try{
            float amountF=Float.parseFloat(amount);
            if(amountF<0){
                throw new NegativeAmountException();
            }
            this.amount=amount;
            return this;
        }
        catch (Exception e){
           throw e;
        }

    }


    @Override
    public final PaymentBase setSender(String SAMPAY_NUMBER) {
        this.sender=SAMPAY_NUMBER;
        return this;
    }//setSender


    @Override
    public final PaymentBase setReceiver(String SAMPAY_NUMBER) {
        this.receiver=SAMPAY_NUMBER;
        return this;
    }//setReceiver


    @Override
    public final void show(Activity activity) {
        //ToDo:check to make sure all required info is set
        activity.startActivity(new Intent(activity,PaymentDialog.class)
                //ToDo:uncomment if fields are no longer static
                /*.putExtra(STRING_LINK, link)
                .putExtra(STRING_RECEIVER, receiver)
                .putExtra(STRING_SENDER, sender)
                .putExtra(STRING_AMOUNT, amount)
                .putExtra(STRING_PAYMENT_CALLBACK, paymentCallback.onSuccessListener())*/
        );
    }//show


}//PaymentDialog
