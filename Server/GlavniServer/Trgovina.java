package GlavniServer;
import Knjige.*;
import java.util.*;
import java.io.*;
import java.lang.Thread;
public class Trgovina
{
  public static Vector<Knjiga> knjige = new Vector<Knjiga>();
  public static Vector<Serijal> serijali=new Vector<Serijal>();
  public static final String KNJIGE = "./sveknjige/";
  private Knjiga[] kupljeneKnjige= new Knjiga[0];
  private Serijal[] kupljeniSerijali=new Serijal[0];
  private int brojac=0;
  public static int vrijeme=5;
  
  public Trgovina(){
    super();
  }

  public Knjiga[] kupljeneKnjige(){
    return kupljeneKnjige;
  }
  
  public Vector<Knjiga> pronadjiKnjigu(String a)
  {
    Vector<Knjiga> pretraga=new Vector<Knjiga>();
    for(int i=0;i<Trgovina.knjige.size();i++)
      if((Trgovina.knjige.get(i).getNaziv().toLowerCase()).startsWith(a.toLowerCase()))
       pretraga.add(Trgovina.knjige.get(i));         
  
  return pretraga;}

  public Vector<Knjiga> pronadjiZanr(String a)
  {
    Vector<Knjiga> pretraga=new Vector<Knjiga>();
    for(int i=0;i<Trgovina.knjige.size();i++){
      boolean ima=false;
      ArrayList<String> knj=Trgovina.knjige.get(i).getZanr();
    for(int ii=0;ii<knj.size();ii++)
    { if(knj.get(ii).toLowerCase().startsWith(a.toLowerCase()))
      ima=true;
    }
    if(ima)
      pretraga.add(Trgovina.knjige.get(i));
    }
    return pretraga;
  }
  
  public Serijal kupiSerijal(String ser,double stanjeNaRacunu,double novaVrijednost)
  {
    boolean moze=true;
    Serijal serijalZaKupiti=null;
    for(int i=0;i<Trgovina.serijali.size();i++){
      if((Trgovina.serijali.get(i).getImeSerijala().toLowerCase()).equals(ser.toLowerCase()) && stanjeNaRacunu>=Trgovina.serijali.get(i).getCijenaSerijala()*novaVrijednost)
      { 
        serijalZaKupiti=Trgovina.serijali.get(i);
        for(int ii=0;ii<serijalZaKupiti.getSerijal().length;ii++)
        if(serijalZaKupiti.getKnjiguIzSerijala(ii).getKolicina()<=0)
          moze=false;
        if(moze){
      Serijal[] temp=new Serijal[kupljeniSerijali.length+1];
      //arraycopy(izvor,pocetak,odrediste,pocetak,koliko)
      System.arraycopy(kupljeniSerijali,0,temp,0,kupljeniSerijali.length);
    temp[kupljeniSerijali.length] = serijalZaKupiti;
    kupljeniSerijali = temp;
        return serijalZaKupiti;
        }}}
    return null;
    }
  
        
  
  
  public Knjiga kupiKnjigu(String knj,double stanjeNaRacunu,double novaVrijednost)
  {
    //System.out.println("dosao kupiti knjigu");
    Knjiga knjigaZaKupiti=null;
    boolean knjigaUKatalogu=false;
    for(int i=0;i<Trgovina.knjige.size();i++)
    {if((Trgovina.knjige.get(i).getNaziv().toLowerCase()).equals(knj.toLowerCase()))
      { 
    if((Trgovina.knjige.get(i)).getKolicina()>0)
    {
      //System.out.println("kolicina kknjige:"+Trgovina.knjige.get(i).getKolicina()+" stanje na racunu: "+stanjeNaRacunu+ "cijena knjige: "+Trgovina.knjige.get(i).getCijena());
      if(stanjeNaRacunu>=Trgovina.knjige.get(i).getCijena()*novaVrijednost)
      {
        knjigaZaKupiti=Trgovina.knjige.get(i);
      knjigaZaKupiti.setKolicina(knjigaZaKupiti.getKolicina()-1);
      for(int ii=0;ii<kupljeneKnjige.length;ii++)
        if(kupljeneKnjige[ii].equals(knjigaZaKupiti))
             { knjigaUKatalogu=true;}
           if(!knjigaUKatalogu){
      Knjiga[] temp=new Knjiga[kupljeneKnjige.length+1];
      //arraycopy(izvor,pocetak,odrediste,pocetak,koliko)
      System.arraycopy(this.kupljeneKnjige,0,temp,0,this.kupljeneKnjige.length);
    temp[this.kupljeneKnjige.length] = knjigaZaKupiti;
    this.kupljeneKnjige = temp;}
           if(knjigaZaKupiti.getKolicina()==0){
//if(knjigaZaKupiti instanceof ElektronskaKnjiga && knjigaZaKupiti.getKolicina() == 0){
     try{
        boolean obrisao =knjige.remove(knjigaZaKupiti);
        System.out.println("knjiga je obrisana: "+obrisao);
      }catch(Exception e){
        e.printStackTrace();
      }
    }
      }}}
   
      
    }
  
   return knjigaZaKupiti;
    }
  
  
  
