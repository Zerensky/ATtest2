package model.store;

import model.toys.Car;
import model.toys.Doll;
import model.toys.SoftToy;
import model.toys.Toy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ToyStore {
    private ArrayList<Toy> prizes;
    private ArrayList<Toy> toys;
    private PrizeWriter prizeWriter;
    private PrizeReader prizeReader;

    public ToyStore(PrizeWriter prizeWriter, PrizeReader prizeReader) {
        this.prizes = new ArrayList<>();
        this.toys = new ArrayList<>();
        this.prizeWriter = prizeWriter;
        this.prizeReader = prizeReader;
    }

    private Integer createId() {
        int max = 0;
        for (Toy toy : toys) {
            if (toy.getId() > max) {
                max = toy.getId();
            }
        }
        return max + 1;
    }

    public void addSoftToy(String name, Integer count, Integer weight) throws Exception {
        SoftToy newSoftToy = new SoftToy(createId(), name, count, weight);
        if (checkToyCount(newSoftToy)) {
            throw new Exception("Wrong count value\n");
        } else if (checkToyWeight(newSoftToy)) {
            throw new Exception("Wrong weight value\n");
        } else {
            this.toys.add(newSoftToy);
        }
    }

    public void addCar(String name, Integer count, Integer weight) throws Exception {
        Car newCar = new Car(createId(), name, count, weight);
        if (checkToyCount(newCar)) {
            throw new Exception("Wrong count value\n");
        } else if (checkToyWeight(newCar)) {
            throw new Exception("Wrong weight value\n");
        } else {
            this.toys.add(newCar);
        }
    }

    public void addDoll(String name, Integer count, Integer weight) throws Exception {
        Doll newDoll = new Doll(createId(), name, count, weight);
        if (checkToyCount(newDoll)) {
            throw new Exception("Wrong count value\n");
        } else if (checkToyWeight(newDoll)) {
            throw new Exception("Wrong weight value\n");
        } else {
            this.toys.add(newDoll);
        }
    }

    private void decreaseCount(Toy toy) {
        toy.setCount(toy.getCount() - 1);
    }

    private void addToyToPrizesList(Toy toy) throws CloneNotSupportedException {
        Toy prize = toy.clone();
        prize.setCount(1);
        prizes.add(prize);
    }

    private boolean checkToyCount(Toy toy) {
        return toy.getCount() <= 0;
    }

    private boolean checkToyWeight(Toy toy) {
        return toy.getWeight() < 1 || toy.getWeight() > 100;
    }

    public ArrayList<Toy> getPrizes() throws Exception {
        if (prizes.isEmpty()) {
            throw new Exception("There's no toys\n");
        } else {
            return prizes;
        }
    }

    public ArrayList<Toy> getToys() throws Exception {
        if (toys.isEmpty()) {
            throw new Exception("There's no toys\n");
        } else {
            return toys;
        }
    }

    public void getRandomPrize() throws Exception {
        if (toys.isEmpty()) {
            throw new Exception("There's no available toys\n");
        } else {
            boolean isPrize = false;
            Collections.sort(toys);
            int randInt = (new Random()).nextInt(100);
            for (Toy toy : toys) {
                if (randInt <= toy.getWeight()) {
                    try {
                        isPrize = true;
                        addToyToPrizesList(toy);
                        decreaseCount(toy);
                        if (checkToyCount(toy)) toys.remove(toy);
                        return;
                    } catch (CloneNotSupportedException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
            }
            if (!isPrize) throw new Exception("Prize wasn't played\n");
        }
    }

    public void releasePrize() throws Exception {
        if (!prizes.isEmpty()) {
            prizeWriter.writePrize(prizes.get(0));
            prizes.remove(0);
        } else throw new Exception("There's no prizes to release\n");
    }

    public ArrayList<String> readAwardedPrizes() {
        return prizeReader.readAwardedPrizes();
    }

    public void changeToyWeight(Integer id, Integer newWeight) throws Exception {
        if (newWeight < 1 || newWeight > 100) {
            throw new Exception("Wrong weight value\n");
        } else {
            for (Toy toy : toys) {
                if (toy.getId().equals(id)) {
                    toy.setWeight(newWeight);
                    break;
                }
            }
        }
    }
}
