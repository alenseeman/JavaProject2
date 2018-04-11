/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektni2016;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author Semanic
 */
public class Tajmer extends Thread {

    public Tajmer() {
        start();
    }

    public void run() {

        try {
            sleep(Long.parseLong(Klijent.vrijeme) * 1000);
            System.out.println(Long.parseLong(Klijent.vrijeme));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Paznja !");
                    alert.setHeaderText(null);
                    alert.setContentText("Knjiga  je vracena , te se moze ponovo iznajmiti!");
                    alert.showAndWait();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
