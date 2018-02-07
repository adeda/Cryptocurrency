package enizneziric.cryptocurrency;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import enizneziric.cryptocurrency.adapters.MainAdapter;
import enizneziric.cryptocurrency.models.Currency;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String JSON_URL = "https://api.coinmarketcap.com/v1/ticker/";
    private ListView listView;
    private List<Currency> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        currencyList = new ArrayList<>();
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        currencyLoad();
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(30);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Currency currency = currencyList.get(i);
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
                String rank = currency.getRank();
                String name = currency.getName();
                String symbol = currency.getSymbol();
                String price_usd = currencyFormat.format(Float.parseFloat(currency.getPrice_usd()));
                String price_btc = currencyFormat.format(Float.parseFloat(currency.getPrice_btc()));
                String total_supply = currencyFormat.format(Float.parseFloat(currency.getTotal_supply()));
                String percent_change_1h = currencyFormat.format(Float.parseFloat(currency.getPercent_change_1h()));
                String percent_change_24h = currencyFormat.format(Float.parseFloat(currency.getPercent_change_24h()));
                String percent_change_7d = currencyFormat.format(Float.parseFloat(currency.getPercent_change_7d()));

                Intent intent = new Intent(MainActivity.this,CurrencyDetails.class);
                intent.putExtra("rank",rank);
                intent.putExtra("name",name);
                intent.putExtra("symbol",symbol);
                intent.putExtra("price_usd",price_usd);
                intent.putExtra("price_btc",price_btc);
                intent.putExtra("percent_change_1h",percent_change_1h);
                intent.putExtra("percent_change_24h",percent_change_24h);
                intent.putExtra("percent_change_7d",percent_change_7d);
                intent.putExtra("total_supply",total_supply);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {

            case R.id.eur:
                Intent intent = new Intent(MainActivity.this,EurActivity.class);
                startActivity(intent);
                break;

            case R.id.cny:
                Intent intent1 = new Intent(MainActivity.this,CnyActivity.class);
                startActivity(intent1);
            default:
                break;
        }

        return true;
    }

    private void currencyLoad() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject currencyObject = jsonArray.getJSONObject(i);
                        Currency currency = new Currency(currencyObject.getString("rank"),currencyObject.getString("name"),currencyObject.getString("symbol"), currencyObject.getString("price_usd"),
                                currencyObject.getString("price_btc"), currencyObject.getString("percent_change_1h"),
                                currencyObject.getString("percent_change_24h"),currencyObject.getString("percent_change_7d"),
                                currencyObject.getString("total_supply"));
                        currencyList.add(currency);
                    }
                    MainAdapter adapterList = new MainAdapter(currencyList, getApplicationContext());
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
