package ex3;

import ex2.interfaces.IArvore;
import ex2.interfaces.INodo;
import ex2.interfaces.INodoArvore;

public class OperacoesEx3 {
    private static <T> String postOrder(IArvore<T> arvore, INodo<T> nodo) {
        INodoArvore<T> nodoArvore = (INodoArvore<T>)nodo;

        String s = "";

        for (INodo<T> filho : nodoArvore.getChildren()) {
            s += postOrder(arvore, filho);
        }

        s +=  ", " + nodo.element().toString();

        return s;
    }

    public static <T> String parentheticRepresentation (IArvore<T> arvore, INodo<T> nodo) {
        String s = nodo.element().toString();

        int quantidadeTabs = depth(arvore, nodo) + 1;

        if (arvore.isInternal(nodo)) {
            Boolean primeiraVez = true;

            for (INodo<T> nodoFilho : arvore.children(nodo)) {
                if (primeiraVez) {
                    s += "(\n";

                    for (int i = 0; i < quantidadeTabs; i++) {
                        s += "\t";
                    }

                    s += parentheticRepresentation(arvore, nodoFilho);

                    primeiraVez = false;
                } else {
                    s += ",\n";

                    for (int i = 0; i < quantidadeTabs; i++) {
                        s += "\t";
                    }

                    s += parentheticRepresentation(arvore, nodoFilho);
                }
            }

            s += "\n";

            for (int i = 0; i < quantidadeTabs - 1; i++) {
                s += "\t";
            }

            s += ")";
        }

        return s;
    }

    public static <T> String toStringPostOrder(IArvore<T> arvore, INodo<T> nodo) {
        String s = "";

        s += postOrder(arvore, nodo);

        s = (s.length() == 0 ? s : s.substring(2));

        return "[" + s + "]";
    }

    public static int diskSpace(IArvore<DiscNode> arvore, INodoArvore<DiscNode> nodo) {
        int somaKbyte = nodo.element().getKbytes();

        for (INodo<DiscNode> w : nodo.getChildren()) {
            somaKbyte += diskSpace(arvore, (INodoArvore<DiscNode>)w);
        }

        if (arvore.isInternal(nodo)) {
            System.out.println(nodo.getElement().getName() + ": " + somaKbyte);
        }

        return somaKbyte;
    }

    public static <T> int depth(IArvore<T> arvore, INodo<T> nodo) {
        INodoArvore<T> nodoArvore = (INodoArvore<T>)nodo;

        if (arvore.root() == nodoArvore)
            return 0;

        return 1 + depth(arvore, nodoArvore.getParent());
    }

    public static <T> int height1(IArvore<T> arvore) {
        int altura = 0;

        Iterable<INodo<T>> nodos = arvore.positions();

        for (INodo<T> nodo : nodos) {
            if (arvore.isExternal(nodo)) {
                altura = Math.max(altura, depth(arvore, nodo));
            }
        }

        return altura;
    }

    public static <T> int height2(IArvore<T> arvore, INodo<T> nodo) {
        INodoArvore<T> nodoArvore = (INodoArvore<T>)nodo;

        if (arvore.isExternal(nodoArvore))
            return 0;

        int altura = 0;

        for (INodo<T> nodoFilho : nodoArvore.getChildren()) {
            altura = Math.max(altura, height2(arvore, nodoFilho));
        }

        return 1 + altura;
    }
}
