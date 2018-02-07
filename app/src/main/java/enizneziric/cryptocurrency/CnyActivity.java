package enizneziric.cryptocurrency;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
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
import java.util.List;

import enizneziric.cryptocurrency.adapters.CnyAdapter;
import enizneziric.cryptocurrency.models.CnyCurrency;

public class CnyActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String JSON_URL = "https://api.coinmarketcap.com/v1/ticker/?convert=CNY";
    private ListView listView;
    private List<CnyCurrency> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cny);

        listView = findViewById(R.id.list_cny);
        currencyList = new ArrayList<>();
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_cny);
        currencyLoad();
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(30);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
    }

    private void currencyLoad() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject currencyObject = jsonArray.getJSONObject(i);
                        CnyCurrency currency = new CnyCurrency(currencyObject.getString("name"),currencyObject.getString("symbol"),
                                currencyObject.getString("rank"), currencyObject.getString("price_cny"),
                                currencyObject.getString("24h_volume_cny"), currencyObject.getString("market_cap_cny"));
                        currencyList.add(currency);
                    }
                    CnyAdapter adapterList = new CnyAdapter(currencyList, getApplicationContext());
                    listView.setAdapter(adapterList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(true);
                    }
                }, 1000);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onRefresh() {

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                currencyList.clear();
                currencyLoad();
            }
        }, 1000);
    }
}
