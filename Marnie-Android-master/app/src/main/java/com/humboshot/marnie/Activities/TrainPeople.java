package com.humboshot.marnie.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.humboshot.marnie.Adapters.JourneyAdapter;
import com.humboshot.marnie.Logic.CallApi;
import com.humboshot.marnie.Logic.DateHandler;
import com.humboshot.marnie.Model.Journey;
import com.humboshot.marnie.Model.MyDate;
import com.humboshot.marnie.R;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Displays a list of Journeys that match the users, and the Persons on those journeys.
public class TrainPeople extends AppCompatActivity {

    private String searchString;
    private RequestQueue requestQueue;
    private ListView listView;
    private Gson gson;
    private List<Journey> journeys;
    private Journey myJourney;
    private DateHandler dh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_people);
        setTitle(R.string.pickDate);
        dh = new DateHandler();

        //initialise gson
        gson = CallApi.getGson();
        Intent intent = getIntent();
        String myJourneyJson =intent.getStringExtra("myJourney");

        //Use gson to generate object from the string parsed with the intent
        CreateMyJourney(myJourneyJson);

        
        listView = (ListView) findViewById(R.id.PeopleList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startDateDetial(position);
            }
        });

        //Save myJourney to DB
        SaveJourneyToDB();

        //Get list of possible dates and show in listView
        GetJourneyListFromApi();
    }

    private void GetJourneyListFromApi() {
        CallApi.q(this).add(new StringRequest(GetSearchString(), this::onLoaded, this::onError));
    }
    private void onLoaded(String response) {
        journeys = Arrays.asList(gson.fromJson(response, Journey[].class));
        ArrayList<Journey> jList = new ArrayList<>(journeys);

        //custom adapter
        JourneyAdapter adapter = new JourneyAdapter(getApplicationContext(), jList);
        listView.setAdapter(adapter);
    }
    private void onError(VolleyError volleyError) {
        Log.i("@volleyError", "Volley Error - " +volleyError.toString());
    }

    private void SaveJourneyToDB(){
        CallApi.q(this).add(new JsonObjectRequest(GetPostUrl(), new CallApi<Journey>().GetJsonObj(myJourney), this::onPostSuccess, this::onPostError));
    }

    private String GetPostUrl() {
        return getString(R.string.endPoint) + "Journey";
    }

    private void onPostSuccess(JSONObject jsonObject) {
        Log.i("@PostSuccess", "Response - " + jsonObject.toString() );
        Toast.makeText(TrainPeople.this, "Ur Journey is saved, Getting dateList", Toast.LENGTH_LONG).show();
    }

    private void onPostError(VolleyError volleyError) {
        Log.i("@volleyError", "Volley Error - " +volleyError.toString());
        Toast.makeText(TrainPeople.this, "not saved to DB" + volleyError.toString(), Toast.LENGTH_LONG).show();
    }

    private void CreateMyJourney(String myJourneyJson) {
        SharedPreferences shared =this.getSharedPreferences(getString(R.string.sharedPref), Context.MODE_PRIVATE);
        String authId = getString(R.string.id);
        int id = shared.getInt(authId, 0);

        myJourney = gson.fromJson(myJourneyJson, Journey.class);
        myJourney.setPersonId(id);
    }

    //Start Activity TrainPeople based on the selected Route
    private void startDateDetial(int position) {
        Journey chosenJourney = journeys.get(position);
        MyDate myDate = GenerateDate(chosenJourney);
        String myDateString = gson.toJson(myDate);

        // TODO: 20-05-2017 make DateDetailPage acticity , create intent with myDateString
        Intent intent = new Intent(TrainPeople.this, DateDetailPage.class);
        intent.putExtra("DateJsonString", myDateString);
        startActivity(intent);
        finish();
    }

    private MyDate GenerateDate(Journey chosenJourney) {
        MyDate myDate = new MyDate();
        if (chosenJourney != null)
        {
            myDate.setPerson1Id(myJourney.getPersonId());
            myDate.setPerson2Id(chosenJourney.getPersonId());
            myDate.setRouteId(chosenJourney.getRouteId());
            myDate.setPerson2(chosenJourney.getPerson());
            myDate.setStatusP1(1);
            if (myJourney.getStartTime().before(chosenJourney.getStartTime()))
            {
                myDate.setStartTime(chosenJourney.getStartTime());
                myDate.setDateStartLocation(chosenJourney.getStartLocation());
            }
            else
            {
                myDate.setStartTime(myJourney.getStartTime());
                myDate.setDateStartLocation(myJourney.getStartLocation());
            }

            if (myJourney.getEndTime().after(chosenJourney.getEndTime()))
            {
                myDate.setEndTime(chosenJourney.getEndTime());
                myDate.setDateDestination(chosenJourney.getDestination());
            }
            else
            {
                myDate.setEndTime(myJourney.getEndTime());
                myDate.setDateDestination(myJourney.getDestination());
            }
        }
        return myDate;
    }

    private String GetSearchString() {
        String end = getString(R.string.endPoint) +
                "Journey?routeId=" +
                myJourney.getRouteId() +
                "&personId=" +
                myJourney.getPersonId() +
                "&myStart=" +
                dh.ParseDateToDateTimeString(myJourney.getStartTime()) +
                "&myStop=" +
                dh.ParseDateToDateTimeString(myJourney.getEndTime());
        searchString = end;
        TextView textViewEndPoint = (TextView) findViewById(R.id.PeopleEndPoint);
        textViewEndPoint.setText(searchString);
        return searchString;
    }
}


