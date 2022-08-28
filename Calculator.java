import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Обработка исключений: нет файла, не число, не верный знак вычислений, деление на 0
public class Calculator {
    public static void main(String[] args) {
        String path = "calc_input";
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                try {
                    String[] symbols = sc.nextLine().split(" ");
                    double a = Double.parseDouble(symbols[0]);
                    double b = Double.parseDouble(symbols[2]);
                    try {
                        String k = symbols[1];
                        if (!k.equals("/") && !k.equals("+") && !k.equals("*") && !k.equals("-")) {
                            try {
                                throw new IOException();
                            } catch (IOException e) {
                                System.out.println("Operation Error!");
                            }
                        } else {
                            if (k.equals("/")) {
                                if (b == 0) {
                                    try {
                                        throw new IOException();
                                    } catch (IOException e) {
                                        System.out.println("Error! Division by zero");
                                    }
                                } else {
                                    System.out.println(a / b);
                                }
                            } else if (k.equals("+")) {
                                System.out.println(a + b);
                            } else if (k.equals("-")) {
                                System.out.println(a - b);
                            } else if (k.equals("*")) {
                                System.out.println(a * b);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Operation Error!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error! Not number");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
