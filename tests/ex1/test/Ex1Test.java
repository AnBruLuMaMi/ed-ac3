package ex1.test;

import ex1.OperacoesEx1;
import ex2.Arvore;
import ex2.ListaNodo;
import ex2.NodoArvore;
import ex2.interfaces.IListaNodo;
import ex2.interfaces.INodo;
import ex2.interfaces.INodoArvore;
import ex3.OperacoesEx3;
import ex3.DiscNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex1Test {
    private <T> NodoArvore<T>  criarFilho(NodoArvore<T> nodo, T elemento) {
        IListaNodo<INodo<T>> filhos;

        NodoArvore<T> aux;
        filhos = nodo.getChildren();

        aux = new NodoArvore<T>(elemento, nodo, new ListaNodo());

        filhos.addLast(aux);

        return aux;
    }

    private Arvore<DiscNode> criarArvoreDisco() {
        Arvore<DiscNode> arvore = new Arvore();
        NodoArvore<DiscNode> raiz, v, m, i, u, z, y, w;


        arvore.addRoot(new DiscNode("/usuário/rt/cursos/", 1));

        raiz = (NodoArvore<DiscNode>)arvore.root();

        raiz.setChildren(new ListaNodo());

        // Filhos da raiz: /usuário/rt/cursos/
        v = criarFilho(raiz, new DiscNode("cs016/", 2));
        m = criarFilho(raiz, new DiscNode("cs252/", 1));

        // Filhos de cs016/
        criarFilho(v, new DiscNode("notas", 8));
        i = criarFilho(v, new DiscNode("temas/", 1));
        u = criarFilho(v, new DiscNode("programas/", 1));

        // Filhos de temas/
        criarFilho(i, new DiscNode("hw1", 3));
        criarFilho(i, new DiscNode("hw2", 2));
        criarFilho(i, new DiscNode("hw3", 4));

        // Filhos de programas/
        criarFilho(u, new DiscNode("pr1", 57));
        criarFilho(u, new DiscNode("pr2", 97));
        criarFilho(u, new DiscNode("pr3", 74));

        // Filhos de cs252/
        z = criarFilho(m, new DiscNode("projetos/", 1));
        criarFilho(m, new DiscNode("notas", 3));

        // Filhos de projetos/
        y = criarFilho(z, new DiscNode("trabalhos/", 1));
        w = criarFilho(z, new DiscNode("demos/", 1));

        // Filhos de trabalhos/
        criarFilho(y, new DiscNode("compre baixo/", 26));
        criarFilho(y, new DiscNode("venda alto/", 55));

        // Filhos de demos//
        criarFilho(w, new DiscNode("mercado", 4786));

        return arvore;
    }

    @Test
    void test() {
        INodoArvore<DiscNode> raiz, nodoCs016, nodoCs252, nodoProjetos, nodoTrabalhos;
        IListaNodo<INodo<DiscNode>> filhos, filhosCs016, filhosCs252, filhosProjetos;

        Arvore<DiscNode> arvore = criarArvoreDisco();

        raiz = arvore.root();

        assertEquals("/usuário/rt/cursos/", raiz.element().getName());
        //a) O nodo '/usuário/rt/cursos/' é a raiz.

        assertEquals("['/usuário/rt/cursos/', 'cs016/', 'temas/', 'programas/', 'cs252/', 'projetos/', 'trabalhos/', 'demos/']", OperacoesEx1.obterNodosInternos(arvore));
        //b) Os nodos internos são os que contém os diretórios: '/usuário/rt/cursos/', 'cs016/', 'temas/', 'programas/', 'cs252/', 'projetos/', 'trabalhos/' e 'demos/'.

        filhos = raiz.getChildren();
        nodoCs016 = (INodoArvore<DiscNode>) filhos.first().element();

        assertEquals("'cs016/'", nodoCs016.element().toString());
        assertEquals(9, OperacoesEx1.contarDescendentes(arvore, nodoCs016));
        //c) O nodo 'cs016/' possui 9 descendentes.

        assertEquals(1, OperacoesEx3.depth(arvore, nodoCs016));
        //d) O nodo 'cs016/' possui 1 ancestral.

        filhosCs016 = nodoCs016.getChildren();
        NodoArvore<DiscNode> nodoTemas = (NodoArvore<DiscNode>) filhosCs016.prev(filhosCs016.last()).element();
        assertEquals("['notas', 'programas/']", OperacoesEx1.obterIrmaos(nodoTemas));
        //e) Os irmão do nodo 'temas/' são: 'notas' e 'programas/'

        nodoCs252 = (INodoArvore<DiscNode>)filhos.last().element();
        filhosCs252 = nodoCs252.getChildren();

        nodoProjetos = (INodoArvore<DiscNode>)filhosCs252.first().element();
        filhosProjetos = nodoProjetos.getChildren();

        assertEquals("['projetos/', 'trabalhos/', 'compre baixo/', 'venda alto/', 'demos/', 'mercado']", OperacoesEx1.obterNodosSubArvore(arvore, nodoProjetos));
        //f) O nodos que pertencem a subarvore são: 'projetos/', 'trabalhos/', 'compre baixo/', 'venda alto/', 'demos/' e 'mercado'.

        nodoTrabalhos = (INodoArvore<DiscNode>)filhosProjetos.first().element();

        assertEquals(3, OperacoesEx3.depth(arvore, nodoTrabalhos));
        //g) O nodo 'trabalhos/' possui 3 de profundidade.

        assertEquals(4, OperacoesEx3.height1(arvore));
        //h) A altura da árvore é 4.
    }
}
