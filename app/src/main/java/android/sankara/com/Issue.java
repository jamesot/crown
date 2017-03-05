package android.sankara.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.dd.morphingbutton.MorphingButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.gmariotti.cardslib.library.internal.Card;

public class Issue extends AppCompatActivity {
    int room;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_issue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        room = getIntent().getIntExtra("id", 1);
        setTitle("Select facility to report");

/*
        if (MyShortcuts.hasInternetConnected(this)) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Getting data ...");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

//            spinner.setItems("Room 1", "Room 2", "Room 3", "Room 4", "Presidential Room®");
            getFacility();
        } else {
            MyShortcuts.showToast("Please turn on your data connection!", getBaseContext());
        }*/
//        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.content_acceptance);

       /* final MorphingButton btnMorph1 = new MorphingButton(this);
        linearLayout.addView(btnMorph1);
        View view=new View(this);
        linearLayout.addView(view);
        final MorphingButton btnMorph2 = new MorphingButton(this);
        linearLayout.addView(btnMorph2);
        View view1=new View(this);
        linearLayout.addView(view1);
        final MorphingButton btnMorph3 = new MorphingButton(this);
        linearLayout.addView(btnMorph3);
        btnMorph2.setText("3");
        View view2=new View(this);
        linearLayout.addView(view2);
        final MorphingButton btnMorph4 = new MorphingButton(this);
        linearLayout.addView(btnMorph4);
        View view3=new View(this);
        linearLayout.addView(view3);
        final MorphingButton btnMorph5 = new MorphingButton(this);
        btnMorph5.setText("5");
        linearLayout.addView(btnMorph5);*/

        final MorphingButton btnMorph1 = (MorphingButton) findViewById(R.id.btnMorph1);
        btnMorph1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph1);
            }
        });
        final MorphingButton btnMorph2 = (MorphingButton) findViewById(R.id.btnMorph2);
        btnMorph2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph2);
            }
        });
        final MorphingButton btnMorph3 = (MorphingButton) findViewById(R.id.btnMorph3);
        btnMorph3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph3);
            }
        });
        final MorphingButton btnMorph4 = (MorphingButton) findViewById(R.id.btnMorph4);
        btnMorph4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph4);
            }
        });
        final MorphingButton btnMorph5 = (MorphingButton) findViewById(R.id.btnMorph5);
        btnMorph5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph5);
            }
        });


        Button btn = (Button) findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyShortcuts.showToast("Your concern submitted successfully, we will working on it shortly!", getBaseContext());
                Intent intent = new Intent(getBaseContext(), ChatActivity.class);
                EditText et = (EditText)findViewById(R.id.issue);
                intent.putExtra("issue",et.getText().toString());
                startActivity(intent);
            }
        });


    }

    private void getFacility() {
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


                    for (int i = 0; i < res.length(); i++) {

                        JSONObject item = res.getJSONObject(i);
                        String facilityName = item.getString("facility_name");



                    }
//
                    mProgressDialog.dismiss();

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

    private void morphToSuccess(final MorphingButton btnMorph) {
        btnMorph.setVisibility(View.VISIBLE);
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(getResources().getInteger(R.integer.mb_animation))
                .cornerRadius((int) getResources().getDimension(R.dimen.mb_height_56))
                .width((int) getResources().getDimension(R.dimen.mb_height_56))
                .height((int) getResources().getDimension(R.dimen.mb_height_56))
                .color(getResources().getColor(R.color.mb_green))
                .colorPressed(getResources().getColor(R.color.mb_green_dark))
                .icon(R.drawable.ic_done);
        btnMorph.morph(circle);
    }
}
