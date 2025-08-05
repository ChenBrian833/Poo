package laboratorio01.juego;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear habilidades
        HabilidadEspecial llamaFinal = new HabilidadEspecial("Llama Final", "ataque", 15, 30);
        HabilidadEspecial escudoNatural = new HabilidadEspecial("Escudo Natural", "defensa", 20, 25);
        HabilidadEspecial impactoRelampago = new HabilidadEspecial("Impacto Relámpago", "daño", 10, 20);

        // Crear catálogo Pokémon
        List<Pokemon> catalogo = Arrays.asList(
            new Pokemon("Charmander", "Fuego", 40, 30, llamaFinal),
            new Pokemon("Bulbasaur", "Planta", 35, 35, escudoNatural),
            new Pokemon("Squirtle", "Agua", 30, 40, impactoRelampago),
            new Pokemon("Pikachu", "Eléctrico", 38, 28, impactoRelampago)
        );

        // Crear entrenadores
        Entrenador e1 = new Entrenador("Ash");
        Entrenador e2 = new Entrenador("Misty");

        elegirEquipo(sc, catalogo, e1);
        elegirEquipo(sc, catalogo, e2);

        Batalla batalla = new Batalla();
        int puntosE1 = 0, puntosE2 = 0;

        for (int ronda = 0; ronda < 4; ronda++) {
            Pokemon p1 = e1.getPokemonRonda(ronda);
            Pokemon p2 = e2.getPokemonRonda(ronda);

            System.out.println("Ronda " + (ronda + 1) + ": " + p1.getNombre() + " vs " + p2.getNombre());

            boolean usarH1 = preguntarSiUsaHabilidad(sc, e1, p1);
            boolean usarH2 = preguntarSiUsaHabilidad(sc, e2, p2);

            int resultado = batalla.enfrentar(p1, p2, usarH1, usarH2);

            if (resultado == 1) {
                puntosE1++;
                System.out.println("Ganador de la ronda: " + e1.getNombre());
            } else if (resultado == 2) {
                puntosE2++;
                System.out.println("Ganador de la ronda: " + e2.getNombre());
            } else {
                System.out.println("Empate en la ronda.");
            }
            System.out.println();
        }

        System.out.println("Resultado final:");
        System.out.println(e1.getNombre() + ": " + puntosE1 + " rondas ganadas.");
        System.out.println(e2.getNombre() + ": " + puntosE2 + " rondas ganadas.");

        if (puntosE1 > puntosE2) {
            System.out.println("Ganador del duelo: " + e1.getNombre());
        } else if (puntosE2 > puntosE1) {
            System.out.println("Ganador del duelo: " + e2.getNombre());
        } else {
            System.out.println("El duelo terminó en empate.");
        }

        sc.close();
    }

    private static void elegirEquipo(Scanner sc, List<Pokemon> catalogo, Entrenador entrenador) {
        System.out.println("Entrenador " + entrenador.getNombre() + ", elige 4 Pokémon:");

        int seleccionados = 0;
        while (seleccionados < 4) {
            System.out.println("Catálogo:");
            for (int i = 0; i < catalogo.size(); i++) {
                Pokemon p = catalogo.get(i);
                System.out.println((i + 1) + ". " + p.getNombre() + " - Tipo: " + p.getTipo());
            }

            System.out.print("Número de Pokémon #" + (seleccionados + 1) + ": ");
            String linea = sc.nextLine();
            int opcion;
            try {
                opcion = Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Número inválido, intenta de nuevo.");
                continue;
            }

            if (opcion < 1 || opcion > catalogo.size()) {
                System.out.println("Número fuera de rango.");
                continue;
            }

            Pokemon elegido = catalogo.get(opcion - 1);

            boolean agregado = entrenador.elegirPokemon(elegido);
            if (!agregado) {
                System.out.println("Ya elegiste ese Pokémon o el equipo está lleno. Escoge otro.");
                continue;
            }

            seleccionados++;
        }
    }

    private static boolean preguntarSiUsaHabilidad(Scanner sc, Entrenador entrenador, Pokemon pokemon) {
        while (true) {
            System.out.print(entrenador.getNombre() + ", ¿usar habilidad especial de " + pokemon.getHabilidad().getNombre() + "? (s/n): ");
            String resp = sc.nextLine().trim().toLowerCase();
            if (resp.equals("s")) return true;
            if (resp.equals("n")) return false;
            System.out.println("Respuesta inválida, escribe 's' o 'n'.");
        }
    }
}
