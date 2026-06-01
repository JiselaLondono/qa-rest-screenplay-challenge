# QA REST Screenplay Challenge 🚀

Este es un proyecto de automatización de pruebas para el consumo de servicios REST (API de GoRest), diseñado bajo el patrón **Screenplay** utilizando **Serenity BDD**, **Java**, **JUnit 5**, y **Gradle**.

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

Una vez finalizada la ejecución de las pruebas, Serenity generará un reporte interactivo detallado en formato HTML. Puedes abrirlo desde tu navegador en la siguiente ruta del proyecto:

```
target/site/serenity/index.html
```