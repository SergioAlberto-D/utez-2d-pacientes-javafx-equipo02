package com.example.clinic.servicio;

import com.example.clinic.modelo.Paciente;
import com.example.clinic.repositorio.FileRepository;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    private FileRepository repo = new FileRepository();

    public List<Paciente> loadList() throws IOException {
        List<String> lines = repo.readAllLines();
        List<Paciente> result = new ArrayList<>();
        for (String line : lines) {
            if (line == null || line.isBlank()) continue;
            String[] partes = line.split("~", -1);
            Paciente p = new Paciente(
                    new SimpleStringProperty(partes[0].trim()),
                    new SimpleStringProperty(partes[1].trim()),
                    new SimpleStringProperty(partes[2].trim()),
                    new SimpleStringProperty(partes[3].trim()),
                    new SimpleStringProperty(partes[4].trim()),
                    new SimpleStringProperty(partes[5].trim())
            );
            result.add(p);
        }
        return result;
    }
    public void addPaciente(String curp,String name,String edad,String telefono,String alergias,List<Paciente> lista) throws IOException {
        validar(curp, name, edad, telefono, lista,null);
        String alergiasv = (alergias == null || alergias.isBlank()) ? "Ninguna" : alergias.trim();
        repo.appendNewLine(curp+"~"+name+"~"+edad+"~"+telefono+"~"+alergiasv+"~"+"Activo");
    }
    public void validar(String curp, String name, String edad, String telefono, List<Paciente> lista, Paciente actual) {
        if (name == null || name.isBlank() || name.length() < 3) {
            throw new IllegalArgumentException("El nombre no cumple con los estándares (mínimo 3 caracteres)");
        }

        if (telefono == null || !telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("El teléfono debe tener 10 dígitos numéricos");
        }

        try {
            if (edad == null || edad.isBlank()) {
                throw new IllegalArgumentException("La edad no puede estar vacía");
            }
            int numerico = Integer.parseInt(edad.trim());

            if (numerico <= 0 || numerico >= 120) {
                throw new IllegalArgumentException("La persona debe tener una edad creíble (1-119)");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La edad debe ser un número válido");
        }

        if (curp == null || curp.isBlank()) {
            throw new IllegalArgumentException("El CURP es obligatorio");
        }

        for (Paciente p : lista) {

            if (p != actual && p.getCurp().equalsIgnoreCase(curp.trim())) {
                throw new IllegalArgumentException("¡Error! El CURP '" + curp + "' ya pertenece a otro paciente.");
            }
        }
    }
    public void guardarCambios(List<Paciente> lista) throws IOException {
        for (Paciente p : lista) {
            validar(p.getCurp(), p.getNombre(), p.getEdad(), p.getTelefono(), lista, p);
        }
        List<String> lineas = new ArrayList<>();
        for (Paciente p : lista) {
            String linea = String.join("~",
                    p.getCurp(), p.getNombre(), p.getEdad(),
                    p.getTelefono(), p.getAlergias(), p.getEstatus());
            lineas.add(linea);
        }
        repo.overwriteFile(lineas);
    }
}
