package mec_2;

import java.util.ArrayList;
import java.util.Stack;

public class Utils {

    public Utils(String message) {
        System.out.println(message.toCharArray()[3]);
    }

    public static int calcularFibonacciLoop(int tope) {
        int n1 = 0;
        int n2 = 1;
        int suma = 0;
        System.out.println("n0: " + n1);

        for(int i=1; i<=tope; i++) {
            suma = n1 + n2;
            System.out.println("n" + i + ": " + suma);
            n1 = n2;
            n2 = suma;
        }

        return suma;
    }

    public static int calcularFibonacciRecursivo(int tope) {

        if (tope < 2 ) {
            return 1;
        }
        else {
            return calcularFibonacciRecursivo(tope - 1) + calcularFibonacciRecursivo(tope - 2);
        }
    }

    public static boolean esNumeroPrimo(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean esPalindromo(String text) {

        Stack pila = new Stack();
        char[] stringAsCharArray = text.toCharArray();
        int indiceMedioPush = stringAsCharArray.length % 2 == 0 ? stringAsCharArray.length / 2 : (stringAsCharArray.length - 1) / 2;
        int indiceMedioPop = stringAsCharArray.length % 2 == 0 ? stringAsCharArray.length / 2 : (stringAsCharArray.length + 1) / 2;

        for(int i=0; i<indiceMedioPush; i++) {
            pila.push(stringAsCharArray[i]);
        }

        for(int j = indiceMedioPop; j<stringAsCharArray.length; j++) {
            if(stringAsCharArray[j] == (char) pila.peek()) {
                pila.pop();
            }
            else {
                return false;
            }
        }
        if(pila.empty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Object[] removeDuplicates(Object[] array) {
        ArrayList<Object> result = new ArrayList<>();

        for(int i = 0; i < array.length; i++) {
            boolean add = true;
            for(Object each : result) {
                if(array[i] == each) {
                    add = false;
                    break;
                }
            }
            if(add) {
                result.add(array[i]);
            }
        }
        return result.toArray();
    }

    public static boolean sonNumerosAmigos(int num1, int num2) {
        int suma_divisores1 = 0;
        int suma_divisores2 = 0;
        int max = num1 >= num2 ? num1 : num2;

        for(int i=1; i<max; i++) {
            // Si el indice es menor que el numero en cuestion, evaluar si es divisor
            if(i<num1) {
                if(num1 % i == 0)  suma_divisores1 += i;
            }
            if(i<num2) {
                if(num2 % i == 0)  suma_divisores2 += i;
            }
        }
        if(suma_divisores1 == num2 && suma_divisores2 == num1) {
            return true;
        }
        else {
            return false;
        }
    }


}
