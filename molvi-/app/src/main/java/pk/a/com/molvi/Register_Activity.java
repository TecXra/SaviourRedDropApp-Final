package pk.a.com.molvi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pk.a.com.molvi.RequestExecuter.RegisterRequest;

/**
 * Created by f on 5/21/2016.
 */
public class Register_Activity extends Activity {

    private EditText uemail, pass, cnfpassword;
    private Button submit;
   private ProgressDialog pDialog;
    private ImageView RegisterReload;

    String Email;
    String Password;
    String ConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().hasExtra("bundle") && savedInstanceState==null){
            savedInstanceState = getIntent().getExtras().getBundle("bundle");

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
      /*  if (savedInstanceState != null) {
            // Restore value of members from saved state
            Email= savedInstanceState.getString("Emaill");
            Password = savedInstanceState.getString("Passwordd");
            ConfirmPassword=savedInstanceState.getString("ConfirmPasswordd");
        } else {
            // Probably initialize members with default values for a new instance
        }*/
        Initialize();

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              getValues();
            }
        });
    }

    protected void Initialize(){

        uemail = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        cnfpassword = (EditText) findViewById(R.id.confpass);
        RegisterReload= (ImageView) findViewById(R.id.registerReload);
        pDialog=new ProgressDialog(this);
      pDialog.setCancelable(false);

        submit = (Button)findViewById(R.id.Register);

    }

    protected void getValues() {
        Email = uemail.getText().toString();
        Password = pass.getText().toString();
        ConfirmPassword = cnfpassword.getText().toString();
        if (Email.length() == 0) {
            uemail.setError("Enter Email");
        }
        else if (!isValidEmail(Email)) {
            uemail.setError("Invalid email format");
        }

        else if (Password.length() == 0) {
            pass.setError("Enter Password");
        } else if (ConfirmPassword.length() == 0) {
            cnfpassword.setError("Enter Password");
        }else if(Password.length()<8)
        {
            pass.setError("Minimum 8 characters required");
        }
        else if (!Password.equals(ConfirmPassword)) {
            cnfpassword.setError("Password Missmatch");
        } else {
        //    Toast.makeText(Register_Activity.this, "perameters registering", Toast.LENGTH_LONG).show();

            pDialog.setMessage("Registering...");
            showDialog();
            request();

        }
    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void request(){

        Response.Listener<String> responselistener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
           hideDialog();
               // Toast.makeText(Register_Activity.this,"come in response listerner "+" ",Toast.LENGTH_LONG).show();
                String key= response;
                if(!key.equals("400"))
                {
                //    Toast.makeText(Register_Activity.this,"key is"+key+" ",Toast.LENGTH_LONG).show();
                    SharedPreferences prefs = getSharedPreferences("data", Context.MODE_PRIVATE);



                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("key", key);
                    editor.commit();
                    Intent intent=new Intent(Register_Activity.this,Profile_Activity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(Register_Activity.this," Bad Request or Email already exist ",Toast.LENGTH_LONG).show();
                }

            }
        };
        Response.ErrorListener errorListener=(new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
Toast.makeText(Register_Activity.this,"Sorry Your request cannot be made due to insufficient network",Toast.LENGTH_LONG).show();
                RegisterReload.setVisibility(View.VISIBLE);
                RegisterReload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                    request();
                        RegisterReload.setVisibility(View.GONE);
                       /* Intent intent = getIntent();
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);*/
                    }
                });

            }
        });



        RegisterRequest registerRequest = new RegisterRequest(Email,Password,ConfirmPassword,responselistener,errorListener);
        RequestQueue queue = Volley.newRequestQueue(Register_Activity.this);
        queue.add(registerRequest);




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
 /*   public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        Email = savedInstanceState.getString("Emaill");
        Password = savedInstanceState.getString("Passwordd");
        ConfirmPassword=savedInstanceState.getString("ConfirmPasswordd");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString("Emaill", Email);
        savedInstanceState.putString("Passwordd",Password);
        savedInstanceState.putString("ConfirmPasswordd",ConfirmPassword);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
*/
}
