package Pojo;

public class Calificacion {
    private String carrera;
    private String semestre;
    private String alumno;
    private String profesor;
    private String materia;
    private String parcial;
    private double calificacion;
    public Calificacion(){
    }

    public Calificacion(String carrera, String semestre, String alumno, String profesor, String materia, String parcial, double calificacion) {
        this.carrera = carrera;
        this.semestre = semestre;
        this.alumno = alumno;
        this.profesor = profesor;
        this.materia = materia;
        this.parcial = parcial;
        this.calificacion = calificacion;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getAlumno() {
        return alumno;
    }

    public String getProfesor() {
        return profesor;
    }

    public String getMateria() {
        return materia;
    }

    public String getParcial() {
        return parcial;
    }

    public double getCalificacion() {
        return calificacion;
    }
}
