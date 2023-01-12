import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Predicate;

import model.Compare;
import model.Contact;

public class App {

    private static Scanner _scan =  new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        while(true){
        afficherMenu();
        String choix = _scan.nextLine();
            switch(choix){
                case "1":
                    addcontact();
                    break;
                case "2":
                    contactslisting();
                    break;
                case "3":
                    System.out.print("Nom ? : ");
                    Contact.chercherNom(_scan.nextLine());
                    break;
                case "4":
                    System.out.print("DDN ? : ");
                    Contact.chercherDDN(_scan.nextLine());
                    break;
                case "5":
                    break;
                case "6":
                    System.out.print("Email ? : ");
                    contactsuppr(_scan.nextLine());

                    break;
                case "7":
                    triDDN();
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Entrez un caractère valide");
                    break;
            }
        }
    }

    

    private static void afficherMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("--------------- MENU ---------------");
        menus.add("/ 1- Ajouter un contact            /");
        menus.add("/ 2- Lister les contacts           /");
        menus.add("/ 3- Chercher contact avec un nom  /");
        menus.add("/ 4- Chercher contact avec DDN     /");
        menus.add("/ 5- Modifier contact              /");
        menus.add("/ 6- Supprimer contact             /");
        menus.add("/ 7- Tri DDN                       /");
        menus.add("/ q- quitter                       /");
        menus.add("------------------------------------");
        for(String menu : menus){
            System.out.println(menu);
        }


    }

    private static void addcontact() throws IOException{
        Contact c =  new Contact();
        System.out.print("Enter first name: ");
        Scanner input = new Scanner(System.in);
        c.setFirstname(input.nextLine());
        System.out.print("Enter last name: ");
        c.setLastname(input.nextLine());

        while(true){
            try{
                System.out.print("Enter email: ");
                c.setEmail(input.nextLine());
                break;
            }catch(ParseException e){
                System.out.println("Incorrect email");
            }
        }

        while(true){
            try{
                System.out.print("Enter number: ");
                c.setNumber(input.nextLine());
                break;
            }catch(ParseException e){
                System.out.println("Incorrect number");
            }
        }
        
        while(true){

            try{
                System.out.print("Enter birthdate: ");
                c.setBirthday(input.nextLine());
                break;
            }catch(ParseException e){
                System.out.println("Incorrect birthdate");
            }
        }
    
        c.enregistrer();
        System.out.println("Contact added");
        
    }
    private static void contactslisting() throws IOException{
        ArrayList<Contact> list = Contact.lister();
        String str = list.toString().replaceAll(",","\n").replaceAll(";"," ");
        System.out.println(str);
    }
    private static void contactsuppr(String contacttosupr) throws IOException{
        ArrayList<Contact> list = Contact.lister();
        Predicate<Contact> condition = contact -> contact.getEmail().startsWith(contacttosupr);

        list.removeIf(condition);
        Contact.refreshlist(list);
        System.out.println(list);
    }
    public static void triDDN()throws IOException{
        try{
            ArrayList<Contact> list = Contact.lister();
            Compare compare = new Compare();
            System.out.println(compare.toString());
            Collections.sort(list, compare);
            String str = list.toString().replaceAll(",","\n").replaceAll(";"," ");
            System.out.println(str);
        }catch(IOException e){
            System.out.println("Error");
        }
    }
    public static void triname() throws IOException{
        
    }

    

    
}
