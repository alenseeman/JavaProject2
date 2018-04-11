package projektni2016;

import Knjige.Knjiga;
import Knjige.Serijal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Semanic
 */
public class FXMLDocumentController implements Initializable {

    static String zanr;
    static String knjiga;
    static String opcija = "";
    private Klijent kk = new Klijent();
    private static String lista;
    private Label label;
    @FXML
    private AnchorPane LoginEkran;
    @FXML
    private AnchorPane KorisnikEkran;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button LoginBtn;
    @FXML
    public TextField userNameText;
    @FXML
    private AnchorPane homeTab;
    @FXML
    private Button iznajmiKnjiguBtn;
    @FXML
    private Button kupiKnjiguBtn;
    @FXML
    private Button kupiSerijalBtn;
    private TextArea tabelaValuta;
    @FXML
    private Label novacValutaKorisnikLabel;
    @FXML
    private Label prikazImenaKorisnikaLabel;
    @FXML
    private Tab prikazsvihKnjigaTab;
    @FXML
    private TableView<Knjiga> prikazSvihKnjigaTabela;
    @FXML
    private Button mojKatalogBtn;
    @FXML
    private Button preuzmiKatalogBtn;
    @FXML
    private Button pretragaPoZanrovimaBtn;
    @FXML
    private Tab prikazsvihSerijalaTab;
    @FXML
    private AnchorPane prikazTabeleEkran;
    @FXML
    private Button pretragaPoNaslovuKnjigebtn;

    public final static int PORT_SERVER = 9000;
    @FXML
    private AnchorPane tabelaEkran;
    @FXML
    private TableView<Serijal> prikazSvihSerijalaTabela;
    @FXML
    private Label labelaValuta1;
    @FXML
    private Label labelaValuta2;
    @FXML
    private Label labelaValuta3;
    @FXML
    private Label labelaValuta4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void klikLoginBtn(ActionEvent event) throws IOException {
        Klijent.pokreniSocket();
        Klijent.poveziInOut();
        String sifra = passwordText.getText();
        String korisnik = userNameText.getText();

        if (korisnik.equals("") || sifra.equals("")) {
            korisnik = "a";
            sifra = "a";
        }
        if ((Klijent.provjeraKorisnika(korisnik, sifra)).equals("true")) {
            String a = "";
            if (((a = Klijent.provjeraKorisnikaBanka(korisnik))).startsWith("true")) {
                prikazImenaKorisnikaLabel.setText(korisnik);
                String valuta = (String) (a.split("#")[1] + " " + a.split("#")[2]);
                novacValutaKorisnikLabel.setText(valuta);
                setLabeleValute();
                pozoviGlavniMeni();
            } else {
                pozoviObavjestenje("Greska !", "Molimo vas pokusajte ponovo !");
            }
        } else {
            pozoviObavjestenje("Greska !", "Molimo vas pokusajte ponovo !");
        }

    }

