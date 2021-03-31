## Membros do Grupo   

| Nome                            | RA |
|--------------------------------|--------- |
| André Lucas Fabbris de Toledo   | 1902777 |
| Bruno Alkimim de Negreiros      | 1902646 |
| Lucas de Alencar Silva          | 1902989 |
| Mariana Rodrigues Zubi da Silva | 1904204 |
| Milena Bispo Gomes              | 1904052 |
    
# AC3 - Estrutura de Dados
1. As questões a seguir são relativas à árvore genérica abaixo.
    - a) Qual nodo é a raiz?<br>***R:** O nodo '/usuário/rt/cursos/' é a raiz.*
    - b) Quais são os nodos internos?<br>***R:** Os nodos internos são os que contém os diretórios: '/usuário/rt/cursos/', 'cs016/', 'temas/', 'programas/', 'cs252/', 'projetos/', 'trabalhos/' e 'demos/'.*
    - c) Quantos descendentes tem o nodo cs016/?<br>***R:** O nodo 'cs016/' possui 9 descendentes.*
    - d) Quantos ancestrais tem o nodo cs016/?<br>***R:** O nodo 'cs016/' possui 1 ancestral.*
    - e) Quais são os irmãos do nodo temas/?<br>***R:** Os irmão do nodo 'temas/' são: 'notas' e 'programas/'.*
    - f) Que nodos pertencem à subárvore com raiz no nodo projetos/?<br>***R:** O nodos que pertencem a subarvore são: 'projetos/', 'trabalhos/', 'compre baixo/', 'venda alto/', 'demos/' e 'mercado'.*
    - g) Qual é a profundidade do nodo trabalhos/?<br>***R:** O nodo 'trabalhos/' possui 3 de profundidade.*
    - h) Qual a altura da árvore?<br>***R:** A altura da árvore é 4.*
2. Implementação e teste da Árvore Genérica conforme os slides de 23 a 33.<br>***R:** /tests/ex2.test*
3. Incrementar a implementação e teste de LinkedTree&lt;E&gt; (Árvore Genérica) com os
seguintes métodos:
    - a) **parentheticRepresentation** conforme o algoritmo do slide 55.
    - b) Com base no algoritmo **posorder** (slide 57) crie o método **toStringPostorder**
(imprime os valores dos nodes visitados).
    - c) **diskSpace** conforme o algoritmo do slide 80.
    - d) **depth** conforme o algoritmo do slide 84. 
    - e) **height1** conforme o algoritmo do slide 90.
    - f) **height2** conforme o algoritmo do slide 103.  
***R:** /tests/ex3.test*
4. Implemente e teste o TAD Árvore Binária conforme slides de 15 a 26.<br>***R:** /tests/ex4.test*
5. Incremente a implementação e teste do TAD Árvore Binária com:
    - a) **buildExpression** conforme slide 30.
    - b) **binaryPreorder** conforme slide 31.
    - c) **binaryPostorder** conforme slide 32.
    - d) **evaluateExpression** conforme slide 34 a 42.
    - e) **inorder** conforme slide 43.
    - f) **makerBTSearch** e exiba o seu caminhamento inorder conforme slide 45.
    - g) Método que desenhe a árvore binária de expressão conforme slide 47.
    - h) **eulerTour** conforme slide 51.
    - i) **printExpression** conforme slide 53.
    - j) Método para contar os nodos esquerdos e externos de uma árvore binária.
    - k) Método para contar os nodos direitos e externos de uma árvore binária.
<br>***R:** /tests/ex5.test*
6. Desenhe uma árvore binária que represente a seguinte expressão aritmética:
“(((5+2)*(2-1))/((2+9)+(7-2)-1))*8)”.<br>***R:***<br><img src="https://github.com/AnBruLuMaMi/ed-ac3/blob/main/imgs/ex6.png" alt="drawing" width="600"/>
