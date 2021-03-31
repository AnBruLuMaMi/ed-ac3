package ex3.test;

import ex2.Arvore;
import ex2.ListaNodo;
import ex2.NodoArvore;
import ex2.interfaces.IArvore;
import ex2.interfaces.IListaNodo;
import ex2.interfaces.INodo;
import ex2.interfaces.INodoArvore;
import ex3.OperacoesEx3;
import ex3.DiscNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex3Test {
    private <T> NodoArvore<T>  criarFilho(NodoArvore<T> nodo, T elemento) {
        IListaNodo<INodo<T>> filhos;

        NodoArvore<T> aux;
        filhos = nodo.getChildren();

        aux = new NodoArvore<T>(elemento, nodo, new ListaNodo());

        filhos.addLast(aux);

        return aux;
    }

    private Arvore<DiscNode> criarArvoreDisco() {
        Arvore<DiscNode> arvore = new Arvore<>();
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
        criarFilho(y, new DiscNode("compre baixo", 26));
        criarFilho(y, new DiscNode("venda alto", 55));

        // Filhos de demos//
        criarFilho(w, new DiscNode("mercado", 4786));

        return arvore;
    }

    @Test
    void test() {
        INodoArvore<DiscNode> raiz, nodoCs016, nodoProgramas, nodoTemas, nodoPr1;
        IListaNodo<INodo<DiscNode>> filhos, filhosCs016, filhosProgramas;

        Arvore<DiscNode> arvoreDisco = criarArvoreDisco();

        raiz = arvoreDisco.root();

        filhos = raiz.getChildren();
        nodoCs016 = (INodoArvore<DiscNode>) filhos.first().element();

        filhosCs016 = nodoCs016.getChildren();
        nodoProgramas = (INodoArvore<DiscNode>) filhosCs016.last().element();
        nodoTemas = (INodoArvore<DiscNode>) filhosCs016.prev(filhosCs016.last()).element();

        filhosProgramas = nodoProgramas.getChildren();
        nodoPr1 = (INodoArvore<DiscNode>) filhosProgramas.first().element();

        String representacaoArvore = "'/usuário/rt/cursos/'(\n\t'cs016/'(\n\t\t'notas',\n\t\t'temas/'(\n\t\t\t'hw1',\n\t\t\t'hw2',\n\t\t\t'hw3'\n\t\t),\n\t\t'programas/'(\n\t\t\t'pr1',\n\t\t\t'pr2',\n\t\t\t'pr3'\n\t\t)\n\t),\n\t'cs252/'(\n\t\t'projetos/'(\n\t\t\t'trabalhos/'(\n\t\t\t\t'compre baixo',\n\t\t\t\t'venda alto'\n\t\t\t),\n\t\t\t'demos/'(\n\t\t\t\t'mercado'\n\t\t\t)\n\t\t),\n\t\t'notas'\n\t)\n)";
        assertEquals(representacaoArvore, OperacoesEx3.parentheticRepresentation(arvoreDisco, raiz));

        String postOrder = "['notas', 'hw1', 'hw2', 'hw3', 'temas/', 'pr1', 'pr2', 'pr3', 'programas/', 'cs016/', 'compre baixo', 'venda alto', 'trabalhos/', 'mercado', 'demos/', 'projetos/', 'notas', 'cs252/', '/usuário/rt/cursos/']";
        assertEquals(postOrder, OperacoesEx3.toStringPostOrder(arvoreDisco, raiz));

        assertEquals(5124, OperacoesEx3.diskSpace(arvoreDisco, raiz));
        assertEquals(249, OperacoesEx3.diskSpace(arvoreDisco, nodoCs016));
        assertEquals(229, OperacoesEx3.diskSpace(arvoreDisco, nodoProgramas));
        assertEquals(10, OperacoesEx3.diskSpace(arvoreDisco, nodoTemas));
        assertEquals(57, OperacoesEx3.diskSpace(arvoreDisco, nodoPr1));

        assertEquals(0, OperacoesEx3.depth(arvoreDisco, raiz));
        assertEquals(1, OperacoesEx3.depth(arvoreDisco, nodoCs016));
        assertEquals(2, OperacoesEx3.depth(arvoreDisco, nodoProgramas));
        assertEquals(2, OperacoesEx3.depth(arvoreDisco, nodoTemas));
        assertEquals(3, OperacoesEx3.depth(arvoreDisco, nodoPr1));

        assertEquals(4, OperacoesEx3.height1(arvoreDisco));
        assertEquals(4, OperacoesEx3.height2(arvoreDisco, raiz));
    }
}
