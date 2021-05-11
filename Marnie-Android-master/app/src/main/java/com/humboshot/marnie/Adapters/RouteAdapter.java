package com.humboshot.marnie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.humboshot.marnie.Model.Route;
import com.humboshot.marnie.R;
import java.util.ArrayList;

/**
 * Created by marti on 19-05-2017.
 */

public class RouteAdapter extends ArrayAdapter<Route> {
    public RouteAdapter(Context context, ArrayList<Route> routes) {
        super(context, 0, routes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Route route = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.routelayout, parent, false);
        }
        // Lookup view for data population
        TextView stationFrom = (TextView) convertView.findViewById(R.id.stationFrom);
        TextView stationTo = (TextView) convertView.findViewById(R.id.stationTo);
        TextView departureTime = (TextView) convertView.findViewById(R.id.departureTime);
        TextView arrivalTime = (TextView) convertView.findViewById(R.id.arrivalTime);

        // Populate the data into the template view using the data object
        stationFrom.setText(route.getStopFrom().getStation().getName());
        stationTo.setText(route.getStopTo().getStation().getName());
        departureTime.setText(route.getStopFrom().getDepartureTime());
        arrivalTime.setText(route.getStopTo().getArrivalTime());

        // Return the completed view to render on screen
        return convertView;
    }
}
