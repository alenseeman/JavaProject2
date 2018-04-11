package GlavniServer;
import Knjige.*; 
import java.net.*;
import java.util.*;
import java.io.*;

public class Server
{
  public static final int PORT=9000;
  public static int counter=0;
  public static final String KNJIGE = "./sveknjige/";
  public static void init()
  {
  //elektonskeKnjige
  ArrayList<String> zek1 = new ArrayList<String>();
  zek1.add("Tragedija");
  zek1.add("Ljubavni");
  ElektronskaKnjiga ek1=new ElektronskaKnjiga(new File(KNJIGE+File.separator+"Anna Karenina.txt"),"Anna Karenina","417-61-7625-174-5","Leo Tolstoy",402,"Ana Karenjina je roman sa suvremenom tematikom u kojoj je središnja tema preljub glavnog lika Ane, a zbog cega biva odbacena od strane društva da bi na kraju doživjela tragican kraj.",
                                              "Russian print",14.5,7,new File(KNJIGE+File.separator+"Anna Karenina.jpg"),zek1);
  
  ArrayList<String> zek2 = new ArrayList<String>();
  zek2.add("Parodija");
  zek2.add("Komedija");
  ElektronskaKnjiga ek2=new ElektronskaKnjiga(new File(KNJIGE+File.separator+"Don Quixote.txt"),"Don Quixote","275-32-7345-356-6","Miguel de Cervantes",564,"Radnja pokriva putovanja i avanture Don Kihota i njegovog štitonoše, Sanco Panse. Alonso Quijano ili Quesada je obicni Španac (hidalgo, najniži red španskog plemstva) koji je opsjednut pricama o viteškim lutanjima (libros de caballerias).",
                                             "Espanna",14,5,new File(KNJIGE+File.separator+"Don Quixote.jpg"),zek2);

 
  ArrayList<String> zek3 = new ArrayList<String>();
  zek3.add("Drama");
  
ElektronskaKnjiga ek3 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"Sandwich Glass.txt"),"Sandwich Glass","75-56-44332211","Lenore Wheeler Williams",290,"Sandwich glass was made of silex, ash, nitre, pig lead, and other"+
"ingredients but the secret of the bright surface on old pressed glass, acharacteristic which differentiates it from the modern pressed glass,was the use of barytes.","Gutenberg",25,3,new File(KNJIGE+File.separator+"Sandwich Glass.jpg"),zek3);
  

ArrayList<String> zek4 = new ArrayList<String>();
  zek4.add("Avantura");
  zek4.add("Misterija");
  zek4.add("Krimi");
  ElektronskaKnjiga ek4 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"The Adventures of Sherlock Holmes.txt"),"The Adventures of Sherlock Holmes","595-64-6931-859-3","Arthur Conan Doyle",653,"Holmes is portrayed as offering a new, fairer sense of justice. The stories were well received, and boosted the subscriptions figures of The Strand Magazine, prompting Doyle to be able to demand more money for his next set of stories.",
                                                "Oxford",16,12,new File(KNJIGE+File.separator+"The Adventures of Sherlock Holmes.jpg"),zek4);
  ArrayList<String> zek5 = new ArrayList<String>();
  zek5.add("Avantura");
  zek5.add("Djeciji");
  
  ElektronskaKnjiga ek5=new ElektronskaKnjiga(new File(KNJIGE+File.separator+"The Adventures of Tom Sawyer.txt"),"The Adventures of Tom Sawyer","137-63-4132-253-6","Mark Twain",224,"A novel about a young boy growing up along the Mississippi River. It is set in the fictional town of St. Petersburg, inspired by Hannibal, Missouri, where Twain lived.",
                                               "American press",13,4,new File(KNJIGE+File.separator+"The Adventures of Tom Sawyer.jpg"),zek5);
  
  ArrayList<String> zek6 = new ArrayList<String>();
  zek6.add("Avantura");
  zek6.add("Fantazija");
  ElektronskaKnjiga ek6= new ElektronskaKnjiga(new File(KNJIGE+File.separator+"The Call of the Wild.txt"),"The Call of the Wild","399-62-6737-691-5","Jack London",132,"The Call of the Wild is a short adventure novel by Jack London published in 1903 and set in Yukon, Canada during the 1890s Klondike Gold Rush, when strong sled dogs were in high demand.",
                                               "American press",19,5,new File(KNJIGE+File.separator+"The Call of the Wild.jpg"),zek6);
   
  ArrayList<String> zek7 = new ArrayList<String>();
  zek7.add("Avantura");
  zek7.add("Fantazija");
  ElektronskaKnjiga ek7=new ElektronskaKnjiga(new File(KNJIGE+File.separator+"The Time Machine.txt"),"The Time Machine","664-43-4513-239-9","H. G. Wells",234,"The Time Machine has since been adapted into three feature films of the same name, as well as two television versions, and a large number of comic book adaptations. It has also indirectly inspired many more works of fiction in many media.",
                                               "Oxford",14,13,new File(KNJIGE+File.separator+"The Time Machine.jpg"),zek7);
  
  ArrayList<String> zek8 = new ArrayList<String>();
  zek8.add("Ljubavni");
  zek8.add("Istorijski");
  zek8.add("Ratni");
  ElektronskaKnjiga ek8=new ElektronskaKnjiga(new File(KNJIGE+File.separator+"War and Peace.txt"),"War and Peace","589-14-9115-811-5","Leo Tolstoy",563,"The novel charts the history of the French invasion of Russia, and the impact of the Napoleonic era on Tsarist society, through the stories of five Russian aristocratic families.",
                                               "Russian print",15,3,new File(KNJIGE+File.separator+"War and Peace.jpg"),zek8);
   
  
  ArrayList<String> zek9 = new ArrayList<String>();
  zek9.add("Drama");
