package pk.a.com.molvi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by WhySoSerious on 8/24/2016.
 */
public class ReviewRequest extends Activity {

    String Namee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_contact_us);

        Intent intent = getIntent();
        Namee = intent.getStringExtra("Name");
       // BloodId = intent.getStringExtra("Bid");
        Toast.makeText(this, "Name is" + Namee, Toast.LENGTH_SHORT).show();

    }

}
