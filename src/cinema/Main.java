package cinema;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 *
 * @author pisit
 */
public class Main{
    public static void main(String[] args) {
       CinemaController cc = CinemaController.getInstance();
       UserController uc = UserController.getInstance();
       PromotionController pc = PromotionController.getInstance();
       BookingController bc = BookingController.getInstance();
       
       Showtime s = cc.getShowtime(9);

       List<Seat> selectSeat = new ArrayList<Seat>();
       // จำลองดึง Seat มา 3 ตัว
       Seat s1 = cc.getShowtimeSeat(s.getId(),"A1");
       Seat s2 = cc.getShowtimeSeat(s.getId(),"F20");
       Seat s3 = cc.getShowtimeSeat(s.getId(),"B20");
       
       selectSeat.add(s1);
       selectSeat.add(s2);
       selectSeat.add(s3);

       User u = uc.getUser(4);
       Promotion p = pc.getPromotion(9);
       
       // get Total seat Price
       double totalSeatPrice = 0;
        for (Seat seat : selectSeat) {
            totalSeatPrice += seat.getSeatPrice();
        }
       // System.out.println(totalSeatPrice);
       // Check promotion and make total
       double totalPrice = totalSeatPrice;
       if(p != null){
          // selected promotion
          totalPrice -= p.getDiscount();
       }
       
       // System.out.println(totalPrice);
       
       Booking b = new Booking(s, selectSeat, u, p, totalPrice);
       //System.out.println(b);
       
      // uc.topupUserMoney(u.getId(), 190); // เติมเงิน
    // bc.addBooking(b);
    bc.cancleBooking(9);
     //   System.out.println(bc.getSumTotal());
    
       
       
    }


}
