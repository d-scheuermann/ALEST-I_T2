package com.trabalho2;

public class BinarySearchTreeOfInteger {

    private static final class Node {

        public Node father;
        public Node left;
        public Node right;
        public Integer element;

        public Node(Integer element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
    }

    // Atributos        
    private int count; //contagem do número de nodos
    private Node root; //referência para o nodo raiz

    public BinarySearchTreeOfInteger() {
        count = 0;
        root = null;
    }

    public void clear() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public Integer getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public void add(Integer element) {
        root = add(root, element, null);
        count++;
    }

    private Node add(Node n, Integer e, Node father) {
        if (n == null) { // insere
            Node aux = new Node(e);
            aux.father = father;
            return aux;
        }
        // Senao insere na subarvore da esq ou da dir
        if (e.compareTo(n.element) > 0) { // elemento a ser inserido é maior que o valor do nodo
            n.right = add(n.right, e, n); // vai para a direita
        }
        else {                            // vai para a esquerda
            n.left = add(n.left, e, n);
        }
        return n;
    }

    public boolean remove(Integer element) {
        // Se arvore vazia
        if (element == null || root==null)
            return false;

        Node aux = searchNodeRef(element, root);
        if (aux == null) { // se nao encontrou element
            return false;
        }

        remove(aux);    // chama metodo que faz a remocao
        count--;        // atualiza o count
        return true;    // return true
    }

    private void remove(Node n) {
        Node father = n.father;
        if (n.left == null && n.right == null) { // se remocao de nodo folha
            if (root == n) { // se remocao da raiz
                root = null;
                return;
            }
            if (father.left == n)
                father.left = null;
            else
                father.right = null;
        }
        else if (n.left == null && n.right != null) { // tem apenas um filho a direita
            if (root == n) { // se remocao da raiz que tem só um filho a direita
                root = root.right;
                root.father = null;
                return;
            }
            if (father.left == n)
                father.left = n.right;
            else
                father.right = n.right;
            n.right.father = father;
        }
        else if (n.left != null && n.right == null) { // tem apenas um filho a esquerda
            if (root == n) { // se remocao da raiz que tem só um filho a esquerda
                root = root.left;
                root.father = null;
                return;
            }
            if (father.left == n)
                father.left = n.left;
            else
                father.right = n.left;
            n.left.father = father;
        }
        else { // tem dois filhos (esq,dir)
            // pega a refencia para o nodo que contem o menor elemento do lado direito
            Node menor = smallest(n.right);
            n.element = menor.element;
            remove(menor);
        }
    }

    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPreAux(root, res);
        return res;
    }

