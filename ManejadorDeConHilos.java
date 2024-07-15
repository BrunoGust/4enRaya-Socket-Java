

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ManejadorDeConHilos implements Runnable{
    private Socket entrante;
    private int contador;
    //CuatroEnLinea game = new CuatroEnLinea();
    public ManejadorDeConHilos(Socket i,int c){
        entrante = i; contador = c;                
    }
    public void run(){
        try {
            try {
                InputStream secuenciaDeEntrada = entrante.getInputStream();
                OutputStream secuenciaDeSalida = entrante.getOutputStream();
                Scanner in = new Scanner(secuenciaDeEntrada);
                PrintWriter out = new PrintWriter(secuenciaDeSalida,true);
                Scanner scanner = new Scanner(System.in);
                int columna;
                out.println("Bienvenido al juego 4 en raya.");
                CuatroEnLinea.inicializarTablero();
                CuatroEnLinea.imprimirTablero();
                for(int i = 0; i < CuatroEnLinea.FILAS;i++){
                    for(int j = 0; j < CuatroEnLinea.COLUMNAS;j++){
                        out.print(CuatroEnLinea.tablero[i][j] + " ");
                        out.flush();
                    }
                    out.print("\n");
                }
                
                
                
                boolean juegoTerminado = false;
                char jugadorActual = CuatroEnLinea.JUGADOR1;
                
                while (!juegoTerminado) {
                    System.out.println("Turno del jugador " + jugadorActual+"....");
                    out.println("Turno del jugador " + jugadorActual + "....");

                    if(jugadorActual==CuatroEnLinea.JUGADOR2){
                        out.println("Elige una columna (0-6): ");
                        columna = in.nextInt();
                    }
                    else{
                        System.out.print("Elige una columna (0-6): ");
                        columna = scanner.nextInt();
                    }

                    if (columna < 0 || columna >= CuatroEnLinea.COLUMNAS) {
                        System.out.println("Columna inválida. Elige otra.");
                        continue;
                    }

                    if (!CuatroEnLinea.turnoJugador(columna, jugadorActual)) {
                        System.out.println("Columna llena. Elige otra.");
                        continue;
                    }

                    CuatroEnLinea.imprimirTablero();
                    for(int i = 0; i < CuatroEnLinea.FILAS;i++){
                        for(int j = 0; j < CuatroEnLinea.COLUMNAS;j++){
                            out.print(CuatroEnLinea.tablero[i][j] + " ");
                            out.flush();
                        }
                        out.print("\n");
                    }
                    

                    if (CuatroEnLinea.verificarGanador(jugadorActual)) {
                        System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
                        out.println("¡El jugador " + jugadorActual + " ha ganado!");
                        juegoTerminado = true;
                    } else if (CuatroEnLinea.tableroLleno()) {
                        System.out.println("¡Empate!");
                        out.println("¡Empate!");
                        juegoTerminado = true;
                    }

                    jugadorActual = (jugadorActual == CuatroEnLinea.JUGADOR1) ? CuatroEnLinea.JUGADOR2 : CuatroEnLinea.JUGADOR1;
                }
                in.close();
                scanner.close();
                }finally {
                    entrante.close();
                }     
            }catch(IOException e){
                e.printStackTrace();
            }
       
    }
}