ElektronskaKnjiga ek9 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"Dracula.txt"),"Dracula","65-55-15","Bram Stoker",310,"The grey of the morning has passed, and the sun ishigh over the distant horizon, which seems jagged, whether with trees or"+
"hills I know not, for it is so far off that big things and little aremixed.",
        "Gutenberg",28,8,new File(KNJIGE+File.separator+"Dracula.jpg"),zek9);
  
  
  ArrayList<String> zek10 = new ArrayList<String>();
  zek10.add("Roman");
ElektronskaKnjiga ek10 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"No Hero.txt"),"No Hero","1245697","E.W. Hornung",208,"What, the Matterhorn? said he, lowering his voice and looking about him as if on the point of some discreditable admission. \"Oh, yes, I've done the Matterhorn, back and front and both sides, with and without guides but everybody has, in these days... ","Gutenberg",17.0,5,new File(KNJIGE+File.separator+"No Hero.jpg"),zek10);
                                              
   ArrayList<String> zek11 = new ArrayList<String>();
    zek11.add("Melodrama");
    ElektronskaKnjiga ek11 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"The Structure and Life.txt"),"The Structure and Life","655432","Alfred Denny",354,"Kratka prica o usponu Panasonica...","Laguna",150,5,new File(KNJIGE+File.separator+"The Structure and Life.jpg"),zek11);
  
                                              
                                              
   ArrayList<String> zek12 = new ArrayList<String>();
    zek12.add("Komedija");
    zek12.add("Naucna-Fantastika"); 
    ElektronskaKnjiga ek12 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"A Walk from London to Fulham.txt"),"A Walk from London to Fulham","123456","Thomas Crofton Croker",200,"Prica o ljubavi izmedju dvoje mladih...","Glas Srpske",25,15,new File(KNJIGE+File.separator+"A Walk from London to Fulham.jpg"),zek12);
                                                 
     
    ArrayList<String> zek13 = new ArrayList<String>();
  zek13.add("Drama");                                                                                  
   ElektronskaKnjiga ek13 = new ElektronskaKnjiga(new File("/sveknjjige/"+File.separator+"Hogarth's Works, Volume 1 of 3.txt"),"Hogarth's Works, Volume 1 of 3","98-55-15","John Ireland",325,"It is a singular fact, that, notwithstanding the enormous popularity"+
"enjoyed by Hogarth in the minds of English people, no perfectly popular edition has been hitherto brought before the public.",
        "Gutenberg",22.5,4,new File(KNJIGE+File.separator+"Hogarth's Works, Volume 1 of 3.jpg"),zek13);
     ek13.setSerijal("Hogarth's Works");
    
     
    ElektronskaKnjiga ek14 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"Hogarth's Works, Volume 2 of 3.txt"),"Hogarth's Works, Volume 2 of 3","78-1-15","John Ireland",340,"The artist has adhered to his engagement: he has struck at an higher order, and displayed the follies and vices which frequently"+
