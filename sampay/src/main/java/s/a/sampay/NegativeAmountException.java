package s.a.sampay;

import androidx.annotation.NonNull;

public class NegativeAmountException extends Exception {

    @NonNull
    @Override
    public String toString() {
        return "expected Positive number but received Negative";
    }
}
