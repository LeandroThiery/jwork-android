package leandrothiery.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ApplyJobActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private int jobseekerID, jobID, bonus;
    private String jobName, jobCategory, selectedPayment;
    private double jobFee;

    private TextView tvJobName, tvJobCategory, tvJobFee, tvTextCode, tvTotalFee;
    private EditText etReferralCode;
    private RadioGroup radioGroup;
    private Button btnApply, btnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job);

        Intent intent = getIntent();

        Job job = (Job) intent.getSerializableExtra("Job");
        jobseekerID = intent.getIntExtra("id", 0);

        Toast.makeText(this, "This id is" + String.valueOf(jobseekerID), Toast.LENGTH_SHORT).show();

        jobID = job.getId();
        jobName = job.getName();
        jobCategory = job.getCategory();
        jobFee = job.getFee();

        tvJobName = findViewById(R.id.job_name);
        tvJobName.setText(jobName);

        tvJobCategory = findViewById(R.id.job_category);
        tvJobCategory.setText(jobCategory);

        tvJobFee = findViewById(R.id.job_fee);
        tvJobFee.setText(String.valueOf(jobFee));

        tvTotalFee = findViewById(R.id.total_fee);

        tvTextCode = findViewById(R.id.textCode);
        tvTextCode.setVisibility(View.INVISIBLE);

        etReferralCode = findViewById(R.id.referral_code);
        etReferralCode.setVisibility(View.INVISIBLE);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        btnApply = findViewById(R.id.btnApply);
        btnApply.setVisibility(View.INVISIBLE);
        btnApply.setOnClickListener(this);

        btnCount = findViewById(R.id.hitung);
        btnCount.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnApply) {
            applyJob();
        } else if (id == R.id.hitung) {
            countFee();
        }

    }

    private void applyJob() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please choose one of the payment type", Toast.LENGTH_SHORT).show();
        } else {
            int id = radioGroup.getCheckedRadioButtonId();

            ApplyJobRequest applyJobRequest = null;

            Response.Listener<String> listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject != null) {
                            Toast.makeText(ApplyJobActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (JSONException e) {
                        Log.e("JSON ERROR", "Failed Response", e);
                    }
                }
            };

            if (id == R.id.ewallet) {
                applyJobRequest = new ApplyJobRequest(
                        jobID,
                        jobseekerID,
                        etReferralCode.getText().toString(),
                        listener);

            } else if (id == R.id.bank) {
                applyJobRequest = new ApplyJobRequest(
                        jobID,
                        jobseekerID,
                        0,
                        listener
                );
            }

            RequestQueue queue = Volley.newRequestQueue(ApplyJobActivity.this);
            queue.add(applyJobRequest);


        }
    }

    private void countFee() {

        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please choose one of the payment type", Toast.LENGTH_SHORT).show();
        } else {
            int id = radioGroup.getCheckedRadioButtonId();
            if (id == R.id.ewallet) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null && jsonObject.getBoolean("active")
                                    && jobFee >= jsonObject.getInt("minTotalFee")) {
                                Toast.makeText(ApplyJobActivity.this, "Code Applied", Toast.LENGTH_SHORT).show();
                                jobFee += jsonObject.getInt("extraFee");
                                tvTotalFee.setText(String.valueOf(jobFee));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                };
                tvTotalFee.setText(String.valueOf(jobFee));
                btnCount.setVisibility(View.INVISIBLE);
                btnApply.setVisibility(View.VISIBLE);

                BonusRequest bonusRequest = new BonusRequest(etReferralCode.getText().toString(), listener);
                RequestQueue queue = Volley.newRequestQueue(ApplyJobActivity.this);
                queue.add(bonusRequest);


            } else if (id == R.id.bank) {
                tvTotalFee.setText(String.valueOf(jobFee));
                btnCount.setVisibility(View.INVISIBLE);
                btnApply.setVisibility(View.VISIBLE);
            }

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.ewallet) {
            tvTextCode.setVisibility(View.VISIBLE);
            etReferralCode.setVisibility(View.VISIBLE);
        } else if (checkedId == R.id.bank) {
            tvTextCode.setVisibility(View.INVISIBLE);
            etReferralCode.setVisibility(View.INVISIBLE);
        }
    }
}