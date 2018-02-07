package enizneziric.cryptocurrency.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.Format;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import enizneziric.cryptocurrency.R;
import enizneziric.cryptocurrency.models.CnyCurrency;

public class CnyAdapter extends ArrayAdapter<CnyCurrency> {

    private List<CnyCurrency> currencyList;
    private Context context;

    public CnyAdapter(List<CnyCurrency> currencyList, Context context){
        super(context, R.layout.list_cny, currencyList);
        this.currencyList = currencyList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listItems = inflater.inflate(R.layout.list_cny,null,true);

        TextView name = listItems.findViewById(R.id.tv_cny1);
        TextView symbol = listItems.findViewById(R.id.tv_cny2);
        TextView rank = listItems.findViewById(R.id.tv_cny3);
        TextView price_cny = listItems.findViewById(R.id.tv_cny4);
        TextView volume_cny_24h = listItems.findViewById(R.id.tv_cny5);
        TextView market_cap_cny = listItems.findViewById(R.id.tv_cny6);

        CnyCurrency currency = currencyList.get(position);

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        name.setText(currency.getName());
        symbol.setText(currency.getSymbol());
        rank.setText(currency.getRank());
        price_cny.setText(currencyFormat.format(Float.parseFloat(currency.getPrice_cny())));
        volume_cny_24h.setText(currencyFormat.format(Float.parseFloat(currency.getVolume_cny_24h())));
        market_cap_cny.setText(currencyFormat.format(Float.parseFloat(currency.getMarket_cap_cny())));

        return  listItems;
    }
}
