package cinema;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CinemaController{
    private CinemaController() {} 
    private static CinemaController instance = new CinemaController();
    public static CinemaController getInstance() {
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
    // Select movie in main page any type of user
    
    // Movie =========================================================================
    
    private Movie selectMovie;
    private boolean isComingSoon;
    public void setSelectMovie(int id){
        selectMovie = getMovie(id);
    }
    public Movie getSelectMovie(){
        return selectMovie;
    }
    public boolean getIsComingSoon() {
        return isComingSoon;
    }
    public void setIsComingSoon(boolean isComingSoon) {
        this.isComingSoon = isComingSoon;
    }
    
    
    // Operation
    public Movie getMovie(int id){
        openConnection();
        // find object
        Movie movie = em.find(Movie.class, id);
        // Close the database connection:
        closeConnection();
        return movie;
    }
    public void addMovie(Movie movie){
        openConnection();
	em.getTransaction().begin(); // start connection
        em.persist(movie); // add user to persist
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
    }
    public void editMovie(int id, Movie editMovie){
        openConnection();
        // find object
        Movie movie = em.find(Movie.class, id);
        em.getTransaction().begin();
        movie.setEnglishName(editMovie.getEnglishName());
        movie.setThaiName(editMovie.getThaiName());
        movie.setDirector(editMovie.getDirector());
        movie.setCast(editMovie.getCast());
        movie.setSynopsis(editMovie.getSynopsis());
        movie.setGenre(editMovie.getGenre());
        movie.setTime(editMovie.getTime());
        movie.setReleaseDate(editMovie.getReleaseDate());
        movie.setPoster(editMovie.getPoster());
        movie.setTrailer(editMovie.getTrailer());
        em.getTransaction().commit();
        // Close the database connection:
        closeConnection();
    }
    public void deleteMovie(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        Query query = em.createQuery("DELETE FROM Movie m WHERE m.id = :id");
        query.setParameter("id", id).executeUpdate();
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
    }
    
    // Get all
    private List<Movie> movieList = new ArrayList<Movie>();
    public void updateMovieList() {
        openConnection();
        em.getTransaction().begin(); // start connection
        Query query = em.createQuery("SELECT m FROM Movie m");
        List<Movie> movies = query.getResultList(); // get movie
        this.movieList = movies;
        closeConnection();
    }
    public List<Movie> getMovieList() {
        this.updateMovieList();
        return movieList;
    }
    
    // Search
    private List<Movie> movieSearchList = new ArrayList<Movie>();
    public void searchMovie(String text, String searchOf){
        // ดึงข้อมูลจาก db ส่วน User
        openConnection();
        em.getTransaction().begin(); // start connection
        Query querySearch = em.createQuery("SELECT m FROM Movie m WHERE lower(m." + searchOf +") LIKE lower(:" + searchOf +")", Movie.class);
        querySearch.setParameter(searchOf.toString(), "%"+text+"%");
        List<Movie> movies = querySearch.getResultList(); // get user
        this.movieSearchList = movies;
        closeConnection();
    }
    public List<Movie> getMovieSearchList(){
        return this.movieSearchList;
    }
    
    // Theatre =========================================================================
    private List<Theatre> theatreList = new ArrayList<Theatre>(); // เก็บโรง

    // Operation
    public Theatre getTheatre(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        // find object
        Theatre theatre = em.find(Theatre.class, id);
        //for(Showtime st : theatre.getShowtimeList()){ } // get 
        em.getTransaction().commit();
        closeConnection();
        return theatre;
    }
    public void addTheatre(Theatre theatre){
        openConnection();
	em.getTransaction().begin(); // start connection
        em.persist(theatre); // add user to persist
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
    }
    public void editTheatre(int id, Theatre editTheatre){
        openConnection();
        Theatre theatre = em.find(Theatre.class, id); // find object
        em.getTransaction().begin();
        theatre.setTheatreNumber(editTheatre.getTheatreNumber());
        theatre.setTheatreType(editTheatre.getTheatreType());
        em.getTransaction().commit();
        closeConnection();
    }
    public void deleteTheatre(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        Query query = em.createQuery("DELETE FROM Theatre t WHERE t.id = :id");
        query.setParameter("id", id).executeUpdate();
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
    }
    
    // Get all
    public void updateTheatreList() {
        openConnection();
        em.getTransaction().begin(); // start connection
        Query q6 = em.createQuery("SELECT t FROM Theatre t ORDER BY t.theatreNumber ");
        List<Theatre> theatres = q6.getResultList(); // get user
        this.theatreList = theatres;
        closeConnection();
    }
    public List<Theatre> getTheatreList() {
        updateTheatreList();
        return this.theatreList;
    }
    
    // Add showtime
    public void addShowtime(int id, Showtime showtime){
        openConnection();
        em.getTransaction().begin();
        Theatre theatre = em.find(Theatre.class, id); // find object
        theatre.addShowtime(showtime);
        em.getTransaction().commit();
        closeConnection();
    }
    
    public void deleteShowtime(int id,int idShowtime){
        openConnection();
        em.getTransaction().begin();
        Theatre theatre = em.find(Theatre.class, id); // find object
        Showtime showtime = em.find(Showtime.class, idShowtime);
        for (Showtime st : theatre.getShowtimeList()) {
            if(st.equals(showtime)){
                theatre.deleteShowtime(st);
            }
        }
        em.getTransaction().commit();
        closeConnection();
    }
    
    
    // Showtime =========================================================================
    private List<Showtime> showtimeList = new ArrayList<Showtime>(); // เก็บโรง
    private int selectShowtime;
    
    public int getSelectShowtime(){
        return this.selectShowtime;
    }
    
    public void setSelectShowtime(int id){
        this.selectShowtime = id;
    }
    
    
    // Operation
    public Showtime getShowtime(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        // find object
        Showtime showtime = em.find(Showtime.class, id);
//        List <Seat> s = showtime.getSeatList();

      //  for(Seat s : showtime.getSeatList()){} // get 
        // Close the database connection:

        em.getTransaction().commit();
        closeConnection();
        return showtime;
    }
    public void addShowtime(Showtime showtime){
        openConnection();
	em.getTransaction().begin(); // start connection
        em.persist(showtime); // add user to persist
        // add showtime into theatre list
        em.flush();
        Showtime st = em.find(Showtime.class, showtime.getId());
        Theatre t = em.find(Theatre.class, st.getTheatre().getId());
        t.addShowtime(st);
        
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
        
    }
    
    public void moveShowtime(int told,int tnew,int s){
        //openConnection();
       // em.getTransaction().begin();
        
        Showtime st = em.find(Showtime.class,s);
        Theatre t1 = em.find(Theatre.class, told);
        Theatre t2 = em.find(Theatre.class, tnew);
        
        t1.deleteShowtime(st);
        t2.addShowtime(st);
        
      //  em.getTransaction().commit();
      //  closeConnection();
    }
    public void removeTheatreShowtime(int t,int s){
        Showtime st = em.find(Showtime.class,s);
        Theatre t1 = em.find(Theatre.class, t);
        
        t1.deleteShowtime(st);
        
    }
    
    public void editShowtime(int id, Showtime editShowtime){
        openConnection();
        Showtime st = em.find(Showtime.class, id); // find object
        em.getTransaction().begin();
        
        // แก้ Theatre Showtime List
        if(st.getTheatre().getId() != editShowtime.getTheatre().getId()){ // ถ้าเปลี่ยนโรง
            moveShowtime(st.getTheatre().getId(),editShowtime.getTheatre().getId(),id);
            System.out.println("Change!");
        }else{
            System.out.println("no change");
        }
          
        // แก้
        st.setMovie(editShowtime.getMovie());
        st.setTheatre(editShowtime.getTheatre());
        st.setSoundtrack(editShowtime.getSoundtrack());
        st.setStartTime(editShowtime.getOnlyStartTime());
//        st.setDate(editShowtime.getDate());
        st.setSystem(editShowtime.getSystem());
        st.setIncreaseSeatPrice(editShowtime.getIncreaseSeatPrice());
        // แก้ราคาใน seat ทั้งหมด
        st.setSeatPrice(editShowtime.getIncreaseSeatPrice());
        
        em.getTransaction().commit();
        closeConnection();
    }
    public void deleteShowtime(int id){
        openConnection();
        em.getTransaction().begin(); // start connection
        Showtime st = em.find(Showtime.class, id); // find object
        //st.getTheatre().deleteShowtime(st); // remove showtime
        removeTheatreShowtime(st.getTheatre().getId(),id);
        em.remove(st);
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
    }
    
    // Get all
    public void updateShowtimeList() {
        openConnection();
        em.getTransaction().begin(); // start connection
        Query q6 = em.createQuery("SELECT st FROM Showtime st");
        List<Showtime> showtimes = q6.getResultList(); // get user
        this.showtimeList = showtimes;
        closeConnection();
    }
    public List<Showtime> getShowtimeList() {
        updateShowtimeList();
        return this.showtimeList;
    }
    
    // Set seat book or unset
    public void setShowtimeSeat(int id,String seatName,boolean seatStatus){
        openConnection();
        em.getTransaction().begin(); // start connection
        // find object
        Showtime showtime = em.find(Showtime.class, id);
        showtime.setSeatStatus(seatName,seatStatus); // set book
        // Close the database connection:
        em.getTransaction().commit();
        closeConnection();
    }
    
    // Get seat from showtime
    public Seat getShowtimeSeat(int id,String seatName){
        openConnection();
        em.getTransaction().begin(); // start connection
        // find object
        Showtime showtime = em.find(Showtime.class, id);
        Seat seat = showtime.getSeat(seatName);
        // Close the database connection:
        em.getTransaction().commit();
        closeConnection();
        return seat;
    }
    
    // Booking ====================================================================================
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
            if(b.getPromotion() != null){
                Promotion p = em.find(Promotion.class, b.getPromotion().getPromotionID());
                u.addPromotion(p);
            }
            System.out.println("Set Promotion to user okay");
            // Set seat to booking true
            List<Seat> seatList = b.getBookedSeatList();
            for (Seat seat : seatList) {
                Seat seatSet = em.find(Seat.class, seat.getId());
                seatSet.setSeatStatus(true);
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
        
        if(b.getPromotion() != null){
            Promotion p = em.find(Promotion.class, b.getPromotion().getPromotionID());
        }
        
        if(!b.isCancel()){
            // u.removePromotion(p); // Remove Promotion from user
            b.setIsCancel(true); // Set cancel to true
            b.updateDatetime();
            // Change money in user and booking
            double userReturn  = b.cancelBooking(); // after cancel will set total only 10% and return user money
            u.returnMoney(userReturn); // return to user account

            // set ที่นั่งว่างเหมือนเดิม
            List<Seat> seatList = b.getBookedSeatList();
            for (Seat seat : seatList) {
                Seat seatSet = em.find(Seat.class, seat.getId());
                seatSet.setSeatStatus(false);
            }
        }else{
            System.out.println("Can't cancel again");
        }
        
            
        em.getTransaction().commit(); // add all persist to database
        closeConnection();
    }
    
    // Get all
    private List<Booking> bookingList = new ArrayList<Booking>();
    public void updateBookingList() {
        openConnection();
        em.getTransaction().begin(); // start connection
        Query query = em.createQuery("SELECT b FROM Booking b");
        List<Booking> booking = query.getResultList(); // get movie
        this.bookingList = booking;
        closeConnection();
    }
    public List<Booking> getBookingList() {
        this.updateBookingList();
        return bookingList;
    }
    public List<Booking> getMyBookingList(int userId) {
        this.updateBookingList();
        List<Booking> userBookingList = new ArrayList<Booking>();
        for (Booking b : this.bookingList) {
            if(b.getUser().getId() == userId){
                userBookingList.add(b);
            }
        }
        return userBookingList;
    }
    public double getSumTotal(){
        double total = 0;
        this.updateBookingList();
        for (Booking booking : bookingList) {
            total += booking.getTotalCost();
        }
        return total;
    }
    

    List<Seat> seatList = new ArrayList<Seat>();
    List<String> selectSeatName = new ArrayList<String>();
    public void setSeatList(List<String> selectSeat){
        selectSeatName.clear();
        selectSeatName = selectSeat;
    } // ใส่ iv เข้ามาเป็น string
    public List<Seat> getSeatList(){
        seatList.clear(); // clear before get new seat
        for (String seatString : selectSeatName) {
            seatList.add(getShowtimeSeat(selectShowtime,seatString));
        }
        return seatList;
    } // get ออกไปเป็น seat
}
