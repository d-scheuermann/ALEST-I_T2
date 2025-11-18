

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

    /////////////////////////////////////
    // Implemente estes metodos!!!!!
    /// /////////////////////////////////
    private void positionsPosAux(Node n, LinkedListOfInteger res) {
        if(n!=null){
        positionsPosAux(n.left, res); //Visita a subárvore da esquerda
        positionsPosAux(n.right, res); //Visita a subárvore da direita
        res.add(n.element); //Visita o nodo
        }

    }

    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsCentralAux(root, res);
        return res;
    }

    /////////////////////////////////////
    // Implemente este metodo!!!!!
    /// /////////////////////////////////
    private void positionsCentralAux(Node n, LinkedListOfInteger res) {
        if (n!=null)
        {
        positionsCentralAux(n.left, res); //Visita a subárvore da esquerda
        res.add(n.element); //Visita o nodo
        positionsCentralAux(n.right, res); //Visita a subárvore da direita
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

    /////////////////////////////////////
    // Implemente estes metodos!!!!!
    /// /////////////////////////////////
    public Integer getRight(Integer element) {
        Node aux = searchNodeRef(element, root);
        if (aux == null) // se nao encontrou element
            return null;
        if(aux.right == null) // se nao tem filho a direita
            return null;
        return aux.right.element;
    }
    public Integer getLeft(Integer element) {
        Node aux = searchNodeRef(element, root);
        if (aux == null) // se nao encontrou element
            return null;
        if(aux.left == null) // se nao tem filho a esquerda
            return null;
        return aux.left.element;
    }
    public Integer getParent(Integer element) {
        Node aux = searchNodeRef(element, root);
        if (aux == null) // se nao encontrou element
            return null;
        if(aux.father == null) // se nao tem filho a direita
            return null;
        return aux.father.element;
    }
    public boolean contains(Integer element) {
        Node aux = searchNodeRef(element, root);
        return aux!=null;
    }
    public boolean isExternal(int element) {
        // verifica se a raiz é nula
        if (root == null) {
            return false;
        }
        // caminha até o nodo onde o elemento está
        Node aux = searchNodeRef(element, root);
        // caso não haja um nodo com esse elemento
        if (aux == null) {
            return false;
        }
        // verifica se o nodo encontrado não tem filhos - é uma folha
        if (aux.right == null && aux.left == null) {
            return true; // se sim, retorna true
        }
        return false; // se não, retorna true
    }
    public boolean isInternal(int element) {
        // verifica se a raiz é nula
        if (root == null) {
            return false;
        }
        // caminha até o nodo onde o elemento está
        Node aux = searchNodeRef(element, root);
        // caso não haja um nodo com esse elemento
        if (aux == null) {
            return false;
        }
        // verifica se o nodo encontrado tem filhos - não é uma folha
        if (aux.right != null || aux.left != null) {
            return true; // se sim, retorna true
        }
        return false; // se não, retorna true
    }
    public int level(Integer element) {//Corrigir
        if (!contains(element)){
            return -1;
        }
        else{
            Node aux = searchNodeRef(element, root);
            int level = 0;
            while(aux.father!=null )
            {
                aux = aux.father;
                level++;
            }
            return level;
        }
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

    public void addNoRec (Integer e) 
    {
        Node n = new Node (e);
        count ++;
        if (root == null)//Se não tem árvore, o node será a raíz
        {
            root = n;
            return;
        }
        Node aux = root;
        boolean achou = false;
        while(!achou)
        {
            if (e.compareTo(aux.element)>0)//elemento maior que o valor nodo
            {
                if (aux.right == null);//encontrei o nodo do pai
                {
                    aux.right = n;
                    n.father = aux;
                    achou = true;
                }
                aux = aux.right;
            }
            
            else//elemento menor que valor nodo 
            {
                if (aux.left == null)
                {
                    aux.left = n;
                    n.father = aux;
                    achou = true;
                }
                aux = aux.left;
            }
            
        }   
    }
    private Node searchNodeRefNoRec(Integer element, Node n) {//não recursivo

        if (element == null || n == null){//não tem nada para pesquisar
            return null;
        }
        Node aux = n;
        
        while(element.compareTo(aux.element)!=0)
        {
            if (element.compareTo(aux.element)>0)//enquanto é maior, caminha para a direita
            {
                aux = aux.right;
            }
            else//Caso contrário, é menor e caminha para a direita
            {
                aux = aux.left;
            }
            if (aux==null)//Não encontrou
            {
                return null;
            }
        }
        return aux;

        
    }
}
