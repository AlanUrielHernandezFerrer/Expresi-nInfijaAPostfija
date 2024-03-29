/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expresión.infija.a.postfija;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Alan
 */
public class ExpresiónInfijaAPostfija {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        System.out.println("Escribe una expresión algebraica: ");
        Scanner leer = new Scanner(System.in);

        
        String expr = depurar(leer.nextLine());
        String[] arrayInfix = expr.split(" ");

      
        Stack< String> E = new Stack< String>(); 
        Stack< String> P = new Stack< String>(); 
        Stack< String> S = new Stack< String>(); 

        
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            E.push(arrayInfix[i]);
        }

        try {
            
            while (!E.isEmpty()) {
                switch (pref(E.peek())) {
                    case 1:
                        P.push(E.pop());
                        break;
                    case 3:
                    case 4:
                        while (pref(P.peek()) >= pref(E.peek())) {
                            S.push(P.pop());
                        }
                        P.push(E.pop());
                        break;
                    case 2:
                        while (!P.peek().equals("(")) {
                            S.push(P.pop());
                        }
                        P.pop();
                        E.pop();
                        break;
                    default:
                        S.push(E.pop());
                }
            }

            
            String infix = expr.replace(" ", "");
            String postfix = S.toString().replaceAll("[\\]\\[,]", "");

         
            System.out.println("Expresion Infija: " + infix);
            System.out.println("Expresion Postfija: " + postfix);

        } catch (Exception ex) {
            System.out.println("Error en la expresión algebraica vuelva a intentar");
            System.err.println(ex);
        }
    }

  
    private static String depurar(String s) {
        s = s.replaceAll("\\s+", "");
        s = "(" + s + ")";
        String simbols = "+-*/()";
        String str = "";

       
        for (int i = 0; i < s.length(); i++) {
            if (simbols.contains("" + s.charAt(i))) {
                str += " " + s.charAt(i) + " ";
            } else {
                str += s.charAt(i);
            }
        }
        return str.replaceAll("\\s+", " ").trim();
    }

    
    private static int pref(String op) {
        int prf = 99;
        if (op.equals("^")) {
            prf = 5;
        }
        if (op.equals("*") || op.equals("/")) {
            prf = 4;
        }
        if (op.equals("+") || op.equals("-")) {
            prf = 3;
        }
        if (op.equals(")")) {
            prf = 2;
        }
        if (op.equals("(")) {
            prf = 1;
        }
        return prf;
    }

}
