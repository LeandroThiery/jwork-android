package leandrothiery.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity of viewing an Invoice
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class SelesaiJobActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvInvoiceId, tvJobseekerName, tvInvoiceDate, tvPaymentType,
            tvInvoiceStatus, tvReferralCode, tvJobName, tvJobFee, tvTotalFee,
            tvStaticReferralCode;
    private Button btnCancel, btnFinish;
    private Invoice invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_job);
        Intent intent = getIntent();
        invoice = (Invoice) intent.getSerializableExtra("Invoice");

        tvInvoiceId = findViewById(R.id.invoice_id);
        tvInvoiceId.setText(String.valueOf(invoice.getId()));

        tvJobseekerName = findViewById(R.id.jobseeker_name);

        tvInvoiceDate = findViewById(R.id.invoice_date);
        tvInvoiceDate.setText(invoice.getDate());

        tvPaymentType = findViewById(R.id.payment_type);
        tvPaymentType.setText(invoice.getPaymentType());

        tvInvoiceStatus = findViewById(R.id.invoice_status);
        tvInvoiceStatus.setText(invoice.getInvoiceStatus());

        tvStaticReferralCode = findViewById(R.id.staticReferralCode);
        tvReferralCode = findViewById(R.id.referral_code);
        tvReferralCode.setText(invoice.getReferralCode());

        if (invoice.getPaymentType().equals("EwalletPayment")) {
            tvReferralCode.setText(invoice.getReferralCode());
        } else {
            tvReferralCode.setVisibility(View.INVISIBLE);
            tvStaticReferralCode.setVisibility(View.INVISIBLE);
        }


        Job job = invoice.getJobs().get(0);

        tvJobName = findViewById(R.id.job_name);
        tvJobName.setText(job.getName());

        tvJobFee = findViewById(R.id.job_fee);
        tvJobFee.setText(String.valueOf(job.getFee()));

        tvTotalFee = findViewById(R.id.total_fee);
        tvTotalFee.setText(String.valueOf(invoice.getTotalFee()));


        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);

        if (invoice.getInvoiceStatus().equals("Cancelled") || invoice.getInvoiceStatus().equals("Finished")) {
            btnFinish.setVisibility(View.INVISIBLE);
            btnCancel.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnCancel) {
            Response.Listener<String> listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject != null) {
                            Toast.makeText(SelesaiJobActivity.this, "Invoice Cancelled", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            JobBatalRequest request = new JobBatalRequest(String.valueOf(invoice.getId()), listener);
            RequestQueue queue = Volley.newRequestQueue(SelesaiJobActivity.this);
            queue.add(request);


        } else if (id == R.id.btnFinish) {
            Response.Listener<String> listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject != null) {
                            Toast.makeText(SelesaiJobActivity.this, "Invoice Finished", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            JobSelesaiRequest request = new JobSelesaiRequest(String.valueOf(invoice.getId()), listener);
            RequestQueue queue = Volley.newRequestQueue(SelesaiJobActivity.this);
            queue.add(request);
        }
    }
}