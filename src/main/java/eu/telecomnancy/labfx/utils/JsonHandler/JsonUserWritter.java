package eu.telecomnancy.labfx.utils.JsonHandler;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.ItemTuple;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonUserWritter implements JsonWritter{
    public static void write(String resourcePath, ArrayList<User> users) {
        //writes users in json file
        System.out.println("Writing users in json: " + resourcePath);

        StringBuilder jsonBuilder = new StringBuilder("[\n");
        for (User user : users) {
            jsonBuilder.append("\t{\n")
                    .append("\t\t\"id\": ").append(user.getId()).append(",\n")
                    .append("\t\t\"identifiant\": \"").append(user.getIdentifiant()).append("\",\n")
                    .append("\t\t\"password\": \"").append(user.getPassword()).append("\",\n")
                    .append("\t\t\"firstName\": \"").append(user.getFirstName()).append("\",\n")
                    .append("\t\t\"lastName\": \"").append(user.getLastName()).append("\",\n")
                    .append("\t\t\"email\": \"").append(user.getEmail()).append("\",\n")
                    .append("\t\t\"ville\": \"").append(user.getCity()).append("\",\n")
                    .append("\t\t\"role\": \"").append(user.getRole()).append("\",\n")
                    .append("\t\t\"florains\": ").append(user.getFlorains()).append(",\n");
            String createdAt = user.getCreatedAt().toString();
            createdAt = createdAt.replace("T", " ");
            if (createdAt.contains(".")) {
                createdAt = createdAt.substring(0, createdAt.lastIndexOf("."));
            }
            else if (createdAt.indexOf(":") == createdAt.lastIndexOf(":")){
                createdAt = createdAt.concat(":00");
            }
                jsonBuilder.append("\t\t\"createdAt\": \"").append(createdAt).append("\",\n")
                .append("\t\t\"image\": \"").append(user.getImage()).append("\",\n")
                .append("\t\t\"description\": \"").append(user.getDescription()).append("\",\n");
            if (user.getItemsOwned() == null) {
                jsonBuilder.append("\t\t\"itemsOwn\": [],\n").append("\t\t\"itemsSell\": [");;
            } else {
                jsonBuilder.append("\t\t\"itemsOwn\": ").append(user.getItemsOwned()).append(" ,\n").append("\t\t\"itemsSell\": [");;
            }
            if (user.getItemsSell() == null) {
                jsonBuilder.append("],\n\t\t\"itemsBuy\": [");
            } else if (user.getItemsSell().isEmpty()) {
                jsonBuilder.append("],\n\t\t\"itemsBuy\": [");
            } else {
                for (ItemTuple itemTuple : user.getItemsSell()) {
                    jsonBuilder.append("\n\t\t\t{\"id\": ").append(itemTuple.getId())
                            .append(", \"transactionTime\": \"");
                    String transactionTime = itemTuple.getTansactionTime().toString();
                    transactionTime = transactionTime.replace("T", " ");
                    if (transactionTime.contains(".")) {
                        transactionTime = transactionTime.substring(0, transactionTime.lastIndexOf("."));
                    }
                    else{
                        transactionTime = transactionTime.concat(":00");
                    }
                    jsonBuilder.append(transactionTime)
                            .append("\"},");
                }
                jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
                jsonBuilder.append("\t\t] ,\n")
                        .append("\t\t\"itemsBuy\": [");
            }
            if (user.getItemsBuy() == null) {
                jsonBuilder.append("]\n\t},\n");
            } else if (user.getItemsBuy().isEmpty()) {
                jsonBuilder.append("]\n\t},\n");
            } else {
                for (ItemTuple itemTuple : user.getItemsBuy()) {
                    jsonBuilder.append("\n\t\t\t\t{\"id\": ").append(itemTuple.getId())
                            .append(", \"transactionTime\": \"");
                    String transactionTime = itemTuple.getTansactionTime().toString();
                    transactionTime = transactionTime.replace("T", " ");
                    if (transactionTime.contains(".")) {
                        transactionTime = transactionTime.substring(0, transactionTime.lastIndexOf("."));
                    }
                    else{
                        transactionTime = transactionTime.concat(":00");
                    }
                    jsonBuilder.append(transactionTime)
                            .append("\"},");
                }
                jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
                jsonBuilder.append("\t\t]\n").append("\t},\n");
            }
        }
        jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
        jsonBuilder.append("]");
        String jsonString = jsonBuilder.toString();
        System.out.println("Writing users in json: " + resourcePath);
        String filePath = JsonUserWritter.class.getResource(resourcePath).getFile();
        System.out.println(filePath);
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(jsonString);
            System.out.println(jsonString.lastIndexOf("]"));
            System.out.println("JSON data written successfully to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

