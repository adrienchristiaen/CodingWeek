package eu.telecomnancy.labfx.utils.JsonHandler;

import eu.telecomnancy.labfx.user.AdminUser;
import eu.telecomnancy.labfx.user.ClassicUser;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.Evaluation;
import eu.telecomnancy.labfx.utils.ItemTuple;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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
        JSONArray favouriteItemsJson = jsonUser.getJSONArray("favorite");
        JSONArray evaluationsJson = jsonUser.getJSONArray("ratings");
        JSONArray itemsSellJson = jsonUser.getJSONArray("itemsSell");
        JSONArray itemsBuyJson = jsonUser.getJSONArray("itemsBuy");
        ArrayList<Integer> itemsOwned = getIntegerArrayFromJson(itemsOwnedJson);
        ArrayList<Integer> favouriteItems = getIntegerArrayFromJson(favouriteItemsJson);
        ArrayList<Evaluation> evaluations = getEvaluationArray(evaluationsJson, formatter);
        ArrayList<ItemTuple> itemsSell = getItemTupleArray(itemsSellJson, formatter);
        ArrayList<ItemTuple> itemsBuy = getItemTupleArray(itemsBuyJson, formatter);

        //creates user
        User user;
        if (role.equals("admin")){
            user = new AdminUser(id, identifiant, password, firstName, lastName, email, city, florains, createdAt, image, description, itemsOwned, favouriteItems, evaluations,itemsSell, itemsBuy);
        }else{
            user = new ClassicUser(id, identifiant, password, firstName, lastName, email, city, florains, createdAt, image, description, itemsOwned, favouriteItems, evaluations,itemsSell, itemsBuy);
        }
        return user;
    }

    public static ArrayList<Integer> getIntegerArrayFromJson(JSONArray jsonArray){
        ArrayList<Integer> integerArray = new ArrayList<Integer>();
        for (Object item : jsonArray) {
            integerArray.add((int) item);
        }
        return integerArray;
    }

    public static ArrayList<Evaluation> getEvaluationArray(JSONArray jsonArray, DateTimeFormatter formatter){
        ArrayList<Evaluation> evaluationArray = new ArrayList<Evaluation>();
        for (Object item : jsonArray) {
            JSONObject jsonEvaluation = (JSONObject) item;
            int id = jsonEvaluation.getInt("id");
            int idUser = jsonEvaluation.getInt("user");
            int rating = jsonEvaluation.getInt("rating");
            String comment = jsonEvaluation.getString("comment");
            LocalDateTime createdAt = LocalDateTime.parse(jsonEvaluation.getString("createdAt"), formatter);
            Evaluation evaluation = new Evaluation(idUser, rating, comment, createdAt);
            evaluationArray.add(evaluation);
        }
        return evaluationArray;
    }

    public static ArrayList<ItemTuple> getItemTupleArray(JSONArray jsonArray, DateTimeFormatter formatter){
        ArrayList<ItemTuple> itemTupleArray = new ArrayList<ItemTuple>();
        for (Object item : jsonArray) {
            JSONObject jsonItemTuple = (JSONObject) item;
            int id = jsonItemTuple.getInt("id");
            LocalDateTime transactionTime = LocalDateTime.parse(jsonItemTuple.getString("transactionTime"), formatter);
            ItemTuple itemTuple = new ItemTuple(id, transactionTime);
            itemTupleArray.add(itemTuple);
        }
        return itemTupleArray;
    }
}
