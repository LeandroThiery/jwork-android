package leandrothiery.jwork_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListInvoiceActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private InvoiceRecycleViewAdapter invoiceRecycleViewAdapter;
    private ArrayList<Invoice> invoices;
    private int jobseekerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_invoice);
        Intent intent = getIntent();
        jobseekerId = intent.getIntExtra("id", 0);

        invoices = new ArrayList<>();

        recyclerView = findViewById(R.id.invoiceRecycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        invoiceRecycleViewAdapter = new InvoiceRecycleViewAdapter(this, invoices);

        recyclerView.setAdapter(invoiceRecycleViewAdapter);

        refreshList();

    }

    private void refreshList() {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);

                    if (jsonResponse != null) {
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject invoiceJson = jsonResponse.getJSONObject(i);
                            int invoiceId = invoiceJson.getInt("id");
                            int totalFee = invoiceJson.getInt("totalFee");
                            String date = invoiceJson.getString("date");
                            String invoiceStatus = invoiceJson.getString("invoiceStatus");
                            String paymentType = invoiceJson.getString("paymentType");

                            JSONArray invoiceJobs = invoiceJson.getJSONArray("jobs");

                            ArrayList<Job> jobs = new ArrayList<>();

                            for (int j = 0; j < invoiceJobs.length(); j++) {
                                JSONObject job = invoiceJobs.getJSONObject(j);

                                int jobId = job.getInt("id");
                                int fee = job.getInt("fee");
                                String jobName = job.getString("name");
                                String category = job.getString("category");

                                JSONObject recruiter = job.getJSONObject("recruiter");

                                int recruiterId = recruiter.getInt("id");
                                String recruiterName = recruiter.getString("name");
                                String email = recruiter.getString("email");
                                String phoneNumber = recruiter.getString("phoneNumber");

                                JSONObject location = recruiter.getJSONObject("location");


                                String province = location.getString("province");
                                String city = location.getString("city");
                                String description = location.getString("description");

                                Location invoiceLocation = new Location(province, description, city);


                                Recruiter invoiceRecruiter = new Recruiter(recruiterId, recruiterName, email, phoneNumber, invoiceLocation);
                                Job invoiceJob = new Job(jobId, jobName, invoiceRecruiter, fee, category);

                                jobs.add(invoiceJob);

                            }


                            Invoice invoice = new Invoice(invoiceId, jobs, totalFee, date, invoiceStatus, paymentType);
                            invoices.add(invoice);
                        }
                    }
                    invoiceRecycleViewAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ListInvoiceRequest listInvoiceRequest = new ListInvoiceRequest(jobseekerId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ListInvoiceActivity.this);
        queue.add(listInvoiceRequest);

    }
}