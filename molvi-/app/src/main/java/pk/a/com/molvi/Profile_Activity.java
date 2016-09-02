package pk.a.com.molvi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.content.res.Resources;
import android.os.Bundle;
//import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import pk.a.com.molvi.Login_Activity;
import pk.a.com.molvi.MainActivity;
import pk.a.com.molvi.R;
import pk.a.com.molvi.RequestExecuter.ProfileRequest;

/**
 * Created by f on 5/21/2016.
 */
public class Profile_Activity extends Activity {


    private EditText name, contact;
    private Spinner bloodg, areea;
    private Button submit;
    private ProgressDialog pDialog;


    String Name,BGId,Area, PhoneNumber,appUserId,key;
    int BGIdd;
    int Areaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Initialize();

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getValues();
        pDialog.setMessage("Registering...");
        showDialog();

    }
});




        //   ses();
    }

    protected void Initialize() {

        name = (EditText) findViewById(R.id.namee);
        contact = (EditText) findViewById(R.id.contect);
        bloodg = (Spinner) findViewById(R.id.spinnerblood1);
        areea = (Spinner) findViewById(R.id.spinnerblood);
        submit = (Button) findViewById(R.id.Register);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);

        SharedPreferences prefs = getSharedPreferences("data", Context.MODE_PRIVATE);
        key=prefs.getString("key","N/A");

         appUserId=key;


        getValues();


    }

    protected void getValues() {

           Name = name.getText().toString();
        PhoneNumber=contact.getText().toString();


     //   Toast.makeText(this,"rhis is"+Name ,Toast.LENGTH_LONG).show();
      //  Toast.makeText(this,"rhis is"+ PhoneNumber,Toast.LENGTH_LONG).show();

        Resources resources = getResources();
        String[] codes = resources.getStringArray(R.array.bloodgroup);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Profile_Activity.this, android.R.layout.simple_list_item_1,codes);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        bloodg.setAdapter(adapter);
        bloodg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                BGIdd=bloodg.getSelectedItemPosition()+1;
             //   Toast.makeText(Profile_Activity.this,"this position is"+BGId, Toast.LENGTH_LONG).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        String[] areas = resources.getStringArray(R.array.Area);
        ArrayAdapter<String> adapters = new ArrayAdapter<String>(Profile_Activity.this, android.R.layout.simple_list_item_1,areas);
        adapters.setDropDownViewResource(android.R.layout.simple_list_item_1);
        areea.setAdapter(adapters);
        //Toast.makeText(Profile_Activity.this,"this position is" , Toast.LENGTH_LONG).show();
        areea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Areaa=areea.getSelectedItemPosition()+1;
                Toast.makeText(Profile_Activity.this,"this position is"+ Areaa , Toast.LENGTH_LONG).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        BGId=Integer.toString(BGIdd);
         Area=Integer.toString(Areaa);



        if(!Name.isEmpty() && !PhoneNumber.isEmpty())
        {
          Toast.makeText(this,"good",Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(Profile_Activity.this,"Please Enter your Credentials",Toast.LENGTH_LONG).show();
        }



        Response.Listener<String> responselistener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Profile_Activity.this,"come in response listener",Toast.LENGTH_LONG).show();
                hideDialog();
                key=response;
                Toast.makeText(Profile_Activity.this,"key is"+ key ,Toast.LENGTH_LONG).show();
                SharedPreferences prefs = getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("key", key);
                editor.commit();
                Intent intent=new Intent(Profile_Activity.this,Home_Activity.class);
                startActivity(intent);
            }
        };

        ProfileRequest profileRequest=new ProfileRequest(Name,BGId,Area,PhoneNumber,appUserId,responselistener);
        RequestQueue queue = Volley.newRequestQueue(Profile_Activity.this);
        queue.add(profileRequest);




    }




    @Override
    public void onBackPressed() {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);
        return;
    }




    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}