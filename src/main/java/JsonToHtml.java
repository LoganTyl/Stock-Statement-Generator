import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class JsonToHtml {
    public void convertJsonToHtml(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("stock_transations.by.account.holder.json")){
            Object object = jsonParser.parse(reader);
            JSONArray stockList = (JSONArray) object;
            stockList.forEach(account -> parseAccountObject((JSONObject) account));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void parseAccountObject(JSONObject singleAccount){
        Account account = new Account();
        account.setAccount_num((int) singleAccount.get("account_number"));
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
        printToHTML(account);
    }

    private void parseTransactionObject(JSONObject singleTransaction, List<Transaction> transactionList) {
        Transaction trans = new Transaction();
        trans.setType((String) singleTransaction.get("type"));
        trans.setStock_symbol((String) singleTransaction.get("stock_symbol"));
        trans.setCount_shares((int) singleTransaction.get("count_shares"));
        trans.setPrice_per_share(moneyStringToDouble((String) singleTransaction.get("price_per_share")));
        transactionList.add(trans);
    }

    public double moneyStringToDouble(String balance){
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

    public void printToHTML(Account account){
        
    }
}
