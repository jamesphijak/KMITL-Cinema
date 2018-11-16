///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cinema.ui.summary;
//
//import cinema.Booking;
//import cinema.CinemaController;
//import cinema.Movie;
//import cinema.NormalSeat;
//import cinema.Promotion;
//import cinema.Seat;
//import cinema.Showtime;
//import cinema.Theatre;
//import cinema.User;
//import cinema.screensframework.ControlledScreen;
//import cinema.screensframework.ScreensController;
//import cinema.screensframework.ScreensFramework;
//import cinema.ui.AlertMaker;
//import java.io.IOException;
//import java.net.URL;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import static java.time.temporal.TemporalQueries.localDate;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.ComboBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
///**
// * FXML Controller class
// *
// * @author BEAMCONAN
// */
//public class summaryController implements Initializable, ControlledScreen {
//
//    @FXML
//    private Text nameEng;
//    @FXML
//    private Text theatre;
//    @FXML
//    private Text date;
//    @FXML
//    private Text typeSeat;
//    @FXML
//    private Text amountSeat;
//    @FXML
//    private Text costPerSeat;
//    @FXML
//    private Text cost;
//    @FXML
//    private ComboBox<String> selectPromotion;
//    @FXML
//    private Text sumcost;
//    @FXML
//    private Text moneyUser;
//    @FXML
//    private Text discount;
//    @FXML
//    private Text details;
//    CinemaController cc;
//    User loginUser;
//    @FXML
//    private Text username;
//    @FXML
//    private Text showtime;
//    @FXML
//    private StackPane rootPane;
//
//    User user;
//    Booking booking;
//    Movie movie;
//    Showtime st;
//    
//    public summaryController() {
//        this.cc = cc.getInstance();
//    }
//
//    ScreensController myController;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        user = new User("yay", "123456", "Yay", "Yaaaaaa", "aa@sss.sss", "customer", 50.0);
//        movie = new Movie(
//                "Fantastic Beasts 2",
//                "สัตว์มหัศจรรย์ อาชญากรรมของกรินเดลวัลด์",
//                "เดวิด เยตส์",
//                "เอ็ดดี้ เรย์แมน, เออซ่า มิลเลอร์, จอห์นนี่ เด็บบ์, จู๊ด ลอว์",
//                "เจ.เค. โรว์ลิ่ง ได้เขียนบทภาพยนตร์เรื่องนี้ ซึ่งเป็นเรื่องราวที่เกิดขึ้นในปี 1927 เพียงไม่กี่เดือนหลังจากที่นิวท์ ได้ช่วยเหลืือจนสามารถเปิดเผยและจับกุมตัวพ่อมดศาสตร์มืด กรินเล็ตต์ กรินเดวัลล์ได้ แต่อย่างที่เขาได้สัญญาเอาไว้, กรินเดลวัลด์สามารถหลบหนีการคุมขังออกมาได้ เขาเดินทางเพื่อรวบรวมผู้ติดตามของเขา เพื่อยกระดับพ่อมดแม่มดให้เหนือกว่าเหล่าโนเมจทั้งมวล มีพ่อมดเพียงคนเดียวที่อาจจะหยุดยั้งเขาได้ นั่นคือเพื่อนรักของเขา อัลบัส ดัมเบิลดอร์ แต่ดัมเบิลดอร์ต้องการร่วมมือพ่อมดที่เคยปะทะกับกรินเดลวัลด์มาก่อน เขาคนนั้นคือ นิวต์ สคาแมนเดอร์ ทำให้การผจญภัยร่วมกันของนิวต์ ทีน่า ควินนี และโจอี้ได้เริ่มต้นขึ้นอีกครั้ง แต่ภารกิจในครั้งนี้จะทดสอบความจงรักภักดีของพวกเขา ขณะเดียวกันก็ต้องเผชิญหน้ากับภัยพิบัติครั้งใหม่ในโลกของพ่อมดและแม่มด ที่อันตรายและพร้อมจะแบ่งแยกทุกฝ่ายออกจากกัน",
//                "ผจญภัย / แฟนตาซี",
//                "20/10/61",
//                "120",
//                "C:\\Users\\BEAMCONAN\\Documents\\NetBeansProjects\\ShowMovie\\src\\pic\\poster.jpg",
//                "C:\\Users\\BEAMCONAN\\Documents\\NetBeansProjects\\KMITL Cinema\\src\\cinema\\ui\\video\\trailer.mp4"
//        );
//        st = new Showtime(movie, new Theatre(1), "3D", "en", "10:00", 0);
//        Seat a = new NormalSeat();
//        Seat b = new NormalSeat();
//        List<Seat> bookedSeat = new ArrayList<Seat>();
////        seatB s1 = SeatFactory.
//        bookedSeat.add(a);
//        bookedSeat.add(b);
////        System.out.print(user);
//        booking = new Booking(st, bookedSeat, user);
////******************************Details movie*********************
//        nameEng.setText(st.getMovieEng());
//        theatre.setText(st.getTheatreNo() + " (" + st.getSystem() + ") " + st.getSoundtrack().toUpperCase() + "/" + st.getSubtitle().toUpperCase());
////****************************** get today ***************************
//        ZoneId zonedId = ZoneId.of("Asia/Singapore");
//        LocalDate today = LocalDate.now(zonedId);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - LLLL - yyyy");
//        String formattedString = today.format(formatter);
//        date.setText(formattedString);
//
//        showtime.setText(st.getShowtime());
//
//        typeSeat.setText(booking.getTypeOfSeat());
//        amountSeat.setText(Integer.toString(booking.getAmountSeat()));
//        costPerSeat.setText(Double.toString(booking.getSeatPrice()));
//        cost.setText(Double.toString(booking.getCostOfSeat()));
//        discount.setText(Double.toString(booking.getDiscount()));
//        sumcost.setText(Double.toString(booking.getTotalCost()));
//
////******************************Details user*********************
//        username.setText(user.getUsername());
//        moneyUser.setText(Double.toString(user.getMoney()));
////******************************* test Promotion *********************
//        int amount = 2;
//        Promotion[] promotion = new Promotion[amount];
//        promotion[0] = new Promotion("2018-12-11",
//                "1. ระยะเวลาในการประชาสัมพันธ์ลูกค้าเข้าร่วมแคมเปญจำนวนทั้งหมด 11 วัน เริ่มวันที่ 11 - 21พ.ย 612. Code สามารถใช้ได้ตั้งแต่วันที่ 11 พ.ย 561 เวลา 08.00 น. Expire 21 พ.ย 2561 เวลา 22.00 น.  3. ใช้ได้ใน Mobile App เท่านั้น4. ส่วนลด 80 Baht /1 ที่นั่ง",
//                "11.11",
//                80);
//        promotion[1] = new Promotion("2018-12-11",
//                "1. ระยะเวลาในการประชาสัมพันธ์ลูกค้าเข้าร่วมแคมเปญจำนวนทั้งหมด 11 วัน",
//                "ลด3000000000000000000000000000000000000000000000000000000000000000000",
//                30);
//        ObservableList<String> promotionList = FXCollections.observableArrayList();
//        for (int i = 0; i < promotion.length; i++) {
//            if (promotion[i].getName().length() > 30) {
//                promotionList.add(promotion[i].getName().substring(0, 30) + "...");
//            } else {
//                promotionList.add(promotion[i].getName());
//            }
//        }
//
//        selectPromotion.setItems(promotionList);
//        selectPromotion.setStyle("-fx-text-fill: #ffffff;");
//        selectPromotion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                System.out.println(newValue);
//                int i = 0;
//                while (i < promotion.length) {
//                    if (promotion[i].getName().length() > 30) {
//                        if ((promotion[i].getName().substring(0, 30) + "...").equals(newValue)) {
//                            details.setText(promotion[i].getDescription());
//                            booking.setPromotion(promotion[i]);
//                            discount.setText(Double.toString(booking.getDiscount()));
//                            sumcost.setText(Double.toString(booking.getTotalCost()));
//                        }
//                    } else {
//                        if (promotion[i].getName().equals(newValue)) {
//                            details.setText(promotion[i].getDescription());
//                            booking.setPromotion(promotion[i]);
//                            discount.setText(Double.toString(booking.getDiscount()));
//                            sumcost.setText(Double.toString(booking.getTotalCost()));
//                        }
//                    }
//                    i++;
//                }
//            }
//
//        });
//    }
//
//    @FXML
//    private void plusMoney100(ActionEvent event) {
//        user.addMoney(100);
//        moneyUser.setText(Double.toString(user.getMoney()));
//    }
//
//    @FXML
//    private void plusMoney150(ActionEvent event) {
//        user.addMoney(150);
//        moneyUser.setText(Double.toString(user.getMoney()));
//    }
//
//    @FXML
//    private void plusMoney200(ActionEvent event) {
//        user.addMoney(200);
//        moneyUser.setText(Double.toString(user.getMoney()));
//    }
//
//    @FXML
//    private void plusMoney300(ActionEvent event) {
//        user.addMoney(300);
//        moneyUser.setText(Double.toString(user.getMoney()));
//    }
//
//    @FXML
//    private void plusMoney500(ActionEvent event) {
//        user.addMoney(500);
//        moneyUser.setText(Double.toString(user.getMoney()));
//    }
//
//    @FXML
//    private void plusMoney1000(ActionEvent event) {
//        user.addMoney(1000);
//        moneyUser.setText(Double.toString(user.getMoney()));
//    }
//
//    @FXML
//    private void payment(ActionEvent event) {
//        booking.payment();
//        System.out.print(booking);
//
//        moneyUser.setText(Double.toString(user.getMoney()));
//    }
//
//    @FXML
//    private void back(ActionEvent event) throws IOException {
//        myController.loadScreen(ScreensFramework.userNowShowingScreenID, ScreensFramework.userNowShowingScreenFile);
//        myController.setScreen(ScreensFramework.userNowShowingScreenID);
//    }
//
//    @Override
//    public void setScreenParent(ScreensController screenParent) {
//        myController = screenParent;
//    }
//
//}
