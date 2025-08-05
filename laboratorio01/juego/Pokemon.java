package laboratorio01.juego;


public class Pokemon {
    private String nombre;
    private String tipo;
    private int ataque;
    private int defensa;
    private HabilidadEspecial habilidad;

    private int bonoAtaque = 0;
    private int rondasBono = 0;

    public Pokemon(String nombre, String tipo, int ataque, int defensa, HabilidadEspecial habilidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.habilidad = habilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public HabilidadEspecial getHabilidad() {
        return habilidad;
    }

    public boolean activarHabilidad(Pokemon enemigo) {
        if (habilidad.seActiva()) {
            aplicarEfectoHabilidad(enemigo);
            return true;
        }
        return false;
    }

    private void aplicarEfectoHabilidad(Pokemon enemigo) {
        switch (habilidad.getEfecto()) {
            case "ataque":
                bonoAtaque += habilidad.getValor();
                rondasBono = 2; // dura esta y la siguiente ronda
                break;
            case "defensa":
                defensa += habilidad.getValor();
                break;
            case "daño":
                enemigo.defensa = Math.max(0, enemigo.defensa - habilidad.getValor());
                break;
        }
    }

    public int calcularAtaqueContra(Pokemon enemigo) {
        int modTipo = calcularModificadorTipo(this.tipo, enemigo.tipo);
        int ataqueTotal = ataque + bonoAtaque + modTipo - enemigo.defensa;
        return Math.max(0, ataqueTotal);
    }

    public void actualizarEstado() {
        if (rondasBono > 0) {
            rondasBono--;
            if (rondasBono == 0) {
                bonoAtaque = 0;
            }
        }
    }

    private int calcularModificadorTipo(String atacante, String defensor) {
        if ((atacante.equals("Fuego") && defensor.equals("Planta")) ||
            (atacante.equals("Planta") && defensor.equals("Agua")) ||
            (atacante.equals("Agua") && defensor.equals("Fuego")) ||
            (atacante.equals("Eléctrico") && defensor.equals("Agua"))) {
            return 20;
        } else if ((atacante.equals("Fuego") && defensor.equals("Agua")) ||
                   (atacante.equals("Planta") && defensor.equals("Fuego")) ||
                   (atacante.equals("Agua") && defensor.equals("Planta")) ||
                   (atacante.equals("Agua") && defensor.equals("Eléctrico"))) {
            return -10;
        }
        return 0;
    }
}
