package CoffeeMachine;

import java.io.*;

public class CoffeeMachine {
    //Postavljanje pocetnih vrijednosti zaliha aparata
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;

    private String adminUsername = "vedran";
    private String adminPassword = "vedran123";
    public CoffeeMachine() {
        loadStatus();
    }
    public void buyCoffee(int choice) {
        // Odabir kave koju želimo kupiti
        switch (choice) {
            case 1: // Espresso
                if (water < 250) {
                    System.out.println("Sorry, not enough water");
                    return;
                }
                if (coffeeBeans < 16) {
                    System.out.println("Sorry, not enough coffee beans");
                    return;
                }
                water -= 250;
                coffeeBeans -= 16;
                money += 4;
                cups--;
                System.out.println("I have enough resources, making you Espresso");
                return;

            case 2: // Latte
                if (water < 350 || milk < 75) {
                    System.out.println("Sorry, not enough resources");
                    return;
                }
                water -= 350;
                milk -= 75;
                coffeeBeans -= 20;
                money += 7;
                cups--;
                System.out.println("I have enough resources, making you Latte");
                return;

            case 3: // Cappuccino
                if (water < 200 || milk < 100) {
                    System.out.println("Sorry, not enough resources");
                    return;
                }
                water -= 200;
                milk -= 100;
                coffeeBeans -= 12;
                money += 6;
                cups--;
                System.out.println("I have enough resources, making you Cappuccino");
                return;

            default:
                System.out.println("Invalid choice");
        }
    }
    // Snimi status u datoteku
    public void saveStatus() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Docs/coffee_machine_status.txt"))) {
            writer.println(water + ";" + milk + ";" + coffeeBeans + ";" + cups + ";" + money);
            writer.println(adminUsername + ";" + adminPassword);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the status.");
        }
    }

    // Učitaj status iz datoteke
    public void loadStatus() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Docs/coffee_machine_status.txt"))) {
            String[] status = reader.readLine().split(";");
            water = Integer.parseInt(status[0]);
            milk = Integer.parseInt(status[1]);
            coffeeBeans = Integer.parseInt(status[2]);
            cups = Integer.parseInt(status[3]);
            money = Integer.parseInt(status[4]);

            String[] adminData = reader.readLine().split(";");
            adminUsername = adminData[0];
            adminPassword = adminData[1];
        } catch (FileNotFoundException e) {
            System.out.println("Status file not found, starting with default values.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading the status.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing the status file.");
        }
    }

    public void fillMachine(int waterAmount, int milkAmount, int coffeeBeansAmount, int cupsAmount) {
        // Nadopuna aparata za kavu sa vodom, mlijekom, zrnom kave i šalicama
        water += waterAmount;
        milk += milkAmount;
        coffeeBeans += coffeeBeansAmount;
        cups += cupsAmount;
    }

    public void takeMoney()
    // Uzimanje novaca iz aparata
    {
        System.out.println("I gave you $" + money);
        money = 0;
    }
    public void printRemainingCoffeeMachine() {
        // Ispis podataka o stanju aparata
        System.out.println("CoffeeMachine{ water=" + water + ", milk=" + milk +
                ", coffeeBeans=" + coffeeBeans + ", cups=" + cups + ", money=" + money + "}");

    }

}
