package ex5.test;

import ex2.interfaces.INodo;
import ex4.ArvoreBinaria;
import ex4.interfaces.IArvoreBinaria;
import ex4.interfaces.INodoArvoreBinaria;
import ex5.OperacoesEx5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex5Test {
    ArvoreBinaria criarArvoreBinariaPesquisa() {
        ArvoreBinaria arvoreBinariaPesquisa = new ArvoreBinaria();

        arvoreBinariaPesquisa.addRoot(58);
        INodoArvoreBinaria nodoRaiz = (INodoArvoreBinaria)arvoreBinariaPesquisa.root();

        INodo nodo31 = arvoreBinariaPesquisa.insertLeft(nodoRaiz, 31);
        INodo nodo90 = arvoreBinariaPesquisa.insertRight(nodoRaiz, 90);

        //Filhos 31
        INodo nodo25 = arvoreBinariaPesquisa.insertLeft(nodo31, 25);
        INodo nodo42 = arvoreBinariaPesquisa.insertRight(nodo31, 42);

        //Filhos 25
        INodo nodo12 = arvoreBinariaPesquisa.insertLeft(nodo25, 12);

        //Filhos 42
        INodo nodo36 = arvoreBinariaPesquisa.insertLeft(nodo42, 36);

        //Filhos 90
        INodo nodo62 = arvoreBinariaPesquisa.insertLeft(nodo90, 62);

        //Filhos 62
        INodo nodo75 = arvoreBinariaPesquisa.insertRight(nodo62, 75);

        return arvoreBinariaPesquisa;
    }

    @Test
    void test() {
        IArvoreBinaria arvoreBinariaPesquisa = criarArvoreBinariaPesquisa();
        IArvoreBinaria arvore = OperacoesEx5.buildExpression("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))");

        assertEquals("-/*+313+-952+*3-746", OperacoesEx5.binaryPreOrder(arvore, arvore.root()));
        assertEquals("583125124236906275", OperacoesEx5.binaryPreOrder(arvoreBinariaPesquisa, arvoreBinariaPesquisa.root()));

        assertEquals("31+3*95-2+/374-*6+-", OperacoesEx5.binaryPostOrder(arvore, arvore.root()));
        assertEquals("122536423175629058", OperacoesEx5.binaryPostOrder(arvoreBinariaPesquisa, arvoreBinariaPesquisa.root()));

        assertEquals(-13.0, OperacoesEx5.evaluateExpression(arvore, arvore.root()));

        assertEquals("3+1*3/9-5+2-3*7-4+6", OperacoesEx5.inorder(arvore, arvore.root()));
        assertEquals("122531364258627590", OperacoesEx5.inorder(arvoreBinariaPesquisa, arvoreBinariaPesquisa.root()));

        assertEquals("12, 25, 31, 36, 42, 58, 62, 75, 90", OperacoesEx5.makerBTSearch(arvoreBinariaPesquisa, arvoreBinariaPesquisa.root()));

        assertEquals("[(0, 3, 12), (1, 2, 25), (2, 1, 31), (3, 3, 36), (4, 2, 42), (5, 0, 58), (6, 2, 62), (7, 3, 75), (8, 1, 90)]", OperacoesEx5.obterCoordenadasArvoreBinaria(arvoreBinariaPesquisa, arvoreBinariaPesquisa.root()));

        assertEquals("-/*+333+111+*333*/+-999-555-+222+/-+*333*-777-444-*+666+-", OperacoesEx5.eulerTour(arvore, arvore.root()));
        assertEquals("583125121212252531423636364242315890626275757562909058", OperacoesEx5.eulerTour(arvoreBinariaPesquisa, arvoreBinariaPesquisa.root()));

        assertEquals("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))", OperacoesEx5.printExpression(arvore, arvore.root()));

        assertEquals(4, OperacoesEx5.contarNodosEsquerdosExternos(arvore, (INodoArvoreBinaria) arvore.root()));
        assertEquals(2, OperacoesEx5.contarNodosEsquerdosExternos(arvoreBinariaPesquisa, (INodoArvoreBinaria) arvoreBinariaPesquisa.root()));

        assertEquals(6, OperacoesEx5.contarNodosDireitosExternos(arvore, (INodoArvoreBinaria) arvore.root()));
        assertEquals(1, OperacoesEx5.contarNodosDireitosExternos(arvoreBinariaPesquisa, (INodoArvoreBinaria) arvoreBinariaPesquisa.root()));

    }
}
