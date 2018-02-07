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

import enizneziric.cryptocurrency.adapters.EurAdapter;
import enizneziric.cryptocurrency.models.EurCurrency;

public class EurActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String JSON_URL = "https://api.coinmarketcap.com/v1/ticker/?convert=EUR";
    private ListView listView;
    private List<EurCurrency> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eur);

        listView = findViewById(R.id.list_eur);
        currencyList = new ArrayList<>();
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_eur);
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
                        EurCurrency currency = new EurCurrency(currencyObject.getString("name"),currencyObject.getString("symbol"),
                                currencyObject.getString("rank"), currencyObject.getString("price_eur"),
                                currencyObject.getString("24h_volume_eur"), currencyObject.getString("market_cap_eur"));
                        currencyList.add(currency);
                    }
                    EurAdapter adapterList = new EurAdapter(currencyList, getApplicationContext());
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
