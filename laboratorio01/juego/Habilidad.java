package laboratorio01.juego;
public class Habilidad {
    
private String nombre;
private String efecto; 
private int valor;
private double probabilidad;




public void seActiva(double probabilidad) {
    if (probabilidad < 0.0 || probabilidad > 1.0) {
        this.probabilidad = 0.0; // valor por defecto
    } else {
        this.probabilidad = probabilidad;
    }
}
// getters
public String getEfecto() {
    return efecto;
}
public int getValor() {
    return valor;
}



}
