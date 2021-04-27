package sample;

import javafx.collections.ObservableList;
import myclasses.Customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerLogic {

    public static List<Customer> taskA(List<Customer> customerList, String parametr){
        return customerList.stream()
                .filter(customer -> parametr.equals(customer.getFirstName()))
                .collect(Collectors.toList());
    }
    // By credit number rage
    public static List<Customer> taskB(List<Customer> customerList, String parametr){
        String[] rage = parametr.split("-");
        long min = Long.parseLong(rage[0]);
        long max = Long.parseLong(rage[1]);
        return customerList.stream()
                .filter(c -> c.getNumberCard() >= min && c.getNumberCard() <= max)
                .collect(Collectors.toList());
    }
    // Owes by increasing of balance
    public static List<Customer> taskC(List<Customer> customerList) {
        return customerList.stream()
                .filter(c -> c.getBalance() < 0)
                .sorted(Comparator.comparing(Customer::getBalance).reversed())
                .collect(Collectors.toList());
    }
    // by increasing of balance, when they have the same by card number
    public static List<Customer> taskD(List<Customer> customerList) {
        return customerList.stream()
                .sorted(Comparator.comparing(Customer::getBalance).thenComparing(Customer::getNumberCard))
                .collect(Collectors.toList());
    }
    // Birth years
    public static Set<Integer> taskE(List<Customer> customerList) {
        return customerList.stream()
                .sorted(Comparator.comparing(Customer::getBirthYear))
                .map(Customer::getBirthYear)
                .collect(Collectors.toSet());
    }
    // The most rich in year of birth
    public static Map<Integer, Customer> taskF(List<Customer> customerList) {
        return customerList.stream()
                .sorted(Comparator.comparing(Customer::getBalance))
                .collect(Collectors.toMap(Customer::getBirthYear, x -> x, (a, b) -> {
                    return a.getBalance() > b.getBalance() ? a : b;
                }));
    }

    public static void loadCustomers(String path, List<Customer> customers) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(Paths.get(path)))){
            int length = Integer.parseInt(bufferedReader.readLine());
            final int n = 8;
            String[] str = new String[n];
            for (int j = 0; j < length; j++) {
                for (int i = 0; i < n; i++) {
                    str[i] = bufferedReader.readLine();
                }
                customers.add(
                        new Customer(Integer.parseInt(str[0]), str[1], str[2], str[3], Integer.parseInt(str[4]), str[5], Long.parseLong(str[6]), Double.parseDouble(str[7]))
                );
            }
    }

}

    public static void saveCustomers(String path, List<Customer> customers) throws IOException{
        try (PrintWriter printWriter = new PrintWriter(new File(path))){
            printWriter.println(customers.size());
            for (Customer tmp: customers) {
                printWriter.print(tmp.fileString());
            }
        }
    }
}
