package leandrothiery.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Main activity of appplication
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class MainActivity extends AppCompatActivity {
    ArrayList<Recruiter> listRecruiter = new ArrayList<>();
    ArrayList<Job> jobIdList = new ArrayList<>();
    HashMap<Recruiter, ArrayList<Job>> childMapping = new HashMap<>();

    private MainListAdapter listAdapter;
    ExpandableListView expListView;

    Button btnApplied;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("id", 0);
        String jobseekerName = intent.getStringExtra("name");

        getSupportActionBar().setTitle("Hello, " + jobseekerName);

        expListView = findViewById(R.id.lvExp);

        listAdapter = new MainListAdapter(getApplicationContext(), listRecruiter, childMapping);
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Job selectedJob = listAdapter.getChild(groupPosition, childPosition);
                Toast.makeText(MainActivity.this, selectedJob.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ApplyJobActivity.class);
                intent.putExtra("Job", selectedJob);
                intent.putExtra("id", userId);
                startActivity(intent);
                return true;
            }
        });

        btnApplied = findViewById(R.id.btnApplied);
        btnApplied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListInvoiceActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
            }
        });

        refreshList();
    }

    /**
     * Refresh the list of Jobs available
     */
    private void refreshList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    Log.d("JOSN", jsonResponse.toString());
                    if (jsonResponse != null) {
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject job = jsonResponse.getJSONObject(i);
                            JSONObject recruiter = job.getJSONObject("recruiter");
                            JSONObject location = recruiter.getJSONObject("location");

                            Location location1 = new Location(location.getString("province"),
                                    location.getString("city"),
                                    location.getString("description"));
                            Recruiter recruiter1 = new Recruiter(recruiter.getInt("id"),
                                    recruiter.getString("name"),
                                    recruiter.getString("email"),
                                    recruiter.getString("phoneNumber"),
                                    location1);

                            listRecruiter.add(recruiter1);

                            Job job1 = new Job(job.getInt("id"),
                                    job.getString("name"),
                                    recruiter1,
                                    job.getInt("fee"),
                                    job.getString("category"));

                            jobIdList.add(job1);

                            for (Recruiter rec : listRecruiter) {
                                ArrayList<Job> temp = new ArrayList<>();
                                for (Job job2 : jobIdList) {
                                    if (job2.getRecruiter().getName().equals(rec.getName()) ||
                                            job2.getRecruiter().getEmail().equals(rec.getEmail()) ||
                                            job2.getRecruiter().getPhoneNumber().equals(rec.getPhoneNumber())) {
                                        temp.add(job2);
                                    }
                                }
                                childMapping.put(rec, temp);
                            }
                        }
                    }
                    listAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e("Error", "JSON ERROR", e);
                }
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }
}