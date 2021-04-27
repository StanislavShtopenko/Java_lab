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

public class CustomerLogic {

    public static List<Customer> taskA(List<Customer> customerList, String parametr){
        List<Customer> sortedByName = new ArrayList<>();
        for (Customer customer : customerList) {
            if (parametr.equals(customer.getFirstName()))
                sortedByName.add(customer);
        }
        return sortedByName;
    }

    public static List<Customer> taskB(List<Customer> customerList, String parametr){
        List<Customer> sorted = new ArrayList<>();
        String[] range = parametr.split("-");
        long min = Long.parseLong(range[0]);
        long max = Long.parseLong(range[1]);
        for (Customer customer : customerList) {
            long number = customer.getNumberCard();
            if (number >= min && number <= max)
                sorted.add(customer);
        }
        return sorted;
    }

    public static Queue<Customer> taskC(List<Customer> customerList) {
        Queue<Customer> sorted = new PriorityQueue<>((o1, o2) -> {
            if (o1.getBalance() < o2.getBalance()) return 1;
            if (o1.getBalance() > o2.getBalance()) return -1;
            return 0;
        });
        for (Customer customer : customerList) {
            if (customer.getBalance() < 0)
                sorted.add(customer);
        }
        return sorted;
    }

    public static Queue<Customer> taskD(List<Customer> customerList) {
        Queue<Customer> sorted = new PriorityQueue<>((o1, o2) -> {
            if (o1.getBalance() < o2.getBalance()) return -1;
            if (o1.getBalance() > o2.getBalance()) return 1;
            if (o1.getBalance() == o2.getBalance()) {
                if (o1.getNumberCard() < o2.getNumberCard()) return -1;
                if (o1.getNumberCard() > o2.getNumberCard()) return 1;
            }
            return 0;
        });
        for (Customer customer : customerList) {
            sorted.add(customer);
        }
        return sorted;
    }

    public static Set<Integer> taskE(List<Customer> customerList) {
        Set<Integer> sortedYears = new TreeSet<>();
        for (Customer customer : customerList) {
            sortedYears.add(customer.getBirthYear());
        }
        return sortedYears;
    }

    public static Map<Integer, Customer> taskF(List<Customer> customerList) {
        Map<Integer, Customer> sortedMap = new HashMap<>();
        for (Customer customer : customerList) {
            if(sortedMap.containsKey(customer.getBirthYear())){
                if (sortedMap.get(customer.getBirthYear()).getBalance() < customer.getBalance()){
                    sortedMap.replace(customer.getBirthYear(), customer);
                }
            }
            else{
                sortedMap.put(customer.getBirthYear(), customer);
            }
        }
        return sortedMap;
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
