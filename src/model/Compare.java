package model;

import java.util.Comparator;
/** 
 * classe Compare qui permet de comparer les données d'un contact 
 */
public class Compare implements Comparator<Contact> {
    /**
     * méthode qui  permet ici de comparer l'email d'un contact avec un deuxième contact
     * @param c1
     * @param c2
     * @return
     */
    public int compare(Contact c1, Contact c2){
        return c1.getBirthday().compareTo(c2.getBirthday());
    }
    /**
     * méthode qui  permet ici de comparer l'email d'un contact avec un deuxième contact
     * @param c1
     * @param c2
     * @return
     */
    public int compare2(Contact c1, Contact c2){
        return c1.getEmail().compareTo(c2.getEmail());
    }
}

