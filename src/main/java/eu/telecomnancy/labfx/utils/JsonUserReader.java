package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.user.AdminUser;
import eu.telecomnancy.labfx.user.ClassicUser;
import eu.telecomnancy.labfx.user.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class JsonUserReader implements JsonReader{

    public static ArrayList<User> read(String json) {

        //reads json file and creates users
        System.out.println("Reading users from json: " + json);
        JSONParser parser = new JSONParser();
        try {
            org.json.simple.JSONArray jsonArrayUser = (org.json.simple.JSONArray) parser.parse(new FileReader(json));
                        //create an array for each user
            ArrayList<User> users = new ArrayList<User>();
            for (Object jsonUser : jsonArrayUser) {
                JSONObject user = (JSONObject) jsonUser;
                users.add(createUserFromJson(user));
            }
            return users;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User createUserFromJson(JSONObject jsonUser){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //gets attributes from json
        long idLong = (long)jsonUser.get("id");
        int id = (int) idLong;
        String identifiant = (String) jsonUser.get("identifiant");
        String password = (String) jsonUser.get("password");
        String firstName = (String) jsonUser.get("firstName");
        String lastName = (String) jsonUser.get("lastName");
        String email = (String) jsonUser.get("email");
        String city = (String) jsonUser.get("city");
        String role = (String) jsonUser.get("role");
        long florainsLong = (long) jsonUser.get("florains");
        int florains = (int) florainsLong;
        LocalDateTime createdAt = LocalDateTime.parse((String) jsonUser.get("createdAt"), formatter);
        String image = (String) jsonUser.get("image");
        String description = (String) jsonUser.get("description");
        ArrayList<Integer> itemsOwned = new ArrayList<Integer>();
        ArrayList<ItemTuple> itemsSell = new ArrayList<ItemTuple>();
        ArrayList<ItemTuple> itemsBuy = new ArrayList<ItemTuple>();
        for (Object itemOwned : (JSONArray) jsonUser.get("itemsOwn")) {
            long itemOwnedLong = (long) itemOwned;
            itemsOwned.add((int) itemOwnedLong);
        }
        for (Object itemSell : (JSONArray) jsonUser.get("itemsSell")) {
            JSONObject itemSellJson = (JSONObject) itemSell;
            long itemSellLong = (long) itemSellJson.get("id");
            itemsSell.add(new ItemTuple((int) itemSellLong, LocalDateTime.parse((String) itemSellJson.get("transactionTime"), formatter)));
        }
        for (Object itemBuy : (JSONArray) jsonUser.get("itemsBuy")) {
            JSONObject itemBuyJson = (JSONObject) itemBuy;
            long itemBuyLong = (long) itemBuyJson.get("id");
            itemsBuy.add(new ItemTuple((int) itemBuyLong, LocalDateTime.parse((String) itemBuyJson.get("transactionTime"), formatter)));
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
