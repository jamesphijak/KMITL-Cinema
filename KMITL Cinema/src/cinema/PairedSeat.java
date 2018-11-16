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
public class PairedSeat extends Seat {
    
    private HoneymoonSeat honeymoonSeatPrice = new HoneymoonSeat();
    
    public PairedSeat() {
        this.seatType = "Paired Seat";
        this.seatPrice = honeymoonSeatPrice.getSeatPrice() + 100;
    }
    
    public PairedSeat(double seatPrice) {
        this.seatType = "Paired Seat";
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
