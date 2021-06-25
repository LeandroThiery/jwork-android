package leandrothiery.jwork_android;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * POST request to login jobseeker
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class LoginRequest extends StringRequest {
    private static final String URL = "http://192.168.0.102:8080/jobseeker/login";
    private Map<String, String> params;

    /**
     * Constructor of request
     *
     * @param email    email of jobseeker
     * @param password password of jobseeker
     * @param listener listener for response
     */
    public LoginRequest(String email, String password, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
