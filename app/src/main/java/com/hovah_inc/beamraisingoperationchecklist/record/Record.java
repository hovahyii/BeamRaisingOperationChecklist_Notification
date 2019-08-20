package com.hovah_inc.beamraisingoperationchecklist.record;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.app.ProgressDialog;

import com.hovah_inc.beamraisingoperationchecklist.MainActivity;
import com.hovah_inc.beamraisingoperationchecklist.R;
import android.content.Intent;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.app.TimePickerDialog;
import android.widget.TimePicker;

public class Record extends AppCompatActivity  {
    private TextView name;
    private TextView section;
    private TextView potNo;
    private TextView remarks;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();

        final QuestionsSpreadsheetWebService spreadsheetWebService = retrofit.create(QuestionsSpreadsheetWebService.class);
        name = findViewById(R.id.question_name);
        section = findViewById(R.id.question_section);
        potNo = findViewById(R.id.question_potNo);
        final EditText startTime = findViewById(R.id.question_startTime);
        final EditText  endTime = findViewById(R.id.question_endTime);
        remarks = findViewById(R.id.question_remarks);

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Record.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        int hour = hourOfDay % 12;
                        startTime.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour, minutes, hourOfDay < 12 ? "am" : "pm"));

                    }
                }, 00, 00, false);
                timePickerDialog.show();

            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Record.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        int hour = hourOfDay % 12;
                        endTime.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour, minutes, hourOfDay < 12 ? "am" : "pm"));

                    }
                }, 00, 00, false);
                timePickerDialog.show();

            }
        });



            findViewById(R.id.questions_submit_button).setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                            String nameInput = name.getText().toString();
                            String sectionInput = section.getText().toString();
                            String potNoInput = potNo.getText().toString();
                            String startTimeInput = startTime.getText().toString();
                            String endTimeInput = endTime.getText().toString();
                            String remarksInput = remarks.getText().toString();

                            if (nameInput.length() == 0) {
                                name.requestFocus();
                                name.setError("FIELD CANNOT BE EMPTY");
                            } else if (!nameInput.matches("[a-zA-Z ]+")) {
                                name.requestFocus();
                                name.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                            } else if (sectionInput.length() == 0) {
                                section.requestFocus();
                                section.setError("FIELD CANNOT BE EMPTY");
                            } else if (potNoInput.length() == 0) {
                                potNo.requestFocus();
                                potNo.setError("FIELD CANNOT BE EMPTY");
                            }else if (startTimeInput.length() == 0) {
                                startTime.requestFocus();
                                startTime.setError("FIELD CANNOT BE EMPTY");
                            }else if (endTimeInput.length() == 0) {
                                endTime.requestFocus();
                                endTime.setError("FIELD CANNOT BE EMPTY");
                            }else{

                            progressDialog = new ProgressDialog(Record.this);
                            progressDialog.setMessage("Please Wait"); // Setting Message
                            progressDialog.setTitle("Updating"); // Setting Title
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                            progressDialog.show(); // Display Progress Dialog
                            progressDialog.setCancelable(false);

                            Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(nameInput, sectionInput, potNoInput, startTimeInput, endTimeInput, remarksInput);
                            completeQuestionnaireCall.enqueue(callCallback);
                            new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        Thread.sleep(10000);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    progressDialog.dismiss();
                                }

                            }).start();
                        }
                        }
                    });
        }

        private final Callback<Void> callCallback = new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Record.this, "Succeed", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Record.this, "Failed", Toast.LENGTH_LONG).show();
            }
        };

    }



