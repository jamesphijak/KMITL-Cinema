package cinema;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author pisit
 */
public class Cinema {
    private static Cinema instance = new Cinema();
    private List<User> userList = new ArrayList<User>();
    private List<Movie> movieList = new ArrayList<Movie>();
    private List<Promotion> promotionList = new ArrayList<Promotion>();
    private List<Showtime> showtimeList = new ArrayList<Showtime>();
    private Theatre theatre;
    
    // logged in user
    private User loginUser;
    // list user search
    private List<User> userSearchList = new ArrayList<User>();
    private List<Movie> movieSearchList = new ArrayList<Movie>();
    
    private Cinema() {}
    
    public static Cinema getInstance() {
        return instance;
    }
    //------------------------------------------------------------------- Method

    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    

    public void setShowtimeList(List<Showtime> showtimeList) {
        this.showtimeList = showtimeList;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
    
    // User ============================================================================
    public List<User> getUserList() {
        this.updateUserList();
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    public void updateUserList() {
        // ดึงข้อมูลจาก db ส่วน User
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/user.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // start connection
        Query q6 = em.createQuery("SELECT u FROM User u");
        List<User> users = q6.getResultList(); // get user
        this.userList = users;
        em.close();
        emf.close();
    }
    
    public boolean checkValidUser(String username,String password){
        this.updateUserList();
        User u = new User();
        String pass = u.encryptPassword(password);
        
        for (User user : this.userList) {
            if(user.getUsername().equals(username) && user.getPassword().equals(pass)){
                loginUser = user;
                return true;
            }
        } 
        return false;  
    }
    
    public User getLoginUser(){
        return loginUser;
    }
    
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
    
    public void searchUser(String text, String searchOf){
        // ดึงข้อมูลจาก db ส่วน User
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/user.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // start connection
        Query querySearch = em.createQuery("SELECT u FROM User u WHERE lower(u." + searchOf +") LIKE lower(:" + searchOf +")", User.class);
        querySearch.setParameter(searchOf.toString(), "%"+text+"%");
//        querySearch.setParameter("firstname", text);
//        querySearch.setParameter("lastname", text);
//        querySearch.setParameter("email", text);
//        querySearch.setParameter("type", text);
        List<User> users = querySearch.getResultList(); // get user
        this.userSearchList = users;
        em.close();
        emf.close();
    }
    
    public List<User> getUserSearchList(){
        return this.userSearchList;
    }
    
    public void addUser(User user){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/user.odb"); // connect to object database file	
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin(); // start connection
        em.persist(user); // add user to persist
        em.getTransaction().commit(); // add all persist to database
        em.close();
        emf.close();
        // update list after add
        this.updateUserList();
    }
    
    public void editUser(int id, User editUser){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/user.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        // find object
        User user = em.find(User.class, id);
        em.getTransaction().begin();
        
        user.setUsername(editUser.getUsername());
        user.setPassword(editUser.getPassword());
        user.setFirstname(editUser.getFirstname());
        user.setLastname(editUser.getLastname());
        user.setEmail(editUser.getEmail());
        user.setType(editUser.getType());
        
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
    }
    
    public void deleteUser(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/user.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // start connection
        // delete from id
        Query q10 = em.createQuery("DELETE FROM User u WHERE u.id = :id");
//        System.out.println("Deleted No of records: "+);
        q10.setParameter("id", id).executeUpdate();
        em.getTransaction().commit(); // add all persist to database
        // Close the database connection:
        em.close();
        emf.close();
        // update list after delete
        this.updateUserList();
    }
    
    // Movie ============================================================================
    public List<Movie> getMovieList() {
        this.updateMovieList();
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
    
    public void updateMovieList() {
        // ดึงข้อมูลจาก db ส่วน Movie
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/movie.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // start connection
        Query q6 = em.createQuery("SELECT m FROM Movie m");
        List<Movie> movies = q6.getResultList(); // get movie
        this.movieList = movies;
        em.close();
        emf.close();
    }
    
    public void addMovie(Movie movie){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/movie.odb"); // connect to object database file	
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin(); // start connection
        em.persist(movie); // add user to persist
        em.getTransaction().commit(); // add all persist to database
        em.close();
        emf.close();
        // update list after add
        updateMovieList();
    }
    
    public void searchMovie(String text, String searchOf){
        // ดึงข้อมูลจาก db ส่วน User
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/movie.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // start connection
        Query querySearch = em.createQuery("SELECT m FROM Movie m WHERE lower(m." + searchOf +") LIKE lower(:" + searchOf +")", Movie.class);
        querySearch.setParameter(searchOf.toString(), "%"+text+"%");
        List<Movie> movies = querySearch.getResultList(); // get user
        this.movieSearchList = movies;
        em.close();
        emf.close();
    }
    
    public List<Movie> getMovieSearchList(){
        return this.movieSearchList;
    }
    
    public void editMovie(int id, Movie editMovie){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/movie.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
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
        em.close();
        emf.close();
    }
    
    public void deleteMovie(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/movie.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // start connection
        // delete from id
        Query q10 = em.createQuery("DELETE FROM Movie m WHERE m.id = :id");
        q10.setParameter("id", id).executeUpdate();
        em.getTransaction().commit(); // add all persist to database
        // Close the database connection:
        em.close();
        emf.close();
        // update list after delete
        this.updateMovieList();
    }
    
    // Promotion ========================================================================
    public void updatePromotionList() {
        
    }
   
    public void updateTheatre() {
        
    }
    
    
    // Showtime ==========================================================================
    public void addShowtime(Showtime showtime){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/showtime.odb"); // connect to object database file	
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin(); // start connection
        em.persist(showtime); // add user to persist
        em.getTransaction().commit(); // add all persist to database
        em.close();
        emf.close();
        // update list after add
        updateShowtimeList();
    }
    
    public void updateShowtimeList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/showtime.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // start connection
        Query q6 = em.createQuery("SELECT s FROM Showtime s");
        List<Showtime> showtime = q6.getResultList(); // get movie
        this.showtimeList = showtime;
        em.close();
        emf.close();
    }
    
    public List<Showtime> getShowtimeList() {
        this.updateShowtimeList();
        return showtimeList;
    }
    
    public void editShowtime(int id, Showtime editShow){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/showtime.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        // find object
        Showtime show = em.find(Showtime.class, id);
        em.getTransaction().begin();
        
        show.setIncreaseSeatPrice(editShow.getIncreaseSeatPrice());
        show.setMovieName(editShow.getMovie());
        show.setPeriod(editShow.getPeriod());
        show.setSoundtrack(editShow.getSoundtrack());
        show.setSubtitle(editShow.getSubtitle());
        show.setTheatreNum(editShow.getTheatre());
        
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
    }
    
    public void deleteShowtime(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/showtime.odb"); // Select file to store database	
	EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // start connection
        // delete from id
        Query q10 = em.createQuery("DELETE FROM Showtime s WHERE s.id = :id");
        q10.setParameter("id", id).executeUpdate();
        em.getTransaction().commit(); // add all persist to database
        // Close the database connection:
        em.close();
        emf.close();
        // update list after delete
        this.updateShowtimeList();
    }
    
    //---------------------------------------------------------- Function Method
    
    
}
