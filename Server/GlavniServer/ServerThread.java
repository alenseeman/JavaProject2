package GlavniServer;
import Knjige.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class ServerThread extends Thread
{
  private Socket sock;
  private ObjectOutputStream out;
  private ObjectInputStream in;
  public static String obavjestenje="";
  private int broj;
  public static final int PORT_BANKE=9001;
  
  public ServerThread(Socket s,int br)
  {
    sock=s;
    broj=br;
    try{
    out = new ObjectOutputStream(sock.getOutputStream());
    in = new ObjectInputStream(sock.getInputStream());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    start();
  }
  
  @Override
  public void run()
  {
    Trgovina trgovina=new Trgovina();
    try{
   InetAddress addr=InetAddress.getByName("127.0.0.1");
    Socket s=new Socket(addr,PORT_BANKE); 
   BufferedReader inBanka= new BufferedReader(new InputStreamReader(s.getInputStream()));
   PrintWriter outBanka= new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
   String klijent="";
   while((klijent=(String)in.readObject())!=null)
   {
     if(klijent.startsWith("LOGIN"))
          {
       String user=klijent.split("#")[1];
       String pass=klijent.split("#")[2];
       String uis=user+"#"+pass;
      String korisnik="";
      File fajl = new File("./GlavniServer/korisnici.txt");
      BufferedReader korisnikfajl = new BufferedReader(new FileReader(fajl));
      boolean plus=false;
   while((korisnik=(String)korisnikfajl.readLine())!=null)
        {
            if(korisnik.equals(uis))
                 {  plus=true;
               break;
            }}
   if(plus)
   out.writeObject("true");
   else
          out.writeObject("false");
   }
     
     if(klijent.startsWith("BANKALOGIN"))
     {
       String korisnik=klijent.split("#")[1];
       outBanka.println("LOGIN#"+korisnik);
       String odgovor=(String) inBanka.readLine();
       out.writeObject(odgovor);
       String odgovor2=(String) inBanka.readLine();
       out.writeObject(odgovor2);
     }
     
     
     if(klijent.startsWith("KURSNAL"))
     {
       outBanka.println("KURSNAL");
       String odgovor=(String) inBanka.readLine();
       out.writeObject(odgovor);
     }
    if(klijent.startsWith("SVEKNJIGE"))
    {
      Vector<Knjiga> knjige = (Vector<Knjiga>)trgovina.sveKnjige();
      out.writeObject(knjige);} 
      
     
    if(klijent.startsWith("SVISERIJALI"))
         {
      Vector<Serijal> serijali = (Vector<Serijal>)trgovina.sviSerijali();
        out.writeObject(serijali); 
    }
     
   if(klijent.startsWith("IZDAVANJE"))
   {
     //System.out.println(" usao u izdavanje");
     String knjiga=klijent.split("#")[1];
     String odgovor=trgovina.iznajmiKnjigu(knjiga);
     //System.out.println(odgovor);
     out.writeObject(String.valueOf(odgovor)); 
    }
        
   
   if(klijent.startsWith("ZANRPRET"))
   {
     String zanr=klijent.split("#")[1];
     Vector<Knjiga> knjigePoZanru=trgovina.pronadjiZanr(zanr);
     out.writeObject(knjigePoZanru);
   }
   if(klijent.startsWith("KNJIGAPRET"))
   {
     String knjiga=klijent.split("#")[1];
     Vector<Knjiga> knjigePoNazivu=trgovina.pronadjiKnjigu(knjiga);
     out.writeObject(knjigePoNazivu);
   }
   
   if(klijent.startsWith("OSVKNJIGE"))
   {
   Vector<Knjiga> knjige=trgovina.osvjeziKnjige();
   out.writeObject(knjige);}
   
  
   if(klijent.startsWith("KUPIKNJIGU"))
   {
     double novaVrijednost=1;
     String knjiga=klijent.split("#")[1];
     String korisnik=klijent.split("#")[2];
     String stanjeNaRacunu=klijent.split("#")[3];
      double vrijednost = Double.parseDouble(stanjeNaRacunu);
     String valuta=klijent.split("#")[4];
     //System.out.println(knjiga+" "+korisnik+" "+stanjeNaRacunu+" "+valuta);
      if(!valuta.equals("BAM")){
            outBanka.println("KONVERZIJA#"+valuta);
            novaVrijednost = Double.parseDouble(inBanka.readLine());
            //vrijednost*=novaVrijednost;
      }
     // System.out.println("u bam "+vrijednost);
     Knjiga kupljenaKnjiga=trgovina.kupiKnjigu(knjiga,vrijednost,novaVrijednost);
     //out.writeObject(vrijednost-kupljenaKnjiga.getCijena());
     //System.out.println("Kupljena knjiga:"+kupljenaKnjiga+" saljem vrijednost: "+(vrijednost-kupljenaKnjiga.getCijena()));
     
if(kupljenaKnjiga !=null){
            outBanka.println("KUPLJENA#"+korisnik+"#"+(kupljenaKnjiga.getCijena()*novaVrijednost));
            out.writeObject("true");
            out.writeObject(vrijednost-(kupljenaKnjiga.getCijena()*novaVrijednost));
           out.writeObject(kupljenaKnjiga);
          }
          else{
            out.writeObject("false");
          }
        }
   
     if(klijent.startsWith("KATALOG")){
        Knjiga[] knjige = (Knjiga[])trgovina.kupljeneKnjige();
        if(knjige.length==0){
        out.writeObject("false");
        }
        else{
        out.writeObject("true");
        out.writeObject(knjige);
        }
        
        }
   
     if(klijent.startsWith("KUPISERIJAL"))
     {
       double novaVrijednost=1;
       String serijal=klijent.split("#")[1];
     String korisnik=klijent.split("#")[2];
     String stanjeNaRacunu=klijent.split("#")[3];
      double vrijednost = Double.parseDouble(stanjeNaRacunu);
     String valuta=klijent.split("#")[4];
     //System.out.println(serijal+" "+korisnik+" "+stanjeNaRacunu+" "+valuta);
      if(!valuta.equals("BAM")){
            outBanka.println("KONVERZIJA#"+valuta);
            novaVrijednost = Double.parseDouble(inBanka.readLine());
            //vrijednost*=novaVrijednost;
      }
     Serijal kupljeniSerijal=trgovina.kupiSerijal(serijal,vrijednost,novaVrijednost);
     if(kupljeniSerijal!=null)
     {
       for(int i=0;i<kupljeniSerijal.getSerijal().length;i++)
         trgovina.kupiKnjigu((kupljeniSerijal.getKnjiguIzSerijala(i)).getNaziv().toString(),vrijednost,novaVrijednost);
//System.out.println("Cijena serijala :"+kupljeniSerijal.getCijenaSerijala()*novaVrijednost);
       outBanka.println("KUPLJENSERIJAL"+"#"+korisnik+"#"+kupljeniSerijal.getCijenaSerijala()*novaVrijednost);
       out.writeObject("true");
       out.writeObject(vrijednost-kupljeniSerijal.getCijenaSerijala()*novaVrijednost);
     }
     else
     {
       out.writeObject("false");}}
   
   
   }
   out.close();
   in.close();
   sock.close();
   outBanka.close();
   inBanka.close();
   s.close();
    }
    catch(SocketException se){
      System.out.println("Klijent "+broj+" se odjavio");
    }
    catch (Exception e)
         {
            e.printStackTrace();
     }}} 
                                                  
   
   
   
  