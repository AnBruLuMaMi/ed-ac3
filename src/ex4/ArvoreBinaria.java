package ex4;

import ex2.ListaNodo;
import ex2.exceptions.ArvoreNaoVaziaException;
import ex2.exceptions.ArvoreVaziaException;
import ex2.exceptions.LimiteVioladoException;
import ex2.exceptions.NodoInvalidoException;
import ex2.interfaces.IListaNodo;
import ex2.interfaces.INodo;
import ex4.interfaces.IArvoreBinaria;
import ex4.interfaces.INodoArvoreBinaria;

import java.util.Iterator;

public class ArvoreBinaria<T> implements IArvoreBinaria<T> {
    private INodoArvoreBinaria<T> raiz;
    private int quantidadeElementos;

    public ArvoreBinaria() {
        this.raiz = null;
        this.quantidadeElementos = 0;
    }

    private void preOrdemNodos(INodo<T> v, IListaNodo<INodo<T>> posicao) throws NodoInvalidoException {
        posicao.addLast(v);

        if (hasLeft(v))
            preOrdemNodos(left(v), posicao);

        if (hasRight(v))
            preOrdemNodos(right(v), posicao);
    }

    protected INodoArvoreBinaria<T> validarNodo(INodo<T> nodo) throws NodoInvalidoException {
        if (nodo == null || !(nodo instanceof INodoArvoreBinaria))
            throw new NodoInvalidoException("A posição é inválida.");

        return (INodoArvoreBinaria<T>)nodo;
    }

    private INodoArvoreBinaria<T> criarNodo(T elemento, INodoArvoreBinaria<T> pai, INodoArvoreBinaria<T> esquerda, INodoArvoreBinaria<T> direita) {
        return new NodoArvoreBinaria(elemento, pai, esquerda, direita);
    }

    private static <T> String toString(ArvoreBinaria<T> arvore) {
        String s = "";

        for (T i : arvore) {
            s += ", " + i;
        }

        s = (s.length() == 0 ? s : s.substring(2));

        return "[" + s + "]";
    }

    public INodo<T> addRoot(T elemento) throws ArvoreNaoVaziaException {
        if (!isEmpty())
            throw new ArvoreNaoVaziaException("A árvore já possui uma raiz.");

        quantidadeElementos = 1;
        raiz = criarNodo(elemento, null, null, null);

        return raiz;
    }

