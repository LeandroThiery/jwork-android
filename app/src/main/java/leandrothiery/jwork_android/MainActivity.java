package leandrothiery.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Recruiter> listRecruiter = new ArrayList<>();
    ArrayList<Job> jobIdList = new ArrayList<>();
    HashMap<Recruiter, ArrayList<Job>> childMapping = new HashMap<>();

    private MainListAdapter listAdapter;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = findViewById(R.id.lvExp);

        refreshList();
    }

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

                            for(Recruiter rec : listRecruiter) {
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
                    listAdapter = new MainListAdapter(getApplicationContext(), listRecruiter, childMapping);
                    expListView.setAdapter(listAdapter);
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