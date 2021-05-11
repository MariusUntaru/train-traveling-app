package com.humboshot.marnie.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.humboshot.marnie.Logic.CallApi;
import com.humboshot.marnie.Logic.DateHandler;
import com.humboshot.marnie.Model.MyDate;
import com.humboshot.marnie.R;

import org.json.JSONObject;

public class DateDetailPage extends AppCompatActivity {
    private MyDate myDate;
    private DateHandler dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_detail_page);
        setTitle("Date Details");
        myDate = CallApi.getGson().fromJson(getIntent().getStringExtra("DateJsonString"), MyDate.class);

        dh = new DateHandler();
        TextView name = (TextView) findViewById(R.id.dateName);
        name.setText(myDate.getPerson2().getName());
        TextView age = (TextView) findViewById(R.id.dateAge);
        age.setText(""+dh.CalculateAge(myDate.getPerson2().getBirthday()));
        TextView gender = (TextView) findViewById(R.id.dateGender);
        gender.setText(myDate.getPerson2().getGender());

        Button saveDate = (Button) findViewById(R.id.saveDate);
        saveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDate.setPerson2(null);
                myDate.setPerson1(null);
                myDate.setRoute(null);
                SaveDate(myDate);
            }
        });
    }

    private void SaveDate(MyDate myDate) {
        CallApi.q(this).add(new JsonObjectRequest(GetPostUrl(), new CallApi<MyDate>().GetJsonObj(myDate), this::onPostSuccess, this::onPostError));
    }

    private String GetPostUrl() {
        return getString(R.string.endPoint) + "Date";
    }

    private void onPostSuccess(JSONObject jsonObject) {
        Log.i("@PostSuccess", "Response - " + jsonObject.toString() );
        Toast.makeText(DateDetailPage.this, "Ur Date is saved, Getting dateList", Toast.LENGTH_LONG).show();
    }

    private void onPostError(VolleyError volleyError) {
        Log.i("@volleyError", "Volley Error - " +volleyError.toString());
        Toast.makeText(DateDetailPage.this, "not saved to DB" + volleyError.toString(), Toast.LENGTH_LONG).show();
    }
}
