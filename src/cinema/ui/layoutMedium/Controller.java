/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.layoutMedium;

import cinema.CinemaController;
import cinema.screensframework.ControlledScreen;
import cinema.screensframework.ScreensController;
import cinema.screensframework.ScreensFramework;
import cinema.ui.AlertMaker;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
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
public class Controller implements Initializable, ControlledScreen {

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
    ArrayList<String> ivList = new ArrayList<String>();
    CinemaController cc;
    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isFound = false;
        count = 0;
        this.cc = cc.getInstance();
        // TODO
    }

    @FXML
    private void nextPage(ActionEvent event) throws IOException { // ยังทำไม่ได้
        System.err.println("Hellllllll");
//        cinema.getScreen().setScene(rootPane.getScene());
//        cinema.getScreen().addScreen("summary", FXMLLoader.load(getClass().getResource("summary.fxml"))); ///cinema/ui/showDetailsMovie/
//        cinema.getScreen().activate("summary");
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

    }

    @FXML
    private void back(ActionEvent event) throws IOException {

//        Parent showMovie = FXMLLoader.load(getClass().getResource("/cinema/ui/showmovie/showmovie.fxml"));
//
//        Scene showMovieScene = new Scene(showMovie);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(showMovieScene);
//        window.setMaximized(false);
//        window.show();
        myController.loadScreen(ScreensFramework.userShowtimeScreenID, ScreensFramework.userShowtimeScreenFile);
        myController.setScreen(ScreensFramework.userShowtimeScreenID);
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

}
