/**
 * Created by marco on 11/05/16.
 */
public class Nodo implements Comparable<Nodo>{
    private char key = '$';
    private int freq;
    private String bitString = "";
    private Nodo figlioDx = null;
    private Nodo figlioSx = null;
    private Nodo padre = null;

    public Nodo(char key, int freq){
        this.key = key;
        this.freq = freq;
    }

    public Nodo(Nodo figlioDx, Nodo figlioSx){
        addDx(figlioDx);
        addSx(figlioSx);
        this.freq = figlioDx.getFreq() + figlioSx.getFreq();
    }



    @Override
    public int compareTo(Nodo o) {
        if (this.getFreq() == o.getFreq()) return 0;
        else if (this.getFreq() < o.getFreq()) return -1;
        else return 1;
    }

    public int getFreq() {
        return freq;
    }

    public void addDx(Nodo figlioDx){
        this.figlioDx=figlioDx;
        this.figlioDx.padre = this;
    }

    public void addSx(Nodo figlioSx){
        this.figlioSx=figlioSx;
        this.figlioSx.padre=this;
    }

    public boolean hasLeft() {
        return figlioSx != null;
    }

    public boolean hasRight() {
        return figlioDx != null;
    }

    public Nodo getFiglioSx() {
        return figlioSx;
    }

    public Nodo getFiglioDx() {
        return figlioDx;
    }

    public void aggiornaEtichettaSx() {
        bitString = padre.bitString + "0";
    }

    public void aggiornaEtichettaDx() {
        bitString =padre.bitString + "1";
    }

    public String getBitString() {
        return bitString;
    }

    public Character getKey() {
        return key;
    }

    public void updateFreq() {
        freq++;
    }
}
