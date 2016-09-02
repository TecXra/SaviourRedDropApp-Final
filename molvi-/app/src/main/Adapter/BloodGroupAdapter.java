package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Models.BloodGroup;

/**
 * Created by Ali ( 03154342359 ) on 7/28/2016.
 */
public class BloodGroupAdapter extends ArrayAdapter<BloodGroup> {


    private Context context;
    private ArrayList<BloodGroup> bloodArray;




    public BloodGroupAdapter(Context context, int textViewResourceId, ArrayList<BloodGroup> list) {
        super(context, textViewResourceId, list);

        this.context = context;
        this.bloodArray = list;


    }



    public int getCount(){
        return bloodArray .size();
    }

    public BloodGroup getItem(int position){
        return bloodArray .get(position);
    }

    public long getItemId(int position){
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(bloodArray.get(position).getBloodgroup());
        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setGravity(Gravity.CENTER);
        label.setText(bloodArray.get(position).getBloodgroup());

        return label;
    }








}
