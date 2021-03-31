package ex1;

import ex2.ListaNodo;
import ex2.interfaces.IArvore;
import ex2.interfaces.IListaNodo;
import ex2.interfaces.INodo;
import ex2.interfaces.INodoArvore;

public class OperacoesEx1 {
    private static <T> void preOrdemNodos(IArvore<T> arvore, INodo<T> nodo, IListaNodo<INodo<T>> listaNodo) {
        listaNodo.addLast(nodo);

        for (INodo<T> nodoFilho : arvore.children(nodo))
            preOrdemNodos(arvore, nodoFilho, listaNodo);
    }

    public static <T> String obterNodosInternos(IArvore<T> arvore) {
        Iterable<INodo<T>> nodos = arvore.positions();

        String s = "";

        for (INodo nodo : nodos) {
            if (arvore.isInternal(nodo)) {
                s += ", " + nodo.toString();
            }
        }

        s = (s.length() == 0 ? s : s.substring(2));

        return "[" + s + "]";
    }

    public static <T> int contarDescendentes(IArvore<T> arvore, INodo<T> nodo) {
        IListaNodo<INodo<T>> listaNodo = new ListaNodo();

        if (arvore.size() != 0)
            preOrdemNodos(arvore, nodo, listaNodo);

        return listaNodo.size() - 1;
    }

    public static <T> String obterIrmaos(INodoArvore<T> nodo) {
        IListaNodo<INodo<T>> filhosDoMeuPai = nodo.getParent().getChildren();
        String irmaos = "";

        for (INodo<T> filho : filhosDoMeuPai) {
            if (filho != nodo) {
                irmaos += ", " + filho.element().toString();
            }
        }

        irmaos = (irmaos.length() == 0 ? irmaos : irmaos.substring(2));

        return "[" + irmaos + "]";
    }

    public static <T> String obterNodosSubArvore(IArvore<T> arvore, INodo<T> nodo) {
        IListaNodo<INodo<T>> listaNodo = new ListaNodo();

        if (arvore.size() != 0)
            preOrdemNodos(arvore, nodo, listaNodo);

        return listaNodo.toString();
    }
}