 @SuppressWarnings("unchecked")
  public Vector<Knjiga> sveKnjige(){
    Vector<Knjiga> knjige=null;
    try{
      ObjectInputStream in=new ObjectInputStream(new FileInputStream("./serijalizacija/"+File.separator+"Knjige.ser"));
      knjige=(Vector<Knjiga>) in.readObject();
      in.close();
    }catch( Exception e)
    { e.printStackTrace();}
  return knjige;
  }
  
  @SuppressWarnings("unchecked")
  public Vector<Knjiga> osvjeziKnjige()
  {
   try{
    ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("./serijalizacija/"+File.separator+"Knjige.ser"));
    oos.writeObject(Trgovina.knjige);
    oos.close();
  }catch( Exception e)
  { e.printStackTrace();
  }
   Vector<Knjiga> knjige=null;
    try{
      ObjectInputStream in=new ObjectInputStream(new FileInputStream("./serijalizacija/"+File.separator+"Knjige.ser"));
      knjige=(Vector<Knjiga>) in.readObject();
      in.close();
    }catch( Exception e)
    { e.printStackTrace();}
  return knjige; 
  }
  @SuppressWarnings("unchecked")
  public Vector<Serijal> sviSerijali(){
    Vector<Serijal> serijali = null;
    try{
    ObjectInputStream in = new ObjectInputStream(new FileInputStream("./serijalizacija/"+File.separator+"Serijali.ser"));
     serijali = (Vector<Serijal>) in.readObject();
     in.close();
    
    }catch(Exception e){
    e.printStackTrace();
    }
    return serijali;
  }
      
public String iznajmiKnjigu(String a)
{
  //System.out.println(a);
  String odgovor="";
  for(int i=0;i<knjige.size();i++)
  {   Knjiga k=knjige.get(i);
    if(k.getNaziv().toLowerCase().equals(a.toLowerCase()) &&(k instanceof ElektronskaKnjiga))
     {
    //if(!((ElektronskaKnjiga)knjige.get(i)).getZakljucana())
      if(k.getKolicina()>0){
      ElektronskaKnjiga ek=(ElektronskaKnjiga)knjige.get(i);
        //ek.setZakljucana(true);
        //System.out.println("knjigu smo zakljucali");
         Random r = new Random();
            vrijeme = r.nextInt(31)+20;
           // System.out.println("Vrijeme za thread je :" +vrijeme+" naziv knjige:"+ek.getNaziv()+" "+ek.getZakljucana());
        procesZaOtkljucavanje(ek.getNaziv(),vrijeme);
      return odgovor=String.valueOf(vrijeme);
    } return odgovor="zakljucana";
   }}
  return odgovor="false";    }

public static void  procesZaOtkljucavanje(String naziv,int vr)
{
  Thread a= new Thread(new Runnable(){public void run(){
          
           try{
             //System.out.println("idemoo");
    for(int i=0;i<Trgovina.knjige.size();i++){
      if(Trgovina.knjige.get(i).getNaziv().toLowerCase().equals(naziv.toLowerCase())){
              ElektronskaKnjiga knjiga = new ElektronskaKnjiga();
              knjiga= (ElektronskaKnjiga)Trgovina.knjige.get(i);
          knjiga.setZakljucana(true);   
          knjiga.setKolicina(knjiga.getKolicina()-1);
          //System.out.println("zakljucao knjigu "+knjiga.getZakljucana()+"  trenutna kolicina: "+knjiga.getKolicina());
             
           Thread.sleep(vr*1000);
                knjiga.setZakljucana(false);
                 knjiga.setKolicina(knjiga.getKolicina()+1);
          // System.out.println("otklucana knjiga "+knjiga.getZakljucana()+" a trenutna kolicina jeeee: "+knjiga.getKolicina());
      }}
           //System.out.println("Proslo vrijeme");
           
        }catch(Exception e){
           e.printStackTrace();
           }
          
          }
          }
          );
        a.start();}


  public static void ispisiVectorKnjige(Vector<Knjiga> k)
  {  for(Iterator<Knjiga> it=k.iterator();it.hasNext();)
   System.out.println((Knjiga) it.next());
  }}