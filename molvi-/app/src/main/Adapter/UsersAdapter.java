package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.live.molviproject.R;

import java.util.ArrayList;

import Models.SRUser;

/**
 * Created by Ali ( 03154342359 ) on 7/28/2016.
 */
public class UsersAdapter extends BaseAdapter
{

    ArrayList<SRUser> userarray = new ArrayList<SRUser>();

    public UsersAdapter() {

    }

    public UsersAdapter(ArrayList<SRUser> ar1) {
        this.userarray = ar1;
    }







    @Override
    public int getCount() {
       return userarray.size();

    }

    @Override
    public Object getItem(int position) {
        return userarray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view==null){
            LayoutInflater inflater =
                    LayoutInflater.from(parent.getContext());
            view=inflater.inflate(
                    R.layout.custom_list_view_item_user,parent,false);
        }

        SRUser su = userarray.get(position);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(su.getName());
        TextView blood = (TextView) view.findViewById(R.id.blood);
        blood.setText(su.getBlood());
        TextView city = (TextView) view.findViewById(R.id.city);
        city.setText(su.getCity());

        return view;

    }
}
