package com.example.lovechinaapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lovechinaapp.volleyGetData.volleyController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity implements LocationListener {

    EditText et1; // Insert Name
    EditText et2; // Insert Address
    Spinner spinner; // type
    EditText et3; //evidence
    EditText et4; // X
    EditText et5; // Y
    EditText et6; // description
    Spinner spinner2; //china or hk type
    String url = "http://192.168.2.16:8080";

    //interval time and handler
    private int mInterval = 4000; // 3 seconds by default, can be changed later
    private Handler mHandler;
    //interval time and handler

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);
        initilized(); // initiilized the layout
        this.setSpinnerType();
        this.setSpinner2();

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                mHandler = new Handler();
                startRepeatingTask();
            }
        }, 10000);   //10 seconds
    }



    public void initilized() {
        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        et4 = (EditText)findViewById(R.id.editText4);
        et5 = (EditText)findViewById(R.id.editText5);
        et6 = (EditText)findViewById(R.id.editText6);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
    }

    //set the spinner1 (type of love china and hk)
    RequestQueue rQueue;
    public void setSpinnerType() {
        String path = url + "/type";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, path, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonarray = jsonObject.getJSONArray("type");
                            ArrayList typelist = new ArrayList();
                            for(int i=0;i<jsonarray.length();i++) { //here get the jsonobject from the /type
                            typelist.add(jsonarray.get(i).toString());
                            }

                            //set the spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertActivity.this, android.R.layout.simple_spinner_item, typelist);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);
                            //end of set the spinner

                            rQueue.getCache().clear();
                        } catch (JSONException e) {
                            System.out.println(e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError.toString());
            }
        });
        rQueue = Volley.newRequestQueue(InsertActivity.this);
        rQueue.add(jsonObjectRequest);
    }

    public void setSpinner2() {
        ArrayList lovetype = new ArrayList();
        lovetype.add("親中派");
        lovetype.add("親港派");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(InsertActivity.this, android.R.layout.simple_spinner_item, lovetype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
    }
    //end of setter


    public void SubmitData(View v) {
        String name = et1.getText().toString();
        String Address = et2.getText().toString();
        String evidence = et3.getText().toString();

        String X = et4.getText().toString();
        String Y = et5.getText().toString();
        if (X.equals("") || Double.parseDouble(X) < 22 || Double.parseDouble(X)  > 24) {
            X = "0"; // X Can not be Smaller than 22 and bigger than 24
        }
        if (Y.equals("") || Double.parseDouble(Y) <113 || Double.parseDouble(Y) > 115 ) {
            Y = "0"; //Y can not be Smaller than 113 and bigger than 115
        }
        String type = spinner.getSelectedItem().toString();
        String lovetype = spinner2.getSelectedItem().toString();
        String description = et6.getText().toString();

        if (name.equals("")) {
            Toast toast = Toast.makeText(v.getContext(), "The Name Can Not Be Null", Toast.LENGTH_LONG);
            toast.show();
        } else {
            if (lovetype.equals("親中派")) {
                InserData(name,Address,evidence,Double.parseDouble(X),Double.parseDouble(Y),type,"china",description);
            } else if (lovetype.equals("親港派")) {
                InserData(name,Address,evidence,Double.parseDouble(X),Double.parseDouble(Y),type,"hk",description);
            }
        }
        }

         JSONObject jsonObject;
         RequestQueue rQueue1;
        public void InserData(String name,String Address,String evidence, Double X, Double Y, String type, String lovetype,String description) {
        String path = url;
            if (lovetype.equals("china")) {
                path = path + "/lovechinainsert";
            } else if (lovetype.equals("hk")) {
                path = path + "/lovehkinsert";
            }
            jsonObject = new JSONObject();
            try {
                jsonObject.put("name",name);
                jsonObject.put("address",Address);
                jsonObject.put("type",type);
                jsonObject.put("evidence",evidence);
                jsonObject.put("X",X);
                jsonObject.put("Y",Y);
                jsonObject.put("description",description);
            } catch (JSONException e) {
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, path, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                Toast toast = Toast.makeText(InsertActivity.this, jsonObject.getString("data"), Toast.LENGTH_LONG);
                                toast.show();
                            }  catch (JSONException e) {
                                Log.i("Exception",e.toString());
                            }
                            rQueue1.getCache().clear();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    System.out.println(volleyError.toString());
                }
            });
            rQueue1 = Volley.newRequestQueue(InsertActivity.this);
            rQueue1.add(jsonObjectRequest);
        }






    // here is to control the GPS and get X and Y
    public void setXY() {
        getLocation();
        Log.i("GET LOCATION","True");
    }

    LocationManager locationManager;
    String locationText = "";
    String locationLatitude = "";
    String locationLongitude = "";
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, (LocationListener) this); //here change -> call onLocationChanged
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }
    public void onLocationChanged(Location location) { //here change will directly change the X and Y editText box , set(XY) just change the boss by time
        locationText = location.getLatitude() + "," + location.getLongitude();
        locationLatitude = location.getLatitude() + "";
        locationLongitude = location.getLongitude() + "";
        et4.setText(locationLatitude);
        et5.setText(locationLongitude);
        Log.i("LocationChange","True");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void UpdateGPS(View v) {
        setXY();
    }

    Runnable mStatusChecker = new Runnable () {
        public void run() {
            try {
                setXY();
            } finally {
                mHandler.postDelayed(mStatusChecker,mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }
    //END


}
