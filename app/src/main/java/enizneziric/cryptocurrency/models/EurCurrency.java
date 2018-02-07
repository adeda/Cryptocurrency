package enizneziric.cryptocurrency.models;

public class EurCurrency {

    private String id,name,symbol,rank,price_usd,price_btc,
            volume_usd_24h,market_cap_usd,available_supply,
            total_supply,max_supply,percent_change_1h,percent_change_24h,
            percent_change_7d,last_updated,price_eur,volume_eur_24h,market_cap_eur;

    public EurCurrency(String name, String symbol, String rank, String price_eur, String volume_eur_24h, String market_cap_eur) {
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.price_eur = price_eur;
        this.volume_eur_24h = volume_eur_24h;
        this.market_cap_eur = market_cap_eur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public String getVolume_usd_24h() {
        return volume_usd_24h;
    }

    public void setVolume_usd_24h(String volume_usd_24h) {
        this.volume_usd_24h = volume_usd_24h;
    }

    public String getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(String market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public String getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(String available_supply) {
        this.available_supply = available_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public String getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(String max_supply) {
        this.max_supply = max_supply;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(String percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(String percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getPrice_eur() {
        return price_eur;
    }

    public void setPrice_eur(String price_eur) {
        this.price_eur = price_eur;
    }

    public String getVolume_eur_24h() {
        return volume_eur_24h;
    }

    public void setVolume_eur_24h(String volume_eur_24h) {
        this.volume_eur_24h = volume_eur_24h;
    }

    public String getMarket_cap_eur() {
        return market_cap_eur;
    }

    public void setMarket_cap_eur(String market_cap_eur) {
        this.market_cap_eur = market_cap_eur;
    }
}
