package laboratorio01.juego;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Entrenador {
    private String nombre;
    private List<Pokemon> equipo = new ArrayList<>();
    private Set<Pokemon> pokemonsElegidos = new HashSet<>();

    public Entrenador(String nombre) {
        this.nombre = nombre;
    }

    public boolean elegirPokemon(Pokemon p) {
        if (pokemonsElegidos.contains(p) || equipo.size() >= 4) {
            return false;
        }
        equipo.add(p);
        pokemonsElegidos.add(p);
        return true;
    }

    public Pokemon getPokemonRonda(int ronda) {
        if (ronda >= 0 && ronda < equipo.size()) {
            return equipo.get(ronda);
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }
}
