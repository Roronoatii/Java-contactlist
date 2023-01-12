package model;

public class Email implements Comparable<Email> {
    private String sender;
    private String subject;
    private String date;

    // Getters and setters

    @Override
    public int compareTo(Email o) {
        return this.date.compareTo(o.date);
    }
}
