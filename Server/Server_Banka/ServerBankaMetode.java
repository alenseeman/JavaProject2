package Server_Banka;

import java.util.*;

public class ServerBankaMetode {

    public static String provjeraKorisnika(String korisnik) {
        Set<String> korisnici = ServerBanka.racuni.keySet();
        if (korisnici.contains(korisnik)) {
            return "true";
        } else {
            return "false";
        }
    }

    public static String novacValuta(String korisnik) {
        return ServerBanka.racuni.get(korisnik);
    }

    public static String getKursnaLista() {
        String[] valuta = {"EUR", "HRK", "USD", "NOK"};
        String vrijednost = "";
        for (int i = 0; i < ServerBanka.lista.size(); i++) {
            vrijednost += ServerBanka.lista.get(valuta[i]);
            vrijednost = vrijednost + "#";
        }
        vrijednost = vrijednost.substring(0, vrijednost.lastIndexOf("#") - 1);
        return vrijednost;
    }

    public static void umanjiIznos(String user, double cijena) {
        String stanje = ServerBanka.racuni.get(user).split("#")[0];
        String valuta = ServerBanka.racuni.get(user).split("#")[1];
        double stanjeRacuna = Double.parseDouble(stanje);

        if (!valuta.equals("BAM")) {
            String vrijednost = ServerBanka.lista.get(valuta);
            Double vr = Double.parseDouble(vrijednost);
            cijena *= vr;
        }
        stanjeRacuna -= cijena;

        stanje = stanjeRacuna + " " + valuta;
        //System.out.println("KUPUJEM....."+stanje);
    }

    public static double konverzija(String valuta) {
        String s = ServerBanka.lista.get(valuta);
        double vrijednost = Double.parseDouble(ServerBanka.lista.get(valuta));
        //System.out.println("konverzija: dobijamo :"+vrijednost);
        return vrijednost;
    }

    public static void osvjeziRacun(String korisnik, double cijena) {
        String stanjeValuta = ServerBanka.racuni.get(korisnik);
        double stanje = Double.parseDouble(stanjeValuta.split("#")[0]);
        String valuta = stanjeValuta.split("#")[1];
        stanje -= cijena;

        stanjeValuta = stanje + "#" + valuta;
        ServerBanka.racuni.remove(korisnik);
        // System.out.println("Promijenili smo racun:"+stanjeValuta);
        ServerBanka.racuni.put(korisnik, stanjeValuta);
    }

    public static void kursnaLista() {
        Random r = new Random();
        String[] valute = {"EUR", "HRK", "USD", "NOK"};
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    ServerBanka.lista.clear();
                    for (int i = 0; i < 4; i++) {
                        Double vrijednost = new Double(r.nextDouble() * 7 + 1.2);
                        ServerBanka.lista.put(valute[i], vrijednost.toString());
                    }
                    try {
                        Thread.sleep(20000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
