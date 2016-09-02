package pk.a.com.molvi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import pk.a.com.molvi.Adapter.BloodGroupAdapter;

import pk.a.com.molvi.RequestExecuter.AppURLs;

/*

import Adapter.BloodGroupAdapter;
import Adapter.CityAdapter;
import Models.BloodCity;
import Models.BloodGroup;
import Models.Cities;
import Utils.AsyncResponse;
import Utils.RequestExecutor;

/**
 * Created by f on 5/21/2016.
 */
public class Search_Activity extends Activity {

    public Spinner BSpinner;
    private Spinner CSpinner;
    String Cid;
    String Bid;
    private ProgressDialog progressDialog;
    ArrayList<String> bloodcity;
    ArrayList<String> bloodgrop;
    //  private BloodGroupAdapter Badapter;
    private JSONArray bg;
    private JSONArray city;
    String bloodposition;
   private ImageButton reload;


    private Button home, search, about, content, searchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_first_search);


        //    BSpinner .setOnItemSelectedListener(this);

        //    BSpinner.setOnItemClickListener((AdapterView.OnItemClickListener) Search_Activity.this);
        Initialize();
    }


    protected void Initialize() {

        home = (Button) findViewById(R.id.home_menu);
        search = (Button) findViewById(R.id.search_menu);
        about = (Button) findViewById(R.id.about_menu);
        content = (Button) findViewById(R.id.contect_menu);
        reload= (ImageButton) findViewById(R.id.imageButton);
        BSpinner = (Spinner) findViewById(R.id.spinnerblood);
        CSpinner = (Spinner) findViewById(R.id.spinnerArea);
        bloodgrop = new ArrayList<String>();
        bloodcity = new ArrayList<String>();


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Home_Activity.class);
                startActivity(i);
            }
        });


        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), ContectUs_Activity.class);
                startActivity(i);
            }
        });


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), About_Activity.class);
                startActivity(i);
            }
        });

        searchbtn = (Button) findViewById(R.id.searchbtn);


        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Users.class);
                i.putExtra("Bid",Bid);
                i.putExtra("Cid",Cid);
                startActivity(i);
            }
        });

        progressDialog.setMessage("Loading");
        showDialog();
        getvalues();

    }


    private void getvalues() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURLs.BaseUrl + AppURLs.bloodCity,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        Toast.makeText(Search_Activity.this, "come in response", Toast.LENGTH_LONG).show();
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            bg = j.getJSONArray("bg");

                            city = j.getJSONArray("city");

                            //Calling method getStudents to get the students from the JSON Array
                            getStudents(bg);
                            getStudent(city);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Search_Activity.this, "Network Error Please Check Your Connection", Toast.LENGTH_LONG).show();
                        hideDialog();
                        reload.setVisibility(View.VISIBLE);
                        reload.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = getIntent();
                                overridePendingTransition(0, 0);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(intent);
                            }
                        });

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);
                //    json.getString("Id");
                //Adding the name of the student to array list
                bloodgrop.add(json.getString("BloodGroupName"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        BSpinner.setAdapter(new ArrayAdapter<String>(Search_Activity.this, android.R.layout.simple_spinner_dropdown_item, bloodgrop));
        BSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bid = getSession(position);

                Toast.makeText(Search_Activity.this, Bid, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String getSession(int position) {
        String session = "";
        try {
            JSONObject json = bg.getJSONObject(position);
            session = json.getString("Id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }


    private void getStudent(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                bloodcity.add(json.getString("CityName"));
                //   bloodgrop.add(json.getString("Id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        CSpinner.setAdapter(new ArrayAdapter<String>(Search_Activity.this, android.R.layout.simple_spinner_dropdown_item, bloodcity));

        CSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cid = getSess(position);

                Toast.makeText(Search_Activity.this, Cid, Toast.LENGTH_SHORT).show();
               // startbutton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String getSess(int position) {
        String session = "";
        try {
            JSONObject json = city.getJSONObject(position);
            session = json.getString("Id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }





    private void showDialog()
    {
        if(!progressDialog.isShowing())
        {
            progressDialog.show();
        }




    }


    private void hideDialog()
    {
        if(progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }

    }







}






