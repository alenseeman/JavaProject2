package Server_Banka;

import Knjige.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerBanka {

    public static final int PORT = 9001;
    private static int counter = 0;

    public static HashMap<String, String> racuni = new HashMap<String, String>();
    public static HashMap<String, String> lista = new HashMap<String, String>();

    public static void main(String[] args) {
        ServerBankaMetode.kursnaLista();
        racuni.put("alen", "250.0#BAM");
        racuni.put("test", "2500.0#HRK");
        racuni.put("student", "10#EUR");
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("Pokrenut je server banke...");
            while (true) {
                Socket s = ss.accept();
                System.out.println("Prihvacen je klijent: " + (++counter));
                new ServerBankaThread(s, counter);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
