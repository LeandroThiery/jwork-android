package leandrothiery.jwork_android;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * POST Request to add an Invoice
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class ApplyJobRequest extends StringRequest {
    private static final String URL_EWALLET = "http://192.168.0.102:8080/invoice/createEwalletPayment";
    private static final String URL_BANK = "http://192.168.0.102:8080/invoice/createBankPayment";
    private Map<String, String> params;

    // E-wallet Payment

    /**
     * Request for Ewallet Payment Type
     *
     * @param jobIdList    list of Job
     * @param jobseekerId  id of Jobseeker
     * @param referralCode Bonus's referralCode
     * @param listener     listener for response
     */
    public ApplyJobRequest(int jobIdList,
                           int jobseekerId,
                           String referralCode,
                           Response.Listener<String> listener) {
        super(Method.POST, URL_EWALLET, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", error.toString());
            }
        });
        params = new HashMap<>();
        params.put("jobIdList", String.valueOf(jobIdList));
        params.put("jobseekerId", String.valueOf(jobseekerId));
        params.put("referralCode", referralCode);
    }

    // Bank Payment

    /**
     * Request for Bank Payment Type
     *
     * @param jobIdList   list of jobs
     * @param jobseekerId id of Jobseeker
     * @param adminFee    bank's adminFee
     * @param listener    listener for response
     */
    public ApplyJobRequest(int jobIdList,
                           int jobseekerId,
                           int adminFee,
                           Response.Listener<String> listener) {
        super(Method.POST, URL_BANK, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", error.toString());
            }
        });
        params = new HashMap<>();
        params.put("jobIdList", String.valueOf(jobIdList));
        params.put("jobseekerId", String.valueOf(jobseekerId));
        params.put("adminFee", String.valueOf(adminFee));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}