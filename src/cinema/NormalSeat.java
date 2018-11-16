/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import javax.persistence.Entity;

/**
 *
 * @author pisit
 */
@Entity
public class NormalSeat extends Seat {
    
    public NormalSeat(String name, Showtime showtime, double increasePrice) {
        this.seatName = name;
        this.seatType = "Normal";
        this.seatPrice = this.basePrice + increasePrice;
        this.showtime = showtime;
    }

    @Override
    public double getSeatPrice() {
        return this.seatPrice;
    }

    @Override
    public void setSeatPrice(double increasePrice) {
        this.seatPrice = this.basePrice + increasePrice;
    }

    @Override
    public String getSeatType() {
        return this.seatType;
    }

    @Override
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    @Override
    public Showtime getShowtime() {
        return this.showtime;
    }

    @Override
    public void setSeatStatus(boolean status) {
        this.seatStatus = status;
    }

    @Override
    public boolean getSeatStatus() {
        return this.seatStatus;
    }
    
    @Override
    public String toString() {
        return this.seatName + " : (" + this.seatStatus + ") : " + this.seatType + ", Price = " + this.seatPrice + "\n";
    }
    
    @Override
    public String getSeatName() {
        return this.seatName;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
