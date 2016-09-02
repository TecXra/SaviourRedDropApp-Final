package pk.a.com.molvi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mian Mohsin on 8/18/2016.
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread mythread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    SharedPreferences prefs = getSharedPreferences("data", Context.MODE_PRIVATE);

                    String key=prefs.getString("key","N/A");

                    if(key.equals("N/A")|| key.equals("400"))
                    {

                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                    else {
                        if(key.length()>4) {
                            Intent i = new Intent(SplashActivity.this, Profile_Activity.class);
                            startActivity(i);
                            finish();
                        }
                        else {
                            Intent i = new Intent(SplashActivity.this, Home_Activity.class);
                            startActivity(i);
                            finish();
                        }

                        Log.d("err","500");
                    }
                   // Intent intent = new Intent(SplashActivity.this,Register_Activity.class);
                   // startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();







      /*

*/
    }
}
