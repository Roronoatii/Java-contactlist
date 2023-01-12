package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private static final String SEPARATEUR = ";";

    private String firstname;
    private String lastname;
    private String email;
    private String number;
    private Date birthday;

    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getEmail() {
        return email;
    }
    public String getNumber() {
        return number;
    }
    public String getBirthday() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(birthday);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setEmail(String email) throws ParseException{
        Pattern pat = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pat.matcher(email);
        if(matcher.matches()){
            this.email = email;
        }else{
            ParseException e= new ParseException("Invalid email", 0);
            throw e;
        }
    }
    public void setNumber(String number) throws ParseException{
        Pattern pat = Pattern.compile("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$");
        Matcher matcher = pat.matcher(number);
        if(matcher.matches()){
            this.number = number;
        }else{
            ParseException e= new ParseException("Invalid number", 0);
            throw e;
        }

    }
    public void setBirthday(String birthday) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.birthday = format.parse(birthday);
        
    }
    public void enregistrer() throws IOException{
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)));
        try{
            pw.println(this.toString());
        }finally{
            pw.close();
        }
        }
    public static ArrayList<Contact> lister() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("contacts.csv"));
        ArrayList<Contact> list = new ArrayList<>();        
        try{
            String ligne = br.readLine();
            while(ligne != null){
                String[] table = ligne.split(";");
                Contact contact = new Contact();

                contact.setFirstname(table[0]);
                contact.setLastname(table[1]);
                contact.setEmail(table[2]);
                contact.setNumber(table[3]);
                contact.setBirthday(table[4]);

                list.add(contact);
                ligne = br.readLine();
    }
        }catch (ParseException | IOException e){
            System.out.println("Error");

        }finally{
            br.close();
            }
        System.out.println(list);
        return list;
        }

    public static ArrayList<Contact> chercherNom(String nom) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("contacts.csv"));
        ArrayList<Contact> list = new ArrayList<>();        
        try{
            String ligne = br.readLine();
            while(ligne != null){
                String[] table = ligne.split(";");
                Contact contact = new Contact();

                contact.setFirstname(table[0]);
                contact.setLastname(table[1]);
                contact.setEmail(table[2]);
                contact.setNumber(table[3]);
                contact.setBirthday(table[4]);
                if (table[1].equals(nom)){
                    System.out.println(contact.getFirstname() + " " + contact.getLastname() + " " + contact.getEmail() + " " + contact.getNumber() + " " + contact.getBirthday());
                    list.add(contact);
                }

                ligne = br.readLine();
            }
        }catch (ParseException | IOException e){
            System.out.println("Error");

        }finally{
            br.close();
        }

        return list;
    }
    public static ArrayList<Contact> chercherDDN(String DDN) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("contacts.csv"));
        ArrayList<Contact> list = new ArrayList<>();        
        try{
            String ligne = br.readLine();
            while(ligne != null){
                String[] table = ligne.split(";");
                Contact contact = new Contact();

                contact.setFirstname(table[0]);
                contact.setLastname(table[1]);
                contact.setEmail(table[2]);
                contact.setNumber(table[3]);
                contact.setBirthday(table[4]);
                if (table[4].equals(DDN)){
                    System.out.println(contact.getFirstname() + " " + contact.getLastname() + " " + contact.getEmail() + " " + contact.getNumber() + " " + contact.getBirthday());
                    list.add(contact);
                }
    
                ligne = br.readLine();
        }
        }catch (ParseException | IOException e){
            System.out.println("Error");

        }finally{
            br.close();
        }

        return list;
    }
    public static void refreshlist(ArrayList<Contact> list) throws IOException{
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", false)));
        try{
            
            for (Contact contact : list) {
                pw.println(contact.toString());
            }
        }finally{
            pw.close();
        }
    }
    

    @Override
    public String toString(){
        StringBuilder build = new StringBuilder();
        build.append(this.getFirstname());
        build.append(SEPARATEUR);
        build.append(this.getLastname());
        build.append(SEPARATEUR);
        build.append(this.getEmail());
        build.append(SEPARATEUR);
        build.append(this.getNumber());
        build.append(SEPARATEUR);
        build.append(this.getBirthday());
        return build.toString();
    }
}
