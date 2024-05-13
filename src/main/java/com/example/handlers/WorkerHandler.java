package com.example.handlers;

import com.example.models.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerHandler {
    private List<Worker> workers;
    private XMLHandler xmlHandler;

    public WorkerHandler() {
        xmlHandler = new XMLHandler();
        workers = xmlHandler.readWorkersFromXML("workers.xml");
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
        xmlHandler.writeWorkersToXML(workers, "workers.xml");
    }

    public void deleteWorker(String personId) {
        Worker workerToDelete = findWorkerById(personId);
        if (workerToDelete != null) {
            workers.remove(workerToDelete);
            xmlHandler.writeWorkersToXML(workers, "workers.xml");
        } else {
            System.out.println("Worker with ID " + personId + " not found.");
        }
    }

    public List<Worker> findWorkersByAttributes(String attribute, String value) {
        List<Worker> foundWorkers = new ArrayList<>();

        switch (attribute.toLowerCase()) {
            case "personid":
                foundWorkers.addAll(workers.stream().filter(worker -> worker.getPersonId().equals(value)).collect(Collectors.toList()));
                break;
            case "firstname":
                foundWorkers.addAll(workers.stream().filter(worker -> worker.getFirstName().equalsIgnoreCase(value)).collect(Collectors.toList()));
                break;
            case "lastname":
                foundWorkers.addAll(workers.stream().filter(worker -> worker.getLastName().equalsIgnoreCase(value)).collect(Collectors.toList()));
                break;
            case "telephoneNumber":
                foundWorkers.addAll(workers.stream().filter(worker -> worker.getTelephone().equals(value)).collect(Collectors.toList()));
                break;
            case "email":
                foundWorkers.addAll(workers.stream().filter(worker -> worker.getEmail().equalsIgnoreCase(value)).collect(Collectors.toList()));
                break;
            case "pesel":
                foundWorkers.addAll(workers.stream().filter(worker -> worker.getPesel().equals(value)).collect(Collectors.toList()));
                break;
            case "isinternal":
                boolean isInternal = Boolean.parseBoolean(value);
                foundWorkers.addAll(workers.stream().filter(worker -> worker.getInternal() == isInternal).collect(Collectors.toList()));
                break;
            default:
                System.out.println("Invalid attribute: " + attribute);
        }

        return foundWorkers;
    }

    public void changeWorkerData(String personId, Worker newWorkerData) {
        Worker oldWorker = findWorkerById(personId);
        if (oldWorker != null) {
            int index = workers.indexOf(oldWorker);
            workers.set(index, newWorkerData);
            xmlHandler.writeWorkersToXML(workers, "workers.xml");
        } else {
            System.out.println("Worker with ID " + personId + " not found.");
        }
    }

    public Worker findWorkerById(String personId) {
        for (Worker worker : workers) {
            if (worker.getPersonId().equals(personId)) {
                return worker;
            }
        }
        return null;
    }
}