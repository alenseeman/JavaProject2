package Server_Banka;

import Knjige.*;
import java.net.*;
import java.util.Random;
import java.io.*;

public class ServerBankaThread extends Thread {

    private Socket sock;

    private PrintWriter out;
    private BufferedReader in;
    private int num;

    public ServerBankaThread(Socket sock, int broj) {
        this.sock = sock;
        num = broj;
        try {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        String u = "";

        try {
            while ((u = (String) in.readLine()) != null) {
                if (u.startsWith("LOGIN")) { //System.out.println("ovo radi fantasticno");
                    String korisnik = u.split("#")[1];
                    String pomoc = "a#b";
                    out.println(ServerBankaMetode.provjeraKorisnika(korisnik));
                    //System.out.println(ServerBankaMetode.provjeraKorisnika(korisnik));
                    if (((ServerBankaMetode.provjeraKorisnika(korisnik)).equals("true"))) {
                        pomoc = ServerBankaMetode.novacValuta(korisnik);
                    }
                    //System.out.println(pomoc);
                    out.println(pomoc);
                }
                if (u.startsWith("KURSNAL")) {
                    String odgovor = ServerBankaMetode.getKursnaLista();
                    out.println(odgovor);
                }

                if (u.startsWith("KONVERZIJA")) {
                    String valuta = u.split("#")[1];
                    System.out.println(valuta);
                    double novaVrijednost = ServerBankaMetode.konverzija(valuta);
                    // System.out.println(novaVrijednost);
                    out.println(novaVrijednost);
                }

                if (u.startsWith("KUPLJENA")) {
                    String korisnik = u.split("#")[1];
                    double cijena = Double.parseDouble(u.split("#")[2]);
                    //System.out.println("Promijenili smo racun:");

                    ServerBankaMetode.osvjeziRacun(korisnik, cijena);
                }

                if (u.startsWith("KUPLJENSERIJAL")) {
                    String korisnik = u.split("#")[1];
                    double cijena = Double.parseDouble(u.split("#")[2]);
                    //System.out.println("Promjena racuna");
                    ServerBankaMetode.osvjeziRacun(korisnik, cijena);
                }

            }

            in.close();
            out.close();
            sock.close();
        } catch (SocketException se) {
            System.out.println("Klijent " + num + " se odjavio");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
