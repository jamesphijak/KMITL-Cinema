/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.layoutMedium;

import cinema.CinemaController;
import cinema.Seat;
import cinema.Showtime;
import cinema.ui.AlertMaker;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author BEAMCONAN
 */
public class Controller implements Initializable {

    @FXML
    private ImageView F1;
    @FXML
    private ImageView F2;
    @FXML
    private ImageView F3;
    @FXML
    private ImageView F4;
    @FXML
    private ImageView F6;
    @FXML
    private ImageView F7;
    @FXML
    private ImageView F8;
    @FXML
    private ImageView F9;
    @FXML
    private ImageView F10;
    @FXML
    private ImageView F11;
    @FXML
    private ImageView F12;
    @FXML
    private ImageView F13;
    @FXML
    private ImageView F14;
    @FXML
    private ImageView F15;
    @FXML
    private ImageView F16;
    @FXML
    private ImageView F17;
    @FXML
    private ImageView F18;
    @FXML
    private ImageView F19;
    @FXML
    private ImageView F20;
    @FXML
    private ImageView E1;
    @FXML
    private ImageView E2;
    @FXML
    private ImageView E3;
    @FXML
    private ImageView E4;
    @FXML
    private ImageView E5;
    @FXML
    private ImageView E6;
    @FXML
    private ImageView E7;
    @FXML
    private ImageView E8;
    @FXML
    private ImageView E9;
    @FXML
    private ImageView E10;
    @FXML
    private ImageView E11;
    @FXML
    private ImageView E12;
    @FXML
    private ImageView E13;
    @FXML
    private ImageView E14;
    @FXML
    private ImageView E15;
    @FXML
    private ImageView E16;
    @FXML
    private ImageView E17;
    @FXML
    private ImageView E18;
    @FXML
    private ImageView E19;
    @FXML
    private ImageView E20;
    @FXML
    private ImageView D1;
    @FXML
    private ImageView D2;
    @FXML
    private ImageView D3;
    @FXML
    private ImageView D4;
    @FXML
    private ImageView D5;
    @FXML
    private ImageView D6;
    @FXML
    private ImageView D7;
    @FXML
    private ImageView D8;
    @FXML
    private ImageView D9;
    @FXML
    private ImageView D10;
    @FXML
    private ImageView D11;
    @FXML
    private ImageView D12;
    @FXML
    private ImageView D13;
    @FXML
    private ImageView D14;
    @FXML
    private ImageView D15;
    @FXML
    private ImageView D16;
    @FXML
    private ImageView D17;
    @FXML
    private ImageView D18;
    @FXML
    private ImageView D19;
    @FXML
    private ImageView D20;
    @FXML
    private ImageView C1;
    @FXML
    private ImageView C2;
    @FXML
    private ImageView C3;
    @FXML
    private ImageView C4;
    @FXML
    private ImageView C5;
    @FXML
    private ImageView C6;
    @FXML
    private ImageView C7;
    @FXML
    private ImageView C8;
    @FXML
    private ImageView C9;
    @FXML
    private ImageView C10;
    @FXML
    private ImageView C11;
    @FXML
    private ImageView C12;
    @FXML
    private ImageView C13;
    @FXML
    private ImageView C14;
    @FXML
    private ImageView C15;
    @FXML
    private ImageView C16;
    @FXML
    private ImageView C17;
    @FXML
    private ImageView C18;
    @FXML
    private ImageView C19;
    @FXML
    private ImageView C20;
    @FXML
    private ImageView B1;
    @FXML
    private ImageView B2;
    @FXML
    private ImageView B3;
    @FXML
    private ImageView B4;
    @FXML
    private ImageView B5;
    @FXML
    private ImageView B6;
    @FXML
    private ImageView B7;
    @FXML
    private ImageView B8;
    @FXML
    private ImageView B9;
    @FXML
    private ImageView B10;
    @FXML
    private ImageView B11;
    @FXML
    private ImageView B12;
    @FXML
    private ImageView B13;
    @FXML
    private ImageView B14;
    @FXML
    private ImageView B15;
    @FXML
    private ImageView B16;
    @FXML
    private ImageView B17;
    @FXML
    private ImageView B18;
    @FXML
    private ImageView B19;
    @FXML
    private ImageView B20;
    @FXML
    private ImageView A3;
    @FXML
    private ImageView A4;
    @FXML
    private ImageView A5;
    @FXML
    private ImageView A2;
    @FXML
    private ImageView A1;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView F5;
    
