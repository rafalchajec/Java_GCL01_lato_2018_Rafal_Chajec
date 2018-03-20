package User;

import Exception.UserLoginException;
import Exception.UserRemoveException;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChatEngine {


    public static int licznik = 0;

    Map<Long, User> userMap = new LinkedHashMap<>();

    void loginUser(User user) throws UserLoginException {
        if (userMap.containsValue(user.getName())) {
            throw new UserLoginException();
        }
        else {
            userMap.put(user.getId(), user);
            licznik++;
            user.setId(licznik);
        }

    }

    void logoutUser(long userId) throws UserRemoveException {
        if (userMap.remove(userId) == null)
            throw new UserRemoveException();
        else
            licznik--;
    }

    int getNumberOfUsers() {
        return userMap.size();
    }

    void addUserStar(String userName) {
        for (Map.Entry<Long, User> entry : userMap.entrySet())
            if (entry.getValue().getName().equals(userName)) {
                entry.getValue().setNumberOfStars(1);
            }
    }

    void removeUserStar(String userName) {
        for (Map.Entry<Long, User> entry : userMap.entrySet())
            if (entry.getValue().getName().equals(userName)) {
                entry.getValue().setNumberOfStars(0);
                break;
            }
    }

    boolean useUser(long userId) {
        return userMap.containsKey(userId);
    }

    boolean useUser(String userName) {

        for (Map.Entry<Long, User> entry : userMap.entrySet()) {
            if (entry.getValue().getName().equals(userName))
                return true;
        }

        return false;
    }

}
