import model.toys.Toy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    private String prompt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String input = scanner.nextLine();
        return input.strip();
    }

    private void showList(List<Toy> list) {
        for (Toy item : list) {
            System.out.println(item);
        }
    }

    public void run() {
        while (true) {
            try {
                String command = prompt("Choose an action:\n" +
                        "1 - Show available toys\n" +
                        "2 - Show pending prizes\n" +
                        "3 - Show awarded prizes\n" +
                        "4 - Add toy to toy list\n" +
                        "5 - Play a prize\n" +
                        "6 - Give out a prize\n" +
                        "7 - Change toys drop chance\n" +
                        "8 - Exit\n");
                switch (command) {
                    case "1":
                        showList(controller.getToys());
                        break;
                    case "2":
                        showList(controller.getPrizes());
                        break;
                    case "3":
                        ArrayList<String> lines = controller.readAwardedPrizes();
                        for (String line : lines) {
                            System.out.println(line);
                        }
                        break;
                    case "4":
                        command = prompt("Choose an action:\n" +
                                "1 - Add soft toy\n" +
                                "2 - Add car\n" +
                                "3 - Add doll\n");
                        switch (command) {
                            case "1":
                                String toyName = prompt("Enter toy name\n");
                                int toyCount = Integer.parseInt(prompt("Enter number of toys\n"));
                                int toyWeight = Integer.parseInt(prompt("Enter drop chance\n"));
                                controller.addSoftToy(toyName, toyCount, toyWeight);
                                System.out.println("New toy was added successfully");
                                break;
                            case "2":
                                toyName = prompt("Enter toy name\n");
                                toyCount = Integer.parseInt(prompt("Enter number of toys\n"));
                                toyWeight = Integer.parseInt(prompt("Enter drop chance\n"));
                                controller.addCar(toyName, toyCount, toyWeight);
                                System.out.println("New toy was added successfully");
                                break;
                            case "3":
                                toyName = prompt("Enter toy name\n");
                                toyCount = Integer.parseInt(prompt("Enter number of toys\n"));
                                toyWeight = Integer.parseInt(prompt("Enter drop chance\n"));
                                controller.addDoll(toyName, toyCount, toyWeight);
                                System.out.println("New toy was added successfully");
                                break;
                            default:
                                System.out.println("Wrong command. Try again!\n");
                                break;
                        }
                        break;
                    case "5":
                        controller.getRandomPrize();
                        System.out.println("Prize was played successfully");
                        break;
                    case "6":
                        controller.releasePrize();
                        System.out.println("Prize was released successfully");
                        break;
                    case "7":
                        ArrayList<Toy> availableToys = controller.getToys();
                        showList(availableToys);
                        Integer toyId = Integer.parseInt(prompt("Enter toy id\n"));
                        Integer newWeight = Integer.parseInt(prompt("Enter new toy drop chance\n"));
                        controller.changeToyWeight(toyId, newWeight);
                        break;
                    case "8":
                        return;
                    default:
                        System.out.println("Wrong command. Try again!\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
