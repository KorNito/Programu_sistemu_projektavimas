package typeracer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TypeRacer {

    public static ArrayList<String> zodziai = new ArrayList();

    public static void main(String[] args) throws IOException {

        Scanner sc = null;
        Scanner scanner = null;
        Scanner ivestis = null;

        String temp = "";

        PrintWriter writer = new PrintWriter("rezultatas.txt", "UTF-8");

        while (true) {
            System.out.println("Pasirinkite: \n"
                    + "Pradėti žaidimą, 1\n"
                    + "Rezultatai, 2\n"
                    + "Išeiti, 3\n"
            );

            scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            System.out.println(input);

            switch (input) {
                case "1":
                    System.out.println("Iveskite savo vardą: ");
                    scanner = new Scanner(System.in);
                    String name = scanner.nextLine();

                    boolean start = true;
                    
                    while (start) {
                        try {
                            sc = new Scanner(new File("words.txt"));

                            while (sc.hasNext()) {
                                temp = sc.next();
                                zodziai.add(temp);
                                //System.out.print(temp + " ");

                            }
                            sc.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        long startTime = System.currentTimeMillis();

                        for (String zodis : zodziai) {
                            while (true) {
                                System.out.println(zodis);
                                ivestis = new Scanner(System.in);
                                String nuskaitytasZodis = ivestis.nextLine();
                                if (nuskaitytasZodis.equals(zodis)) {

                                    System.out.println("Žodis sutampa");
                                    System.out.println();
                                    break;

                                } else {

                                    System.out.println("Žodis nesutampa");
                                    System.out.println();

                                }
                            }
                        }

                        int simboliuKiekis = skaiciuotiSimbolius();
                        long endTime = System.currentTimeMillis();
                        double rezultatas = ((endTime - startTime) * 0.001) / simboliuKiekis;
                        writer.println(name + " " + rezultatas);

                        FileWriter rasyti = null;
                        try {
                            rasyti = new FileWriter("rezultatas.txt");
                            rasyti.append(name + " " + rezultatas);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (rasyti != null) {
                                try {
                                    rasyti.close();
                                } catch (Exception t) {
                                    t.printStackTrace();
                                }
                            }
                        }
                        System.out.format("Jūsų rezultatas: %.2f žodžiai per sekundę", rezultatas);
                        System.out.println("");
                        System.out.println("Ar norite žaisti dar kartą? Y/N");

                        String inputas = scanner.nextLine();
                        if (inputas.equals("Y")) {

                            start = false;
                        } else {
                            System.exit(1);
                        }

                    }
                case "2":

                    scanner = new Scanner(new File("rezultatas.txt"));

                    while (scanner.hasNextLine()) {
                        temp = scanner.nextLine();
                        System.out.println(temp);

                    }

                    break;
                case "3":
                    System.exit(1);

            }
        }

    }

    public static int skaiciuotiSimbolius() {
        int simboliuKiekis = 0;
        for (String zodis : zodziai) {
            simboliuKiekis += zodis.length();
        }
        return simboliuKiekis;
    }

}
