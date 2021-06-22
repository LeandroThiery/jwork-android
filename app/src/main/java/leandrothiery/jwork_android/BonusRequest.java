package leandrothiery.jwork_android;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class BonusRequest extends StringRequest {
    private static final String URL = "http://192.168.0.102:8080/bonus/";

    public BonusRequest(String referralCode, Response.Listener<String> listener) {
        super(Method.GET, URL + referralCode, listener, null);
    }
}
