/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektni2016;

import Knjige.Knjiga;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Semanic
 */
public class FXMLKatalogController implements Initializable {

    private HBox redKnjige;
    @FXML
    private GridPane gridP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            GridPane.setMargin(gridP, new Insets(5, 30, 20, 10));
            Knjiga[] knjige = Klijent.preuzmiKatalog();
            for (int i = 0; i < knjige.length; i++) {
                // System.out.println(knjige[i]);
                popuniRedKataloga(knjige[i], i);
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLKatalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void popuniRedKataloga(Knjiga knjiga, int red) throws IOException {
        File file = new File(knjiga.getNaslovnaStranica());
        javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toURL().toString());
        ImageView iv = new ImageView(image);
        iv.setFitHeight(200);
        iv.setFitWidth(130);

        String s = "";
        for (Iterator<String> it = knjiga.getZanr().iterator(); it.hasNext();) {
            s += it.next() + " ";
        }
        final Label label = new Label(" Naziv knjige : " + knjiga.getNaziv() + "\n Autor : " + knjiga.getImePisca() + "\n Zanr : " + s + "\n **********************************************************" + "\n Kratak sadrzaj: \n " + knjiga.getKratakSadrzaj());
        RowConstraints row = new RowConstraints();
        label.setWrapText(true);

        gridP.getRowConstraints().add(row);
        gridP.add(iv, 0, red);
        gridP.add(label, 1, red);
        GridPane.setHalignment(gridP, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(gridP, VPos.CENTER);

    }

}
