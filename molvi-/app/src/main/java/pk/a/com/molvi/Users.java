package pk.a.com.molvi;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.List;
import java.util.jar.Attributes;

import pk.a.com.molvi.Adapters.CustomListAdapterBloodSearch;
import pk.a.com.molvi.Model.BloodList;
import pk.a.com.molvi.RequestExecuter.AppURLs;
import pk.a.com.molvi.RequestExecuter.RegisterRequest;

/*
import Adapter.UsersAdapter;
import Models.SRUser;
import Utils.AsyncResponse;
import Utils.RequestExecutor;
*/
public class Users extends AppCompatActivity  {

    private ProgressDialog pDialog;
    ListView listView;
    String CityId;
    String BloodId;
   private int jsonlength;
   private String jArraySize;
    private List<BloodList> bloodListss = new ArrayList<BloodList>();
    private CustomListAdapterBloodSearch adapter;
    String bloodgroupname, cityname, name, phone,Name;
    private String ReviewId,Id;
    String namee[];


      ArrayList<BloodList> UserList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        listView = (ListView) findViewById(R.id.UserList);
        adapter = new CustomListAdapterBloodSearch(this, bloodListss);
        listView.setAdapter(adapter);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      //  Review();
       // View parentView = (View) view.getParent();
       // String message = ((TextView) parentView.findViewById(R.id.name)).getText().toString();
       // Name=message;
    //   String temp=  namee[position];
 //       TextView tv = (TextView)findViewById(R.id.name);
    //    tv.setText(temp);
     //   int listItem = (int) l istView.getItemAtPosition(position);
    //    String val =(String) parent.getItemAtPosition(position);
   //     System.out.println("Value is "+val);

   //     Intent intent= new Intent(getBaseContext(),ReviewRequest.class);
   //     intent.putExtra("name",Name);
  //      startActivity(intent);
        Toast.makeText(Users.this, UserList.get(position).getname()+"", Toast.LENGTH_SHORT).show();
        Intent I = new Intent(Users.this,ContectUs_Activity.class);
        I.putExtra("Name",UserList.get(position).getname());
        I.putExtra("blood",UserList.get(position).getBloodGroupName());
        I.putExtra("Contact",UserList.get(position).getPhoneNumber());
        startActivity(I);


    }
});
        pDialog = new ProgressDialog(this);
// Showing progress dialog before making http request
        pDialog.setMessage("Loading…");
        pDialog.show();


        Intent intent = getIntent();
        CityId = intent.getStringExtra("Cid");
        BloodId = intent.getStringExtra("Bid");


        Toast.makeText(this, "Bid text is" + BloodId, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Cid text is" + CityId, Toast.LENGTH_SHORT).show();


        Request();


        /*RequestExecutor re = new RequestExecutor(this);
        re.delegate = this;
        re.execute("1",Bid,Cid);
*/


    }

    private void Request() {
        final String allUserr = String.format(AppURLs.BaseUrl + AppURLs.allUserr, CityId, BloodId
                , 1);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, allUserr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        Toast.makeText(Users.this, "Come in Responselistener", Toast.LENGTH_SHORT).show();
                        String resp = response;



                            try {
                                JSONArray jsonArray = new JSONArray(resp);
                                jsonlength=jsonArray.length();
                                jArraySize=Integer.toString(jsonlength);
                                if(jsonlength==0)
                                {
                                    textViewSize();
                                    Toast.makeText(Users.this, " Sorry no match found... :(", Toast.LENGTH_SHORT).show();
                                }



                                for (int i = 0; i <= jsonArray.length(); i++) {

                                    JSONObject jsonBlood = jsonArray.getJSONObject(i).getJSONObject("BloodGroup");
                                    BloodList list = new BloodList();



                                    list.setBloodGroupName(jsonBlood.getString("BloodGroupName"));
                                     bloodgroupname = jsonBlood.getString("BloodGroupName");
                               //      Toast.makeText(Users.this,bloodgroupname,Toast.LENGTH_SHORT).show();

                                    JSONObject jsonCity = jsonArray.getJSONObject(i).getJSONObject("City");
                                    list.setCity(jsonCity.getString("CityName"));
                                     cityname = jsonCity.getString("CityName");
                                   //   Toast.makeText(Users.this,cityname,Toast.LENGTH_SHORT).show();
                                    list.setname(jsonArray.getJSONObject(i).getString("Name"));
                                    list.setPhoneNumber(jsonArray.getJSONObject(i).getString("PhoneNumber"));

                                    name = jsonArray.getJSONObject(i).getString("Name");
                                    ReviewId=jsonArray.getJSONObject(i).getString("ReviewStatus");
                                    Toast.makeText(Users.this,ReviewId,Toast.LENGTH_SHORT).show();
                                    Id=jsonArray.getJSONObject(i).getString("Id");

                                  //   Toast.makeText(Users.this,name,Toast.LENGTH_SHORT).show();
                                   phone = jsonArray.getJSONObject(i).getString("PhoneNumber");
                                 //    Toast.makeText(Users.this,phone,Toast.LENGTH_SHORT).show();
                                    bloodListss.add(list);


                                
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        adapter.notifyDataSetChanged();
                        textViewSize();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                Toast.makeText(Users.this, "Network Not Found or Poor Connection", Toast.LENGTH_LONG).show();
            }
        });




        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    protected void Review()
    {
        Intent intent = new Intent(this,ContectUs_Activity.class);
        startActivity(intent);
    }


    private void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }
    private void textViewSize()
    {
        TextView textView;
        textView= (TextView) findViewById(R.id.searchresult);
        textView.setText(null);
        textView.setText("Match Found     "+jArraySize);

    }

}


/*

    @Override
    public void onProcessCompelete(Object result) {

        UserList = (ArrayList<SRUser>)result;

        listView= (ListView) findViewById(R.id.UserList);
        UsersAdapter ba = new UsersAdapter(UserList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);
                if(position %2 == 1)
                {
                    view.setBackgroundColor(Color.parseColor("#FFB6B546"));
                }
                else
                {
                    view.setBackgroundColor(Color.parseColor("#FFCCCB4C"));
                }
                return view;
            }
        };
        listView.setAdapter(ba);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                Intent I = new Intent(Users.this,ContectUs_Activity.class);
                I.putExtra("Id",UserList.get(position).getId());
                I.putExtra("Name",UserList.get(position).getName());
                I.putExtra("Contact",UserList.get(position).getContact());
                startActivity(I);
            }
        });
*/


