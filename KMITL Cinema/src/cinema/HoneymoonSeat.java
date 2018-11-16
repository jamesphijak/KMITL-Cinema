/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

/**
 *
 * @author pisit
 */
public class HoneymoonSeat extends Seat {
    
    private NormalSeat normalSeatPrice = new NormalSeat();
    
    public HoneymoonSeat() {
        this.seatType = "Honeymmoon Seat";
        this.seatPrice = normalSeatPrice.getSeatPrice() + 30;
    }
    
    public HoneymoonSeat(double seatPrice) {
        this.seatType = "Honeymmoon Seat";
        this.seatPrice = seatPrice;
    }

    @Override
    public double getSeatPrice() {
        return this.seatPrice;
    }

    @Override
    void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    @Override
    String getSeatType() {
        return this.seatType;
    }
    
    @Override
    public String toString() {
        return this.seatType + " : " + "Seat price = " + this.seatPrice;
    }
    
}
