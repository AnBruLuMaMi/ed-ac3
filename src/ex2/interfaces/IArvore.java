package ex2.interfaces;

import ex2.exceptions.ArvoreVaziaException;
import ex2.exceptions.LimiteVioladoException;
import ex2.exceptions.NodoInvalidoException;

import java.util.Iterator;

public interface IArvore<T> extends Iterable<T> {
    Iterable<INodo<T>> positions();
    T replace(INodo<T> nodo, T elemento) throws NodoInvalidoException;
    INodo<T> root() throws ArvoreVaziaException;
    INodo<T> parent(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException;
    Iterable<INodo<T>> children(INodo<T> nodo) throws NodoInvalidoException;
    boolean isInternal(INodo<T> nodo) throws NodoInvalidoException;
    boolean isExternal(INodo<T> nodo) throws NodoInvalidoException;
    boolean isRoot(INodo<T> nodo) throws NodoInvalidoException;
    Iterator<T> iterator();
    int size();
    boolean isEmpty();
}
