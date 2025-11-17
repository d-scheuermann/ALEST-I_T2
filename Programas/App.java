
public class App {
    public static void main(String[] args) {
        BinarySearchTreeOfInteger bs = new BinarySearchTreeOfInteger();
        Integer [] pontuacoesIniciais = {1500, 1200, 1800, 900, 2100, 750, 1300, 1700, 950, 2000};
        for(int i =0; i<pontuacoesIniciais.length ; i++)
        {
            bs.add(pontuacoesIniciais[i]);
            
        }
        rankMed(bs);
       
        
    }



    //GERENCIAMENTO BÁSICO DE PONTUAÇÕES
    public static void derrota(BinarySearchTreeOfInteger bs, Integer antigo, Integer novo)
    {
        if (bs == null||(antigo<novo))
        {
           throw new IllegalArgumentException("Valores irregulares"); 
        }
        bs.remove(antigo);
        bs.add(novo);
        System.out.println("DERROTA!");
        System.out.println("Rank: "+antigo+"--->"+novo);
        System.out.println("Rank atualizado");
    }
    public static void vitoria(BinarySearchTreeOfInteger bs, Integer antigo, Integer novo)
    {
        if (bs == null||(antigo>novo))
        {
           throw new IllegalArgumentException("Valores irregulares"); 
        }
        bs.remove(antigo);
        bs.add(novo);
        System.out.println("VITÓRIA!");
        System.out.println("Rank: "+antigo+"--->"+novo);
        System.out.println("Rank atualizado");
    }
    public static void contagem(BinarySearchTreeOfInteger bs)
    {
        System.out.println("Número de jogadores: "+bs.size());
    }



    //VISUALIZACOES DO RANKING
    public static void rankCrescente(BinarySearchTreeOfInteger bs)
    {  
        LinkedListOfInteger aux = new LinkedListOfInteger();
        aux = bs.positionsCentral();
        System.out.println("Rank em ordem crescente");

        int pos = aux.size();
        for(int i = 0; i<aux.size(); i++)
        {
            System.out.println(pos+"º: "+aux.get(i)+" |  ");
            pos--;
        }
    }
    public static void rankPre(BinarySearchTreeOfInteger bs)
    {
        LinkedListOfInteger aux = new LinkedListOfInteger();
        aux = bs.positionsPre();
        System.out.println("Rank em pré-ordem");

        int pos = aux.size();
        for(int i = 0; i<aux.size(); i++)
        {
            System.out.println(pos+"º: "+aux.get(i)+" |  ");
            pos--;
        }
    }
    public static void rankPos(BinarySearchTreeOfInteger bs)
    {
        LinkedListOfInteger aux = new LinkedListOfInteger();
        aux = bs.positionsPos();
        System.out.println("Rank em pós-ordem");

        int pos = aux.size();
        for(int i = 0; i<aux.size(); i++)
        {
            System.out.println(pos+"º: "+aux.get(i)+" |  ");
            pos--;
        }
    }
    public static void rankWidth(BinarySearchTreeOfInteger bs)
    {
        LinkedListOfInteger aux = new LinkedListOfInteger();
        aux = bs.positionsWidth();
        System.out.println("Rank por nível");

        int pos = aux.size();
        for(int i = 0; i<aux.size(); i++)
        {
            System.out.println(pos+"º: "+aux.get(i)+" |  ");
            pos--;
        }
    }



    //CONSULTAS RÁPIDAS
    public static void procuraRank(BinarySearchTreeOfInteger bs, Integer rank)
    {
        System.out.println("Busca pelo rank: "+rank);
        if (!bs.contains(rank))
        {
            System.out.println("Pontuação não encontrada");
        }
        else
        {
            System.out.println("Pontuação presente nos ranks");
        }
    }
    public static void menorRank(BinarySearchTreeOfInteger bs)
    {
        System.out.println(bs.getSmallest());
    }




    //ANÁLISES ESTRATÉGICAS
    public static void faixaSkill(BinarySearchTreeOfInteger bs)
    {
        LinkedListOfInteger aux = new LinkedListOfInteger();
        aux = bs.positionsCentral();
        System.out.println("Faixa de Skill");

        
        for(int i = 0; i<aux.size(); i++)
        {
            if(aux.get(i)<1000)
            {
                System.out.println(aux.get(i)+": Rank Iniciante");
            }
            if((aux.get(i)>=1000)&&(aux.get(i)<1500))
            {
                System.out.println(aux.get(i)+": Rank Intermediário");
            }
            if((aux.get(i)>=1500)&&(aux.get(i)<2000))
            {
                System.out.println(aux.get(i)+": Rank Avançado");
            }
            if(aux.get(i)>=2000)
            {
                System.out.println(aux.get(i)+": Rank Expert");
            }
            //System.out.println(aux.get(i));
            
        }
    }
    public static void rankRef(BinarySearchTreeOfInteger bs, Integer rank, int limite)
    {
        LinkedListOfInteger aux = new LinkedListOfInteger();
        aux = bs.positionsCentral();
        int dif; // diferença entre Integer rank e o Integer dentro do limite

        System.out.println("Pontuação referência: "+rank);
        System.out.println("Limite: "+limite);
        System.out.println("Saída: ");
        for(int i = 0; i<aux.size(); i++)//rank +||- limite
        {
            if ((aux.get(i) <= rank +limite) && (aux.get(i) >= rank -limite))
            {
                dif = Math.abs(rank - aux.get(i));
                System.out.println(aux.get(i)+"(diferença:"+dif+")");
            }
            
        }
    }
    public static void rankMed(BinarySearchTreeOfInteger bs)
    {
        LinkedListOfInteger aux = new LinkedListOfInteger();
        aux = bs.positionsCentral();
        int media = 0;
        for (int i = 0; i < aux.size(); i++)
        {
            media += aux.get(i);
        }
        media = media/aux.size();
        System.out.println("Média dos ranks: "+media);
    }
    
        
   
}
