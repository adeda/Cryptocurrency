package enizneziric.cryptocurrency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CurrencyDetails extends AppCompatActivity {


    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    String item1,item2,item3,item4,item5,item6,item7,item8,item9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_details);

        textView1 = findViewById(R.id.tv_details1);
        textView2 = findViewById(R.id.tv_details2);
        textView3 = findViewById(R.id.tv_details3);
        textView4 = findViewById(R.id.tv_details4);
        textView5 = findViewById(R.id.tv_details5);
        textView6 = findViewById(R.id.tv_details6);
        textView7 = findViewById(R.id.tv_details7);
        textView8 = findViewById(R.id.tv_details8);
        textView9 = findViewById(R.id.tv_details9);

        item1 = getIntent().getStringExtra("rank");
        item2 = getIntent().getStringExtra("name");
        item3 = getIntent().getStringExtra("symbol");
        item4 = getIntent().getStringExtra("price_usd");
        item5 = getIntent().getStringExtra("price_btc");
        item6 = getIntent().getStringExtra("percent_change_1h");
        item7 = getIntent().getStringExtra("percent_change_24h");
        item8 = getIntent().getStringExtra("percent_change_7d");
        item9 = getIntent().getStringExtra("total_supply");

        textView1.setText(item1);
        textView2.setText(item2);
        textView3.setText(item3);
        textView4.setText(item4);
        textView5.setText(item5);
        textView6.setText(item6);
        textView7.setText(item7);
        textView8.setText(item8);
        textView9.setText(item9);

    }
}
