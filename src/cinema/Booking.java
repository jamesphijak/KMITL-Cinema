package cinema;

import cinema.ui.AlertMaker;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pisit
 */
@Entity
public class Booking implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private Showtime showtime;
    private User user;
    private Promotion promotion = null;
    
    private List<Seat> bookedSeatList;
    private double totalCost;

    public Booking(Showtime showtime, List<Seat> seat, User user, Promotion promotion,double totalCost) {
        this.showtime = showtime;
        this.bookedSeatList = seat;
        this.user = user;
        this.promotion = promotion;
        this.totalCost = totalCost;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Promotion getPromotion() {
        return this.promotion;
    }
//    public Theatre getTheatre() {
//        return this.showtime.getTheatre();
//    }

    //------------------------------------------------------------------- Method
//    public void postpone(int id, Showtime next_st, List<Seat> seat) {
//        for (Seat s : this.bookedSeat) {                  // วนเอาที่นั่งที่จองไว้แล้วไปยกเลิกที่นั่งที่ showtime
//            this.showtime.setBooked(s, false);
//        }
//        this.showtime = next_st;                           // แก้ Showtime เป็นล่าสุด
//        this.bookedSeat = seat;
//        for (Seat s : this.bookedSeat) {                  // วนเอาที่นั่งใหม่ที่จะจองแล้วไปใส่ที่ showtime ใหม่
//            this.showtime.setBooked(s, true);
//        }
//    }

    public void requestCancel(int id) {

    }

//    public void payment() {
////        double sum = 150;
////        for(int i = 0;i<this.bookedSeat.size();i++){
////            sum += this.showtime.getPrice(this.bookedSeat.get(i).getId());
////        }
//        if (this.user.getMoney() >= this.getTotalCost()) {
//            this.user.setMoney(this.user.getMoney() - this.getTotalCost());
//        } else {
//            AlertMaker.showErrorMessage("แจ้งเตือน", "กรุณาเติมเงิน");
//        }
//    }

//    public String getTypeOfSeat() {
//        return this.bookedSeat.get(0).getSeatType();
//    }

//    public Double getCostOfSeat() {
//        return this.getSeatPrice() * this.getAmountSeat();
//    }

//    public Double getTotalCost() {
//        if(promotion == null) {
//            return this.getCostOfSeat() ;
//        }
//        return this.getCostOfSeat() - this.getDiscount();
//    }

//    public Double getSeatPrice() {
//        return this.bookedSeat.get(0).getSeatPrice();
//    }
//
//    public int getAmountSeat() {
//        return this.bookedSeat.size();
//    }

    public Double getDiscount() {
        if (promotion == null) {
            return 0.0;
        }
        return this.promotion.getDiscount();
    }

    public boolean checkPrice(double price) { //---------------------- รอ User->Member
        return true;
    }

}
