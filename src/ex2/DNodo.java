package ex2;

import ex2.exceptions.NodoInvalidoException;
import ex2.interfaces.IListaNodo;
import ex2.interfaces.INodo;

public class DNodo<T> implements INodo {
    private IListaNodo<T> lista;
    private DNodo<T> anterior, proximo;
    private T elemento;

    public DNodo(DNodo<T> prev, DNodo<T> next, T elemento, IListaNodo<T> lista) {
        this.anterior = prev;
        this.proximo = next;
        this.elemento = elemento;
        this.lista = lista;
    }

    @Override
    public T element() throws NodoInvalidoException {
        if ((anterior == null) && (proximo == null))
            throw new NodoInvalidoException("O nodo não está em uma lista!");

        return elemento;
    }

    public DNodo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(DNodo<T> prev) {
        this.anterior = prev;
    }

    public DNodo<T> getProximo() {
        return proximo;
    }

    public void setProximo(DNodo<T> next) {
        this.proximo = next;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public IListaNodo<T> getLista() {
        return lista;
    }
}


