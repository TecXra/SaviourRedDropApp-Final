package pk.a.com.molvi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pk.a.com.molvi.Model.BloodList;
import pk.a.com.molvi.R;

/**
 * Created by Mian Mohsin on 8/30/2016.
 */
public class CustomListAdapterBloodSearch extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<BloodList> bloodLists;

    public CustomListAdapterBloodSearch(Activity activity, List<BloodList> bloodLists) {
        this.activity = activity;
        this.bloodLists = bloodLists;
    }

    @Override
    public int getCount() {
        return bloodLists.size();
    }

    @Override
    public Object getItem(int position) {
        return bloodLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(inflater==null)
           inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           if(convertView==null)
               convertView = inflater.inflate(R.layout.custom_list_view_item_userr, null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView contact = (TextView) convertView.findViewById(R.id.cellno);
        TextView city = (TextView) convertView.findViewById(R.id.city);
        TextView bloodgroup = (TextView) convertView.findViewById(R.id.blood);

        BloodList bloodList = bloodLists.get(position);
        name.setText(" Name :       " + String.valueOf(bloodList.getname()));
        contact.setText(" Contct # :       " + String.valueOf(bloodList.getPhoneNumber()));
        city.setText(" City :       " + String.valueOf(bloodList.getCity()));
        bloodgroup.setText(" Blood Group :        " + String.valueOf(bloodList.getBloodGroupName()));





        return convertView;
        
    }
}
