package com.royeth.randomgame;

public class Process {

    public Process() {
        super();
    }

//    populate an array with numbers 1 - 100
    public int[] arrayOfNumbers(){
        int[] arr = new int[100];

        for (int index = 0; index < arr.length; index++){
            arr[index] = index + 1;
        }

        return arr;
    }

//    Operator Method
    public int operator(){
//        1 - multiplication, 2 - division, 3 - addition, 4 - subtraction and 5 - modulus
        int[] arr = {1, 2, 3, 4, 5};

        int oprtr = (int) (Math.random() * arr.length);

        return oprtr;
    }

    public int firstNumber(){

        int[] arr = arrayOfNumbers();

        int number = (int) (Math.random() * arr.length);

        return number;
    }

    public int secondNumber(){

        int[] arr = arrayOfNumbers();

        int number = (int) (Math.random() * arr.length);

        return number;
    }

    public float perform(float firstNum, float secondNum, int sign){

        float answer = 0;

        switch (sign){
            case 1:
                answer = firstNum * secondNum;
                break;
            case 2:
                answer = firstNum / secondNum;
                break;
            case 3:
                answer = firstNum + secondNum;
                break;
            case 4:
                answer = Math.abs(firstNum - secondNum);
                break;
            case 5:
                answer = firstNum % secondNum;
            default:
                break;
        }

        return answer;
    }

    public char operatorChar(int sign){
        char signage = '%';
        switch (sign){
            case 1:
                signage = '*';
                break;
            case 2:
                signage = '/';
                break;
            case 3:
                signage = '+';
                break;
            case 4:
                signage = '-';
                break;
            case 5:
                signage = '%';
                break;
            default:
                break;
        }

        return signage;
    }

}
