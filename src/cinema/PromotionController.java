package cinema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PromotionController {
    private PromotionController() {} 
    private static PromotionController instance = new PromotionController();
    public static PromotionController getInstance() {
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
    
    // Operation
    public Promotion getPromotion(int id){
        openConnection();
        // find object
        em.getTransaction().begin(); // start connection
        Promotion promotion = em.find(Promotion.class, id);
        // Close the database connection:
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
        return promotion;
    }
    public void addPromotion(Promotion promotion){
        openConnection();
	em.getTransaction().begin(); // start connection
        em.persist(promotion); // add user to persist
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
    }
    public void editPromotion(int id, Promotion editPromotion){
        openConnection();
        // find object
        Promotion promotion = em.find(Promotion.class, id);
        em.getTransaction().begin();
        promotion.setName(editPromotion.getName());
        promotion.setDescription(editPromotion.getDescription());
        promotion.setDiscount(editPromotion.getDiscount());
        promotion.setRemainingDate(editPromotion.getRemainingDate());
        em.getTransaction().commit();
        closeConnection();
    }
    public void deletePromotion(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        Query query = em.createQuery("DELETE FROM Promotion p WHERE p.promotionID = :id");
        query.setParameter("id", id).executeUpdate();
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
    }
    
    // Get all
    private List<Promotion> promotionList = new ArrayList<Promotion>();
    public void updatePromotionList() {
        openConnection();
        em.getTransaction().begin(); // start connection
        Query query = em.createQuery("SELECT p FROM Promotion p");
        List<Promotion> promotion = query.getResultList(); // get movie
        this.promotionList = promotion;
        closeConnection();
    }
    public List<Promotion> getPromotionList() {
        this.updatePromotionList();
        return promotionList;
    }
    
    // Search
    private List<Promotion> promotionSearchList = new ArrayList<Promotion>();
    public void searchPromotion(String text, String searchOf){
        // ดึงข้อมูลจาก db ส่วน User
        openConnection();
        em.getTransaction().begin(); // start connection
        Query querySearch = em.createQuery("SELECT p FROM Promotion p WHERE lower(p." + searchOf +") LIKE lower(:" + searchOf +")", Promotion.class);
        querySearch.setParameter(searchOf.toString(), "%"+text+"%");
        List<Promotion> promotions = querySearch.getResultList(); // get user
        this.promotionSearchList = promotions;
        closeConnection();
    }
    public List<Promotion> getPromotionSearchList(){
        return this.promotionSearchList;
    }
    
    public List<Promotion> getActivePromotionList() {
        this.updatePromotionList();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

        List<Promotion> activePromotion = new ArrayList<Promotion>();
        for (Promotion promotion : getPromotionList()) {
            if((LocalDate.now().until(LocalDate.parse(promotion.getRemainingDate(),formatter), ChronoUnit.DAYS) >= 0)){
                activePromotion.add(promotion);
            }
        }
        return activePromotion;
    }
    
    public List<Promotion> getUnusePromotion(int userId){
        List<Promotion> unuseList = new ArrayList<Promotion>();
        openConnection();
        // find object
        em.getTransaction().begin(); // start connection
        User user = em.find(User.class, userId);
        
        List<Promotion> userP = user.getPromotionList();
        List<Integer> userPInt = new ArrayList<Integer>();
        for (Promotion promotion : userP) {
            userPInt.add(promotion.getPromotionID());
        }
        
        List<Promotion> allP = getActivePromotionList();
        List<Integer> allPInt = new ArrayList<Integer>();
        for (Promotion promotion : allP) {
            allPInt.add(promotion.getPromotionID());
        }
        
        for (Integer integer : userPInt) {
            allPInt.remove(integer);
        }
        
        for (Integer integer : allPInt) {
            unuseList.add(getPromotion(integer));
        }
        return unuseList;
        
    }
    
}