    int count;
    boolean isFound;
    String maxIV;
    String minIV;
    List<String> ivList = new ArrayList<String>();
    
    List<ImageView> ivListAll = new ArrayList<>(); // ใช้เก็บ Image View เพื่อ Disable
    List<String> ivListDisable = new ArrayList<>(); // ใช้เก็บ List ที่นั่งที่จองไปแล้วในรอบฉายนั้น
    
    CinemaController cc;
    
    public Controller() {
        this.cc = cc.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isFound = false;
        count = 0;
        seatAssign();
        // Get Showtime seat which is not available
        Showtime st = cc.getShowtime(9);
        List<Seat> seatAlreadyBooked = st.getSeatList();
        for (Seat seat : seatAlreadyBooked) {
            if(seat.getSeatStatus()){ // if seat status is true => already book => disable it
                ivListDisable.add(seat.getSeatName());
            }
        }
        seatDisable(); // find in ivlist to disable seat
    }
    
    public void seatAssign(){
        ivListAll.add(A1);
        ivListAll.add(A2);
        ivListAll.add(A3);
        ivListAll.add(A4);
        ivListAll.add(A5);
        ivListAll.add(B1);
        ivListAll.add(B2);
        ivListAll.add(B3);
        ivListAll.add(B4);
        ivListAll.add(B5);
        ivListAll.add(B6);
        ivListAll.add(B7);
        ivListAll.add(B8);
        ivListAll.add(B9);
        ivListAll.add(B10);
        ivListAll.add(B11);
        ivListAll.add(B12);
        ivListAll.add(B13);
        ivListAll.add(B14);
        ivListAll.add(B15);
        ivListAll.add(B16);
        ivListAll.add(B17);
        ivListAll.add(B18);
        ivListAll.add(B19);
        ivListAll.add(B20);
        ivListAll.add(C1);
        ivListAll.add(C2);
        ivListAll.add(C3);
        ivListAll.add(C4);
        ivListAll.add(C5);
        ivListAll.add(C6);
        ivListAll.add(C7);
        ivListAll.add(C8);
        ivListAll.add(C9);
        ivListAll.add(C10);
        ivListAll.add(C11);
        ivListAll.add(C12);
        ivListAll.add(C13);
        ivListAll.add(C14);
        ivListAll.add(C15);
        ivListAll.add(C16);
        ivListAll.add(C17);
        ivListAll.add(C18);
        ivListAll.add(C19);
        ivListAll.add(C20);
        ivListAll.add(D1);
        ivListAll.add(D2);
        ivListAll.add(D3);
        ivListAll.add(D4);
        ivListAll.add(D5);
        ivListAll.add(D6);
        ivListAll.add(D7);
        ivListAll.add(D8);
        ivListAll.add(D9);
        ivListAll.add(D10);
        ivListAll.add(D11);
        ivListAll.add(D12);
        ivListAll.add(D13);
        ivListAll.add(D14);
        ivListAll.add(D15);
        ivListAll.add(D16);
        ivListAll.add(D17);
        ivListAll.add(D18);
        ivListAll.add(D19);
        ivListAll.add(D20);
        ivListAll.add(E1);
        ivListAll.add(E2);
        ivListAll.add(E3);
        ivListAll.add(E4);
        ivListAll.add(E5);
        ivListAll.add(E6);
        ivListAll.add(E7);
        ivListAll.add(E8);
        ivListAll.add(E9);
        ivListAll.add(E10);
        ivListAll.add(E11);
        ivListAll.add(E12);
        ivListAll.add(E13);
        ivListAll.add(E14);
        ivListAll.add(E15);
        ivListAll.add(E16);
        ivListAll.add(E17);
        ivListAll.add(E18);
        ivListAll.add(E19);
        ivListAll.add(E20);
        ivListAll.add(F1);
        ivListAll.add(F2);
        ivListAll.add(F3);
        ivListAll.add(F4);
        ivListAll.add(F5);
        ivListAll.add(F6);
        ivListAll.add(F7);
        ivListAll.add(F8);
        ivListAll.add(F9);
        ivListAll.add(F10);
        ivListAll.add(F11);
        ivListAll.add(F12);
        ivListAll.add(F13);
        ivListAll.add(F14);
        ivListAll.add(F15);
        ivListAll.add(F16);
        ivListAll.add(F17);
        ivListAll.add(F18);
        ivListAll.add(F19);
        ivListAll.add(F20);
        
        // Test for Disable all
//        for (int i = 1; i <= 5; i++) { ivListDisable.add("A"+i); }
//        for (int i = 1; i <= 20; i++) { ivListDisable.add("B"+i); }
//        for (int i = 1; i <= 20; i++) { ivListDisable.add("C"+i); }
//        for (int i = 1; i <= 20; i++) { ivListDisable.add("D"+i); }
//        for (int i = 1; i <= 20; i++) { ivListDisable.add("E"+i); }
//        for (int i = 1; i <= 20; i++) { ivListDisable.add("F"+i); }

//        ivListDisable.add("A1");
//        ivListDisable.add("A5");
//        ivListDisable.add("B17");
        
        
    }
    public void seatDisable(){
        for (ImageView iv : ivListAll) {
            for (String id : ivListDisable) {
                if(id.equals(iv.getId())){
                    System.out.println("Found" + iv.getId());
                    iv.setDisable(true);
                    if(iv.getId().substring(0,1).equals("A")){
                        // disable image for a
                        InputStream imagePaired = getClass().getResourceAsStream("/cinema/ui/images/pair1.png");
                        iv.setImage(new Image(imagePaired));
                    }else if(iv.getId().substring(0,1).equals("B") || iv.getId().substring(0,1).equals("C")){
                        // disable image for b
                        InputStream imageHoneymoon = getClass().getResourceAsStream("/cinema/ui/images/blue1.png");
                        iv.setImage(new Image(imageHoneymoon));
                    }else{
                        // disable for d-f
                        InputStream imageNormal = getClass().getResourceAsStream("/cinema/ui/images/red1.png");
                        iv.setImage(new Image(imageNormal));
                    }
                }
            }
        }
    }

