import java.util.ArrayList;
import java.util.Scanner;

import model.contact;



public class App {

    private static Scanner _scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        afficherMenu();
        String choix = _scan.nextLine();
        while(true){
        switch(choix){
            case "1":
                break;
            case "2":
                break;
            case "q":
                return;
            default:
                System.out.println("boulet !!!!");
                break;
        }

        }
        
    }
        private static void ajouterContact(){
            contact c = new contact();

            System.out.println("saisir le nom");
            c.setNom(_scan.nextLine());

            System.out.println("saisir le prenom");
            c.setPrenom(_scan.nextLine());

            System.out.println("saisir le mail");
            c.setMail(_scan.nextLine());

            System.out.println("saisir le telephone");
            c.setTelephone(_scan.nextLine());
           
            System.out.println("saisir le date De Naissance");
           // c.setDateNaissance(_scan.nextLine());
            
            

    }
    
    private static void afficherMenu(){
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-- MENU --");
        menus.add("1- Ajouter un contact");
        menus.add("2- Lister les contacts");
        menus.add("q- Quitter");
        for(String menu : menus ){
            System.out.println(menu);
        }
    }

}
