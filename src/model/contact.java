package model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class contact {
    private String nom;
    private String prenom;
    private String Mail;
    private String telephone;
    private Date dateNaissance;

    public String getNom(){
        return nom;
    }

    public void setNom(String valeur){
        this.nom = valeur; 
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    

    public void setDateDeNaissance(Date dateNaissance) {
       // this.dateNaissance = format.parse(dateNaissance);
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }



}