"degrade our nobility.","Gutenberg",25,2,new File(KNJIGE+File.separator+"Hogarth's Works, Volume 2 of 3.jpg"),zek13);
     ek14.setSerijal("Hogarth's Works");
    
        ElektronskaKnjiga ek15 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"Hogarth's Works, Volume 3 of 3.txt"),"Hogarth's Works, Volume 3 of 3","78-2-15","John Ireland",345,"The artist has adhered to his engagement: he has struck at an higher order, and displayed the follies and vices which frequently"+
"degrade our nobility.","Gutenberg",25,3,new File(KNJIGE+File.separator+"Hogarth's Works, Volume 3 of 3.jpg"),zek13);
    ek15.setSerijal("Hogarth's Works");
  
    Knjiga[] serijalKnjiga1 = {ek13,ek14,ek15};
    Serijal serijal2 = new Serijal(serijalKnjiga1,"Hogarth's Works",3);
    Trgovina.serijali.add(serijal2);  
    
    
  ArrayList<String> zek16 = new ArrayList<String>();
  zek16.add("Roman");
    ElektronskaKnjiga ek16 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"Beethoven.txt"),"Beethoven","1245697","George Alexander Fischer",217,"Beethoven A Character Study New Complete Edition Brand New Copies By George Alexander Fischer If Bach is the mathematician of music, as has been asserted, Beethoven is its philosopher...","Gutenberg",15.5,5,new File(KNJIGE+File.separator+"Beethoven.jpg"),zek16);

    
    ArrayList<String> zek17 = new ArrayList<String>();
  zek17.add("Drama");                                                                        
     ElektronskaKnjiga ek17 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"Erwachen und Bestimmung.txt"),"Erwachen und Bestimmung","78-55-15","Carl Maria Weber",340,"Aus lohenden Ekstasen Sprang ich zurück zu mir, Nun bin ich ausgeblasen -- Verklimpertes Klavier...",
        "Gutenberg",25,2,new File(KNJIGE+File.separator+"Erwachen und Bestimmung.jpg"),zek17);
    
    ArrayList<String> zek18 = new ArrayList<String>();
    zek18.add("Istorijski");
     ElektronskaKnjiga ek18 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"People of Africa.txt"),"People of Africa","isbn1","Edith A. How",240,"In this book we are going to read about some of the other people who live in our own great country--Africa.  Africa is very, very large, so"+
   "big that no one would be able to go to all the places in it.  But different people have been to different parts, and have told what they saw where they went..","Laguna",45.0,1,new File(KNJIGE+File.separator+"People of Africa.jpg"),zek18);   
    
     ArrayList<String> zek19 = new ArrayList<String>();
    zek19.add("Roman");
     ElektronskaKnjiga ek19 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"Transylvanian Superstitions.txt"),"Transylvanian Superstitions","1111-97","Emily Gerard",160,"Transylvania might well be termed the land of superstition, for nowhere else does this curious crooked plant of delusion flourish as persistently and in such bewildering variety....","Gutenberg",20.5,5,new File(KNJIGE+File.separator+"Transylvanian Superstitions.jpg"),zek19);
   ArrayList<String> zek20 = new ArrayList<String>();
    zek20.add("Istorijski");
    ElektronskaKnjiga ek20 = new ElektronskaKnjiga(new File(KNJIGE+File.separator+"With Wolfe in Canada.txt"),"With Wolfe in Canada","345-97","G. A. Henty",450,"Mr. Henty here gives an account of the struggle between Britain and France for supremacy in the North American continent. The fall of Quebec decided that the Anglo-Saxon race should predominate in the New World; and that English and American commerce, the English language, and English literature, should spread right round the globe...","Gutenberg",19.5,5,new File(KNJIGE+File.separator+"With Wolfe in Canada.jpg"),zek20);
  
