package me.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Consegna:
 * Genera una lista di almeno 10 elementi inserendo come informazione un numero intero generato in modo casuale con range 1-9;
 * togli dalla lista gli elementi pari inserendoli in una nuova lista puntata doppia.
 */
public class Main {
    static Scanner in = new Scanner(System.in);
    static DoubleLinkedList nums = new DoubleLinkedList();
    static DoubleLinkedList pari = new DoubleLinkedList();

    /**
     * Punto d'entrata principale dell'applicazione
     *
     * @param args Argomenti da linea di comando
     */
    public static void main(String[] args) {
        System.out.println("Inserire numero di elementi da generare:");
        int size = in.nextInt();
        Random rng = new Random();
        for (int i = 0; i < size; i++) {
            nums.addCoda(rng.nextInt(9) + 1);
        }

        for (int i = 0; i < nums.getSize(); i++) {
            if (nums.get(i) % 2 == 0) {
                pari.addCoda(nums.rimuoviElemento(i));
            }
        }

        System.out.println("Numeri pari:");
        pari.forEachTesta(System.out::println);
        System.out.println("-----");
        System.out.println("Numeri:");
        nums.forEachTesta(System.out::println);
    }
}
