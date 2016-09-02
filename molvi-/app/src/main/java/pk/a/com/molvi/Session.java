package pk.a.com.molvi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class Session extends AppCompatActivity

{
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;


    public Session(Context context) {
        SharedPreferences prefs = getSharedPreferences("data", Context.MODE_PRIVATE);



        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("key", "N/A");
        editor.commit();

    }



    public boolean getLoggedIn() {
        return sp.getBoolean("is_logged_in", false);
    }
}