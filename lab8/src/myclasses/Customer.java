package myclasses;

import java.util.Objects;

public class Customer {
    private static int counter = 0;
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String adres;
    private long numberCard;
    private double balance;

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    private int birthYear;

    public Customer(String lastName, String firstName, String patronymic, int birthYear, String adres, long numberCard, double balance) {
        this(++counter, lastName, firstName, patronymic, birthYear, adres, numberCard, balance);
    }

    public Customer(int id, String lastName, String firstName, String patronymic, int birthYear, String adres, long numberCard, double balance)
    {
        this.id = counter = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthYear = birthYear;
        this.adres = adres;
        this.numberCard = numberCard;
        this.balance = balance;
    }

    public String fileString(){
        return id + "\n" +  lastName + "\n" + firstName + "\n" + patronymic + "\n" + birthYear + "\n" + adres + "\n" + numberCard + "\n" + balance + "\n";
    }

    public int getId() { return id; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getPatronymic() { return patronymic; }

    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getAdres() { return adres; }

    public void setAdres(String adres) { this.adres = adres; }

    public long getNumberCard() { return numberCard; }

    public void setNumberCard(int numberCard) { this.numberCard = numberCard; }

    public double getBalance() { return balance; }

    public void setBalance(int balance) { this.balance = balance; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                numberCard == customer.numberCard &&
                Double.compare(customer.balance, balance) == 0 &&
                birthYear == customer.birthYear &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(patronymic, customer.patronymic) &&
                Objects.equals(adres, customer.adres);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", adres='" + adres + '\'' +
                ", numberCard=" + numberCard +
                ", balance=" + balance +
                ", birthYear=" + birthYear +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, patronymic, adres, numberCard, balance, birthYear);
    }
}
