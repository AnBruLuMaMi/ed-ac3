package ex5;

import ex2.interfaces.INodo;
import ex4.ArvoreBinaria;
import ex4.interfaces.IArvoreBinaria;
import ex4.interfaces.INodoArvoreBinaria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperacoesEx5 {
    private static <T> String inorderComVirgula(IArvoreBinaria<T> arvore, INodo nodo) {
        String s = "";

        if (arvore.hasLeft(nodo))
            s += inorderComVirgula(arvore, arvore.left(nodo));

        s += ", " + nodo.element().toString();

        if (arvore.hasRight(nodo))
            s += inorderComVirgula(arvore, arvore.right(nodo));

        return s;
    }

    private static <T> int inorder3(IArvoreBinaria<T> arvore, INodo nodo, int nroVisitas, ArrayList<CoordenadasArvore<T>> listaCoordenadas) {
        if (arvore.hasLeft(nodo))
            nroVisitas = inorder3(arvore, arvore.left(nodo), nroVisitas, listaCoordenadas);

        int y = depth(arvore, nodo);
        listaCoordenadas.add(new CoordenadasArvore<T>(nroVisitas, y, nodo.element().toString()));
        nroVisitas++;

        if (arvore.hasRight(nodo))
            nroVisitas = inorder3(arvore, arvore.right(nodo), nroVisitas, listaCoordenadas);

        return nroVisitas;
    }

    private static <T> int depth(IArvoreBinaria<T> arvore, INodo<T> nodo) {
        INodoArvoreBinaria<T> nodoArvore = (INodoArvoreBinaria<T>)nodo;

        if (arvore.root() == nodoArvore)
            return 0;

        return 1 + depth(arvore, nodoArvore.getParent());
    }

    private static <T> int contarNodosEsquerdosExternos(IArvoreBinaria<T> arvore, INodoArvoreBinaria nodo, int contagemAtual) {
        if (arvore.hasRight(nodo))
            contagemAtual = contarNodosEsquerdosExternos(arvore, (INodoArvoreBinaria) arvore.right(nodo), contagemAtual);

        if (arvore.hasLeft(nodo))
            contagemAtual = contarNodosEsquerdosExternos(arvore, (INodoArvoreBinaria) arvore.left(nodo), contagemAtual);

        if (arvore.isExternal(nodo) && nodo.getParent().getLeft() == nodo)
            return ++contagemAtual;

        return contagemAtual;
    }

    private static <T> int contarNodosDireitosExternos(IArvoreBinaria<T> arvore, INodoArvoreBinaria nodo, int contagemAtual) {
        if (arvore.hasRight(nodo))
            contagemAtual = contarNodosDireitosExternos(arvore, (INodoArvoreBinaria) arvore.right(nodo), contagemAtual);

        if (arvore.hasLeft(nodo))
            contagemAtual = contarNodosDireitosExternos(arvore, (INodoArvoreBinaria) arvore.left(nodo), contagemAtual);

        if (arvore.isExternal(nodo) && nodo.getParent().getRight() == nodo)
            return ++contagemAtual;

        return contagemAtual;
    }

    public static ArvoreBinaria buildExpression(String expressao) {
        Stack<ArvoreBinaria> pilha = new Stack();

        Pattern pattern = Pattern.compile("[\\d*/+-]", Pattern.CASE_INSENSITIVE);

        for (int i = 0; i < expressao.length(); i++) {
            char caractere = expressao.charAt(i);

            Matcher matcher = pattern.matcher("" + caractere);
            boolean caracterValido = matcher.find();

            if (caracterValido) {
                ArvoreBinaria<Character> arvoreBinaria = new ArvoreBinaria();
                arvoreBinaria.addRoot(caractere);
                pilha.push(arvoreBinaria);
            } else if (caractere == '(') {
                continue;
            } else {
                ArvoreBinaria<Character> arvoreBinaria2 = pilha.pop();
                ArvoreBinaria<Character> arvoreBinaria = pilha.pop();
                ArvoreBinaria<Character> arvoreBinaria1 = pilha.pop();

                arvoreBinaria.attach(arvoreBinaria.root(), arvoreBinaria1, arvoreBinaria2);
                pilha.push(arvoreBinaria);
            }
        }

        return pilha.pop();
    }

    public static <T> String binaryPreOrder(IArvoreBinaria<T> arvore, INodo nodo) {
        String s = nodo.element().toString();

        if (arvore.hasLeft(nodo))
            s += binaryPreOrder(arvore, arvore.left(nodo));

        if (arvore.hasRight(nodo))
            s += binaryPreOrder(arvore, arvore.right(nodo));

        return s;
    }

    public static <T> String binaryPostOrder(IArvoreBinaria<T> arvore, INodo nodo) {
        String s = "";

        if (arvore.hasLeft(nodo))
            s += binaryPostOrder(arvore, arvore.left(nodo));

        if (arvore.hasRight(nodo))
            s += binaryPostOrder(arvore, arvore.right(nodo));

        s += nodo.element().toString();

        return s;
    }

    public static double evaluateExpression(IArvoreBinaria<Character> arvore, INodo<Character> nodo) {
        if (arvore.isInternal(nodo)) {
            double x = evaluateExpression(arvore, arvore.left(nodo));
            double y = evaluateExpression(arvore, arvore.right(nodo));

            switch (nodo.element()) {
                case '+': {
                    return x + y;
                }
                case '-': {
                    return x - y;
                }
                case '*': {
                    return x * y;
                }
                case '/': {
                    return x / y;
                }
                default:
                    return 0;
            }
        } else {
            return Double.parseDouble(nodo.element().toString());
        }
    }

    public static <T> String inorder(IArvoreBinaria<T> arvore, INodo nodo) {
        String s = "";

        if (arvore.hasLeft(nodo))
            s += inorder(arvore, arvore.left(nodo));

        s += nodo.element().toString();

        if (arvore.hasRight(nodo))
            s += inorder(arvore, arvore.right(nodo));

        return s;
    }



    public static <T> String makerBTSearch(IArvoreBinaria<T> arvore, INodo nodo) {
        String s = "";

        s += inorderComVirgula(arvore, nodo);
        s = (s.length() == 0 ? s : s.substring(2));

        return s;
    }

    public static <T> String obterCoordenadasArvoreBinaria(IArvoreBinaria<T> arvore, INodo nodo) {
        var listaCoordenadas = new ArrayList<CoordenadasArvore<T>>();

        inorder3(arvore, nodo, 0, listaCoordenadas);

        return listaCoordenadas.toString();
    }

    public static <T> String eulerTour(IArvoreBinaria<T> arvore, INodo nodo) {
        String s = "";

        s += nodo.element().toString();

        if (arvore.hasLeft(nodo))
            s += eulerTour(arvore, arvore.left(nodo));

        s += nodo.element().toString();

        if (arvore.hasRight(nodo))
            s += eulerTour(arvore, arvore.right(nodo));

        s += nodo.element().toString();

        return s;
    }

    public static <T> String printExpression(IArvoreBinaria<T> arvore, INodo nodo) {
        String s = "";

        if (arvore.isInternal(nodo))
            s += "(";

        if (arvore.hasLeft(nodo))
            s += printExpression(arvore, arvore.left(nodo));

        s += nodo.element().toString();

        if (arvore.hasRight(nodo))
            s += printExpression(arvore, arvore.right(nodo));

        if (arvore.isInternal(nodo))
            s += ")";

        return s;
    }

    public static <T> int contarNodosEsquerdosExternos(IArvoreBinaria<T> arvore, INodoArvoreBinaria nodo) {
        int contagem = 0;

        contagem = contarNodosEsquerdosExternos(arvore, (INodoArvoreBinaria) nodo, contagem);

        return contagem;
    }

    public static <T> int contarNodosDireitosExternos(IArvoreBinaria<T> arvore, INodoArvoreBinaria nodo) {
        int contagem = 0;

        contagem = contarNodosDireitosExternos(arvore, (INodoArvoreBinaria) nodo, contagem);

        return contagem;
    }

    public static <T> String parentheticRepresentation (IArvoreBinaria<T> arvore, INodo<T> nodo) {
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
}
