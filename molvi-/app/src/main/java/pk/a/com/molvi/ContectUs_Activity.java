package pk.a.com.molvi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
/*
import Utils.AsyncResponse;
import Utils.RequestExecutor;
*/

/**
 * Created by f on 5/21/2016.
 */
public class ContectUs_Activity extends Activity {
    ArrayList<String> textArray;
    EditText comText;
    TextView textView;
    ArrayList<String> reviews;
    LinearLayout linearLayout;
    private Button home, search, about, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_contact_us);




        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String Contact = intent.getStringExtra("Contact");
        String Id= intent.getStringExtra("Id");

        TextView name = (TextView)findViewById(R.id.nameshow);
        name.setText(Name);

        TextView contact = (TextView) findViewById(R.id.contactshow);
        contact.setText(Contact);
/*


        RequestExecutor re = new RequestExecutor(this);
        re.delegate = this;
        re.execute("3",Id);

*/




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


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Home_Activity.class);
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






    }
/*
    @Override
    public void onProcessCompelete(Object result) {



            reviews = (ArrayList<String>)result;
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout12);
        for( int i = 0; i < reviews.size(); i++ )
        {
            textView = new TextView(this);
            textView.setText(" -=> : " + reviews.get(i));
            linearLayout.addView(textView);
        }






    }






    public void commentadd(View view)
    {
        comText = (EditText)findViewById(R.id.comment);

        TextView text = new TextView(ContectUs_Activity.this);
        text.setText(" -=> : " + comText.getText());
        linearLayout.addView(text);




    }

*/
}
