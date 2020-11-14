package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password

        if (userDao.findByName(username) != null) {
            return true;
        }

        if (username.length() < 3) {
            return true;
        }
        if (password.length() < 8) {
            return true;
        }

        if (!username.matches("^[a-z]+$")) {
            return true;
        }

        char[] p = password.toCharArray();
        for (int i = 0; i < p.length; i++) {
            if (!Character.isLetter(p[i])) {
                return false;
            }
        }
        return true;

        /*
        username.toUpperCase();
        char[] u = username.toCharArray();
        for (int i = 0; i < u.length; i++) {
            int value = Character.getNumericValue(u[i]);
            if (value < 10 || value > 35) {
                return true;
            }
        }

        if (password.matches("^[a-zA-Z]+$")) {
            return true;
        }
        */
    }
}
