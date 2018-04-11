/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektni2016;

import Knjige.Knjiga;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Semanic
 */
public class FXMLTabeleController implements Initializable {

    @FXML
    private AnchorPane EkranPrikazTabelaPretrazivanja;
    @FXML
    private TableView<Knjiga> tabelaZaPrikazPretraga;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String opcija = FXMLDocumentController.opcija;
        if (opcija.equals("zanr")) {
            Vector<Knjiga> knjige = Klijent.pretragaPoZanru(FXMLDocumentController.zanr);
            if (knjige.isEmpty()) {
                FXMLDocumentController.pozoviObavjestenje("Greska !", "Ne posjedujemo nijednu knjigu tog zanra !");
            } else {
                postaviTabeluKnjige(knjige);
            }
        } else if (opcija.equals("naslov")) {
            Vector<Knjiga> knjige = Klijent.pretragaPoNaslovu(FXMLDocumentController.knjiga);
            if (knjige.isEmpty()) {
                FXMLDocumentController.pozoviObavjestenje("Greska !", "Ne posjedujemo knjigu takvog naziva !");
            } else {
                postaviTabeluKnjige(knjige);
            }
        }
    }

    public void postaviTabeluKnjige(Vector<Knjiga> knjige) {
        ObservableList<Knjiga> data = FXCollections.observableArrayList(knjige);
        data.removeAll(data);
        tabelaZaPrikazPretraga.getColumns().clear();
        TableColumn prviRed = new TableColumn("Naziv Knjige");
        TableColumn drugiRed = new TableColumn("Autor");
        TableColumn treciRed = new TableColumn("Izdavac");
        TableColumn trRed = new TableColumn("Zanr");
        TableColumn cetvrtiRed = new TableColumn("Cijena(BAM)");
        TableColumn petiRed = new TableColumn("Kolicina");
        tabelaZaPrikazPretraga.getColumns().addAll(prviRed, drugiRed, treciRed, trRed, cetvrtiRed, petiRed);
        data = FXCollections.observableArrayList(knjige);
        prviRed.setCellValueFactory(
                new PropertyValueFactory<>("naziv")
        );
        drugiRed.setCellValueFactory(
                new PropertyValueFactory<>("imePisca")
        );
        treciRed.setCellValueFactory(
                new PropertyValueFactory<>("izdavac")
        );

        trRed.setCellValueFactory(
                new PropertyValueFactory<>("zanrString")
        );
        cetvrtiRed.setCellValueFactory(
                new PropertyValueFactory<>("cijena")
        );
        petiRed.setCellValueFactory(
                new PropertyValueFactory<>("kolicina")
        );
        tabelaZaPrikazPretraga.setItems(data);
    }

}
