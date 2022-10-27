package org.example.task3;


public class Main {
    static final String FILE_PATH = "pr2\\src\\anvarzhonov\\ikbo2019\\task3\\total.txt";

    public static void main(String[] args) {
        CalculateCheckSumService calculateCheckSumService = new CalculateCheckSumService();

        calculateCheckSumService.execute(FILE_PATH);
    }
}
