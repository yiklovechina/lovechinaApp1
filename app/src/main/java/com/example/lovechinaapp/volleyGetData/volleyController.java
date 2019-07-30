package com.example.lovechinaapp.volleyGetData;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lovechinaapp.InsertActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class volleyController {
//    private String url = "";
//    private ArrayList typelist;
//
//    private Activity act;
//    public volleyController(String url,Activity act) {
//        this.url = url;
//        this.typelist = new ArrayList();
//        this.act = act;
//    }
//
//    public ArrayList getType() {
//        this.volleygetTypeController(new ServerCallback() {
//            public void onSuccess(JSONObject response) {
//                try {
//                    JSONArray jarray = response.getJSONArray("type");
//                    ArrayList tl = new ArrayList();
//                    for(int i=0;i<jarray.length();i++) { //here get the jsonobject from the /type
//                        tl.add(jarray.get(i).toString());
//                    }
//                   settypelist(tl);
//                } catch (JSONException e) {
//                    Log.i("Error",e.toString());
//                }
//            }
//        });
//        return typelist;
//    }
//
//    public void settypelist(ArrayList typelist) {
//        this.typelist = typelist;
//    }
//
//    RequestQueue rQueue;
//    public void volleygetTypeController(final ServerCallback callback) {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject js) {
//                callback.onSuccess(js);
////                try {
////                    JSONArray jarray = js.getJSONArray("type");
////                    ArrayList tl = new ArrayList();
////                    for(int i=0;i<jarray.length();i++) { //here get the jsonobject from the /type
////                        tl.add(jarray.get(i).toString());
////                    }
////\                    rQueue.getCache().clear();
////                } catch (JSONException e) {
////                    Log.i("Error",e.toString());
////                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println(volleyError.toString());
//            }
//        });
//        rQueue = Volley.newRequestQueue(act);
//        rQueue.add(jsonObjectRequest);
//    }




}


