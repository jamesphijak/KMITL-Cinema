package cinema;
import cinema.ui.FieldValidation;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author pisit
 */
public class Main {

    public static void main(String[] args) {
       CinemaController cc = CinemaController.getInstance();
       Showtime s = cc.getShowtime(9);
        System.out.println(s.getMovie().getTime());
        System.out.println(s.getShowtime());
    
    }
}
