package eu.telecomnancy.labfx.utils.JsonHandler;

import eu.telecomnancy.labfx.user.AdminUser;
import eu.telecomnancy.labfx.user.ClassicUser;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.ItemTuple;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class JsonUserReader implements JsonReader{

    public static ArrayList<User> read(String resourcePath) {
        ArrayList<User> users = new ArrayList<>();

        String jsonString = null;
        try (InputStream inputStream = JsonUserReader.class.getResourceAsStream(resourcePath)) {
            assert inputStream != null;
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {

                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }

                jsonString = stringBuilder.toString();
                // Now you have your JSON string, proceed with parsing

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //reads json file and creates users
        System.out.println("Reading users from json: " + resourcePath);
        if (jsonString == null) {
            return users;
        }
        JSONArray array = new JSONArray(jsonString);
        if (!jsonString.equals("[]")) {
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonUser = array.getJSONObject(i);
                User user = createUserFromJson(jsonUser);
                users.add(user);
            }
        }
        return users;
    }

    public static User createUserFromJson(JSONObject jsonUser){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //gets attributes from json

        int id = jsonUser.getInt("id");
        String identifiant = jsonUser.getString("identifiant");
        String password = jsonUser.getString("password");
        String firstName = jsonUser.getString("firstName");
        String lastName = jsonUser.getString("lastName");
        String email = jsonUser.getString("email");
        String city =  jsonUser.getString("ville");
        String role =  jsonUser.getString("role");
        int florains = jsonUser.getInt("florains");
        LocalDateTime createdAt = LocalDateTime.parse(jsonUser.getString("createdAt"), formatter);
        String image = jsonUser.getString("image");
        String description = jsonUser.getString("description");
        JSONArray itemsOwnedJson = jsonUser.getJSONArray("itemsOwn");
        JSONArray itemsSellJson = jsonUser.getJSONArray("itemsSell");
        JSONArray itemsBuyJson = jsonUser.getJSONArray("itemsBuy");
        ArrayList<Integer> itemsOwned = new ArrayList<Integer>();
        ArrayList<ItemTuple> itemsSell = new ArrayList<ItemTuple>();
        ArrayList<ItemTuple> itemsBuy = new ArrayList<ItemTuple>();
        for (Object itemOwned : itemsOwnedJson) {
            itemsOwned.add((int) itemOwned);
        }

        for (Object itemSell : itemsSellJson) {
            JSONObject jsonItemSell = (JSONObject) itemSell;
            int idItem = jsonItemSell.getInt("id");
            LocalDateTime transactionTime = LocalDateTime.parse(jsonItemSell.getString("transactionTime"), formatter);
            ItemTuple itemTuple = new ItemTuple(idItem, transactionTime);
            itemsSell.add(itemTuple);
        }
        for (Object itemBuy : itemsBuyJson) {
            JSONObject jsonItemBuy = (JSONObject) itemBuy;
            int idItem = jsonItemBuy.getInt("id");
            LocalDateTime transactionTime = LocalDateTime.parse(jsonItemBuy.getString("transactionTime"), formatter);
            ItemTuple itemTuple = new ItemTuple(idItem, transactionTime);
            itemsBuy.add(itemTuple);
        }
        //creates user
        User user;
        if (role.equals("admin")){
            user = new AdminUser(id, identifiant, password, firstName, lastName, email, city, florains, createdAt, image, description, itemsOwned, itemsSell, itemsBuy);
        }else{
            user = new ClassicUser(id, identifiant, password, firstName, lastName, email, city, florains, createdAt, image, description, itemsOwned, itemsSell, itemsBuy);
        }
        return user;
    }
}
