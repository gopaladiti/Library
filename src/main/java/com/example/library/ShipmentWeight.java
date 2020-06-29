package com.example.library;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShipmentWeight {

    private List<Integer> weights = new ArrayList<>();
    private Integer noOfDays = 0;
    Integer leastWeight;
    Integer sumOfElements;
    List<Integer> sumOfSums = new ArrayList<>();
    int noOfPackages;
    List<Data> daysArray = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    class Data {
        Integer index;
        Integer count;
        Data(Integer index, Integer count) {
            this.index = index;
            this.count = count;
        }
    }

    /*
     * Gets inputs from user and validates
     */
    private void getInputs() {
        System.out.println("Enter the array of weights, for example: 1,2,3,4,5 \n");
        String value = validateArray();

        if (value != null) {
            String[] strArr = value.split(",");
            for (String s : strArr) {
                weights.add(Integer.parseInt(s.trim()));
            }
        }
        noOfPackages = weights.size();

        System.out.println("Enter the no of days: \n");
        validateNoOfDays();
    }

    /*
     * Validates the input array
     */
    public String validateArray() {
        String value = scanner.nextLine();
        boolean matched = false;
        while (!matched) {
            Pattern inputArrayPattern = Pattern.compile(String.format("^(\\d+(\\S)+$(,\\d+)*)$"));
            matched = inputArrayPattern.matcher(value).matches();
            if(!matched) {
                System.out.println("Invalid array format!");
                System.out.println("Enter the array of weights, for example: 1,2,3,4,5 \n");
                value = scanner.nextLine();
            }
        }
        return value;
    }

    /*
     * Validates the input number of days
     */
    public void validateNoOfDays() {
        String days = scanner.nextLine();
        boolean matched = false;
        while (!matched) {
            Pattern inputArrayPattern = Pattern.compile(String.format("^(\\d+)$"));
            matched = inputArrayPattern.matcher(days).matches();
            if (!matched) {
                System.out.println("Invalid number of days!");
                System.out.println("Enter the no of days: \n");
                days = scanner.nextLine();
            } else {
                noOfDays = Integer.parseInt(days);
                if (noOfDays > noOfPackages || noOfDays == 0) {
                    matched = false;
                    System.out.println("The number of days should be less than the number of weights in the array, but not 0!");
                    System.out.println("Enter the no of days: \n");
                    days = scanner.nextLine();
                }
            }
        }
    }

    /*
     * Prints the user inputs to the console
     */
    public void printInputs() {
        System.out.println("Input Array:");
        System.out.print("[ ");
        for (Integer wt : weights) {
            System.out.print(wt + " ");
        }
        System.out.println("]");
        System.out.println("No of days: \n" + noOfDays + "\n");
    }

    /*
     * Populates 'daysArray' with an array of 'Data' elements.
     * For each day, there will be a 'Data' element containing
     * 'index' as the start index of the subset to be created
     * and 'count' as the number of elements, from the start index
     * to be included in the subset
     */
    public void prepareDaysArray() {
        int noOfElements = weights.size() - (noOfDays - 1);
        Data dataValue = new Data(0,noOfElements);
        daysArray.add(dataValue);
        for(int i=1; i<noOfDays; i++) {
            int index = daysArray.get(i-1).index + daysArray.get(i-1).count;
            dataValue = new Data(index, 1);
            daysArray.add(dataValue);
        }
    }

    /*
     * Returns the subsets of 'weights' list based on the index and count
     * for each Day mentioned in the daysArrForPackages list
     */
    public List<List<Integer>> getListOfPackages(List<Data> daysArrForPackages) {
        List<List<Integer>> listOfPackageCombinations = new ArrayList<>();
        for(int i=0; i<noOfDays; i++) {
            int index = daysArrForPackages.get(i).index;
            int count = daysArrForPackages.get(i).count;
            List<Integer> listOfPackages = new ArrayList<>();
            for(int j = index; j<(index + count); j++) {
                listOfPackages.add(weights.get(j));
            }
            listOfPackageCombinations.add(listOfPackages);
        }
        return listOfPackageCombinations;
    }

    /*
     * Returns the maximum sum of the sum of subset elements of the
     * particular combination
     */
    public Integer getMaxSum(List<Data> daysArrForSum) {
        List<List<Integer>> combList = getListOfPackages(daysArrForSum);
        int maxSum = 0;
        for(List<Integer> listOfPkgs : combList) {
            int sum = listOfPkgs.stream().mapToInt(Integer::intValue).sum();
            if(sum > maxSum)
                maxSum = sum;
        }
        return maxSum;
    }

    /*
     * Prints the subsets of weights for each day for the particular
     * combination
     */
    public void printDaysArr(List<Data> daysArrForPrint) {
        for(int i=0; i<noOfDays; i++) {
            System.out.print("Day: " + (i+1) + " ");
            System.out.print("[");
            for(int j=daysArrForPrint.get(i).index;
            j<daysArrForPrint.get(i).index + daysArrForPrint.get(i).count; j++) {
                System.out.print(weights.get(j) + " ");
            }
            System.out.println("]");
        }
    }

    /*
     * Finds all combinations of subsets of weights based on index (.i.e. start index)
     * and count (.i.e. number of elements from start index) in List<Data> daysArrForComb
     */
    public void findAllCombinations(List<Data> daysArrForComb, int n) {
        List<Data> daysArrLocal = new ArrayList<>();
        daysArrLocal = daysArrForComb.stream().map(data ->
                new Data(new Integer(data.index.intValue()), new Integer(data.count.intValue())))
                .collect(Collectors.toList());

        if (n == (noOfDays - 1))
            return;

        while (daysArrLocal.get(n).count > 1) {
            daysArrLocal.get(n).count--;
            daysArrLocal.get(n + 1).index--;
            daysArrLocal.get(n + 1).count++;

            System.out.println("*************************************************");

            printDaysArr(daysArrLocal);

            sumOfElements = getMaxSum(daysArrLocal);
            System.out.println("Max Sum for this combination: " + sumOfElements);
            sumOfSums.add(sumOfElements);
            getMinValue();

            findAllCombinations(daysArrLocal, n + 1);
        }
        n = n+1;
    }

    /*
     * Get the minimum from sumOfSums which contains the maximum sums of subsets
     * of each combination
     */
    public Integer getMinValue() {
        leastWeight =  sumOfSums.stream().mapToInt(Integer::intValue).min().getAsInt();
        return leastWeight;
    }

    public static void main(String[] args) {
        ShipmentWeight shipmentWeight = new ShipmentWeight();
        shipmentWeight.getInputs();
        shipmentWeight.printInputs();

        shipmentWeight.prepareDaysArray();
        shipmentWeight.printDaysArr(shipmentWeight.daysArray);

        Integer maxSum = shipmentWeight.getMaxSum(shipmentWeight.daysArray);
        shipmentWeight.sumOfSums.add(maxSum);
        System.out.println("Max Sum for this combination : " + maxSum);

        shipmentWeight.getMinValue();

        for(int i=0; i<shipmentWeight.noOfDays; i++)
            shipmentWeight.findAllCombinations(shipmentWeight.daysArray, i);

        System.out.println("\nLeast Weight Capacity is: " + shipmentWeight.leastWeight);
    }

}