    private void positionsPreAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            res.add(n.element); //Visita o nodo
            positionsPreAux(n.left, res); //Visita a subárvore da esquerda
            positionsPreAux(n.right, res); //Visita a subárvore da direita
        }
    }

    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPosAux(root, res);
        return res;
    }

    private void positionsPosAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsPreAux(n.left, res); //Visita a subárvore da esquerda
            positionsPreAux(n.right, res); //Visita a subárvore da direita
            res.add(n.element); //Visita o nodo
        }
    }

    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsPreAux(n.left, res); //Visita a subárvore da esquerda
            res.add(n.element); //Visita o nodo
            positionsPreAux(n.right, res); //Visita a subárvore da direita
        }
    }

    public LinkedListOfInteger positionsWidth() {
        Queue<Node> fila = new Queue<>();
        Node atual = null;
        LinkedListOfInteger res = new LinkedListOfInteger();
        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                if (atual.left != null) {
                    fila.enqueue(atual.left);
                }
                if (atual.right != null) {
                    fila.enqueue(atual.right);
                }
                res.add(atual.element);
            }
        }
        return res;
    }

    private Node searchNodeRef(Integer element, Node n) {
        if (element == null || n == null)
            return null;

        int c = element.compareTo(n.element);
        if (c==0) // são iguais -> encontrou
            return n;
        if (c > 0) {    // elemento é maior que o valor do nodo
            return searchNodeRef(element, n.right);    // vai para a direita
        }
        else {          // elemento é menor que o valor do nodo
            return searchNodeRef(element, n.left);     // vai para a esquerda
        }
    }

    /**
     * Retorna o menor elemento da arvore.
     * @return o menor elemento
     */
    public Integer getSmallest() {
        Node n = smallest(root);
        if (n==null)
            return null;
        else
            return n.element;
    }
    private Node smallest(Node n) {
        if (n == null)
            return null;
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    public Integer getRight(Integer element) {
        Node node = searchNodeRef(element, root);
        if (node != null && node.right != null) {
            return node.right.element;
        }
        return null;
    }

    public Integer getLeft(Integer element) {
        Node node = searchNodeRef(element, root);
        if (node != null && node.left != null) {
            return node.left.element;
        }
        return null;
    }

    public Integer getParent(Integer element) {
        Node node = searchNodeRef(element, root);
        if (node != null && node.father != null) {
            return node.father.element;
        }
        return null;
    }

    public boolean contains(Integer element) {
        return searchNodeRef(element, root) != null;
    }

    public boolean isExternal(int element) {
        Node node = searchNodeRef(element, root);
        return node != null && node.left == null && node.right == null;
    }

    public boolean isInternal(int element) {
        Node node = searchNodeRef(element, root);
        return node != null && (node.left != null || node.right != null);
    }

    public int level(Integer element) {
        Node node = searchNodeRef(element, root);
        if (node == null) {
            return -1; // Não encontrou o nó
        }

        int level = 0;
        while (node.father != null) {
            level++;
            node = node.father;
        }
        return level;
    }

    private void GeraConexoesDOT(Node nodo) {
        if (nodo == null) {
            return;
        }

        GeraConexoesDOT(nodo.left);
        //   "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            System.out.println("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\" " + "\n");
        }

        GeraConexoesDOT(nodo.right);
        //   "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            System.out.println("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\" " + "\n");
        }
        //"[label = " << nodo->hDir << "]" <<endl;
    }

    private void GeraNodosDOT(Node nodo) {
        if (nodo == null) {
            return;
        }
        GeraNodosDOT(nodo.left);
        //node10[label = "<esq> | 10 | <dir> "];
        System.out.println("node" + nodo.element + "[label = \"<esq> | " + nodo.element + " | <dir> \"]" + "\n");
        GeraNodosDOT(nodo.right);
    }

    public void GeraConexoesDOT() {
        GeraConexoesDOT(root);
    }

    public void GeraNodosDOT() {
        GeraNodosDOT(root);
    }

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    //

    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n" + "\n");

        GeraNodosDOT();
        System.out.println("");
        GeraConexoesDOT(root);
        System.out.println("}" + "\n");
    }

    /*
     *
     *  BUSCA NODO E INSERÇÃO NÃO RECURSIVOS
     *
     */

    public void addNoRec (Integer e) {
        Node n = new Node (e);
        count++;
        if(root==null){
            root=n;
            return;
        }
        Node aux = root;
        boolean achou = false;
        while(!achou){
            if(e.compareTo(aux.element)>0){ //elemento maior que valor nodo
                if(aux.right==null){
                    aux.right = n;
                    n.father = aux;
                    achou = true;
                }
                aux = aux.right; //desce pela direita
            }
            else{
                if(aux.left == null){
                    aux.left = n;
                    n.father = aux;
                    achou = true;
                }
                aux = aux.left;
            }
        }
    }
    
    private Node searchNodeRefNoRec(Integer element, Node n) {

        if (element == null || n == null){
            return null;
        }

        Node aux = n;

        while(element.compareTo(aux.element)!=0){
            if (element.compareTo(aux.element)>0){
            aux = aux.right;
            }
            else{
                aux=aux.left;
            }
            if (aux==null){
                return null; //não encontrou
            }
        } //while
        return aux; //encontrou elemento no Nodo
    }
}