    private void pozoviGlavniMeni() {
        pozoviObavjestenje("Dobrodosli !", "Uspjesno ste se prijavili");
        Vector<Serijal> serijali = Klijent.preuzmiSveSerijale();
        upisiSerijale(serijali);
        Vector<Knjiga> knjige = Klijent.preuzmiSveKnjige();
        postaviTabeluKnjige(knjige);
        LoginEkran.setVisible(false);
        KorisnikEkran.setVisible(true);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setLabeleValute();
                        Vector<Knjiga> knjige = Klijent.osvjezavajTabeluKnjiga();
                        postaviTabeluKnjige(knjige);
                    }
                });
            }
        }, 2000, 2000);
    }

    public void postaviTabeluKnjige(Vector<Knjiga> knjige) {
        ObservableList<Knjiga> data = FXCollections.observableArrayList(knjige);
        data.removeAll(data);
        prikazSvihKnjigaTabela.getColumns().clear();
        TableColumn prviRed = new TableColumn("Naziv Knjige");
        TableColumn drugiRed = new TableColumn("Autor");
        TableColumn treciRed = new TableColumn("Izdavac");
        TableColumn cetvrtiRed = new TableColumn("Cijena(BAM)");
        TableColumn petiRed = new TableColumn("Kolicina");
        prikazSvihKnjigaTabela.getColumns().addAll(prviRed, drugiRed, treciRed, cetvrtiRed, petiRed);
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
        cetvrtiRed.setCellValueFactory(
                new PropertyValueFactory<>("cijena")
        );
        petiRed.setCellValueFactory(
                new PropertyValueFactory<>("kolicina")
        );
        prikazSvihKnjigaTabela.setItems(data);
    }

    public void upisiSerijale(Vector<Serijal> serijali) {
        ObservableList<Serijal> data = FXCollections.observableArrayList(serijali);
        data.removeAll(data);

        prikazSvihSerijalaTabela.getColumns().clear();
        TableColumn prviRed = new TableColumn("Naziv Serijala");
        TableColumn drugiRed = new TableColumn("Knjige Serijala");
        TableColumn treciRed = new TableColumn("Cijena(BAM)");
        prikazSvihSerijalaTabela.getColumns().addAll(prviRed, drugiRed, treciRed);
        data = FXCollections.observableArrayList(serijali);
        prviRed.setCellValueFactory(
                new PropertyValueFactory<>("imeSerijala")
        );
        drugiRed.setCellValueFactory(
                new PropertyValueFactory<>("knjigeSerijala")
        );
        treciRed.setCellValueFactory(
                new PropertyValueFactory<>("cijenaSerijala")
        );
        prikazSvihSerijalaTabela.setItems(data);
    }

    public void setLabeleValute() {
        lista = Klijent.postaviKursnuListu();
        String[] valute = lista.split("#");
        //System.out.println(kursnaLista);

        labelaValuta1.setText("EUR" + "     " + valute[0].substring(0, 6));
        labelaValuta2.setText("HRK" + "     " + valute[1].substring(0, 6));
        labelaValuta3.setText("USD" + "     " + valute[2].substring(0, 6));
        labelaValuta4.setText("NOK" + "     " + valute[3].substring(0, 6));
    }

    public void setNovacValutaLabel(String n) {
        novacValutaKorisnikLabel.setText(n);
    }

    @FXML
    private void klikIznajmiKnjigu(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("naslov knjige...");
        dialog.setTitle("Iznajmljivanje knjige");
        dialog.setHeaderText(null);
        dialog.setContentText("Upisite naslov knjige :");
        try {
            Optional<String> result = dialog.showAndWait();
            String odgovor = Klijent.iznajmiK(result.get());

            if (!odgovor.equals("false")) {
                if (odgovor.equals("zakljucana")) {
                    pozoviObavjestenje("Obavjestenje !", "Knjigu u ovom trenutku nije moguce iznajmiti.");
                } else {
                    pozoviObavjestenje("Obavjestenje !", "Iznajmili ste knjigu.");
                    Tajmer a = new Tajmer();
                }
            } else {
                pozoviObavjestenje("Greska !", "Knjigu nije moguce iznajmiti");

            }

        } catch (Exception e) {
        }
    }

    @FXML
    private void klikKupiKnjigu(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("naslov knjige...");
        dialog.setTitle("Kupovina knjige");
        dialog.setHeaderText(null);
        dialog.setContentText("Upisite naslov knjige :");
        try {
            Optional<String> result = dialog.showAndWait();
            Double novac = Klijent.kupiKnjigu(result.get(), prikazImenaKorisnikaLabel.getText(), novacValutaKorisnikLabel.getText());
            String a = novacValutaKorisnikLabel.getText().split(" ")[1];
            setNovacValutaLabel(new DecimalFormat(".##").format(novac) + " " + a);
        } catch (Exception e) {
        }
    }

    @FXML
    private void klikKupiSerijal(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("naslov serijala...");
        dialog.setTitle("Kupovina serijala");
        dialog.setHeaderText(null);
        dialog.setContentText("Upisite naslov serijala :");
        try {
            Optional<String> result = dialog.showAndWait();
            if (!result.get().equals("naslov serijala...")) {
                Double novac = Klijent.kupiSerijal(result.get(), prikazImenaKorisnikaLabel.getText(), novacValutaKorisnikLabel.getText());
                String a = novacValutaKorisnikLabel.getText().split(" ")[1];
                setNovacValutaLabel(new DecimalFormat(".##").format(novac) + " " + a);
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void klikMojKatalog(ActionEvent event) {

        Knjiga[] knjige = Klijent.preuzmiKatalog();
        if (!(knjige.length == 0)) {
            try {
                Stage stage = new Stage();
                Parent root3 = FXMLLoader.load(getClass().getResource("FXMLKatalog.fxml"));
                Scene scene = new Scene(root3);
                stage.setTitle("Knjigoholik-katalog");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pozoviObavjestenje("Paznja !", "Katalog je prazan");
        }
    }

    @FXML
    private void klikPreuzmiKatalog(ActionEvent event) {
        try {
            Knjiga[] knjige = Klijent.preuzmiKatalog();
            File fajl = new File("korisnici_preuzeliKatalog" + File.separator + userNameText.getText());
            fajl.mkdir();
            for (int i = 0; i < knjige.length; i++) {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fajl + File.separator + knjige[i].getNaziv() + ".txt")));
                out.println("Naziv knjige: " + knjige[i].getNaziv());
                out.println(" ISBN: " + knjige[i].getIsbn());
                out.println(" Autor: " + knjige[i].getImePisca());
                out.println(" Broj stranice: " + knjige[i].getBrojStrana());
                out.println(" Kratak sadrzaj: " + knjige[i].getKratakSadrzaj());
                out.println(" Izdavac: " + knjige[i].getIzdavac());
                out.println(" Cijena: " + knjige[i].getCijena());
                out.close();

            }
            pozoviObavjestenje("Obavjestenje !", "Uspjesno ste preuzeli katalog!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void klikPretragaPoZanrovima(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("zanr...");
        dialog.setTitle("Pretraga knjiga po zanru");
        dialog.setHeaderText(null);
        dialog.setContentText("Upisite zanr:");
        try {
            Optional<String> result = dialog.showAndWait();
            if (!result.get().equals("zanr...")) {
                opcija = "zanr";
                zanr = result.get();
                Vector<Knjiga> knjige = Klijent.pretragaPoZanru(result.get());
                if (knjige.isEmpty()) {
                    pozoviObavjestenje("Greska !", "Ne posjedujemo nijednu knjigu tog zanra !");
                } else {
                    Stage stage = new Stage();
                    Parent root2 = FXMLLoader.load(getClass().getResource("FXMLTabele.fxml"));
                    Scene scene = new Scene(root2);
                    stage.setTitle("Knjigoholik");
                    stage.setScene(scene);
                    stage.show();
                }
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void klikPretragaPoNaslovuKnjige(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("naslov knjige...");
        dialog.setTitle("Pretraga knjiga po naslovu");
        dialog.setHeaderText(null);
        dialog.setContentText("Upisite naslov knjige :");
        try {
            Optional<String> result = dialog.showAndWait();
            if (!result.get().equals("naslov knjige...")) {
                opcija = "naslov";
                knjiga = result.get();
                Vector<Knjiga> knjige = Klijent.pretragaPoNaslovu(result.get());
                if (knjige.isEmpty()) {
                    pozoviObavjestenje("Greska !", "Ne posjedujemo knjigu takvog naziva !");
                } else {
                    Stage stage = new Stage();
                    Parent root2 = FXMLLoader.load(getClass().getResource("FXMLTabele.fxml"));
                    Scene scene = new Scene(root2);
                    stage.setTitle("Knjigoholik");
                    stage.setScene(scene);
                    stage.show();
                }
            }
        } catch (Exception e) {
        }
    }

    public TableView getTabeluKnjiga() {
        return prikazSvihKnjigaTabela;
    }

    public TableView getTabeluSerijala() {
        return prikazSvihSerijalaTabela;
    }

    public static void pozoviObavjestenje(String a, String b) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(a);
        alert.setHeaderText(null);
        alert.setContentText(b);
        alert.showAndWait();
    }

}
