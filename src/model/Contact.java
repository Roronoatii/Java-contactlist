package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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

    private String nom;
    private String prenom;
    private String mail;
    private String telephone;
    private Date dateNaissance;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) throws ParseException {
        Pattern pat = Pattern.compile("^[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]{2,}\\.[a-zA-Z.]{2,10}$");
        Matcher matcher = pat.matcher(mail);
        if (matcher.matches()) {
            this.mail = mail;
        } else {
            ParseException e = new ParseException("Le format du mail est incorrect.", 0);
            throw e;
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws ParseException {
        Pattern pat = Pattern.compile("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$");
        Matcher matcher = pat.matcher(telephone);
        if (matcher.matches()) {
            this.telephone = telephone;
        } else {
            ParseException e = new ParseException("Le format du numéro est incorrect.", 0);
            throw e;
        }
    }

    public String getDateNaissance() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(dateNaissance);
    }

    public void setDateNaissance(String dateNaissance) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.dateNaissance = format.parse(dateNaissance);
    }

    public void enregistrer() throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)));
        try {
            pw.println(this.toString());
        } finally {
            pw.close();
        }
    }

    public static ArrayList<Contact> lister() throws FileNotFoundException, IOException, ParseException{
        ArrayList<Contact> list= new ArrayList<>();
        try(BufferedReader buf = new BufferedReader(new FileReader("contacts.csv"))){
            String ligne = buf.readLine();
            while (ligne!= null){
                String[] tab = ligne.split(SEPARATEUR);
                Contact c =  new Contact();
                c.setNom(tab[0]);
                c.setPrenom(tab[1]);
                c.setMail(tab[2]);
                c.setTelephone(tab[3]);
                c.setDateNaissance(tab[4]);
                list.add(c);
                ligne = buf.readLine();
            }
        }
        return list;
    }

    public static ArrayList<Contact> RechercherPrenom(String prenom) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("contacts.csv"));
        ArrayList<Contact> list = new ArrayList<>();        
        try{
            String ligne = br.readLine();
            while(ligne != null){
                String[] table = ligne.split(";");
                Contact contact = new Contact();

                contact.setNom(table[0]);
                contact.setPrenom(table[1]);
                contact.setMail(table[2]);
                contact.setTelephone(table[3]);
                contact.setDateNaissance(table[4]);
                if (table[1].equals(prenom)){
                    System.out.println(contact.getNom() + " " + contact.getPrenom() + " " + contact.getMail() + " " + contact.getTelephone() + " " + contact.getDateNaissance());
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
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append(this.getNom());
        build.append(SEPARATEUR);
        build.append(this.getPrenom());
        build.append(SEPARATEUR);
        build.append(this.getMail());
        build.append(SEPARATEUR);
        build.append(this.getTelephone());
        build.append(SEPARATEUR);
        build.append(this.getDateNaissance());
        return build.toString();
    }

}