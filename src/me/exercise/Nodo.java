package me.exercise;

import lombok.Data;

/**
 * Classe che rappresenta un nodo a doppio riferimento.
 * Il nodo contiene un riferimento al nodo precedente e successivo
 */
@Data
public class Nodo implements Cloneable {
    private int info;
    private Nodo prec;
    private Nodo next;

    public Nodo(int info) {
        this.info = info;
        prec = null;
        next = null;
    }

    @Override
    public Nodo clone() {
        try {
            return (Nodo) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    // toString dà solo il valore del nodo per evitare StackOverflowException
    @Override
    public String toString() {
        return String.format("Nodo(info = %d)", info);
    }
}
