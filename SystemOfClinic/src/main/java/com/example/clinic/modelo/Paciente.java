package com.example.clinic.modelo;

import javafx.beans.property.StringProperty;

public class Paciente {
    public String getEstatus() {
        return estatus.get();
    }

    public StringProperty estatusProperty() {
        return estatus;
    }

    public String getAlergias() {
        return alergias.get();
    }

    public StringProperty alergiasProperty() {
        return alergias;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public String getEdad() {
        return edad.get();
    }

    public StringProperty edadProperty() {
        return edad;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getCurp() {
        return curp.get();
    }

    public StringProperty curpProperty() {
        return curp;
    }

    private final StringProperty curp;
    private final StringProperty nombre;
    private final StringProperty edad;
    private final StringProperty telefono;
    private final StringProperty alergias;
    private final StringProperty estatus;

    public Paciente(StringProperty curp, StringProperty nombre, StringProperty edad, StringProperty telefono, StringProperty alergias, StringProperty estatus) {
        this.curp = curp;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.alergias = alergias;
        this.estatus  = estatus;
    }
}
