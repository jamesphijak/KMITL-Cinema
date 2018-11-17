package cinema;

import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
       CinemaController cc = CinemaController.getInstance();
       UserController uc = UserController.getInstance();
       PromotionController pc = PromotionController.getInstance();
       
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
       
  //  uc.topupUserMoney(u.getId(), 500); // เติมเงิน
    //    System.out.println(u.getMoney());
    // cc.addBooking(b);
//    cc.cancleBooking(9);
//    cc.cancleBooking(10);
//    cc.cancleBooking(12);
//    cc.cancleBooking(13);
//    cc.cancleBooking(15);
//    cc.cancleBooking(16);
  //  cc.cancleBooking(21);
    
     //   System.out.println(bc.getSumTotal());
    
       pc.getUnusePromotion(15);
       
    }


}
