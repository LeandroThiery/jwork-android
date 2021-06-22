package leandrothiery.jwork_android;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class ListInvoiceRequest extends StringRequest {
    private static final String URL = "http://192.168.0.102:8080/invoice/jobseeker/";

    public ListInvoiceRequest(int jobseekerId, Response.Listener<String> listener) {
        super(Method.GET, URL + jobseekerId, listener, null);
    }
}
