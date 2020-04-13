import java.util.List;

public class Account {
    private long account_num;
    private String ssn;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private double beginning_balance;
    private double stock_holdings_balance;
    private List<Transaction> stock_trades;

    public Account(long account_num, String ssn, String first_name, String last_name, String email, String phone, double beginning_balance, double stock_holdings_balance, List<Transaction> stock_trades) {
        this.account_num = account_num;
        this.ssn = ssn;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.beginning_balance = beginning_balance;
        this.stock_holdings_balance = stock_holdings_balance;
        this.stock_trades = stock_trades;
    }

    public Account(){ };

    public long getAccount_num() {
        return account_num;
    }

    public void setAccount_num(long account_num) {
        this.account_num = account_num;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBeginning_balance() {
        return beginning_balance;
    }

    public void setBeginning_balance(double beginning_balance) {
        this.beginning_balance = beginning_balance;
    }

    public double getStock_holdings_balance() {
        return stock_holdings_balance;
    }

    public void setStock_holdings_balance(double stock_holdings_balance) {
        this.stock_holdings_balance = stock_holdings_balance;
    }

    public List<Transaction> getStock_trades() {
        return stock_trades;
    }

    public void setStock_trades(List<Transaction> stock_trades) {
        this.stock_trades = stock_trades;
    }

}
