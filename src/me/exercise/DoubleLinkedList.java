package me.exercise;

import lombok.Getter;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Lista con nodi a doppio riferimento.
 * Ogni nodo contiene un riferimento al nodo precedente e successivo
 */
public class DoubleLinkedList {
    private Nodo testa;
    private Nodo coda;
    @Getter
    private int size;

    /**
     * Costruttore di default, setta testa e coda a null
     */
    public DoubleLinkedList() {
        testa = null;
        coda = null;
        size = 0;
    }

    /**
     * Costruttore che inizializza testa e coda con parametri dati dall'utente
     *
     * @param testaInfo Dato da inserire nella testa della lista
     * @param codaInfo  Dato da inserire nella coda della lista
     */
    public DoubleLinkedList(int testaInfo, int codaInfo) {
        testa = null;
        coda = null;
        addTesta(testaInfo);
        addCoda(codaInfo);
    }

    /**
     * Restituisce un elemento della lista dato il suo indice
     *
     * @param index Indice dell'elemento da restituire
     * @return L'elemento richiesto
     */
    public int get(int index) {
        Nodo output = testa.clone();
        try {
            // itero tante volte quante index per trovare l'elemento
            for (int i = 0; i < index; i++) {
                output = output.getNext();
            }
        } catch (NullPointerException ex) { // potrebbe tornare un NullPointerException se l'utente dà un numero più grande della lista
            return -1;
        }
        return output.getInfo();
    }

    /**
     * Restituisce un nodo dalla lista dato il suo indice.
     * protected perchè l'utente non deve modificare direttamente i nodi.
     *
     * @param index Indice dell'elemento da restituire
     * @return Il nodo richiesto
     */
    protected Nodo getNodo(int index) {
        Nodo output = testa.clone();
        try {
            for (int i = 0; i < index; i++) {
                output = output.getNext();
            }
        } catch (NullPointerException ex) {
            return null;
        }
        return output;
    }

    /**
     * Aggiunge un dato alla testa della lista
     *
     * @param info Il dato da aggiungere
     */
    public void addTesta(int info) {
        // se non c'è una testa la creo e gli do il valore
        if (testa == null) {
            testa = new Nodo(info);
            size++;
            return;
        }
        // aggiungo un nodo al prec della testa
        testa.setPrec(new Nodo(info));
        // setto la testa della lista come next del nodo appena creato
        testa.getPrec().setNext(testa.clone());
        // aggiorno la testa con quella nuova
        testa = testa.getPrec();
        setTestaCoda();
        size++;
    }

    /**
     * Aggiunge un dato alla coda della lista
     *
     * @param info Il dato da aggiungere
     */
    public void addCoda(int info) {
        if (coda == null) {
            coda = new Nodo(info);
            size++;
            return;
        }
        coda.setNext(new Nodo(info));
        coda.getNext().setPrec(coda.clone());
        coda = coda.getNext();
        setTestaCoda();
        size++;
    }

    /**
     * Rimuove il dato alla testa della lista
     */
    public void rimuoviTesta() {
        testa = testa.getNext();
        size--;
    }

    /**
     * Rimuove il dato alla coda della lista
     */
    public void rimuoviCoda() {
        coda = coda.getPrec();
        size--;
    }

    /**
     * Rimuove ul'elemento indicato dall'utente
     *
     * @param index L'indice dell'elemento da eliminare
     */
    public int rimuoviElemento(int index) {
        Nodo toRem = getNodo(index);
        Nodo nodoNext = toRem.getNext();
        Nodo nodoPrec = toRem.getPrec();
        // setto il next del nodo precedente a il nodo successiv
        // (dal punto di vista del nodo da eliminare)
        nodoPrec.setNext(nodoNext);
        // setto il prec del nodo successivo al nodo precedente
        nodoNext.setPrec(nodoPrec);
        // spero il GC butti via toRem, dato che non esiste dispose
        return toRem.getInfo();
    }

    /**
     * Esegue un metodo che non ritorna niente su tutti gli elementi della lista partendo dalla coda
     *
     * @param action Metodo da applicare agli elementi
     */
    public void forEachCoda(Consumer<Nodo> action) {
        Objects.requireNonNull(action);
        if (coda != null) {
            Nodo n;
            for (n = coda; n != null; n = n.getPrec()) {
                action.accept(n);
            }
        }
    }

    /**
     * Esegue un metodo che non ritorna niente su tutti gli elementi della lista partendo dalla testa
     *
     * @param action Metodo da applicare agli elementi
     */
    public void forEachTesta(Consumer<Nodo> action) {
        Objects.requireNonNull(action);
        if (testa != null) {
            Nodo n;
            for (n = testa; n != null; n = n.getNext()) {
                action.accept(n);
            }
        }
    }

    /**
     * Metodo che assiste nell'assegnazione della testa e le coda.
     * Nel caso che la testa o la coda non siano assegnate correttamente questo metodo ripara l'errore
     */
    protected void setTestaCoda() {
        // se la coda non c'e ma la testa c'è, cerco la fine della lista dalla testa e metto l'ultimo nodo come coda
        if (coda == null && testa != null) {
            Nodo n = testa;
            while (n.getNext() != null) {
                n = n.getNext();
            }
            coda = n;
        }
        // opposto di prima
        if (testa == null && coda != null) {
            Nodo n = coda;
            while (n.getPrec() != null) {
                n = n.getPrec();
            }
            testa = n;
        }
    }
}
