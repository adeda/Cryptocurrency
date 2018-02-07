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
import enizneziric.cryptocurrency.models.EurCurrency;

public class EurAdapter extends ArrayAdapter<EurCurrency> {

    private List<EurCurrency> currencyList;
    private Context context;

    public EurAdapter(List<EurCurrency> currencyList, Context context){
        super(context, R.layout.list_eur, currencyList);
        this.currencyList = currencyList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listItems = inflater.inflate(R.layout.list_eur,null,true);

        TextView name = listItems.findViewById(R.id.tv_eur1);
        TextView symbol = listItems.findViewById(R.id.tv_eur2);
        TextView rank = listItems.findViewById(R.id.tv_eur3);
        TextView price_eur = listItems.findViewById(R.id.tv_eur4);
        TextView volume_eur_24h = listItems.findViewById(R.id.tv_eur5);
        TextView market_cap_eur = listItems.findViewById(R.id.tv_eur6);

        EurCurrency currency = currencyList.get(position);

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        name.setText(currency.getName());
        symbol.setText(currency.getSymbol());
        rank.setText(currency.getRank());
        price_eur.setText(currencyFormat.format(Float.parseFloat(currency.getPrice_eur())));
        volume_eur_24h.setText(currencyFormat.format(Float.parseFloat(currency.getVolume_eur_24h())));
        market_cap_eur.setText(currencyFormat.format(Float.parseFloat(currency.getMarket_cap_eur())));

        return  listItems;
    }
}
