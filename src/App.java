import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Contact;

public class App {
    private static Scanner _scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        while(true){
            afficherMenu();
            String choix = _scan.nextLine();
            switch (choix) {
                case "1": 
                    ajouterContact();
                    break;
                case "2": 
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Boulet !!!");
                    break;
            }
        }
    }
    private static void ajouterContact(){
        Contact c = new Contact();
        System.out.println("Saisir le nom");
        c.setPrenom(_scan.nextLine());

        System.out.println("Saisir le prenom");
        c.setPrenom(_scan.nextLine());

        System.out.println("Saisir l'email'");
        c.setMail(_scan.nextLine());

        System.out.println("Saisir le numéro de téléphone");
        c.setTelephone(_scan.nextLine());
        
        while(true){
            try{
                System.out.println("Saisir la date de naissance");
                c.setDateNaissance(_scan.nextLine());
                break;
            } catch (ParseException e){
                System.out.println("Mauvaise date");
            }
        }
        

    }
    public static void afficherMenu(){
        ArrayList<String> menus =new ArrayList<>();
        menus.add("-- MENU --");
        menus.add("1- Ajouter un contact");
        menus.add("2- Lister les contacts");
        menus.add("q- Quitter");
        for (String menu :menus){
            System.out.println(menu);
        }
    }
}
