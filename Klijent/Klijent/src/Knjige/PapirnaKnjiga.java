package Knjige;

import java.util.ArrayList;
import java.io.File;
import java.io.Serializable;

public class PapirnaKnjiga extends Knjiga implements Serializable {

    public PapirnaKnjiga() {
        super();
    }

    public PapirnaKnjiga(String n, String im, String p, int s, String a, String b, double c, int k, File naslovna, ArrayList<String> z) {
        super(n, im, p, s, a, b, c, k, naslovna, z);
    }
}
