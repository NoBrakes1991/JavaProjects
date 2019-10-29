import dao.DAOimpl;
import entities.User;
import dao.UserDAO;

public class Main {
    static UserDAO userDAO = new DAOimpl();
    public static void main(String[] args) {


        User user = new User("Vasia", "Pupkin", "Vasek-pupok@gmail.com", "+3755-555-55-55");
        userDAO.saveUser(user);
    }
}
