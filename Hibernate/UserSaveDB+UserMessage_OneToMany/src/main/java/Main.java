import dao.DAOimpl;
import entities.User;
import dao.UserDAO;
import entities.UserMessage;

public class Main {
    static UserDAO userDAO = new DAOimpl();

    public static void main(String[] args) {


        User user = new User("Vasia", "Pupkin", "Vasek-pupok@gmail.com", "+3755-555-55-55");
        user.addMessage(new UserMessage("Hi!"));
        userDAO.saveUser(user);
        User user2 = new User("Igar", "Pirogov", "Igarok-pirajok@gmail.com", "+3755-777-77-77");
        user2.addMessage(new UserMessage("Hi! I'm Igar!"));
        user2.addMessage(new UserMessage("how are you?"));
        userDAO.saveUser(user2);



    }
}
