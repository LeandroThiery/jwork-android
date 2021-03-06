package leandrothiery.jwork_android;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * POST request to register a new Jobseeker
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class RegisterRequest extends StringRequest {
    private static final String URL = "http://192.168.0.102:8080/jobseeker/register";
    private Map<String, String> params;

    /**
     * Constructor of request
     * @param name name of Jobseeker
     * @param email email of jobseeker
     * @param password password of jobseeker
     * @param listener listener for response
     */
    public RegisterRequest(String name,
                           String email,
                           String password,
                           Response.Listener<String> listener) {
        super(Method.POST, URL, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", error.toString());
            }
        });
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        Log.d("Help", name);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
