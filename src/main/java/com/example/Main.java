package com.example;

import com.example.handlers.WorkerHandler;
import com.example.models.Worker;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WorkerHandler workerHandler = new WorkerHandler();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Select an option:");
            System.out.println("1. Add a new worker");
            System.out.println("2. Delete a worker");
            System.out.println("3. Change worker data");
            System.out.println("4. Find worker");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewWorker(scanner, workerHandler);
                    break;
                case 2:
                    deleteWorker(scanner, workerHandler);
                    break;
                case 3:
                    changeWorkerData(scanner, workerHandler);
                    break;
                case 4:
                    findWorkers(scanner, workerHandler);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        scanner.close();
    }

    private static void addNewWorker(Scanner scanner, WorkerHandler workerManager) {
        System.out.println("Enter worker details:");
        System.out.print("Person ID: ");
        String personId = scanner.nextLine();
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("TelephoneNumber: ");
        String telephoneNumber = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("PESEL: ");
        String pesel = scanner.nextLine();
        System.out.print("Is internal? (true/false): ");
        boolean isInternal = scanner.nextBoolean();
        scanner.nextLine();

        Worker worker = new Worker(personId, firstName, lastName, telephoneNumber, email, pesel, isInternal);
        workerManager.addWorker(worker);
        System.out.println("Worker added successfully.");
    }

    private static void deleteWorker(Scanner scanner, WorkerHandler workerHandler) {
        System.out.print("Enter person ID of the worker to delete: ");
        String personId = scanner.nextLine();
        workerHandler.deleteWorker(personId);
        System.out.println("Worker deleted successfully.");
    }

    private static void changeWorkerData(Scanner scanner, WorkerHandler workerHandler) {
        System.out.print("Enter person ID of the worker to change data: ");
        String personId = scanner.nextLine();
        Worker oldWorker = workerHandler.findWorkerById(personId);
        if (oldWorker != null) {
            System.out.println("Enter new worker details:");
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Telephone Number: ");
            String telephoneNumber = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("PESEL: ");
            String pesel = scanner.nextLine();
            System.out.print("Is internal? (true/false): ");
            boolean isInternal = scanner.nextBoolean();
            scanner.nextLine();
            Worker newWorker = new Worker(personId, firstName, lastName, telephoneNumber, email, pesel, isInternal);
            workerHandler.changeWorkerData(personId, newWorker);
            System.out.println("Worker data changed successfully.");
        } else {
            System.out.println("Worker with ID " + personId + " not found.");
        }
    }
    private static void findWorkers(Scanner scanner, WorkerHandler workerHandler) {
        System.out.println("Enter attribute name (personId, firstName, lastName, telephoneNumber, email, pesel, isInternal):");
        String attribute = scanner.nextLine();
        System.out.println("Enter value to search for:");
        String value = scanner.nextLine();

        List<Worker> foundWorkers = workerHandler.findWorkersByAttributes(attribute, value);
        if (foundWorkers.isEmpty()) {
            System.out.println("No workers found with the specified criteria.");
        } else {
            System.out.println("Found workers:");
            for (Worker worker : foundWorkers) {
                System.out.println(worker);
            }
        }
    }
}
