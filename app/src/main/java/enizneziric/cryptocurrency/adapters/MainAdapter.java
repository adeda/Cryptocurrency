package enizneziric.cryptocurrency.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import enizneziric.cryptocurrency.R;
import enizneziric.cryptocurrency.models.Currency;

public class MainAdapter extends ArrayAdapter<Currency> {

    private List<Currency> currencyList;
    private Context context;

    public MainAdapter(List<Currency> currencyList, Context context){
        super(context, R.layout.list_items, currencyList);
        this.currencyList = currencyList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listItems = inflater.inflate(R.layout.list_items,null,true);

        TextView rank = listItems.findViewById(R.id.tv_1);
        TextView symbol = listItems.findViewById(R.id.tv_2);
        TextView price = listItems.findViewById(R.id.tv_3);
        TextView change_24h = listItems.findViewById(R.id.tv_4);

        Currency currency = currencyList.get(position);

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        rank.setText(currency.getRank());
        symbol.setText(currency.getSymbol());
        price.setText(currencyFormat.format(Float.parseFloat(currency.getPrice_usd())));
        change_24h.setText(currencyFormat.format(Float.parseFloat(currency.getPercent_change_24h())));

        return  listItems;
    }
}
