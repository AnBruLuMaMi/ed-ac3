package ex4.test;

import ex2.interfaces.INodo;
import ex4.ArvoreBinaria;
import ex4.interfaces.IArvoreBinaria;
import ex4.interfaces.INodoArvoreBinaria;
import ex5.OperacoesEx5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex4Test {
    @Test
    void test() {
        INodo<Character> filhoEsquerda, filhoEsquerda2, filhoDireita;
        INodoArvoreBinaria<Character> irmaoFilhoDireita;

        ArvoreBinaria arvore = OperacoesEx5.buildExpression("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))");

        assertEquals("[-, /, *, +, 3, 1, 3, +, -, 9, 5, 2, +, *, 3, -, 7, 4, 6]", arvore.toString());
        assertEquals("-", arvore.root().element().toString());

        assertEquals(true, arvore.hasLeft(arvore.root()));

        filhoEsquerda = arvore.left(arvore.root());
        assertEquals("/", filhoEsquerda.element().toString());

        assertEquals(true, arvore.hasLeft(filhoEsquerda));

        filhoEsquerda2 = arvore.left(filhoEsquerda);
        assertEquals("*", filhoEsquerda2.element().toString());

        assertEquals(true, arvore.hasRight(filhoEsquerda2));
        filhoDireita = arvore.right(filhoEsquerda2);
        assertEquals("3", filhoDireita.element().toString());

        assertEquals(false, arvore.hasRight(filhoDireita));

        arvore.replace(filhoDireita, 10);
        assertEquals("[-, /, *, +, 3, 1, 10, +, -, 9, 5, 2, +, *, 3, -, 7, 4, 6]", arvore.toString());

        assertEquals(true, arvore.isExternal(filhoDireita));
        assertEquals(false, arvore.isInternal(filhoDireita));

        assertEquals(false, arvore.isExternal(filhoEsquerda2));
        assertEquals(true, arvore.isInternal(filhoEsquerda2));

        assertEquals(filhoEsquerda, arvore.parent(filhoEsquerda2));

        assertEquals(false, arvore.isEmpty());
        assertEquals(true, arvore.isRoot(arvore.root()));
        assertEquals(false, arvore.isRoot(filhoDireita));

        irmaoFilhoDireita = ((INodoArvoreBinaria)filhoEsquerda2).getLeft();
        assertEquals(irmaoFilhoDireita, arvore.sibling(filhoDireita));

        ArvoreBinaria arvore2 = OperacoesEx5.buildExpression("((2+2)/2)");
        ArvoreBinaria arvore3 = OperacoesEx5.buildExpression("((4+4)/2)");

        arvore.attach(filhoDireita, arvore2, arvore3);
        assertEquals("[-, /, *, +, 3, 1, 10, /, +, 2, 2, 2, /, +, 4, 4, 2, +, -, 9, 5, 2, +, *, 3, -, 7, 4, 6]", arvore.toString());

        assertEquals(29, arvore.size());
    }
}
