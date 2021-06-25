package leandrothiery.jwork_android;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * GET request to retrieve all Invoice
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class ListInvoiceRequest extends StringRequest {
    private static final String URL = "http://192.168.0.102:8080/invoice/jobseeker/";

    /**
     * Constructor of Request
     *
     * @param jobseekerId id of jobseeker
     * @param listener    listener for response
     */
    public ListInvoiceRequest(int jobseekerId, Response.Listener<String> listener) {
        super(Method.GET, URL + jobseekerId, listener, null);
    }
}