ArrayList<String> zek21 = new ArrayList<String>();
   zek21.add("Komedija");
    File file8 = new File(KNJIGE+File.separator+"Historia de una parisiense.jpg");
    File file88 = new File(KNJIGE+File.separator+"Historia de una parisiense.txt");
    ElektronskaKnjiga ek21 = new ElektronskaKnjiga(file88,"Historia de una parisiense","784651","Octave Feuillet",201,"Sería excesivo pretender que todas las jóvenes casaderas son unosángeles; pero hay ángeles entre las jóvenes casaderas. Esto no es una rareza, y, lo que parece más extraño, es que quizá en París es menosraro que en otra parte. La razón es sencilla.","Akademska misao",125,8,file8,zek21);
    

    
Knjiga[] serijalKnjigaSveE = {ek1,ek2,ek3,ek4,ek5,ek6,ek7,ek8,ek9,ek10,ek11,ek12,ek13,ek14,ek15,ek16,ek17,ek18,ek19,ek20,ek21};
    Serijal serijalSveE = new Serijal(serijalKnjigaSveE,"Sve elektronske knjige",21);
    Trgovina.serijali.add(serijalSveE);  

//papirne knjige
  ArrayList<String> zpk1= new ArrayList<String>();
  zpk1.add("Komedija");
  zpk1.add("Avantura");
  Knjiga pk1=new PapirnaKnjiga("Orlovi rano lete","978-86-7781-194-5","Branko Copic",240,"„Orlovi rano lete“ se sastoji od dva dela. Radnja se odvija u periodu pre pocetka Drugog svetskog rata. Mesto radnje:Bosanska krajina, selo Lipovo.","YU knjiga",10,5,new File(KNJIGE+File.separator+"Orlovi rano lete.jpg"),zpk1);
  ArrayList<String> zpk2=new ArrayList<String>();
  zpk2.add("Istorijski");
  Knjiga pk2=new PapirnaKnjiga("Na Drini cuprija","978-86-1300-444-8","Ivo Andric",360,"Roman pripoveda o gradenju mosta preko reke Drine u bosanskom gradu Višegradu. Gradenje mosta narucio je Mehmed paša Sokolovic, cuveni zvanicnik Osmanskog carstva koji je bio rodeni Srbin iz Rudog. Još kao mali decak, Bajica, odveden je sa ostalom decom kao danak u krvi u Carigrad.","Glas Srpske",13,6,new File(KNJIGE+File.separator+"Na Drini cuprija.jpg"),zpk2);
  ArrayList<String> zpk3=new ArrayList<String>();
  zpk3.add("Drama");
  Knjiga pk3=new PapirnaKnjiga("Tvrdjava","978-86-6107-029-7","Mesa Selimovic",363,"Tvrdjava s obzirom na prvu glavu romana, te kroz sugerisanje u samom naslovu knjige je (kao rijec i kao pojava pojam) roman rata i mira u njihovoj unutrašnjoj vezi kao ljudsko pojedinacno i skupno iskustvo, moralni iskaz na ispitima meduljudskog odnosa uz pritisak društvenog, politickog, državnog regulativa gdje je tema vlasti preuzeta iz Derviša, sa istim pretpostavkama.","Glas Srpske",12,9,new File(KNJIGE+File.separator+"Tvrdjava.jpg"),zpk3);
  ArrayList<String> zpk4=new ArrayList<String>();
  zpk4.add("Tragedija");
  Knjiga pk4=new PapirnaKnjiga("Antigona","978-86-3532-137-3","Sofokle",134,"Glavna tema u Antigoni nošenje je pojedinca s boli i patnjom, uzrokovanom kada se netko (Kreont) uporno protivi božjim propisima ili željama i odbija pustiti sudbinu okolnostima, što dovodi do bolnog (Kreontovog) otkrivanja istine i na kraju do osvete njegovom ponašanju.","Prosveta",8.7,6,new File(KNJIGE+File.separator+"Antigona.jpg"),zpk4);
  Knjiga[] serijalKnjiga2 = {pk1,pk2,pk3};
    Serijal serijal3 = new Serijal(serijalKnjiga2,"Domace Knjige",3);
    Trgovina.serijali.add(serijal3);  
  
  
  ArrayList<String> zpk5 = new ArrayList<String>();
    zpk5.add("Fantastika");
    zpk5.add("Triler");
  Knjiga pk5 = new PapirnaKnjiga("Atlantida","54-134","Borislav Pekic",259,"Izmedju njega i sveta postoji zid mracne tajne...Radnja se dogadja danas, ali buducnost je vec prošla!","Laguna",25,3,new File(KNJIGE+File.separator+"Atlantida.jpg"),zpk5);  
  
  ArrayList<String> zpk6 = new ArrayList<String>();
    zpk6.add("Psiholoska drama");
    File file23 = new File(KNJIGE+File.separator+"Proces.jpg");
    PapirnaKnjiga pk6 = new PapirnaKnjiga("Proces","909-01-3588-555-2","Franc Kafka",154,"Proces je složeno, viseslojno i najpoznatije delo Franca Kafke. Kao sto je naznaceno vec naslovom, u romanu se ne radi, o sudbini bankarskog službenika Jozefa K. o njegovom hapsenju, njegovoj potrazi za nepoznatom mu krivicom i o njegovom pogubljenju na kraju, vec se radi o postupku kome je on podvrgnut. Predmet romana jeste stvarnost nevidljivog suda koji svojim prisustvom prožima ceo roman.","Akademska misao",17,5,file23,zpk6);
   
  ArrayList<String> zpk7 = new ArrayList<String>();
    zpk7.add("Djecija");
    zpk7.add("Slikovnica");
    File file28 = new File(KNJIGE+File.separator+"Tijana na selu.jpg");
    PapirnaKnjiga pk7 = new PapirnaKnjiga("Tijana na selu","86-7436-260-5","Pjer Kuron",16,"Tijana i njen psic Lucko provesce nekoliko dana na selu. Na farmi se rodilo mnogo životinja otkako je poslednji put bila na njoj. ","Laguna",5.5,7,file28,zpk7);
   
    Knjiga[] serijalKnjigaSveP = {pk1,pk2,pk3,pk4,pk5,pk6,pk7};
    Serijal serijalSveP = new Serijal(serijalKnjigaSveP,"Sve papirne knjige",7);
    Trgovina.serijali.add(serijalSveP);  
  
  
  Trgovina.knjige.add(ek1);
  Trgovina.knjige.add(ek2);
  Trgovina.knjige.add(ek3);
  Trgovina.knjige.add(ek4);
  Trgovina.knjige.add(ek5);
  Trgovina.knjige.add(ek6);
  Trgovina.knjige.add(ek7);
  Trgovina.knjige.add(ek8);
  Trgovina.knjige.add(ek9);
  Trgovina.knjige.add(ek10);
  Trgovina.knjige.add(ek11);
  Trgovina.knjige.add(ek12);
  Trgovina.knjige.add(ek13);
  Trgovina.knjige.add(ek14);
  Trgovina.knjige.add(ek15);
  Trgovina.knjige.add(ek16);
  Trgovina.knjige.add(ek17);
  Trgovina.knjige.add(ek18);
  Trgovina.knjige.add(ek19);
  Trgovina.knjige.add(ek20);
  Trgovina.knjige.add(ek21);
  Trgovina.knjige.add(pk1);
  Trgovina.knjige.add(pk2);
  Trgovina.knjige.add(pk3);
  Trgovina.knjige.add(pk4);
  Trgovina.knjige.add(pk5);
  Trgovina.knjige.add(pk6);
  Trgovina.knjige.add(pk7);
  //serijalizacija----RADI KAKO TREBA 
  try{
    ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("./serijalizacija/"+File.separator+"Knjige.ser"));
    oos.writeObject(Trgovina.knjige);
    oos.close();
  }catch( Exception e)
  { e.printStackTrace();
  }
  try{ ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("./serijalizacija/"+File.separator+"Serijali.ser"));
    oos.writeObject(Trgovina.serijali);
    oos.close();
  }catch( Exception e)
  { e.printStackTrace();
  }
  }
  
  public static void main(String[] args)
  {
    init();
    try{
      ServerSocket ss=new ServerSocket(PORT);
      System.out.println("Pokrenut je glavni server...");
      while(true)
      {
        Socket sock=ss.accept();
        System.out.println("Prihvacen je klijent: "+(++counter));
        new ServerThread(sock,counter);}
       }
    catch(SocketException se){
      System.out.println("Klijent se odjavio");
    }catch( Exception e)
    {
      e.printStackTrace();
    }}}
      