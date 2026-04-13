# Sistema de Gestión de Pacientes - Clínica (Equipo 02)
Desarrolladores: Lenin Sanchez Gonzalez | Serrano Maldonado Sergio Alberto

Versión: 1 (Estable)

Este proyecto es una aplicación de escritorio desarrollada en JavaFX diseñada para la
administración eficiente de expedientes clínicos. El sistema permite gestionar el ciclo de 
vida completo de la información de un paciente bajo una arquitectura robusta de capas.

🚀 Funcionalidades Principales

El sistema ofrece una interfaz intuitiva para interactuar con la base de datos local (pacientes.csv):


/Panel de Visualización: Tabla dinámica que muestra CURP, Nombre, Edad, Teléfono, Alergias y Estatus (Activo/Inactivo).

/Gestión CRUD Completa:Registro: Validación en tiempo real de datos (CURP único, formato de teléfono y rangos de edad).

/Edición: Permite modificar datos existentes manteniendo la integridad del CURP.

/Eliminación: Borrado permanente de registros en la memoria a largo plazo (archivo físico).

/Control de Estatus: Alternancia rápida entre estados "Activo" e "Inactivo" para control administrativo.

/Estadísticas en Tiempo Real: Contadores dinámicos que muestran el total de pacientes, así como el desglose de activos e inactivos mediante el uso de Java Streams.


🛠️ Stack TecnológicoLenguaje: Java 25 (LTS).

-Framework UI: JavaFX con FXML para la separación de vista y lógica.

-Persistencia: Sistema de archivos plano (CSV) utilizando un delimitador personalizado (~) para evitar conflictos de lectura.

-Gestión de Dependencias: Maven. Arquitectura: Modelo-Vista-Controlador (MVC) con Capa de Servicio y Repositorio.

📂 Estructura del ProyectoModelo (Paciente.java): Utiliza StringProperty para implementar Data Binding, permitiendo que los cambios en los datos se reflejen automáticamente en la interfaz.

=Servicio (PacienteService.java): Contiene la lógica de negocio y las validaciones críticas (RegEx y unicidad de CURP).

=Repositorio (FileRepository.java): Gestiona la entrada/salida de archivos utilizando la librería moderna Java NIO.

=Controladores: Orquestan la interacción entre el usuario y la lógica del sistema. 

📋 Requisitos e InstalaciónJDK: Se requiere Java Development Kit 25 LTS instalado.

=IDE: Se recomienda IntelliJ IDEA para una mejor integración con JavaFX y Maven. 

=Dependencias: Al abrir el proyecto, permita que Maven descargue las dependencias necesarias.

⚙️ EjecuciónNavegue a la ruta: src/main/java/com/example/clinic.Localice el archivo Launcher.java.Haga clic en el botón de reproducción (Run) situado en la parte superior derecha de su IDE o junto a la declaración de la clase.

![img.png](materialextra/img.png)

le das al boton berde de play y se ejecuta.
