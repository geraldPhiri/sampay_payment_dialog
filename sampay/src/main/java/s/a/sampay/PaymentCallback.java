package s.a.sampay;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import static s.a.sampay.PaymentBase.SUCCESS;

public abstract class PaymentCallback implements Callback{


    public PaymentCallback(){
        super();
    }


    @Override
    public final void onFailure(Call call, final IOException e) {
        onFailureListener(e.toString());
    }


    @Override
    public final void onResponse(Call call, final Response response) throws IOException {
        String msg=response.body().string();
        System.out.println(msg);
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String status=jsonObject.getString("status");
            String message=jsonObject.getString("msg");
            onResponseListener(status,message);
        }
        catch (Exception e){

        }
    }


    /**
     *
     * @param status indicates what happened
     * @param msg gives more details of a transaction
     */
    private final void onResponseListener(String status, String msg){
        if(status!=null){
            if(status.equals(SUCCESS)){

                onSuccessListener(msg);
            }
            else{
                onFailureListener(msg);
            }
        }
    }


    /**
     * perform appropriate action if Error occurs
     */
    public abstract void onFailureListener(String msg);


    /**
     * perform appropriate action on Success
     */
    public abstract void onSuccessListener(String msg);


}
