package com.muhammad.ari.firstvolley;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.muhammad.ari.firstvolley.Url.Url.url;
import static com.muhammad.ari.firstvolley.Url.Url.urlData;

public class ListActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView lvSantri;
    RequestQueue requestQueue;
    StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lvSantri = (RecyclerView) findViewById(R.id.list_santri);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listMethod();

            }
        });

        listMethod();


    }

    private void listMethod() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //membuat list lv_santri akan secara otomatis bertambah kebawah (vertical) bila ada tambahan data
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //membuat list lv_santri menjadi lebih lembut saat di swipe
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        lvSantri.setLayoutManager(linearLayoutManager);


        //untuk menambah queue baru
        requestQueue = Volley.newRequestQueue(ListActivity.this);

        final ArrayList<HashMap<String, String>> list;
        list = new ArrayList<>();


        stringRequest = new StringRequest(Request.Method.POST, urlData, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("santri");
                    // for berfungsi untuk menghitung panjang array database
                    for (int a = 0; a < jsonArray.length(); a++) {
                        final JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> parampa = new HashMap<>();

                        //mengambil setiap data JSON yang ada pada database dengan parameter
                        parampa.put("id", json.getString("id"));
                        parampa.put("gambar", json.getString("gambar"));
                        parampa.put("hd", json.getString("hd"));
                        parampa.put("nama", json.getString("nama"));
                        parampa.put("keterangan", json.getString("keterangan"));
                        parampa.put("profesi", json.getString("profesi"));
                        //menaruh setiap data yang telah diambil pada Arraylist
                        list.add(parampa);
                        AdapterList adapter = new AdapterList(ListActivity.this, list);
                        lvSantri.setAdapter(adapter);

//                        ================= refresh layout ==========================
                        refresh();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //menampilkan pesan keterangan error ketika error
                Toast.makeText(ListActivity.this, "error" + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }

    private void refresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