    @FXML
    private void nextPage(ActionEvent event) throws IOException { // ยังทำไม่ได้
        System.err.println("Hellllllllo");
    }

    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) throws FileNotFoundException { //MouseEvent
        ImageView iv = (ImageView) event.getSource(); // get image source
        InputStream fileInputStream;
        isFound = false;
        // Loop to find id is list
        for (String id : ivList) {
            if (id.equals(iv.getId())) {
                isFound = true;
            }
        }
        if (isFound) {
            if (Integer.parseInt(maxIV.substring(1)) == Integer.parseInt(iv.getId().substring(1)) || Integer.parseInt(iv.getId().substring(1)) == Integer.parseInt(minIV.substring(1))) {
                System.out.println(maxIV.substring(1));
                if (Integer.parseInt(maxIV.substring(1)) == Integer.parseInt(iv.getId().substring(1))) {
                    maxIV = maxIV.substring(0, 1) + Integer.toString(Integer.parseInt(maxIV.substring(1)) - 1);
                    System.out.println(maxIV);

                } else {
                    minIV = minIV.substring(0, 1) + Integer.toString(Integer.parseInt(minIV.substring(1)) + 1);
                    System.out.println(minIV);

                }
                if (Integer.parseInt(maxIV.substring(1)) < Integer.parseInt(minIV.substring(1))) {
                    count = 0;
                }
                //                System.out.println("Found"); // เจอข้อมูล
                ivList.remove(new String(iv.getId())); // ลบ id นั้นออก
                if (new String(minIV.substring(0, 1)).equals("A")) {
                    fileInputStream = getClass().getResourceAsStream("/cinema/ui/images/pair.png");
                    Image image = new Image(fileInputStream); //, 100, 200, false, true
                    iv.setImage(image);

                } else if (new String(minIV.substring(0, 1)).equals("C") || new String(minIV.substring(0, 1)).equals("B")) {
                    fileInputStream = getClass().getResourceAsStream("/cinema/ui/images/blue.png");
                    Image image = new Image(fileInputStream); //, 100, 200, false, true
                    iv.setImage(image);
                } else {
                    fileInputStream = getClass().getResourceAsStream("/cinema/ui/images/red.png");
                    Image image = new Image(fileInputStream); //, 100, 200, false, true
                    iv.setImage(image);
                };

            } else {
                AlertMaker.showErrorMessage("แจ้งเตือน", "ไม่สามารถลบที่นั่งที่ไม่ได้อยู่ชิดริมได้");
                System.out.print("can't");
            }
        } else {
            if (count > 0) {
//            System.out.print(minIV.substring(0, 1));
//            System.out.print(iv.getId().substring(0, 1));
//            System.out.print(new String(minIV.substring(0, 1)).equals(iv.getId().substring(0, 1)));
                if (new String(minIV.substring(0, 1)).equals(iv.getId().substring(0, 1))) { //check same line
//                System.out.println((Integer.parseInt(maxIV.substring(1)) + 1).equals(Integer.parseInt(iv.getId().substring(1))));
//                System.out.println(Integer.parseInt(iv.getId().substring(1)) == Integer.parseInt(minIV.substring(1)) - 1);
                    if (Integer.parseInt(maxIV.substring(1)) + 1 == Integer.parseInt(iv.getId().substring(1)) || Integer.parseInt(iv.getId().substring(1)) == Integer.parseInt(minIV.substring(1)) - 1) { //check continuous
                        if (count == 1) {
                            if (Integer.parseInt(iv.getId().substring(1)) > Integer.parseInt(iv.getId().substring(1))) {
                                maxIV = minIV;
                                minIV = iv.getId();
                            } else {
                                maxIV = iv.getId();
                            }

                        } else {
                            if (Integer.parseInt(iv.getId().substring(1)) == Integer.parseInt(maxIV.substring(1)) + 1) {
                                maxIV = iv.getId();
                            } else {
                                minIV = iv.getId();
                            }

                        }
                        // check that minIV > maxIV, if yes, swap!!
                        if (Integer.parseInt(minIV.substring(1)) > Integer.parseInt(maxIV.substring(1))) {
                            String temp = maxIV;
                            maxIV = minIV;
                            minIV = temp;
                        }

                        //                System.out.println("Not found"); // ไม่เจอข้อมูล
                        ivList.add(iv.getId()); // เพิ่ม id เข้าไป
                        if (new String(minIV.substring(0, 1)).equals("A")) {
                            fileInputStream = getClass().getResourceAsStream("/cinema/ui/images/pair2.png");
                            Image image = new Image(fileInputStream); //, 100, 200, false, true
                            iv.setImage(image);

                        } else if (new String(minIV.substring(0, 1)).equals("C") || new String(minIV.substring(0, 1)).equals("B")) {
                            fileInputStream = getClass().getResourceAsStream("/cinema/ui/images/blue2.png");
                    Image image = new Image(fileInputStream); //, 100, 200, false, true
                    iv.setImage(image);
                        } else {
                            fileInputStream = getClass().getResourceAsStream("/cinema/ui/images/red2.png");
                            Image image = new Image(fileInputStream); //, 100, 200, false, true
                            iv.setImage(image);
                        };

                    } else {
                        AlertMaker.showErrorMessage("แจ้งเตือน", "กรุณาเลือกที่นั่งที่ติดกัน"); //+minIV+maxIV);
                        System.out.print("Noooo continuous");
                    }
                } else {
                    AlertMaker.showErrorMessage("แจ้งเตือน", "กรุณาเลือกที่นั่งแถวเดียวกัน");
                    System.out.print("Noooooooooo same line");
                }
            } else {
                System.out.print(iv.getId().substring(0, 1));
                minIV = iv.getId();
                maxIV = iv.getId();
                System.out.println(new String(minIV.substring(0, 1)).equals("C"));
                //                System.out.println("Not found"); // ไม่เจอข้อมูล
                ivList.add(iv.getId()); // เพิ่ม id เข้าไป
                if (new String(minIV.substring(0, 1)).equals("A")) {
                    fileInputStream = getClass().getResourceAsStream("/cinema/ui/images/pair2.png");
                    Image image = new Image(fileInputStream); //, 100, 200, false, true
                    iv.setImage(image);

                } else if (new String(minIV.substring(0, 1)).equals("C") || new String(minIV.substring(0, 1)).equals("B")) {
                    fileInputStream = getClass().getResourceAsStream("/cinema/ui/images/blue2.png");
                    Image image = new Image(fileInputStream); //, 100, 200, false, true
                    iv.setImage(image);
                } else {
                    fileInputStream = getClass().getResourceAsStream("/cinema/ui/images/red2.png");
                    Image image = new Image(fileInputStream); //, 100, 200, false, true
                    iv.setImage(image);
                };

            }
            count += 1;
        }
        
        System.out.println(ivList);

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showtime/showtime.fxml"));
        Scene parentScene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(parentScene);
        window.show();
    }



}
