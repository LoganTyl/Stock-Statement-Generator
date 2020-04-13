import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class JsonToHtml {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public void convertJsonToHtml(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("stock_transations.by.account.holder.json")){
            Object object = jsonParser.parse(reader);
            JSONArray stockList = (JSONArray) object;
            stockList.forEach(account -> {
                try {
                    parseAccountObject((JSONObject) account);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void parseAccountObject(JSONObject singleAccount) throws IOException {
        Account account = new Account();
        account.setAccount_num((long) singleAccount.get("account_number"));
        account.setSsn((String) singleAccount.get("ssn"));
        account.setFirst_name((String) singleAccount.get("first_name"));
        account.setLast_name((String) singleAccount.get("last_name"));
        account.setEmail((String) singleAccount.get("email"));
        account.setPhone((String) singleAccount.get("phone"));
        account.setBeginning_balance(moneyStringToDouble((String) singleAccount.get("beginning_balance")));
        account.setStock_holdings_balance(0);
        List<Transaction> transactionList = new ArrayList<>();
        ((JSONArray)singleAccount.get("stock_trades")).forEach(transaction -> parseTransactionObject((JSONObject) transaction, transactionList));
        account.setStock_trades(transactionList);
        setBalanceAndStockHoldings(account);
        printToHTML(account);
    }

    private void parseTransactionObject(JSONObject singleTransaction, List<Transaction> transactionList) {
        Transaction trans = new Transaction();
        trans.setType((String) singleTransaction.get("type"));
        trans.setStock_symbol((String) singleTransaction.get("stock_symbol"));
        trans.setCount_shares((long) singleTransaction.get("count_shares"));
        trans.setPrice_per_share(moneyStringToDouble((String) singleTransaction.get("price_per_share")));
        transactionList.add(trans);
    }

    private double moneyStringToDouble(String balance){
        double convertedBalance;
        if(Character.toString(balance.charAt(0)).equals("$")){
            String balWithoutSign = balance.substring(1);
            convertedBalance = Double.parseDouble(balWithoutSign);
        }
        else{
            throw new IllegalArgumentException("Input must be a double with a '$' in front");
        }
        return convertedBalance;
    }

    private void printToHTML(Account account) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        OutputStream outputStream = new FileOutputStream(String.format("html/%d.html", account.getAccount_num()));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write("<!DOCTYPE html>");
        outputStreamWriter.write("<html><body>");

        outputStreamWriter.write(String.format("<h1>Name: %s %s; <small>Account Number: %d; Statement Date: %s</small></h1>", account.getFirst_name(), account.getLast_name(), account.getAccount_num(), dtf.format(now)));
        outputStreamWriter.write(String.format("<h2>SSN: %s</h2>", account.getSsn()));
        outputStreamWriter.write("<h2><u>Contact</u></h2>");
        outputStreamWriter.write(String.format("<h3>Email: %s</h3>", account.getEmail()));
        outputStreamWriter.write(String.format("<h3>Phone Number: %s</h3><br/><br/>", account.getPhone()));
        outputStreamWriter.write("<table>");
        outputStreamWriter.write("<tr><th>Transaction Type</th><th>Stock Symbol</th><th>Price Per Share</th><th>Total Shares Bought/Sold</th><th>Total Amount</th></tr>");
        for (Transaction transaction: account.getStock_trades()) {
            outputStreamWriter.write(String.format("<tr><td>%s</td><td>%s</td><td>$%.2f</td><td>%d</td><td>$%.2f</td></tr>", transaction.getType(), transaction.getStock_symbol(), transaction.getPrice_per_share(), transaction.getCount_shares(), ((double) transaction.getCount_shares() * transaction.getPrice_per_share())));
        }
        outputStreamWriter.write("</table><br/><br/>");
        outputStreamWriter.write(String.format("Account Balance: $%.2f | Account Stock Holdings: $%.2f", account.getBeginning_balance(), account.getStock_holdings_balance()));
        outputStreamWriter.write("</body></html>");
        outputStreamWriter.close();
    }

    private void setBalanceAndStockHoldings(Account account){
        for(Transaction transaction : account.getStock_trades()){
            if(transaction.getType().equalsIgnoreCase("Buy")){
                account.setBeginning_balance(account.getBeginning_balance() - (transaction.getCount_shares() * transaction.getPrice_per_share()));
                account.setStock_holdings_balance(account.getBeginning_balance() + (transaction.getCount_shares() * transaction.getPrice_per_share()));
            }
            else if(transaction.getType().equalsIgnoreCase("Sell")){
                account.setBeginning_balance(account.getBeginning_balance() + (transaction.getCount_shares() * transaction.getPrice_per_share()));
                account.setStock_holdings_balance(account.getBeginning_balance() - (transaction.getCount_shares() * transaction.getPrice_per_share()));
            }
        }
    }
}
