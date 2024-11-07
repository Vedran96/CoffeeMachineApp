package CoffeeMachine;

import java.io.*;

public class CoffeeMachine {

    private CoffeeMake state;


    public CoffeeMachine() {
        loadStatus();
    }
    public void buyCoffee(int choice) {
        // Odabir kave koju želimo kupiti
        switch (choice) {
            case 1: // Espresso
                if (state.getWater() < 250) {
                    System.out.println("Sorry, not enough water");
                    return;
                }
                if (state.getCoffeeBeans() < 16) {
                    System.out.println("Sorry, not enough coffee beans");
                    return;
                }
                state.setWater(state.getWater() - 250);
                state.setCoffeeBeans(state.getCoffeeBeans() - 16);
                state.setMoney(state.getMoney() + 4);
                state.setCups(state.getCups() - 1);
                System.out.println("I have enough resources, making you Espresso");
                return;

            case 2: // Latte
                if (state.getWater() < 350 || state.getMilk() < 75) {
                    System.out.println("Sorry, not enough resources");
                    return;
                }
                state.setWater(state.getWater() - 350);
                state.setMilk(state.getMilk() - 75);
                state.setCoffeeBeans(state.getCoffeeBeans() - 20);
                state.setMoney(state.getMoney() + 7);
                state.setCups(state.getCups() - 1);
                System.out.println("I have enough resources, making you Latte");
                return;

            case 3: // Cappuccino
                if (state.getWater() < 200 || state.getMilk() < 100) {
                    System.out.println("Sorry, not enough resources");
                    return;
                }
                state.setWater(state.getWater() - 200);
                state.setMilk(state.getMilk() - 100);
                state.setCoffeeBeans(state.getCoffeeBeans() - 12);
                state.setMoney(state.getMoney() + 6);
                state.setCups(state.getCups() - 1);
                System.out.println("I have enough resources, making you Cappuccino");
                return;

            default:
                System.out.println("Invalid choice");
        }
    }

    // Snimi status u datoteku
    public void saveStatus() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Docs/coffee_machine_status.txt"))) {
            writer.println(state.getWater() + ";" + state.getMilk() + ";" + state.getCoffeeBeans() + ";" + state.getCups() + ";" + state.getMoney());
            writer.println(state.getAdminUsername() + ";" + state.getAdminPassword());
        } catch (IOException e) {
            System.out.println("An error occurred while saving the status.");
        }
    }

    // Učitaj status iz datoteke
    public void loadStatus() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Docs/coffee_machine_status.txt"))) {
            String[] status = reader.readLine().split(";");
            int water = Integer.parseInt(status[0]);
            int milk = Integer.parseInt(status[1]);
            int coffeeBeans = Integer.parseInt(status[2]);
            int cups = Integer.parseInt(status[3]);
            int money = Integer.parseInt(status[4]);

            String[] adminData = reader.readLine().split(";");
            String adminUsername = adminData[0];
            String adminPassword = adminData[1];

            this.state = new CoffeeMake(water, milk, coffeeBeans, cups, money, adminUsername, adminPassword);
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
        state.setWater(state.getWater() + waterAmount);
        state.setMilk(state.getMilk() + milkAmount);
        state.setCoffeeBeans(state.getCoffeeBeans() + coffeeBeansAmount);
        state.setCups(state.getCups() + cupsAmount);
    }

    public void takeMoney() {
        // Uzimanje novaca iz aparata
        System.out.println("I gave you $" + state.getMoney());
        state.setMoney(0);
    }

    public void printRemainingCoffeeMachine() {
        // Ispis podataka o stanju aparata
        System.out.println("CoffeeMachine{ water=" + state.getWater() + ", milk=" + state.getMilk() +
                ", coffeeBeans=" + state.getCoffeeBeans() + ", cups=" + state.getCups() + ", money=" + state.getMoney() + "}");
    }

}
