package ex2;

import ex2.interfaces.IListaNodo;
import ex2.interfaces.INodo;
import ex2.interfaces.INodoArvore;

public class NodoArvore<T> implements INodoArvore<T> {
    private T elemento;
    private INodoArvore<T> pai;
    private IListaNodo<INodo<T>> filhos;

    public NodoArvore(T elemento, INodoArvore<T> pai, IListaNodo<INodo<T>> filhos) {
        setElement(elemento);
        setParent(pai);
        setChildren(filhos);
    }

    @Override
    public T element() {
        return elemento;
    }

    @Override
    public void setElement(T elemento) {
        this.elemento = elemento;
    }

    @Override
    public T getElement() {
        return this.elemento;
    }

    @Override
    public IListaNodo<INodo<T>> getChildren() {
        return filhos;
    }

    @Override
    public void setChildren(IListaNodo<INodo<T>> filhos) {
        this.filhos = filhos;
    }

    @Override
    public INodoArvore<T> getParent() {
        return pai;
    }

    @Override
    public void setParent(INodoArvore<T> pai) {
        this.pai = pai;
    }

    @Override
    public String toString() {
        return elemento.toString();
    }
}
