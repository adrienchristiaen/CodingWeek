package eu.telecomnancy.labfx.user;

import eu.telecomnancy.labfx.utils.ItemTuple;
import eu.telecomnancy.labfx.utils.JsonUserReader;
import eu.telecomnancy.labfx.utils.JsonUserWritter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserController {
    //use singleton pattern to create only one instance of the controller whitch contains the user list ArrayList<User>
    private static UserController instance = null;
    private ArrayList<User> users;
    //singleton pattern
    private UserController() {
        this.users = JsonUserReader.read("/eu/telecomnancy/labfx/data/user.json");
    }
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }
    //getters
    public ArrayList<User> getUsers() {
        return users;
    }
    public void addUser(User user) {
        users.add(user);
    }

    public void saveUsers() {
        JsonUserWritter.write("src/main/resources/eu/telecomnancy/labfx/data/user.json", this.users);
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public User getUserByIdentifiant(String identifiant) {
        for (User user : users) {
            if (user.getIdentifiant().equals(identifiant)) {
                return user;
            }
        }
        return null;
    }
    public User testConnexion(String identifiant, String password) {
        User user = getUserByIdentifiant(identifiant);
        if (user==null){
            return null;
        }
        else if (user.getPassword().equals(password)){
            return user;
        }
        else{
            return null;
        }
    }

    public int getMaxId() {
        int maxId = 0;
        for (User user : users) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId;
    }

    public void createClassicUser(String identifiant, String password, String firstName, String lastName, String email, String city, int florains, String image, String description) {
        int id = getMaxId();
        User user = new ClassicUser(id, identifiant, password, firstName, lastName, email, city, florains, LocalDateTime.now(), image, description, new ArrayList<Integer>(), new ArrayList<ItemTuple>(), new ArrayList<ItemTuple>());
        users.add(user);
        saveUsers();
    }

}
