
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by marco on 11/05/16.
 */
public class Codifica {
    private PriorityQueue<Nodo> pq = new PriorityQueue<>(21);
    private Nodo root;
    private HashMap<Character,String> hm = new HashMap<>();
    private String input = "";
    private String output = "";

    public Codifica() throws IOException {
        createQueue();
        createTree();
        etichetta(root);
        generateOutput();
    }

    private void createQueue() throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        int c;
        Nodo n;
        System.out.println("Inserire stringa:");
        while ((c = is.read()) != -1 && c != '\n'){
            n = searchNode((char) c);
            input += (char)c;
            if(n != null) n.updateFreq();
            else pq.add(new Nodo((char)c,1));
        }
        is.close();
    }

    private Nodo searchNode(char c) {
        Iterator i = pq.iterator();
        Nodo n;
        while(i.hasNext()){
            n = (Nodo) i.next();
            if(n.getKey() == c) return n;
        }
        return null;
    }

    private void createTree() {
        while (pq.size()>1){
            root = new Nodo(pq.poll(),pq.poll());
            pq.add(root);
        }
    }

    private void etichetta(Nodo nodo){
        if(nodo.hasLeft()){
            nodo.getFiglioSx().aggiornaEtichettaSx();
            etichetta(nodo.getFiglioSx());
        }
        if(nodo.hasRight()){
            nodo.getFiglioDx().aggiornaEtichettaDx();
            etichetta(nodo.getFiglioDx());
        }
        if(nodo.getKey() != '$') hm.put(nodo.getKey(),nodo.getBitString());
        return;
    }

    private void generateOutput(){
        for (int i=0;i < input.length();i++){
            //System.err.println(hm.get(input.charAt(i)));
            output += hm.get(input.charAt(i));
        }
    }

    public void printCode() {
        Set keys = hm.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();)
        {
            char key = (char) i.next();
            String value = hm.get(key);
            System.out.println(key + " = " + value);
        }
    }

    public void printOutput(){
        System.out.println("Input: "+ input + "\n" + "Output: "+ output );
    }

    public static void main(String[] args) throws IOException {
        Codifica codifica = new Codifica();
        codifica.printOutput();
    }

    public Nodo getTree() {
        return root;
    }
}

