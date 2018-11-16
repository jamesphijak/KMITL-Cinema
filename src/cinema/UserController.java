package cinema;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserController {
    private UserController() {} 
    private static UserController instance = new UserController();
    public static UserController getInstance() {
        return instance;
    }
    
    public EntityManagerFactory emf;
    public EntityManager em;
    public void openConnection(){
        emf = Persistence.createEntityManagerFactory("db/CinemaODB.odb"); // connect to object database file	
	em = emf.createEntityManager();
    }    
    public void closeConnection(){
        em.close();
        emf.close();
    }
    
    private List<User> userList = new ArrayList<User>();
    
    // Operation
    public User getUser(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        // find object
        User user = em.find(User.class, id);
        for(Promotion p : user.getPromotionList()){} // get 
        // Close the database connection:
        em.getTransaction().commit();
        closeConnection();
        return user;
    }
    public void addUser(User user){
        openConnection();
	em.getTransaction().begin(); // start connection
        em.persist(user); // add user to persist
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
        //this.updateUserList(); // update list after add
    }
    public void editUser(int id, User editUser){
        openConnection();
        User user = em.find(User.class, id); // find object
        em.getTransaction().begin();
        
        user.setUsername(editUser.getUsername());
        user.setPassword(editUser.getPassword());
        user.setFirstname(editUser.getFirstname());
        user.setLastname(editUser.getLastname());
        user.setEmail(editUser.getEmail());
        user.setType(editUser.getType());
        
        em.getTransaction().commit();
        closeConnection();
        updateUserList();
    }
    public void deleteUser(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        Query query = em.createQuery("DELETE FROM User u WHERE u.id = :id");
        query.setParameter("id", id).executeUpdate();
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
        // update list after delete
        this.updateUserList();
    }
    
    // Get all
    public void updateUserList() {
        openConnection();
        em.getTransaction().begin(); // start connection
        Query q6 = em.createQuery("SELECT u FROM User u");
        this.userList = q6.getResultList(); // get user
        closeConnection();
    }
    public List<User> getUserList() {
        updateUserList();
        return userList;
    }
    
    // Login & Logout
    private User loginUser;
    private boolean isLogin = false;
    public boolean checkValidUser(String username,String password){
        updateUserList();
        String pass = User.encryptPassword(password);
        
        for (User user : this.userList) {
            if(user.getUsername().equals(username) && user.getPassword().equals(pass)){
                loginUser = user;
                isLogin = true;
                return true;
            }
        } 
        return false;  
    }
    public User getLoginUser() {
        return loginUser;
    }
    public void setLogout(){
        isLogin = false;
    }
    
    // Register
    public boolean checkExistEmail(String email){
        this.updateUserList();
            for (User u : this.userList) {
                if (u.getEmail().equals(email)) {
                    return true;
                }
            }
        return false;
    }
    public boolean checkExistUsername(String username){
        this.updateUserList();
            for (User u : this.userList) {
                if (u.getUsername().equals(username)) {
                    return true;
                }
            }
        return false;
    }
    
    // Search
    private List<User> userSearchList = new ArrayList<User>();
    public void searchUser(String text, String searchOf){
        openConnection();
        em.getTransaction().begin(); // start connection
        Query querySearch = em.createQuery("SELECT u FROM User u WHERE lower(u." + searchOf +") LIKE lower(:" + searchOf +")", User.class);
        querySearch.setParameter(searchOf.toString(), "%"+text+"%");
        List<User> users = querySearch.getResultList(); // get user
        this.userSearchList = users;
        closeConnection();
    }
    public List<User> getUserSearchList(){
        return this.userSearchList;
    }
    
    // Booking
    public void setUserMoney(int id, double money){
        openConnection();
        User user = em.find(User.class, id); // find object
        em.getTransaction().begin();
        user.setMoney(money);
        em.getTransaction().commit();
        closeConnection();
        updateUserList();
    }
    
    // Used Promotion
    public void addUserPromotion(int id, Promotion promotion){
        openConnection();
        em.getTransaction().begin();
        User user = em.find(User.class, id); // find object
        user.addPromotion(promotion);
        em.getTransaction().commit();
        closeConnection();
    }
    
    
    

}
