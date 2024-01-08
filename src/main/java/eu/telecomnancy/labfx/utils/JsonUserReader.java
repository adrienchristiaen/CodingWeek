package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.user.AdminUser;
import eu.telecomnancy.labfx.user.ClassicUser;
import eu.telecomnancy.labfx.user.User;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class JsonUserReader implements JsonReader{
    private static JsonUserReader instance = null;
    ArrayList<User> users;
    String json = "src/main/resources/user.json";
    private JsonUserReader() {
        this.users = read();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public JsonUserReader getInstances(){
        //singleton
        if (instance == null){
            instance = new JsonUserReader();
        }
        return instance;
    }

    public ArrayList<User> read() {
        //reads json file and creates users
        System.out.println("Reading users from json: " + json);
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArrayUser = (JSONArray) parser.parse(new FileReader(json));
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

    public User createUserFromJson(JSONObject jsonUser){
        //gets attributes from json
        int id = (int) jsonUser.get("id");
        String identifiant = (String) jsonUser.get("identifiant");
        String password = (String) jsonUser.get("password");
        String firstName = (String) jsonUser.get("firstName");
        String lastName = (String) jsonUser.get("lastName");
        String email = (String) jsonUser.get("email");
        String city = (String) jsonUser.get("city");
        String role = (String) jsonUser.get("role");
        int florains = (int) jsonUser.get("florains");
        LocalDateTime createdAt = (LocalDateTime) jsonUser.get("createdAt");
        String image = (String) jsonUser.get("image");
        String description = (String) jsonUser.get("description");
        ArrayList<Integer> itemsOwned = new ArrayList<Integer>();
        ArrayList<ItemTuple> itemsSell = new ArrayList<ItemTuple>();
        ArrayList<ItemTuple> itemsBuy = new ArrayList<ItemTuple>();
        for (Object itemOwned : (JSONArray) jsonUser.get("itemsOwned")) {itemsOwned.add((int) itemOwned);}
        for (Object itemSell : (JSONArray) jsonUser.get("itemsSell")) {
            JSONObject itemSellJson = (JSONObject) itemSell;
            itemsSell.add(new ItemTuple((int) itemSellJson.get("id"), (LocalDateTime) itemSellJson.get("transactionTime")));
        }
        for (Object itemBuy : (JSONArray) jsonUser.get("itemsBuy")) {
            JSONObject itemBuyJson = (JSONObject) itemBuy;
            itemsBuy.add(new ItemTuple((int) itemBuyJson.get("id"), (LocalDateTime) itemBuyJson.get("transactionTime")));
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
