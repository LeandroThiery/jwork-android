package leandrothiery.jwork_android;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * GET Request to retrieve a Bonus
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class BonusRequest extends StringRequest {
    private static final String URL = "http://192.168.0.102:8080/bonus/";

    /**
     * Constructor of a new Request
     *
     * @param referralCode Bonus Referral code
     * @param listener     listener for response
     */
    public BonusRequest(String referralCode, Response.Listener<String> listener) {
        super(Method.GET, URL + referralCode, listener, null);
    }
}
