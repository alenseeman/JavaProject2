package Knjige;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.File;
import java.io.Serializable;

public class Knjiga implements Serializable {

    public static final String valuta = "BAM";
    protected String naziv;
    protected String isbn;
    protected String imePisca;
    protected int brojStrana;
    protected String kratakSadrzaj;
    protected String izdavac;
    protected String naslovnaStrana;
    protected double cijena;
    protected int kolicina;
    protected String zanrString;
    protected ArrayList<String> zanr = new ArrayList<String>();
    protected String serijal;

    public Knjiga() {
        super();
    }

    public Knjiga(String n, String is, String pisac, int strana, String sadrzaj, String izdavac, double c, int k, File naslovna, ArrayList<String> z) {
        naziv = n;
        isbn = is;
        imePisca = pisac;
        brojStrana = strana;
        kratakSadrzaj = sadrzaj;
        this.izdavac = izdavac;
        cijena = c;
        kolicina = k;
        zanr = z;
        zanrString = "";
        for (int i = 0; i < zanr.size(); i++) {
            zanrString += zanr.get(i) + " , ";
        }

        try {
            if (!naslovna.getName().endsWith(".jpg")) {
                throw new Exception("Naziv fajla nije dobar!");
            }
            naslovnaStrana = naslovna.getAbsolutePath();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getNaslovnaStranica() {

        return this.naslovnaStrana;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getImePisca() {
        return imePisca;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getBrojStrana() {
        return brojStrana;
    }

    public String getKratakSadrzaj() {
        return kratakSadrzaj;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public double getCijena() {
        return cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setImePisca(String p) {
        imePisca = p;
    }

    public void setIsbn(String i) {
        isbn = i;
    }

    public void setBrojStrana(int br) {
        brojStrana = br;
    }

    public void setKratakSadrzaj(String s) {
        kratakSadrzaj = s;
    }

    public void setIzdavac(String i) {
        izdavac = i;
    }

    public void setCijena(double c) {
        cijena = c;
    }

    public void setKolicina(int k) {
        kolicina = k;
    }

    public void dodajZanr(String za) {
        if (!zanr.contains(za)) {
            zanr.add(za);
        }
    }

    public ArrayList<String> getZanr() {
        ArrayList<String> b = new ArrayList<String>();
        for (Iterator<String> it = zanr.iterator(); it.hasNext();) {
            String s = it.next();
            b.add(s);
        }
        return b;

    }

    public void setZanr(ArrayList<String> b) {
        zanr = b;
    }

    public void setSerijal(String s) {
        serijal = s;
    }

    public String getSerijal() {
        return serijal;
    }

    public String getZanrString() {
        String s = "";
        for (Iterator<String> it = zanr.iterator(); it.hasNext();) {
            s += it.next() + " ";
        }
        return s;
    }

    //@Override
    public boolean equals(Knjiga knjiga) {
        if (knjiga == null) {
            return false;
        }
        if (getClass() != knjiga.getClass()) {
            return false;
        }
        final Knjiga k = (Knjiga) knjiga;
        if (!this.isbn.equals(k.isbn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Naziv: " + naziv + " Ime pisca: " + imePisca + " Zanrovi: " + getZanrString();
    }
}
