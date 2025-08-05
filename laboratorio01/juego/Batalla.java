package laboratorio01.juego;

public class Batalla {
    // Retorna 1 si gana p1, 2 si gana p2, 0 empate
    public int enfrentar(Pokemon p1, Pokemon p2, boolean usaH1, boolean usaH2) {
        if (usaH1) {
            p1.activarHabilidad(p2);
        }
        if (usaH2) {
            p2.activarHabilidad(p1);
        }

        int ataque1 = p1.calcularAtaqueContra(p2);
        int ataque2 = p2.calcularAtaqueContra(p1);

        p1.actualizarEstado();
        p2.actualizarEstado();

        if (ataque1 > ataque2) return 1;
        if (ataque2 > ataque1) return 2;
        return 0;
    }
}
