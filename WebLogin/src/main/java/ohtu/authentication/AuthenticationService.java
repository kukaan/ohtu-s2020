package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

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

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (username.length()<3 ) {
            status.addError("username should have at least 3 characters");
        }

        if (password.length() < 8) {
            status.addError("password should have at least 8 characters");
        }

        if (!username.matches("^[a-z]+$")) {
            status.addError("username should be formed only of letters a-z");
        }

        if (!password.equals(passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }

        boolean passwordIsValid = false;

        char[] p = password.toCharArray();
        for (int i = 0; i < p.length; i++) {
            if (!Character.isLetter(p[i])) {
                passwordIsValid = true;
            }
        }
        if (!passwordIsValid) {
            status.addError("password should contain non-letter characters");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

}
