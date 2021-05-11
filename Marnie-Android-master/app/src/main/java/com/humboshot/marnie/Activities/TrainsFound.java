package com.humboshot.marnie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.humboshot.marnie.Adapters.RouteAdapter;
import com.humboshot.marnie.Logic.CallApi;
import com.humboshot.marnie.Logic.DateHandler;
import com.humboshot.marnie.Model.Journey;
import com.humboshot.marnie.Model.Route;
import com.humboshot.marnie.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainsFound extends AppCompatActivity {
    private String searchString = "";
    private List<Route> routes = new ArrayList<>();
    private Gson gson;
    private ListView listView;
    private String travelDate;
    private Journey myJourney;
    private DateHandler dh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains_found);
        setTitle(R.string.pickRoute);

        gson = CallApi.getGson();
        dh = new DateHandler();
        Intent intent = getIntent();
        myJourney = gson.fromJson(intent.getStringExtra("myJourney"), Journey.class);
        travelDate = dh.ParseDateToDateString(myJourney.getStartTime());

        //show search parameters in text field
        TextView txtList = (TextView) findViewById(R.id.textViewList);
        txtList.setText(myJourney.getStartLocation() + " " + myJourney.getDestination() + " " + myJourney.getStartTime());

        //Show api endpoint in Textfield
        TextView textViewEndPoint = (TextView) findViewById(R.id.textViewEndPoint);
        String end = getString(R.string.endPoint) +
                "Route?from=" +
                myJourney.getStartLocation() +
                "&to=" +
                myJourney.getDestination() +
                "&startTime=" +
                dh.ParseDateToDateTimeString(myJourney.getStartTime());
        searchString = end;
        textViewEndPoint.setText(searchString);

        TextView displayDate = (TextView) findViewById(R.id.routesOnThisDate);
        displayDate.setText(travelDate);

        listView = (ListView) findViewById(R.id.RoutesList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startTrainPeople(position);
            }
        });

        SetRouteList();
    }

    //Start Activity TrainPeople based on the selected Route
    private void startTrainPeople(int position) {
        Route route = routes.get(position);

        Intent intent = new Intent(TrainsFound.this, TrainPeople.class);

        myJourney.setRouteId(route.getId());
        myJourney.setRoute(route);
        myJourney.setStartTime(dh.ParseString(travelDate, route.getStopFrom().getDepartureTime()));
        myJourney.setEndTime(dh.ParseString(travelDate, route.getStopTo().getDepartureTime()));
        String myJourneyJson = gson.toJson(myJourney);
        intent.putExtra("myJourney", myJourneyJson);
        startActivity(intent);
    }


    private void SetRouteList() {
        StringRequest request = new StringRequest(com.android.volley.Request.Method.GET, searchString, onRoutesLoaded, onRoutesError);
        CallApi.q(this).add(request);
    }

    private final com.android.volley.Response.Listener<String> onRoutesLoaded = new com.android.volley.Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d(MainActivity.class.getSimpleName(), response);

            routes = Arrays.asList(gson.fromJson(response, Route[].class));
            ArrayList<Route> rList = new ArrayList<>(routes);

            //custom adapter
            RouteAdapter adapter = new RouteAdapter(getApplicationContext(), rList);
            listView.setAdapter(adapter);
        }
    };

    private final com.android.volley.Response.ErrorListener onRoutesError = new com.android.volley.Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(MainActivity.class.getSimpleName(), error.toString());
        }
    };
}


