/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplogit;

import java.util.Scanner;

/**
 *
 * @author fedts
 */
public class Principal {
    
    public static void mostrarTabuada(int num) {
        System.out.println("Tabuada do " + num);
        for (int i = 0; i < 11; i++) {
            System.out.println("" + num + " X " + i + " = " + (num*i));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Digite um número inteiro: ");
        int teste1 = entrada.nextInt();
        System.out.println("Valor teste1 + 1: " + teste1++);
        
        teste1 *= 210;
        System.out.println("Teste1 x 210 = " + teste1);

        teste1 = 0;
        System.out.println("Valor teste1: " + teste1++);
        
        int teste2 = 0;
        System.out.println("Valor teste1: " + --teste2);

        System.out.println("Exemplo Git");
        
        System.out.println("Qual tabuada será calculada?");
        int num = entrada.nextInt();
        mostrarTabuada(num);

    }
    
}
