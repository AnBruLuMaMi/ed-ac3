package ex4.interfaces;

import ex2.exceptions.LimiteVioladoException;
import ex2.exceptions.NodoInvalidoException;
import ex2.interfaces.IArvore;
import ex2.interfaces.INodo;

public interface IArvoreBinaria<T> extends IArvore<T> {
    INodo<T> left(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException;
    INodo<T> right(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException;
    boolean hasLeft(INodo<T> nodo) throws NodoInvalidoException;
    boolean hasRight(INodo<T> nodo) throws NodoInvalidoException;
}
