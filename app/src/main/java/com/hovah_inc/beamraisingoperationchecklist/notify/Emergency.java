package com.hovah_inc.beamraisingoperationchecklist.notify;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hovah_inc.beamraisingoperationchecklist.R;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Emergency extends AppCompatActivity {
    EditText edtTitle;
    EditText edtMessage;
    final  String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAApxh3n3Q:APA91bHTJMY-yJXGf9m5PCwpwb1EG9XwhQihCFPSNPkwLNBTbVZHUAnYc5iRYaWz9em1FFRitFm5rLQihCFPSNPkwLNBTbVZHUAnYc5iRYaWz9em1EG9XyJgwVosLWYX7URUBcFoif9h";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        edtTitle = findViewById(R.id.editTitle);
        edtMessage = findViewById(R.id.edtMessage);

        findViewById(R.id.btnSend).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
                NOTIFICATION_TITLE = edtTitle.getText().toString();
                NOTIFICATION_MESSAGE = edtMessage.getText().toString();

                JSONObject notification = new JSONObject();
                JSONObject notifcationBody = new JSONObject();
                try {
                    notifcationBody.put("title", NOTIFICATION_TITLE);
                    notifcationBody.put("message", NOTIFICATION_MESSAGE);

                    notification.put("to", TOPIC);
                    notification.put("data", notifcationBody);
                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage() );
                }
                sendNotification(notification);
            }
        });

    }

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        edtTitle.setText("");
                        edtMessage.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Emergency.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;

            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}