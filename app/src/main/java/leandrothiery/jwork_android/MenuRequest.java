package leandrothiery.jwork_android;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * GET request to retrieve all jobs
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class MenuRequest extends StringRequest {
    private static final String URL = "http://192.168.0.102:8080/job";

    /**
     * Constructor of Request
     * @param listener listener for response
     */
    public MenuRequest(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", error.toString());
            }
        });
    }
}
