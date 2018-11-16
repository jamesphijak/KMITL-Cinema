
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pisit
 */
public class checkDate {
    
    
    public static void main(String[] args) {
        int showingDay = 14; // จำนวนวันที่จะให้หนังอยู่ในโรง
        
        LocalDate today = LocalDate.now();
        LocalDate releaseDate = LocalDate.of(2018, Month.NOVEMBER, 11);

        // ถ้า today มากกว่า releaseDate แสดงว่าเป็น coming soon
        if(today.until(releaseDate, ChronoUnit.DAYS) >= 0) {
            // เป็น now showing
            System.out.println("Coming soon");
        }
        // แต่ถ้า today น้อยกว่า releaseDate แสดงว่าเป็น coming soon
        else {
            // เป็น coming soon
            System.out.println("Now Showing");
            
            // แล้วจะฉายถึงเมื่อไร
            long tempDayShowing = today.until(releaseDate, ChronoUnit.DAYS); // ฉายมาแล้วกี่วัน (Return เป็น long)
            int dayShowing = (int) tempDayShowing + showingDay; // เปลี่ยน tempDayShowing เป็น int และไปบวกกับ showingDay
            
            System.out.println("เหลือวันฉายอีก " + dayShowing + " วัน"); // ได้จำนวนวันที่เหลือที่จะฉาย
        }
    }
}
