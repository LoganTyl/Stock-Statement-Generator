import java.io.*;
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

    private void parseAccountObject(JSONObject account){

    }
}
