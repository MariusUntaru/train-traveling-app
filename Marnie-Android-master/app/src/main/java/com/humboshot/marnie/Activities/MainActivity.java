package com.humboshot.marnie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.humboshot.marnie.Logic.CallApi;
import com.humboshot.marnie.Logic.DateHandler;
import com.humboshot.marnie.Model.Journey;
import com.humboshot.marnie.R;

public class MainActivity extends AppCompatActivity {
    private EditText editTo;
    private EditText editFrom;
    private EditText editDate;
    private EditText editTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.titleTrainSearch);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        Button logOutButton = (Button) findViewById(R.id.log_out);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        Toast.makeText(MainActivity.this, getString(R.string.login_success), Toast.LENGTH_LONG).show();

        editTo = (EditText) findViewById(R.id.to);
        editFrom = (EditText) findViewById(R.id.from);
        editDate = (EditText) findViewById(R.id.date);
        editTime = (EditText) findViewById(R.id.time);
        Button btnSearchTrain = (Button) findViewById(R.id.btn_TrainSearch);

        //ToDo delete when testing is done:
        editFrom.setText("Aalborg");
        editTo.setText("Vejle");
        editDate.setText("2017-05-11");
        editTime.setText("10:00:00");

        // TODO: 21-05-2017 add datPicker and TimePicker too main to ensure date format.
        btnSearchTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });
    }
    /**Called when u press send button, can be replaced with a actionListener.*/
    public void sendMessage(View view){
        Intent intent = new Intent(this, TrainsFound.class);

        Journey myJourney = new Journey();
        try{
            myJourney.setStartLocation(editFrom.getText().toString().trim());
            myJourney.setDestination(editTo.getText().toString().trim());
            myJourney.setStartTime(new DateHandler().ParseString(editDate.getText().toString(), editTime.getText().toString()));
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage()+ getString(R.string.errorInDate), Toast.LENGTH_SHORT).show();
            return;
        }

        String myJourneyJsonString = CallApi.getGson().toJson(myJourney, Journey.class);
        intent.putExtra("myJourney", myJourneyJsonString);
        startActivity(intent);
    }


    private void logOut() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
