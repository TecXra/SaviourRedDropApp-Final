package pk.a.com.molvi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import pk.a.com.molvi.RequestExecuter.AppURLs;
import pk.a.com.molvi.RequestExecuter.LoginRequest;

/*
import Utils.AsyncResponse;
import Utils.RequestExecutor;
import Utils.RgPreference;
*/

/**
 * Created by f on 5/21/2016.
 */
public class Login_Activity extends Activity {

    SharedPreferences sharedPref;
  //  String Username,Password;


    private EditText user, pass;
    private Button login;
    private String Email, Password;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        Initialize();
    }

    protected void Initialize() {
        user = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
boolean flag = getValues();

               if(flag){
          /*      RequestExecutor re = new RequestExecutor(Login_Activity.this);
                re.delegate = Login_Activity.this;
                re.execute("4", username, password);
            */
                getValues();


            }

        });
    }

    protected void getValues() {
        Email = user.getText().toString();
        Password = pass.getText().toString();

       if (Email.equals("") || Password.equals(""))
       {
            Toast.makeText(Login_Activity.this, "Enter Fields",
                    Toast.LENGTH_SHORT).show();
        }
       else
       {
           pDialog=new ProgressDialog(Login_Activity.this);
           pDialog.setCancelable(true);
            pDialog.setMessage("Checking Your Credentilas...");
            showDialog();

        }

         Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                Toast.makeText(Login_Activity.this, "come in response listerner " + " ", Toast.LENGTH_LONG).show();

                String User_Id = response;
                if (User_Id.equals("404")) {

                    Toast.makeText(Login_Activity.this, "Invalid Username Or Password ... Tyr Again.. ",
                            Toast.LENGTH_SHORT).show();
                    Intent ourIntent = new Intent(getBaseContext(), Login_Activity.class);
                    startActivity(ourIntent);
                    finish();

                } else {
                    sharedPref = getPreferences(Context.MODE_PRIVATE);
                        sharedPref.edit().putString(AppURLs.UserId, User_Id).apply();

                    Intent ourIntent = new Intent(getBaseContext(), Home_Activity.class);
                    startActivity(ourIntent);
                    finish();

                }


            }
        };

        LoginRequest loginRequest= new LoginRequest(Email,Password,listener);
        RequestQueue queue= Volley.newRequestQueue(Login_Activity.this);
        queue.add(loginRequest);
        Toast.makeText(Login_Activity.this,"request has been made",Toast.LENGTH_LONG).show();



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





/*
    @Override
    public void onProcessCompelete(Object result) {

        String User_Id = (String)result;

//        sharedPref = getPreferences(Context.MODE_PRIVATE);
  //      sharedPref.edit().putString(RgPreference.UserId, User_Id).apply();

        if(User_Id.equals("404"))
        {

            Toast.makeText(Login_Activity.this, "Invalid Username Or Password ... Tyr Again.. ",
                    Toast.LENGTH_SHORT).show();
            Intent ourIntent = new Intent(getBaseContext(), Login_Activity.class);
            startActivity(ourIntent);
            finish();

        }
        else
        {
            Intent ourIntent = new Intent(getBaseContext(), Home_Activity.class);
            startActivity(ourIntent);
            finish();

        }





    }
*/
