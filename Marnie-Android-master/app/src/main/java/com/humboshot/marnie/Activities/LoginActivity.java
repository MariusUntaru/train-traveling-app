package com.humboshot.marnie.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.google.gson.Gson;
import com.humboshot.marnie.Logic.CallApi;
import com.humboshot.marnie.Model.Person;
import com.humboshot.marnie.R;

import org.json.JSONObject;


public class LoginActivity extends Activity {

    private ProgressDialog progress;
    private Gson gson;
    private CallApi<IdToken> callApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind views
        final EditText emailEditText = (EditText) findViewById(R.id.emailEditext);
        final EditText passwordEditText = (EditText) findViewById(R.id.passwordEditext);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        //Hardcoded values for debugging
        emailEditText.setText("mm@mm.com");
        passwordEditText.setText("123");

        // Add the onClick listener to the database login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show a progress dialog to block the UI while the request is being made.
                login(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
            }
        });

        //Settings for loading Person from Web Api
        callApi = new CallApi();
        gson = CallApi.getGson();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //Check if the result belongs to a pending web authentication
        if (WebAuthProvider.resume(intent)) {
            return;
        }
        super.onNewIntent(intent);
    }

    private void login(String email, String password) {
        Auth0 auth0 = new Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain));
        AuthenticationAPIClient client = new AuthenticationAPIClient(auth0);

        progress = ProgressDialog.show(this, null, "Logging in..", true, false);
        progress.show();

        String connectionName = "Username-Password-Authentication";
        client.login(email, password, connectionName)
                .start(new BaseCallback<Credentials, AuthenticationException>() {
                    @Override
                    public void onSuccess(Credentials payload) {
                        progress.dismiss();
                        //Get Auth and call Web Api to get Person and save to shared preference
                        IdToken idToken = new IdToken();
                        idToken.setId_token(payload.getIdToken());
                        GetUserFromAuth0(idToken);
                    }

                    @Override
                    public void onFailure(final AuthenticationException error) {
                        progress.dismiss();
                        //Show error to the user
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    /**
     * Getting user_id from auth.
     * @param idToken
     */
    private void GetUserFromAuth0(IdToken idToken) {
        String requestString = getString(R.string.auth0Endpoint)+"tokeninfo";

        CallApi.q(this).add(new JsonObjectRequest(1, requestString, callApi.GetJsonObj(idToken), this:: onLoadedUser, this:: onError));
    }

    private void onLoadedUser(JSONObject jsonObject) {
        UserId userId = gson.fromJson(jsonObject.toString(), UserId.class);
        String user_id = userId.getUser_id().replace("auth0|", "");
        GetAndSetPersonFromApi(user_id);
    }

    /**
     * Getting Person from Auth, based on user_id
     * @param user_id
     */
    private void GetAndSetPersonFromApi(String user_id) {
        String searchString = getString(R.string.endPoint)+ getString(R.string.person) +"?" + getString(R.string.authId) + "=" + user_id;
        CallApi.q(this).add(new StringRequest(searchString, this::onLoaded, this::onError));
    }

    //On response from Api
    private void onLoaded(String response) {
        Person person = gson.fromJson(response, Person.class);

        //Save Person in shared preferences
        //file location and readability
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.sharedPref), Context.MODE_PRIVATE);
        //creating a new editor, and writing values to it
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.authId), person.getAuthId());
        editor.putInt(getString(R.string.id), person.getId());
        editor.putString(getString(R.string.personName), person.getName());
        editor.putString(getString(R.string.gender), person.getGender());
        //write to the shared preference file
        editor.commit();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
    private void onError(VolleyError volleyError) {
        Log.i("@volleyError", "Volley Error - " +volleyError.toString());
        Toast.makeText(this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
    }

    //private class to deserialize user_id from auth0
    private class UserId {
        private String user_id;

        public UserId() {
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
    //private class to send id_token to auth0
    private class IdToken{
        private String id_token;

        public IdToken(){
        }

        public String getId_token() {
            return id_token;
        }

        public void setId_token(String id_token) {
            this.id_token = id_token;
        }
    }
}
