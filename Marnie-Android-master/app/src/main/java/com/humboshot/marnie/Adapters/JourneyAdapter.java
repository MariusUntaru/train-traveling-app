package com.humboshot.marnie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.humboshot.marnie.Logic.DateHandler;
import com.humboshot.marnie.Model.Journey;
import com.humboshot.marnie.R;

import java.util.ArrayList;

public class JourneyAdapter extends ArrayAdapter<Journey> {
    public JourneyAdapter(Context context, ArrayList<Journey> journeys) {
        super(context, 0, journeys);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Journey journey = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowlayoutjourney, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.personName);
        TextView age = (TextView) convertView.findViewById(R.id.personAge);
        TextView gender = (TextView) convertView.findViewById(R.id.personGender);
        TextView stationFrom = (TextView) convertView.findViewById(R.id.peopleStationFrom);
        TextView stationTo = (TextView) convertView.findViewById(R.id.peopleStationTo);

        // Populate the data into the template view using the data object
        DateHandler dh = new DateHandler();
        int userAge = 0;
        try {
            userAge = dh.CalculateAge(journey.getPerson().getBirthday());
        }catch (Exception e){
            e.printStackTrace();
        }

        name.setText(journey.getPerson().getName());
        age.setText("" + userAge);
        gender.setText(journey.getPerson().getGender());
        stationFrom.setText(journey.getStartLocation());
        stationTo.setText(journey.getDestination());

        // Return the completed view to render on screen
        return convertView;
    }
}