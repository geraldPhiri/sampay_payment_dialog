package s.a.sampay;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;

import static androidx.core.content.ContextCompat.getSystemService;


/**
 * Base Interface for Sampay Payments
 * @author Samafricaonline
 * @version 1.0.0
 */

public interface PaymentBase {
    //CODES
    int SUCCESS=0;//ToDo:set
    int ERROR=0;//ToDo:set

    String STRING_LINK = "link";
    String STRING_RECEIVER = "receiver";
    String STRING_SENDER = "sender";
    String STRING_AMOUNT = "amount";
    String STRING_PAYMENT_CALLBACK = "callback";

    //LAYOUTS
    int DEFAULT_CONTENT_VIEW=R.layout.dialog_1;


    PaymentBase onNegativeButtonClick(View view);


    PaymentBase onPositiveButtonClick(View view);

    //void setNeutralButton(@NonNull String title, View.OnClickListener onClickListener);

    /**
     * Set Callback to handle what happens on payment successful, and error
     * @param paymentCallback
     * @return this
     */
    PaymentBase setPaymentCallback(@NonNull PaymentCallback paymentCallback);


    /**
     * set Payment link provided by samafricaonline.com
     * @param link
     * @return this
     */
    PaymentBase setLink(final String link);


    /**
     *
     * @param amount
     * @return
     */
    PaymentBase setAmount(final String amount) throws NegativeAmountException;



    /**
     *
     * @param SAMPAY_NUMBER
     * @return
     */
    PaymentBase setSender(final String SAMPAY_NUMBER);


    /**
     *
     * @param SAMPAY_NUMBER
     * @return
     */
    PaymentBase setReceiver(final String SAMPAY_NUMBER);


    /**
     * Display the Payment Dialog in Passed Activity
     * @return
     */
    void show(Activity activity);


}//PaymentBase

