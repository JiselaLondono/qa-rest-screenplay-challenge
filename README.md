# QA REST Screenplay Challenge 🚀

Este es un proyecto de automatización de pruebas para el consumo de servicios REST (API de GoRest), diseñado bajo el patrón **Screenplay** utilizando **Serenity BDD**, **Java**, **JUnit 5**, y **Gradle**. Con este proyecto se asume la participación del reto de automatización como propuesta de evaluación técnica realizada por el equipo Strange.

---

## 🛠️ Tecnologías y Librerías Utilizadas

- **Lenguaje**: Java
- **Framework de Automatización**: Serenity BDD + Screenplay
- **Gestión de Pruebas**: JUnit 5 (Jupyter)
- **Cliente HTTP**: RestAssured (integrado con Serenity Screenplay Rest)
- **Gestión de Proyectos**: Gradle
- **Generación de Datos de Prueba**: Net Datafaker
- **Formateador de Código**: Spotless (Google Java Format)
- **Utilidades**: Lombok

---

## 🏗️ Arquitectura del Proyecto (Patrón Screenplay)

El proyecto está estructurado siguiendo las mejores prácticas del patrón Screenplay:

- **Actors (Actores)**: Representan al usuario o entidad que interactúa con la aplicación. En este caso, el actor `Jisela` realiza las peticiones.
- **Abilities (Habilidades)**: Permiten al actor interactuar con el sistema (e.g., `CallAnApi`).
- **Tasks (Tareas)**: Representan acciones de alto nivel que el actor realiza en el sistema:
  - `CreateUser`: Envía una petición `POST` para registrar un usuario.
  - `GetUser`: Envía una petición `GET` para consultar un usuario por su `id`.
  - `UpdateUser`: Envía una petición `PUT` para actualizar los datos de un usuario.
  - `DeleteUser`: Envía una petición `DELETE` para eliminar un usuario del sistema.
- **Interactions (Interacciones)**: Representan las peticiones HTTP directas hacia los recursos (`Get`, `Post`, `Put`, `Delete`).
- **Questions (Preguntas)**: Consultan el estado del sistema o las respuestas recibidas para realizar aserciones (`TheResponseBodyUser`, `TheStatusCodeResponse`).
- **Models (Modelos)**: Representan los objetos de transferencia de datos (DTOs) tanto para solicitudes (`UserRequest`) como para respuestas (`UserResponse`).
- **Utils (Utilidades)**: Clases y enums de soporte:
  - `Resources`: Enum con los endpoints de la API.
  - `EnviromentConfig`: Administrador de lectura del archivo de configuración `serenity.conf`.

---

## ⚙️ Configuración del Entorno

El archivo de configuración principal se encuentra en `src/test/resources/serenity.conf`. Aquí se definen los parámetros de conexión y autenticación:

```hocon
restapi {
    baseurl = "https://gorest.co.in",
    token = "TU_TOKEN_DE_ACCESO"
}
```

> [!IMPORTANT]
> El token de autenticación se gestiona de forma segura a través de `EnviromentConfig` y se inyecta en la memoria del actor (`API_TOKEN`) mediante `actor.remember()`. Esto evita que los tokens queden quemados (*hardcoded*) en las tareas de consumo de la API.

---

## 🚀 Ejecución de las Pruebas

Para compilar el proyecto y ejecutar toda la suite de pruebas desde la terminal, utiliza el siguiente comando:

```bash
./gradlew clean test
```

### Formateo Automático de Código
El proyecto utiliza la herramienta **Spotless** para asegurar que el código cumpla con el estándar de formato (Google Java Format AOSP). Spotless se ejecutará automáticamente antes de compilar:

Si deseas formatear manualmente el código, puedes ejecutar:
```bash
./gradlew spotlessApply
```

---

## 📊 Reportes de Pruebas

+ Dado que SerenityBDD se caracteriza por generar reportes detallados que sirven como documentación viva, una vez que se ejecutan los tests, se generará un archivo html llamado index.html, el cual contiene el detalle de la ejecución. Este reporte se presenta en la ruta del proyecto `/target/site/serenity/index.html`. Hay que tener en cuenta que esta ruta se genera cuando se ha presentado la primera ejecución de los tests.
+ Por otro lado tenemos el Serenity Summary Report. Este es un informe de resumen html autónomo de una sola página llamado serenity-summary.html, que contiene una descripción general de los resultados de la prueba. Para generarlo, se debe ejecutar el comando `./gradlew reports`
+ Dentro de la ejecución de los tests de este proyecto, se presentaron resultados exitosos, los cuales se evidencian detalladamente en el reporte o summary de Serenity. Para visualizar esto dentro de este repositorio, por favor dirigirse a la ruta `src/test/resources/results_report`. Aquí se encontrarán 3 documentos de evidencia de la última ejecución realizada:
  
  - `last_run_report.pdf`: Screenshots del reporte de Serenity traídos desde el index.html.
  - `serenity_summary_report.pdf`: Informe de resumen con descripción general de los resultados de los tests, traído desde el serenity-summary.html.
  - `successful_test_execution.pdf`: Evidencia de los resultados de la ejecución en consola posteriores a ejecución de comando `./gradlew clean test`
