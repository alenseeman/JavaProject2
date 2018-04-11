/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektni2016;

import Knjige.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author Semanic
 */
public class Klijent {

    private static Socket sock;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    public static String vrijeme;

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String v) {
        vrijeme = v;
    }

    public static String postaviKursnuListu() {
        String odgovor = "";

        try {
            out.writeObject("KURSNAL");
            odgovor = (String) in.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return odgovor;
    }

    public static Vector<Knjiga> preuzmiSveKnjige() {
        Vector<Knjiga> knjige = new Vector<Knjiga>();
        try {
            out.writeObject("SVEKNJIGE");
            knjige = (Vector<Knjiga>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return knjige;
    }

    public static Knjiga[] preuzmiKatalog() {
        String a = "";
        Knjiga[] knjigeIzKataloga = new Knjiga[0];
        try {
            out.writeObject("KATALOG");
            a = (String) in.readObject();
            if (a.equals("true")) {
                knjigeIzKataloga = (Knjiga[]) in.readObject();
            }
            if (a.equals("false")) {
                System.out.println("Katalog je prazan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return knjigeIzKataloga;
    }

    public static Vector<Knjiga> osvjezavajTabeluKnjiga() {
        Vector<Knjiga> knjige = new Vector<Knjiga>();
        try {
            out.writeObject("OSVKNJIGE");
            knjige = (Vector<Knjiga>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return knjige;
    }

    public static void pokreniSocket() {
        try {
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            sock = new Socket(addr, 9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void poveziInOut() {
        try {
            in = new ObjectInputStream(sock.getInputStream());
            out = new ObjectOutputStream(sock.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Vector<Serijal> preuzmiSveSerijale() {
        Vector<Serijal> serijali = new Vector<Serijal>();
        try {
            out.writeObject("SVISERIJALI");
            serijali = (Vector<Serijal>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serijali;
    }

    public static String iznajmiK(String knjiga) {
        String odgovor = "";
        try {
            out.writeObject("IZDAVANJE#" + knjiga);
            odgovor = (String) in.readObject();
            if (!odgovor.equals("false") && !odgovor.equals("zakljucana")) {
                Klijent.vrijeme = odgovor;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return odgovor;
    }

    public static Double kupiSerijal(String serijal, String korisnik, String novacValuta) {
        String odgovor = "";
        Double stanje = 0.0;
        String novac = novacValuta.split(" ")[0];
        String valuta = novacValuta.split(" ")[1];
        try {
            out.writeObject("KUPISERIJAL#" + serijal + "#" + korisnik + "#" + novac + "#" + valuta);
            odgovor = (String) in.readObject();
            if (odgovor.equals("true")) {
                stanje = (Double) in.readObject();
                FXMLDocumentController.pozoviObavjestenje("Hvala vam", "Uspjesno ste kupili serijal.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (odgovor.equals("true")) {
            System.out.println("Serijal je uspjesno kupljen");
            return stanje;
        } else {
            System.out.println("Serijal NIJE uspjesno kupljena");
            FXMLDocumentController.pozoviObavjestenje("Greska", "Nismo u mogucnosti da vam prodamo taj serijal.");
            return Double.parseDouble(novac);
        }
    }

    public static Double kupiKnjigu(String knjiga, String korisnik, String novacValuta) {
        String odgovor = "";
        Double stanje = 0.0;
        Knjiga kupljenaKnjiga = null;
        String novac = novacValuta.split(" ")[0];
        String valuta = novacValuta.split(" ")[1];
        try {
            out.writeObject("KUPIKNJIGU#" + knjiga + "#" + korisnik + "#" + novac + "#" + valuta);
            odgovor = (String) in.readObject();
            if (odgovor.equals("true")) {
                stanje = (Double) in.readObject();
                kupljenaKnjiga = (Knjiga) in.readObject();
                FXMLDocumentController.pozoviObavjestenje("Hvala vam !", "Uspjesno ste kupili knjigu !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (kupljenaKnjiga instanceof ElektronskaKnjiga) {
                ElektronskaKnjiga elKnjiga = (ElektronskaKnjiga) kupljenaKnjiga;
                File fajl = new File("korisnik" + File.separator + korisnik);
                fajl.mkdir();
                FileInputStream fis = new FileInputStream(new File(".\\..\\..\\Server\\sveKnjige" + File.separator + elKnjiga.getNaziv() + ".txt"));
                FileOutputStream fos = new FileOutputStream(fajl + File.separator + elKnjiga.getNaziv() + ".txt");
                byte[] buffer = new byte[2 * 1024 * 1024];
                long length = elKnjiga.getSadrzajElektronskeKnjige().length();
                int duzina = 0, ukupno = 0;
                while ((duzina = fis.read(buffer)) > 0) {
                    ukupno += duzina;
                    fos.write(buffer, 0, duzina);
                    if (ukupno == length) {
                        break;
                    }
                }
                fis.close();
                fos.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (odgovor.equals("true")) {
            System.out.println("Knjiga je uspjesno kupljena");
            return stanje;
        } else {
            System.out.println("Knjiga NIJE uspjesno kupljena");
            FXMLDocumentController.pozoviObavjestenje("Greska", "Nismo u mogucnosti da vam prodamo trazenu knjigu.");
            return Double.parseDouble(novac);
        }
    }

    public static Vector<Knjiga> pretragaPoZanru(String a) {
        Vector<Knjiga> odgovor = new Vector<Knjiga>();
        try {
            out.writeObject("ZANRPRET#" + a);
            odgovor = (Vector<Knjiga>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return odgovor;
    }

    public static Vector<Knjiga> pretragaPoNaslovu(String a) {
        Vector<Knjiga> odgovor = new Vector<Knjiga>();
        try {
            out.writeObject("KNJIGAPRET#" + a);
            odgovor = (Vector<Knjiga>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return odgovor;
    }

    public static String provjeraKorisnika(String a, String b) {
        String odgovor = "";
        try {
            out.writeObject("LOGIN#" + a + "#" + b);
            odgovor = (String) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return odgovor;
    }

    public static String provjeraKorisnikaBanka(String a) {
        String odgovor = "";
        try {
            out.writeObject("BANKALOGIN#" + a);
            odgovor = (String) in.readObject();
            String novacvaluta = (String) in.readObject();
            odgovor = odgovor + "#" + novacvaluta;
            System.out.println(odgovor);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return odgovor;
    }
}
