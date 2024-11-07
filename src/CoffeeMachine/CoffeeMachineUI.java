package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachineUI {
    private final CoffeeMachine coffeeMachine;
    private final Scanner scanner;


    public CoffeeMachineUI() {
        coffeeMachine = new CoffeeMachine();
        scanner = new Scanner(System.in);
    }

    public void runCoffeeMachine() {
        // Odabira akcije koju želimo
        String action;
        label:
        while (true) {
            System.out.print("Write action\nbuy, login, exit\nEnter:");
            action = scanner.nextLine();
            switch (action) {
                case "login":
                    login();
                    break;
                case "buy":
                    buyCoffee();
                    break;
                case "exit":
                    coffeeMachine.saveStatus(); // Snimi stanje pri izlasku
                    break label;
                default:
                    System.out.println("Unknown action. Please try again.");
                    break;
            }
        }
    }

    private void login() {
        // Sučelje za prijavu
        System.out.print("Enter username:\n");
        String username = scanner.nextLine();
        System.out.print("Enter password:\n");
        String password = scanner.nextLine();

        if (username.equals("vedran") && password.equals("vedran123")) {
            adminActions();
        } else {
            System.out.println("Wrong username or password");
        }
    }

    private void adminActions() {
        // Definiranje vrijednosti koje admin može postaviti
        String action;
        do {
            System.out.print("Write action:\n *** fill, remaining, take, exit ***\nEnter:");
            action = scanner.nextLine();
            switch (action) {
                case "remaining":
                    coffeeMachine.printRemainingCoffeeMachine();
                    break;
                case "take":
                    coffeeMachine.takeMoney();
                    break;
                case "fill":
                    fillMachine();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Unknown action. Please try again.");
            }
        } while (!action.equals("exit"));
    }

    private void fillMachine() {
        // Nadopuna aparata sa sastojcima
        System.out.print("Write how many ml of water you want to add:\n");
        int water = Integer.parseInt(scanner.nextLine());

        System.out.print("Write how many ml of milk you want to add:\n");
        int milk = Integer.parseInt(scanner.nextLine());

        System.out.print("Write how many grams of coffee beans you want to add:\n");
        int coffeeBeans = Integer.parseInt(scanner.nextLine());

        System.out.print("Write how many disposable cups you want to add:\n");
        int cups = Integer.parseInt(scanner.nextLine());

        coffeeMachine.fillMachine(water, milk, coffeeBeans, cups);
    }
    private void buyCoffee() {
        // Odabir kave koju želimo kupiti
        System.out.print("What do you want to buy?\n1 - espresso\n2 - latte\n3 - cappuccino\nEnter:");
        String choiceCoffee = scanner.nextLine();

        if (choiceCoffee.equals("1") || choiceCoffee.equals("2") || choiceCoffee.equals("3")) {
            int coffeeChoice = Integer.parseInt(choiceCoffee);
            coffeeMachine.buyCoffee(coffeeChoice);
        } else {
            System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        }
    }

    public static void main(String[] args) {
        CoffeeMachineUI ui = new CoffeeMachineUI();
        ui.runCoffeeMachine();
    }
}

