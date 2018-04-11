package Knjige;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.Serializable;

public class ElektronskaKnjiga extends Knjiga implements Serializable {

    private File sadrzajKnjige;
    private String putanja;
    protected boolean zakljucan = false;

    public ElektronskaKnjiga() {
        super();
    }

    public ElektronskaKnjiga(File knjiga, String n, String is, String pisac, int strana, String sadrzaj, String izdavac, double c, int k, File naslovna, ArrayList<String> z) {
        super(n, is, pisac, strana, sadrzaj, izdavac, c, k, naslovna, z);
        sadrzajKnjige = knjiga;
    }

    public synchronized void setZakljucana(boolean ba) {
        zakljucan = ba;
    }

    public boolean getZakljucana() {
        return zakljucan;
    }

    public File getSadrzajElektronskeKnjige() {
        return this.sadrzajKnjige;
    }

    public void setSadrzajKnjige(File knjiga) {
        FileInputStream in = null;
        FileOutputStream out = null;
        File sadrzajKnjige = new File(naziv + ".txt");

        try {
            in = new FileInputStream(sadrzajKnjige);
            out = new FileOutputStream(knjiga);
            int m = 0;
            byte[] bafer = new byte[2 * 1024 * 1024];
            while ((m = in.read(bafer)) > 0) {
                out.write(bafer, 0, m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPutanja(String p) {
        putanja = p;
    }

    public String getPutanja() {
        return putanja;
    }
}
