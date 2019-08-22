package com.hovah_inc.beamraisingoperationchecklist.notify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.hovah_inc.beamraisingoperationchecklist.MainActivity;
import com.hovah_inc.beamraisingoperationchecklist.R;

import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PushNotification extends AppCompatActivity {
    EditText edtTitle;
    EditText edtMessage;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String API_KEY = "key=" + "AIzaSyCQqIvYVbYFfvWYfh15ffeJ4eKborhs8UM\n";
    final private String contentType = "application/json";
    final String TAG = "Emergency";


    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        edtTitle = findViewById(R.id.edtTitle);
        edtMessage = findViewById(R.id.edtMessage);

        findViewById(R.id.btnSend).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( PushNotification.this,  new OnSuccessListener<InstanceIdResult>() {
                            @Override
                            public void onSuccess(InstanceIdResult instanceIdResult) {
                                final String newToken = "d_1j88vn374:APA91bEvzQuzqVFyXKRx__1-o4bXFnUB-f3qE06UkPtNBs5WtORGCZn6DSoe9HGU7QFYTV0R9wLJVmo6C0fzX93husvP_Ag8OYpf0Fko-Sgg475ZIWYfEQbB6DhMYMyLfgD2LokEFHJU";//instanceIdResult.getToken();
                                Log.e("newToken",newToken);
                                TOKEN = newToken; //TOKEN has to match what the receiver subscribed to
                                NOTIFICATION_TITLE = edtTitle.getText().toString();
                                NOTIFICATION_MESSAGE = edtMessage.getText().toString();

                                JSONObject notification = new JSONObject();
                                JSONObject notificationBody = new JSONObject();
                                try {
                                    notificationBody.put("title", NOTIFICATION_TITLE);
                                    notificationBody.put("message", NOTIFICATION_MESSAGE);

                                    notification.put("to", TOKEN);
                                    notification.put("data", notificationBody);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                sendNotification(notification);
                            }
                        });
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
                        Toast.makeText(PushNotification.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", API_KEY);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


}