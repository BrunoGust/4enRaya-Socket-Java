import java.util.Scanner;

public class CuatroEnLinea {
    public static final int FILAS = 7;
    public static final int COLUMNAS = 7;
    public static final char VACIO = '-';
    public static final char JUGADOR1 = 'X';
    public static final char JUGADOR2 = 'O';
    public static char[][] tablero = new char[FILAS][COLUMNAS];
    //public static int [] jugadores;

    public static void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = VACIO;
            }
        }
    }

    public static void imprimirTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean turnoJugador(int columna, char jugador) {
        for (int i = FILAS - 1; i >= 0; i--) {
            if (tablero[i][columna] == VACIO) {
                tablero[i][columna] = jugador;
                return true;
            }
        }
        return false; // Columna llena
    }

    public static boolean verificarGanador(char jugador) {
        // Verificar filas
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j <= COLUMNAS - 4; j++) {
                if (tablero[i][j] == jugador && tablero[i][j + 1] == jugador &&
                        tablero[i][j + 2] == jugador && tablero[i][j + 3] == jugador) {
                    return true;
                }
            }
        }
        // Verificar columnas
        for (int i = 0; i <= FILAS - 4; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j] == jugador && tablero[i + 1][j] == jugador &&
                        tablero[i + 2][j] == jugador && tablero[i + 3][j] == jugador) {
                    return true;
                }
            }
        }
        // Verificar diagonales \
        for (int i = 0; i <= FILAS - 4; i++) {
            for (int j = 0; j <= COLUMNAS - 4; j++) {
                if (tablero[i][j] == jugador && tablero[i + 1][j + 1] == jugador &&
                        tablero[i + 2][j + 2] == jugador && tablero[i + 3][j + 3] == jugador) {
                    return true;
                }
            }
        }
        // Verificar diagonales /
        for (int i = 0; i <= FILAS - 4; i++) {
            for (int j = 3; j < COLUMNAS; j++) {
                if (tablero[i][j] == jugador && tablero[i + 1][j - 1] == jugador &&
                        tablero[i + 2][j - 2] == jugador && tablero[i + 3][j - 3] == jugador) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean tableroLleno() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j] == VACIO) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarTablero();
        imprimirTablero();

        boolean juegoTerminado = false;
        char jugadorActual = JUGADOR1;
        while (!juegoTerminado) {
            System.out.println("Turno del jugador " + jugadorActual);
            System.out.print("Elige una columna (0-6): ");
            int columna = scanner.nextInt();

            if (columna < 0 || columna >= COLUMNAS) {
                System.out.println("Columna inválida. Elige otra.");
                continue;
            }

            if (!turnoJugador(columna, jugadorActual)) {
                System.out.println("Columna llena. Elige otra.");
                continue;
            }

            imprimirTablero();

            if (verificarGanador(jugadorActual)) {
                System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
                juegoTerminado = true;
            } else if (tableroLleno()) {
                System.out.println("¡Empate!");
                juegoTerminado = true;
            }

            jugadorActual = (jugadorActual == JUGADOR1) ? JUGADOR2 : JUGADOR1;
        }

        scanner.close();
    }
}