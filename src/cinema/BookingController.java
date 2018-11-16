package cinema;

import cinema.ui.AlertMaker;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Phijak
 */
public class BookingController {
    private BookingController() {} 
    private static BookingController instance = new BookingController();
    public static BookingController getInstance() {
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
    public Booking getBooking(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        Booking booking = em.find(Booking.class, id);
        em.getTransaction().commit();
        closeConnection();
        return booking;
    }
    public void addBooking(Booking booking){
        openConnection();
	em.getTransaction().begin(); // start connection
        em.persist(booking); // add user to persist
        // add showtime into theatre list
        em.flush();
        Booking b = em.find(Booking.class, booking.getId()); // Get Last Add Booking
        User u = em.find(User.class, b.getUser().getId()); // Get User
        // หักเงิน
        if(u.checkCanPay(booking.getTotalCost())){
            u.payMoney(booking.getTotalCost());
            System.out.println("User pay okay");
            Promotion p = em.find(Promotion.class, b.getPromotion().getPromotionID());
            u.addPromotion(p);
            System.out.println("Set Promotion to user okay");
            // Set seat to booking true
            List<Seat> seatList = b.getBookedSeatList();
            for (Seat seat : seatList) {
                Seat seatSet = em.find(Seat.class, seat.getId());
                seat.setSeatStatus(true);
            }
            System.out.println("Set Seat alredy booked okay");
            em.getTransaction().commit(); // add all persist to database
            System.out.println("Book completed");
        }else{
            System.out.println("Money not enought to pay Please transfer money to account");
        }
        closeConnection();
    }
    
    public void cancleBooking(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        Booking b = em.find(Booking.class, id); // find object
        User u = em.find(User.class, b.getUser().getId()); // Get User
        Promotion p = em.find(Promotion.class, b.getPromotion().getPromotionID());
        
        u.removePromotion(p); // Remove Promotion from user
        b.setIsCancel(true); // Set cancel to true
        // Change money in user and booking
        double userReturn  = b.cancelBooking(); // after cancel will set total only 10% and return user money
        u.returnMoney(userReturn); // return to user account
        
        // set ที่นั่งว่างเหมือนเดิม
        List<Seat> seatList = b.getBookedSeatList();
        for (Seat seat : seatList) {
            Seat seatSet = em.find(Seat.class, seat.getId());
            seat.setSeatStatus(false);
        }
            
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
    }
    

}
