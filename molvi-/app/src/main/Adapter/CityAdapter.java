package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Models.Cities;

/**
 * Created by Ali ( 03154342359 ) on 7/28/2016.
 */
public class CityAdapter extends ArrayAdapter<Cities> {


    private Context context;
    private ArrayList<Cities> cityArray;


    public CityAdapter(Context context, int textViewResourceId, ArrayList<Cities> list) {
        super(context, textViewResourceId, list);

        this.context = context;
        this.cityArray = list;


    }


    public int getCount(){
        return cityArray.size();
    }

    public Cities getItem(int position){
        return cityArray.get(position);
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
        label.setText(cityArray.get(position).getCityname());
        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setGravity(Gravity.CENTER);
        label.setText(cityArray.get(position).getCityname());

        return label;
    }











}
