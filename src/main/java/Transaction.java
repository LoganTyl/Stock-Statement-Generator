public class Transaction {
    private String type;
    private String stock_symbol;
    private int count_shares;
    private double price_per_share;

    public Transaction(String type, String stock_symbol, int count_shares, double price_per_share) {
        this.type = type;
        this.stock_symbol = stock_symbol;
        this.count_shares = count_shares;
        this.price_per_share = price_per_share;
    }

    public Transaction(){};

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStock_symbol() {
        return stock_symbol;
    }

    public void setStock_symbol(String stock_symbol) {
        this.stock_symbol = stock_symbol;
    }

    public int getCount_shares() {
        return count_shares;
    }

    public void setCount_shares(int count_shares) {
        this.count_shares = count_shares;
    }

    public double getPrice_per_share() {
        return price_per_share;
    }

    public void setPrice_per_share(double price_per_share) {
        this.price_per_share = price_per_share;
    }
}
