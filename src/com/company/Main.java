package com.company;

import java.util.Scanner;

class Calculate{
    Boolean first_is_rome = false;
    int result;
    StringBuilder final_result = new StringBuilder();
    String  first_operand;
    String second_operand;
    int first_operand_int;
    int second_operand_int;

    public void finalResult(){

        if(first_is_rome){

            if(result < 10){
                final_result.append(convert(result));
            }
            else {
                int decimal = result / 10;
                int ostatok = result % 10;

                if(result<40){
                    final_result.append("X".repeat(decimal));
                    final_result.append(convert(ostatok));
                }
                if(result >= 40 && result < 50) {
                    final_result.append("XL");
                    final_result.append(convert(ostatok));
                }
                if(result >= 50 && result < 90){
                    final_result.append("L");
                    final_result.append("X".repeat(Math.max(0, decimal - 5)));
                    final_result.append(convert(ostatok));
                  }
                if( result >= 90 && result < 100 ){
                    final_result.append("XC");
                    final_result.append(convert(ostatok));
                }
                if(result == 100 ) final_result.append("C");
            }
        } else final_result.append(result);

        System.out.println(final_result);
    }

    public static String convert(int ostatok){
        if(ostatok == 0) return "";
        return switch (ostatok) {
            case 0 -> "Nil";
            case 1 ->  "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            default -> throw new IllegalStateException("Unexpected value: " + ostatok);
        };
    }

    public void convert_first_operand(){

        try{
            first_operand_int = Integer.parseInt(first_operand);
            if(first_operand_int > 10 || first_operand_int <= 0) throw new NumberFormatException();
        }catch (NumberFormatException exception){
            first_is_rome = true;
            switch (first_operand) {
                case "I" -> first_operand_int = 1;
                case "II" -> first_operand_int = 2;
                case "III" -> first_operand_int = 3;
                case "IV" -> first_operand_int = 4;
                case "V" -> first_operand_int = 5;
                case "VI" -> first_operand_int = 6;
                case "VII" -> first_operand_int = 7;
                case "VIII" -> first_operand_int = 8;
                case "IX" -> first_operand_int = 9;
                case "X" -> first_operand_int = 10;
                default -> {
                    System.out.println("Format of first operand is incorrect");
                    System.exit(0);
                }
            }
        }
    }

    public void convert_second_operand(){
        try {
            second_operand_int = Integer.parseInt(second_operand);
            if(second_operand_int > 10 || second_operand_int <= 0) throw new NumberFormatException();
            if(first_is_rome) {
                System.out.println("Format of first operand is rome, but of second in not");
                System.exit(0);
            }
        } catch (NumberFormatException exception){
            if(!first_is_rome) {
                System.out.println("Format of first operand or second is incorrect");
                System.exit(0);
            }
            switch (second_operand) {
                case "I" -> second_operand_int = 1;
                case "II" -> second_operand_int = 2;
                case "III" -> second_operand_int = 3;
                case "IV" -> second_operand_int = 4;
                case "V" -> second_operand_int = 5;
                case "VI" -> second_operand_int = 6;
                case "VII" -> second_operand_int = 7;
                case "VIII" -> second_operand_int = 8;
                case "IX" -> second_operand_int = 9;
                case "X" -> second_operand_int = 10;
                default -> {
                    System.out.println("Format of second operand is incorrect");
                    System.exit(0);
                }
            }
        }

    }


}

class Minus extends  Calculate {
    public void solve(){
        result = first_operand_int - second_operand_int;
    }
}

class Plus extends  Calculate {
    public void solve(){
        result = first_operand_int + second_operand_int;
    }
}

class Multiply extends  Calculate {
    public void solve(){
        result = first_operand_int * second_operand_int;
    }
}

class Subtract extends  Calculate {
    public void solve(){
        result = first_operand_int / second_operand_int;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true){
            getResult(input.nextLine());
        }
    }


    private static void getResult(String input) {

        input = input.replaceAll("\\s","");
        int indexOfOperation = input.indexOf('+');
        if(indexOfOperation == -1)
            indexOfOperation = input.indexOf('-');
        if(indexOfOperation == -1)
            indexOfOperation = input.indexOf('*');
        if(indexOfOperation == -1)
            indexOfOperation = input.indexOf('/');


        switch (input.charAt(indexOfOperation)) {
            case '+' -> { Plus plusObj = new Plus();
                            plusObj.first_operand = input.substring(0,indexOfOperation);
                            plusObj.second_operand = input.substring(indexOfOperation+1);
                            plusObj.convert_first_operand();
                            plusObj.convert_second_operand();
                            plusObj.solve();
                            plusObj.finalResult();
                        }
            case '-' -> { Minus minusObj = new Minus();
                              minusObj.first_operand = input.substring(0,indexOfOperation);
                              minusObj.second_operand = input.substring(indexOfOperation+1);
                              minusObj.convert_first_operand();
                              minusObj.convert_second_operand();
                              minusObj.solve();
                              minusObj.finalResult();}

            case '*' ->  { Multiply multiplyObj = new Multiply();
                               multiplyObj.first_operand = input.substring(0,indexOfOperation);
                               multiplyObj.second_operand = input.substring(indexOfOperation+1);
                               multiplyObj.convert_first_operand();
                               multiplyObj.convert_second_operand();
                               multiplyObj.solve();
                               multiplyObj.finalResult();}

            case '/' ->  { Subtract subtractObj = new Subtract();
                              subtractObj.first_operand = input.substring(0,indexOfOperation);
                              subtractObj.second_operand = input.substring(indexOfOperation+1);
                              subtractObj.convert_first_operand();
                              subtractObj.convert_second_operand();
                              subtractObj.solve();
                              subtractObj.finalResult(); }

            default -> System.out.println("Default");
        }
    }
}