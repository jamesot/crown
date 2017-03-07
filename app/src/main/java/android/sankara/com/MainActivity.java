package android.sankara.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.gmariotti.cardslib.library.internal.Card;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    MaterialSpinnerAdapter<String> adapter;
    MaterialSpinner spinner;
    ArrayList<String> data2 = new ArrayList<String>();
    ArrayList<String> id = new ArrayList<String>();

    ArrayAdapter<String> adapter2;
    // Used to load the 'native-lib' library on application startup.
    /*static {
        System.loadLibrary("native-lib");
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Sankara Hotel");
        MyShortcuts.setDefaults("chat_Watcher", "0", getBaseContext());

//        getIntent().getStringExtra("Sankara Hotel");

        // Example of a call to a native method
        /*TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());*/
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        if (MyShortcuts.hasInternetConnected(this)) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Getting data ...");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

//            spinner.setItems("Room 1", "Room 2", "Room 3", "Room 4", "Presidential Room®");
            getRooms();
        } else {
            MyShortcuts.showToast("Please turn on your data connection!", getBaseContext());
        }
//        spinner.setItems("101", "102", "103", "104", "Presidential Room®");
//        spinner.setItems("101", "102", "103", "104", "105");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                MyShortcuts.showToast(item + " picked!   ", getBaseContext());
                Intent intent = new Intent(getBaseContext(), Issue.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();

    private void getRooms() {
        // Toast.makeText(getBaseContext(), "Inside function!", Toast.LENGTH_SHORT).show();
        // Tag used to cancel the request

//        Log.e("JSON serializing", js.toString());
        String tag_string_req = "req_Categories";

        Log.e("url is", MyShortcuts.baseURL() + "/sankara/api/roomJson.php");
        StringRequest strReq = new StringRequest(Request.Method.GET, MyShortcuts.baseURL() + "/sankara/api/roomJson.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response from server is", response.toString());

                ArrayList<Card> cards = new ArrayList<Card>();
                String success = null;
                try {

                    JSONArray res = new JSONArray(response);

                    data2.add("Choose room");

                    for (int i = 0; i < res.length(); i++) {

                        JSONObject shipper = res.getJSONObject(i);
                        String room = shipper.getString("room_name");

                        data2.add(room);
                        String id_no = shipper.getString("id");
                        id.add(id_no);
                    }
                    spinner.setItems(data2);
//                    spinner.notifyAll();
//                    adapter = new MaterialSpinnerAdapter<String>(getBaseContext(),data2);
                   /* adapter2 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, data2);
                    spinner.setAdapter(adapter2);
                    spinner.setTextColor(Color.BLACK);*/
//                    adapter.notifyDataSetChanged();
                    mProgressDialog.dismiss();


                   /* // looping through All res


                  *//*  adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, data);
                    spinner.setAdapter(adapter);*//*
                    adapter.notifyDataSetChanged();*/
                    if (res.length() == 0) {
                        Toast.makeText(getBaseContext(), "No room service available! please contact our service desk ", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
//                    Toast.makeText(getBaseContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("JSON ERROR", e.toString());
                    mProgressDialog.dismiss();
                }
            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("VolleyError", "Error: " + error.getMessage());
//                hideProgressDialog();
                mProgressDialog.dismiss();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                setRetryPolicy(new DefaultRetryPolicy(5 * DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 0));
                setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
                headers.put("Content-Type", "application/json; charset=utf-8");

                String creds = String.format("%s:%s", "admin", "demo");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                headers.put("Authorization", auth);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
//                Log.e("category id", getIntent().getStringExtra("category_id"));
//                params.put("categoryId", 2 + "");


                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        Log.e("request is", strReq.toString());
    }
}
