package Korisnik;
import java.io.*;
import java.net.*;

public class Klijent
{
  public static final int PORT_BANKA=9001;
  public static final int PORT_SERVER=9000;
  
  public static void main(String[] args)
  {
    try{
      InetAddress addr=InetAddress.getByName("127.0.0.1");
      Socket s=new Socket(addr,PORT_SERVER);
    
    BufferedReader in= new BufferedReader(new InputStreamReader(s.getInputStream()));
    PrintWriter out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
    
    
    
    out.println("bla");
    String a=in.readLine();
    System.out.println(a);
    
    
    in.close();
    out.close();
    s.close();
    }catch (Exception e)
    { e.printStackTrace();}}}