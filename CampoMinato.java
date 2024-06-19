import java.util.Random;
/**
 * Questa classe rappresenta il campo minato del gioco.
 * Viene utilizzata per gestire la logica del campo minato, inclusa la popolazione del campo con mine,
 * la stampa del campo, il controllo se una determinata posizione corrisponde a una mina e la descrizione del gioco.
 */
public class CampoMinato {

    String[][] campoMinato; // Matrice che rappresenta il campo minato
    int[][] mine; // Matrice che memorizza le posizioni delle mine
    int dimensioneCampo; // Dimensione del campo minato
    String simboloCampo; // Simbolo utilizzato per riempire il campo

    /**
     * Costruttore della classe CampoMinato.
     * @param dimensioneCampo La dimensione del campo minato.
     * @param simboloCampo Il simbolo utilizzato per riempire il campo minato.
     */
    public CampoMinato(int dimensioneCampo, String simboloCampo) {
        this.dimensioneCampo = dimensioneCampo;
        this.simboloCampo = simboloCampo;
        this.campoMinato = new String[dimensioneCampo][dimensioneCampo];
        this.mine = new int[dimensioneCampo][2]; // La seconda dimensione rappresenta le coordinate x e y di ogni fiore
    }

    /**
     * Popola il campo minato con il simbolo fornito dall'utente e posiziona le mine in posizioni casuali.
     * @param random Genera numeri casuali per posizionare le mine.
     */
    public void popolaCampo(Random random) {
        for (int i = 0; i < campoMinato.length; i++) {
            for (int j = 0; j < campoMinato[i].length; j++) {
                campoMinato[i][j] = simboloCampo; // Riempie il campo con il simbolo inserito dall'utente
            }
        }

        // Genera le posizioni casuali delle mine e le memorizza nella matrice mine
        for (int i = 0; i < mine.length; i++) {
            mine[i][0] = random.nextInt(dimensioneCampo); // Genera una posizione casuale per x
            mine[i][1] = random.nextInt(dimensioneCampo); // Genera una posizione casuale per y
        }
    }

    /**
     * Popola il campo minato in modalità sviluppatore mostrando le mine con il simbolo *.
     * @param random Genera numeri casuali per posizionare le mine.
     */
    public void popolaCampoDeveloperMode(Random random) {
        for (int i = 0; i < campoMinato.length; i++) {
            for (int j = 0; j < campoMinato[i].length; j++) {
                campoMinato[i][j] = simboloCampo; // Riempie il campo con il simbolo inserito dall'utente
            }
        }

        // Genera le posizioni casuali delle mine e le memorizza nella matrice mine
        for (int i = 0; i < mine.length; i++) {
            mine[i][0] = random.nextInt(dimensioneCampo); // Genera una posizione casuale per x
            mine[i][1] = random.nextInt(dimensioneCampo); // Genera una posizione casuale per y
        }

        // Mostra le posizioni delle mine con il simbolo *
        for (int[] mina : mine) {
            campoMinato[mina[0]][mina[1]] = "*"; // Mostra la mina nella posizione corrispondente
        }
    }

    /**
     * Stampa il campo minato.
     */
    public void stampaCampo() {
        for (int i = 0; i < campoMinato.length; i++) {
            for (int j = 0; j < campoMinato[i].length; j++) {
                System.out.print(campoMinato[i][j] + " "); // Stampa il campo
            }
            System.out.println(); // Vai a capo alla fine di ogni riga
        }
    }

    /**
     * Controlla se la posizione specificata corrisponde a una mina.
     * @param riga La riga della posizione da controllare.
     * @param colonna La colonna della posizione da controllare.
     * @return True se la posizione corrisponde a una mina, altrimenti false.
     */
    public boolean controllaMine(int riga, int colonna) {
        for (int[] mina : mine) {
            if (mina[0] == riga && mina[1] == colonna) {
                return true; // La posizione corrisponde a una mina
            }
        }
        return false; // La posizione non corrisponde a una mina
    }

    /**
     * Classe che definisce i colori del testo.
     * Fonte: internet
     */
    static class Color { // statica perchè utilizzata senza creare un'istanza della classe CampoMinato
        public static final String RESET = "\033[0m"; // Testo Default
        public static final String RED = "\033[0;31m"; // Testo Rosso
        public static final String GREEN = "\033[0;32m"; // Testo Verde
        public static final String YELLOW = "\033[0;33m"; // Testo Giallo
    }

    /**
     * Mostra una descrizione del gioco.
     */
    public void descrizione() {

        System.out.println();
        System.out.println("DESCRIZIONE GIOCO:");
        System.out.println();
        System.out.println(Color.RED + "Il gioco consiste nel trovare delle mine all'interno del campo,");
        System.out.println("dovrai inserire la grandezza di esso e ad ogni round ti verranno chieste");
        System.out.println("delle coordinate x e y. Dopo di che se le coordinate inserite da te corrisponderanno");
        System.out.println("alle coordinate di una delle mine all'interno del campo, il gioco finisce e verrai sconfitto!");
        System.out.println("Se invece riesci ad arrivare alla fine con tutte le mine al loro posto, vincerai la partita." + Color.RESET);
        System.out.println();

    }
}
