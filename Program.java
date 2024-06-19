import java.util.InputMismatchException; // classe che fa parte del package java.util e serve per le eccezioni, nello specifico quando l'input dato è diverso da quello atteso
import java.util.Random;
import java.util.Scanner;

/**
 * Questa classe rappresenta il programma principale per il gioco Campo Minato.
 * Gli utenti possono giocare al campo minato inserendo il proprio nickname,
 * la grandezza del campo e il simbolo del campo.
 * Il gioco continua fino a quando l'utente non colpisce una mina o completa con successo il campo.
 * Viene utilizzato un oggetto CampoMinato per gestire il campo e le operazioni correlate.
 */
public class Program {
    
    /**
     * Il metodo principale del programma. Avvia il gioco Campo Minato e gestisce il flusso di gioco.
     * @param args Argomenti della riga di comando (non utilizzati in questo programma)
     */
    public static void main(String[] args) {
        boolean isGameOver = false; // Valore boolean per indicare se il gioco è finito
        int punteggio = 0; // Punteggio del giocatore
        Sound.play("background_Music.wav");

        Scanner scanner = new Scanner(System.in); // Oggetto Scanner per l'input dell'utente

        System.out.println("Inserisci il tuo nickname: ");
        String nomePlayer = scanner.nextLine(); // Nome del giocatore

        System.out.println("Inserisci la grandezza del campo Minato: ");
        int grandezzaCampo = 0; // Grandezza del campo minato
        try {
            grandezzaCampo = scanner.nextInt(); // Legge la grandezza del campo dall'utente
            scanner.nextLine(); // Consuma il carattere di nuova riga rimasto nel buffer di input
        } catch (InputMismatchException e) {
            System.out.println(CampoMinato.Color.RED + "Input non valido. Inserisci un numero intero per la grandezza del campo." + CampoMinato.Color.RESET);
            scanner.close();
            return;
        } 

        if (grandezzaCampo < 3 || grandezzaCampo > 50) {
            System.out.print(CampoMinato.Color.RED + "Valore non valido per costruire il campo, esso deve essere compreso tra 3 e 50 !" + CampoMinato.Color.RESET);
            scanner.close();
            return;
        }
        
        System.out.println("Inserisci il simbolo del campo (# oppure $): ");
        String simboloCampo = scanner.next(); // Simbolo del campo minato
        scanner.nextLine(); // Consuma il carattere di nuova riga rimasto nel buffer di input

        if (!simboloCampo.equals("#") && !simboloCampo.equals("$")) {
            System.out.println(CampoMinato.Color.RED + "Simbolo inserito non valido, riprovare !" + CampoMinato.Color.RESET);
            scanner.close();
            return;
        }

        CampoMinato campoMinato1 = new CampoMinato(grandezzaCampo, simboloCampo); // CampoMinato
        int celleTotali = grandezzaCampo * grandezzaCampo; // Numero totale di celle nel campo minato

        System.out.println("Sai come giocare? [S/N]: ");
        String regoleGioco = scanner.nextLine(); // Risposta alle regole del gioco

        if (regoleGioco.equals("N") || regoleGioco.equals("n")) { // se l'utente non ha capito
            campoMinato1.descrizione(); // Mostra le regole del gioco
            System.out.print(CampoMinato.Color.GREEN + "Continua? [Enter]: " + CampoMinato.Color.RESET);
            String conferma = scanner.nextLine(); // variabile che alla fine serve solo per far premere Enter all'utente
            System.out.println();
        } else if (regoleGioco.equals("S") || regoleGioco.equals("s")) {
            System.out.println(CampoMinato.Color.YELLOW + "Perfetto, buona fortuna!" + CampoMinato.Color.RESET);
            System.out.println();
        }

        Random random = new Random(); 
        if (nomePlayer.equals("developerMode") || nomePlayer.equals("mattia")) {
            campoMinato1.popolaCampoDeveloperMode(random);
        }

        else {
            campoMinato1.popolaCampo(random); 
        }

        try {
            while (!isGameOver) {
                if (celleTotali == grandezzaCampo) { // se sono rimaste solo celle con delle mine si ha vinto
                    System.out.println();
                    System.out.println(CampoMinato.Color.GREEN + "Hai vinto !" + CampoMinato.Color.RESET);
                    System.out.println("Giocatore: " + nomePlayer);
                    System.out.println("Punteggio: " + punteggio);
                    isGameOver = true;
                    System.out.println();
                    System.out.println("Campo finale: ");
                }

                campoMinato1.stampaCampo(); // Stampa il campo minato
                System.out.println();
                System.out.println("Inserisci la riga: ");
                int riga = scanner.nextInt(); // Input della riga

                System.out.println();
                System.out.println("Inserisci la colonna: ");
                int colonna = scanner.nextInt(); // Input della colonna

                try { // se riga e colonna non sono posizioni contenute nel campo
                    String generaEccezione = campoMinato1.campoMinato[riga][colonna];
                } catch (ArrayIndexOutOfBoundsException a) { // genera l'eccezione che avverte l'utente
                    System.out.println(CampoMinato.Color.RED + "Posizione cella non esistente all'interno del campo, riprovare !" + CampoMinato.Color.RESET);
                    System.out.println();
                }

                if (campoMinato1.controllaMine(riga, colonna)) { // se il metodo ritorna True ovvero che c'è una mina
                    campoMinato1.campoMinato[riga][colonna] = CampoMinato.Color.GREEN + "*" + CampoMinato.Color.RESET; 
                    campoMinato1.stampaCampo();
                    System.out.println(CampoMinato.Color.RED + "Game over !" + CampoMinato.Color.RESET);
                    System.out.println();
                    System.out.println("Giocatore: " + nomePlayer);
                    System.out.println("Punteggio: " + punteggio);
                    isGameOver = true; // il gioco finisce
                } else if (campoMinato1.campoMinato[riga][colonna].equals(" ")) {
                    System.out.println(CampoMinato.Color.RED + "Posizione già selezionata, passare alla successiva !" + CampoMinato.Color.RESET);
                    System.out.println();
                } else { // se non si becca la mina
                    punteggio++; // aumenta il punteggio
                    celleTotali--; 
                    campoMinato1.campoMinato[riga][colonna] = " "; // viene scoperta quella posizione
                    System.out.println(celleTotali);
                    System.out.println(grandezzaCampo);
                }
                
            }
        } catch (InputMismatchException e) { 
            System.out.println(CampoMinato.Color.RED + "Input non valido. Assicurati di inserire numeri interi per le righe e le colonne." + CampoMinato.Color.RESET);
        } finally {
            scanner.close();
        }
    }
}