    public void attach(INodo<T> nodo, IArvoreBinaria<T> T1, IArvoreBinaria<T> T2) throws NodoInvalidoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);

        if (isInternal(nodo))
            throw new NodoInvalidoException("Não é possivel anexao a partir de um nodo interno.");

        if (!T1.isEmpty()) {
            INodoArvoreBinaria<T> nodoEsquerda = validarNodo(T1.root());

            nodoArvoreBinaria.setLeft(nodoEsquerda);
            nodoEsquerda.setParent(nodoArvoreBinaria);
            quantidadeElementos += T1.size();
        }

        if (!T2.isEmpty()) {
            INodoArvoreBinaria<T> nodoDireita = validarNodo(T2.root());

            nodoArvoreBinaria.setRight(nodoDireita);
            nodoDireita.setParent(nodoArvoreBinaria);
            quantidadeElementos += T2.size();
        }
    }

    @Override
    public Iterable<INodo<T>> positions() {
        IListaNodo<INodo<T>> nodos = new ListaNodo();

        if (quantidadeElementos != 0)
            preOrdemNodos(root(), nodos);

        return nodos;
    }

    @Override
    public T replace(INodo<T> nodo, T elemento) throws NodoInvalidoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);

        T temp = nodo.element();

        nodoArvoreBinaria.setElement(elemento);

        return temp;
    }

    @Override
    public INodo<T> root() throws ArvoreVaziaException {
        if (raiz == null)
            throw new ArvoreVaziaException("A árvore está vazia.");

        return raiz;
    }

    @Override
    public INodo<T> parent(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);

        INodo<T> nodoPai = nodoArvoreBinaria.getParent();

        if (nodoPai == null)
            throw new LimiteVioladoException("O nodo não possui pai.");

        return nodoPai;
    }

    @Override
    public Iterable<INodo<T>> children(INodo<T> nodo) throws NodoInvalidoException {
        IListaNodo<INodo<T>> filhos = new ListaNodo();

        if (hasLeft(nodo))
            filhos.addLast(left(nodo));

        if (hasRight(nodo))
            filhos.addLast(right(nodo));

        return filhos;
    }

    public INodo<T> sibling(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);
        INodoArvoreBinaria<T> nodoPai = nodoArvoreBinaria.getParent();

        if (nodoPai != null) {
            INodoArvoreBinaria<T> nodoIrmao;
            INodoArvoreBinaria<T> nodoEsquerda = nodoPai.getLeft();

            if (nodoEsquerda == nodoArvoreBinaria)
                nodoIrmao = nodoPai.getRight();
            else
                nodoIrmao = nodoPai.getLeft();

            if (nodoIrmao != null)
                return nodoIrmao;
        }

        throw new LimiteVioladoException("Não há irmão");
    }

    public INodo<T> insertLeft(INodo<T> nodo, T elemento) throws NodoInvalidoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);

        INodo<T> nodoEsquerda = (INodo<T>) nodoArvoreBinaria.getLeft();

        if (nodoEsquerda != null)
            throw new NodoInvalidoException("O nodo já possui um filho na esquerda.");

        INodoArvoreBinaria<T> nodoArvoreBinaria2 = criarNodo(elemento, nodoArvoreBinaria, null, null);

        nodoArvoreBinaria.setLeft(nodoArvoreBinaria2);

        quantidadeElementos++;

        return nodoArvoreBinaria2;
    }

    public INodo<T> insertRight(INodo<T> nodo, T elemento) throws NodoInvalidoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);

        INodo<T> nodoDireita = (INodo<T>) nodoArvoreBinaria.getRight();

        if (nodoDireita != null)
            throw new NodoInvalidoException("O nodo já possui um filho na direita.");

        INodoArvoreBinaria<T> nodoArvoreBinaria2 = criarNodo(elemento, nodoArvoreBinaria, null, null);

        nodoArvoreBinaria.setRight(nodoArvoreBinaria2);

        quantidadeElementos++;

        return nodoArvoreBinaria2;
    }

    @Override
    public boolean isInternal(INodo<T> nodo) throws NodoInvalidoException {
        validarNodo(nodo);

        return hasLeft(nodo) || hasRight(nodo);
    }

    @Override
    public boolean isExternal(INodo<T> nodo) throws NodoInvalidoException {
        return !isInternal(nodo);
    }

    @Override
    public boolean isRoot(INodo<T> nodo) throws NodoInvalidoException {
        validarNodo(nodo);

        return nodo == root();
    }

    @Override
    public Iterator<T> iterator() {
        Iterable<INodo<T>> nodos = positions();

        IListaNodo<T> listaNodos = new ListaNodo();

        for (INodo<T> nodo : nodos)
            listaNodos.addLast(nodo.element());

        return listaNodos.iterator();
    }

    @Override
    public int size() {
        return quantidadeElementos;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public INodo<T> left(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);

        INodo<T> nodoEsquerdo = nodoArvoreBinaria.getLeft();

        if (nodoEsquerdo == null)
            throw new LimiteVioladoException("Não há filho a esquerda.");

        return nodoEsquerdo;
    }

    @Override
    public INodo<T> right(INodo<T> nodo) throws NodoInvalidoException, LimiteVioladoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);

        INodo<T> nodoDireita = nodoArvoreBinaria.getRight();

        if (nodoDireita == null)
            throw new LimiteVioladoException("Não há filho a direita.");

        return nodoDireita;
    }

    @Override
    public boolean hasLeft(INodo<T> nodo) throws NodoInvalidoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);
        return nodoArvoreBinaria.getLeft() != null;
    }

    @Override
    public boolean hasRight(INodo<T> nodo) throws NodoInvalidoException {
        INodoArvoreBinaria<T> nodoArvoreBinaria = validarNodo(nodo);
        return nodoArvoreBinaria.getRight() != null;
    }

    @Override
    public String toString() {
        return toString(this);
    }
}
