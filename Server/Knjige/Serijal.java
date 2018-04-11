package Knjige;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Iterator;
import java.text.DecimalFormat;

public class Serijal implements Serializable {

    Knjiga[] serijal;
    private String imeSerijala;
    private int brojKnjiga;
    private double cijenaSerijala;
    private String knjigeSerijala;

    public Serijal() {
        super();
    }

    public Serijal(Knjiga[] s, String ime, int broj) {
        if (s.length == broj || s.length > broj) {
            if (broj > 1) {
                serijal = s;
                imeSerijala = ime;
                brojKnjiga = broj;
                cijenaSerijala = 0.0;
                knjigeSerijala = "";
                for (int i = 0; i < serijal.length; i++) {
                    knjigeSerijala += serijal[i].getNaziv() + "\n";
                }
                for (int i = 0; i < brojKnjiga; i++) {
                    cijenaSerijala += s[i].getCijena() * (0.85);
                }
                new DecimalFormat("###.###").format(cijenaSerijala);
            }
        } else {
            System.out.println("Nije moguce napraviti serijal,pokusajte ponovo sa dvije ili vise knjiga.");
        }
    }

    public String getKnjigeSerijala() {
        return knjigeSerijala;
    }

    public void setCijenaSerijala(double cijena) {
        cijenaSerijala = cijena;
    }

    public Double getCijenaSerijala() {
        return cijenaSerijala;
    }

    public void setSerijal(Knjiga[] lista) {
        serijal = new Knjiga[lista.length];
        serijal = lista;
        brojKnjiga = lista.length;
        cijenaSerijala = 0;
        for (int i = 0; i < this.brojKnjiga; i++) {
            cijenaSerijala += serijal[i].getCijena() * 0.85;
        }
    }

    public Knjiga getKnjiguIzSerijala(int i) {
        return serijal[i];
    }

    public Knjiga[] getSerijal() {
        return this.serijal;
    }

    public void setBrojKnjiga(int b) {
        brojKnjiga = b;
    }

    public int getBrojKnjiga() {
        return brojKnjiga;
    }

    public String getImeSerijala() {
        return imeSerijala;
    }

    public void setImeSerijala(String ime) {
        imeSerijala = ime;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < serijal.length; i++) {
            s += serijal[i] + ",";
        }

        return s;
    }

}
