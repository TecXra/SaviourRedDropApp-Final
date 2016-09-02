package pk.a.com.molvi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by f on 5/21/2016.
 */
public class About_Activity extends Activity {
    private Button home, search, about, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_about_us);

        Initialize();
    }

    protected void Initialize(){
        home = (Button) findViewById(R.id.home_menu);
        search = (Button) findViewById(R.id.search_menu);
        about = (Button) findViewById(R.id.about_menu);
        content = (Button) findViewById(R.id.contect_menu);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Search_Activity.class);
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


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Home_Activity.class);
                startActivity(i);
            }
        });




    }

}
