package io.github.jeangiraldoo.sudoku;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Modelo {
    private final HashMap[] numbers;
    private int totalLabels;

    /**
     * Generates the numbers that form the Sudoku board. This representation of the board is
     * an array of hashmaps, each hashmap represents a 2x3 block.
     */
    public HashMap[] generateNumbers(){
        HashMap[] numbers = generateBaseStructure();
        for (int block = 0; block < 6; block++) {
            ArrayList<Integer> availableNumbers = new ArrayList<>();
            availableNumbers.add(1);
            availableNumbers.add(2);
            availableNumbers.add(3);
            availableNumbers.add(4);
            availableNumbers.add(5);
            availableNumbers.add(6);
            for (int element = 0; element < 6; element++) {
                int randomNumber;
                int randomPos;
                while(true) {
                    randomPos = chooseRandomNumber(availableNumbers.size());
                    randomNumber = availableNumbers.get(randomPos);
                    boolean valueColumn = checkColumn(numbers, randomNumber, block, element);
                    boolean valueRow = checkRow(numbers, element, block, randomNumber);
                    if (valueRow && valueColumn) {
                        break;
                    }
                }
                numbers[block].put(Integer.toString(element), randomNumber);
                availableNumbers.remove(randomPos);
            }
        }
        return numbers;
    }
    /**
     * Factory-like function that serves as a middleman for iterateColumn, passing the appropriate values depending on
     * the values checkColumn received.
     * @param numbers current state of the board
     * @param randomNumber random number that represents the number candidate
     * @param block current block
     * @param element current element
     */
    public boolean checkColumn(HashMap[] numbers, int randomNumber, int block, int element){
        if(block == 0 || block == 2 || block == 4){
            return iterateColumn(0, numbers, element, randomNumber);
        }else{
            return iterateColumn(1, numbers, element, randomNumber);
        }
    }
    /**
     * Jumps from one block to another that mirrors it. Effectively iterating through a column in the board.
     * @param start block to start jumping from
     * @param numbers current state of the board
     * @param randomNumber random number that represents the number candidate
     * @param element current element
     */
    public boolean iterateColumn(int start, HashMap[] numbers, int element, int randomNumber){
        int mirrorElement;
        if (element == 0) {
            mirrorElement = 3;
        } else if (element == 1) {
            mirrorElement = 4;
        } else if (element == 2) {
            mirrorElement = 5;
        }else if(element == 3) {
            mirrorElement = 0;
        } else if (element == 4) {
            mirrorElement = 1;
        }else{
            mirrorElement = 2;
        }
        for (int block = start; block < 6; block+= 2) {
            Integer posValue1 = (Integer) numbers[block].get(Integer.toString(element));
            Integer posValue2 = (Integer) numbers[block].get(Integer.toString(mirrorElement));
            if(posValue1 == randomNumber || posValue2 == randomNumber){
                return false;
            }
        }
        return true;
    }
    /**
     * Factory-like function that serves as a middleman for iterateRow, passing the appropriate values depending on
     * the values checkRow received.
     * @param numbers current state of the board
     * @param randomNumber random number that represents the number candidate
     * @param block current block
     * @param element current element
     */
    public boolean checkRow(HashMap[] numbers, int element, int block, int randomNumber){
        if(block == 0 || block == 1){
            return iterateRow(0, numbers, element, randomNumber);
        }else if(block == 2 || block == 3){
            return iterateRow(2, numbers, element, randomNumber);
        } else {
            return iterateRow(4, numbers, element, randomNumber);
        }
    }
    /**
     * Iterates through the first 3 positions in a block, then jumps to the next block and iterates through its first
     * 3 positions, effectively iterating through a file in the board.
     * @param start block to start jumping from
     * @param numbers current state of the board
     * @param randomNumber random number that represents the number candidate
     * @param element current element
     */
    public boolean iterateRow(int start, HashMap[] numbers, int element, int randomNumber){
        int[] mirrorElements = new int[2];
        if (element == 0) {
            mirrorElements[0] = 1;
            mirrorElements[1] = 2;
        } else if (element == 1) {
            mirrorElements[0] = 0;
            mirrorElements[1] = 2;
        } else if (element == 2) {
            mirrorElements[0] = 0;
            mirrorElements[1] = 1;
        }else if(element == 3) {
            mirrorElements[0] = 4;
            mirrorElements[1] = 5;
        } else if (element == 4) {
            mirrorElements[0] = 3;
            mirrorElements[1] = 5;
        }else {
            mirrorElements[0] = 4;
            mirrorElements[1] = 3;
        }
        for (int block = start; block <= start + 1; block++) {
            Integer posValue = (Integer) numbers[block].get(Integer.toString(element));
            Integer posValue2 = (Integer) numbers[block].get(Integer.toString(mirrorElements[0]));
            Integer posValue3 = (Integer) numbers[block].get(Integer.toString(mirrorElements[1]));
            if(posValue == randomNumber || posValue2 == randomNumber || posValue3 == randomNumber){
                return false;
            }
        }
        return true;
    }
    /**
     * Generates the basic structure of the board, creating a hashmap in every position of the numbers array,
     * and initializing the array with 6 keys that represent every position in a block, and sets the values
     * associated with those keys to 0 as a placeholder.
     */
    public HashMap<String, Integer>[] generateBaseStructure(){
        HashMap<String, Integer>[] numbers = new HashMap[6];
        for (int i = 0; i < 6; i++) {
            numbers[i] = new HashMap<>();
            for (int j = 0; j < 6; j++) {
                numbers[i].put(Integer.toString(j), 0);
            }
        }
        return numbers;
    }
    /**
     * Checks if a number given as input is equal to the value in the same position in the numbers array.
     *
     * @param number input value
     * @param blockPos position of the block where the textField is in
     * @param textFieldPos position of the textField within any of the 2 halves of a block
     */
    public boolean validateNumber(int number, int blockPos, int textFieldPos){
        return (int) numbers[blockPos].get(Integer.toString(textFieldPos)) == number;
    }
    /**
     * Validates that the input given by the user is a number between 1 and 6.
     */
    public boolean validateInput(String input){
        int number;
        try{
            number = Integer.parseInt(input);
        }catch (NumberFormatException e){
            return false;
        }

        return number <= 6 && number >= 1;

    }
    /**
     * Generates a random number between zero and a given number
     */
    public int chooseRandomNumber(int range){
        Random random = new Random();
        return random.nextInt(0,range);
    }
    public HashMap[] getNumbers(){
        return numbers;
    }
    public int getTotalLabels(){
        return this.totalLabels;
    }
    public void increaseTotalLabels(){
        this.totalLabels++;
    }
    public Modelo(){
        this.numbers = generateNumbers();
        this.totalLabels = 12;
    }
}