package leandrothiery.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * PUT request to finish an Invoice status
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class JobSelesaiRequest extends StringRequest {
    private static final String URL = "http://192.168.0.102:8080/invoice/invoiceStatus/";
    private Map<String, String> params;

    /**
     * Constructor of Request
     *
     * @param invoiceId id of invoice
     * @param listener  listener for response
     */
    public JobSelesaiRequest(String invoiceId, Response.Listener<String> listener) {
        super(Method.PUT, URL + invoiceId, listener, null);
        params = new HashMap<>();
        params.put("invoiceStatus", "Finished");

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}

