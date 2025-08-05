package laboratorio01.juego;

 public class HabilidadEspecial {
    private String nombre;
    private String efecto; // "ataque", "defensa", "daño"
    private int valor;
    private int probabilidad; // porcentaje (0-100)

    public HabilidadEspecial(String nombre, String efecto, int valor, int probabilidad) {
        this.nombre = nombre;
        this.efecto = efecto;
        this.valor = valor;
        this.probabilidad = probabilidad;
    }

    public boolean seActiva() {
        int dado = (int)(Math.random() * 100);
        return dado < probabilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEfecto() {
        return efecto;
    }

    public int getValor() {
        return valor;
    }
}
