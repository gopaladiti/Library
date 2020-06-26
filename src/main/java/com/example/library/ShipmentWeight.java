package com.example.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShipmentWeight {

    private List<Integer> weights = new ArrayList<>();
    private Integer noOfDays = 0;
    Integer minAnswer;
    Integer answer;
    int iteration;
    int noOfPackages;
    List<Data> daysArray = new ArrayList<>();

    class Data {
        Integer index;
        Integer count;
    }

    private void getInputs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the array of weights, for example: 1,2,3,4,5 \n");
        String value = scanner.next();
        if (value != null) {
            String[] strArr = value.split(",");
            for (String s : strArr) {
                weights.add(Integer.parseInt(s.trim()));
            }
        }
        System.out.println("Enter the no of days: \n");
        noOfDays = scanner.nextInt();
        noOfPackages = weights.size();
    }

    public void printInputs() {
        System.out.println("Input Array:");
        System.out.print("[");
        for (Integer wt : weights) {
            System.out.print(wt + " ");
        }
        System.out.println("]");
        System.out.println("No of days: \n" + noOfDays);
    }

    public void prepareDaysArray() {
        int noOfElements = weights.size() - (noOfDays - 1);
        Data dataValue = new Data();
        dataValue.index = 0;
        dataValue.count = noOfElements;
        daysArray.add(dataValue);
        for(int i=1; i<noOfDays; i++) {
            dataValue = new Data();
            dataValue.index = daysArray.get(i-1).index + daysArray.get(i-1).count;
            dataValue.count = 1;
            daysArray.add(dataValue);
        }
    }

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

    public void printDaysArr(List<Data> daysArrForPrint) {
        for(int i=0; i<noOfDays; i++) {
            System.out.print("Day: " + i + " index: " + daysArrForPrint.get(i).index
            + " count: " + daysArrForPrint.get(i).count + " ");
            System.out.print("[");
            for(int j=daysArrForPrint.get(i).index;
            j<daysArrForPrint.get(i).index + daysArrForPrint.get(i).count; j++) {
                System.out.print(weights.get(j) + " ");
            }
            System.out.println("]");
        }
    }

    public void findAllCombinations(List<Data> daysArrForComb, int n) {
        List<Data> daysArrLocal = new ArrayList<>();
        for (Data d : daysArrForComb) {
            daysArrLocal.add(d);
        }

        if (n == (noOfDays - 1))
            return;

        System.out.println("******************Before**************");
        for (int i = 0; i < noOfDays; i++) {
            System.out.println("Day: " + i + " index: " + daysArrLocal.get(i).index
                    + " count: " + daysArrLocal.get(i).count + " ");
        }

        System.out.println("******************Iteration**************");

        while (daysArrLocal.get(n).count > 1) {
            daysArrLocal.get(n).count--;
            daysArrLocal.get(n + 1).index--;
            daysArrLocal.get(n + 1).count++;

            printDaysArr(daysArrLocal);

            answer = getMaxSum(daysArrLocal);
            getMinValue(answer);

            findAllCombinations(daysArrLocal, n + 1);

            System.out.println("After finding combinations...");
            printDaysArr(daysArrLocal);

        }

        System.out.println("***************After Iteration***************");
        n = n+1;
    }

    public Integer getMinValue(Integer value) {
        if(minAnswer == null) {
            minAnswer = value;
        } else if(value < minAnswer) {
            minAnswer = value;
        }
        return minAnswer;
    }

    public static void main(String[] args) {
        ShipmentWeight shipmentWeight = new ShipmentWeight();
        shipmentWeight.getInputs();
        shipmentWeight.printInputs();

        shipmentWeight.prepareDaysArray();
        shipmentWeight.printDaysArr(shipmentWeight.daysArray);

        int maxSum = shipmentWeight.getMaxSum(shipmentWeight.daysArray);
        System.out.println("Max Sum for a combination : " + maxSum);

        shipmentWeight.getMinValue(maxSum);

        for(int i=0; i<shipmentWeight.noOfDays; i++)
            shipmentWeight.findAllCombinations(shipmentWeight.daysArray, i);

        System.out.println("Solution is: " + shipmentWeight.minAnswer);
    }

}

