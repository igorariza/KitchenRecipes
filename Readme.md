## APP - Kotlin para Recetas de Cocina - Documentación de Decisiones de Diseño

<div align="center">
    <img src="https://github.com/igorariza/course_app/assets/18409088/9416b8bb-e53a-4b97-9105-4541f0beaf83" width="130px"</img>      
      &nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/igorariza/course_app/assets/18409088/c976a843-9a73-485c-963b-5ddfdbf53e3a" width="130px"</img>
       &nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/igorariza/course_app/assets/18409088/cc153114-fc89-4d28-ad33-cbb3e727bf97" width="130px"</img>
     &nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/igorariza/course_app/assets/18409088/0a0cfca5-595e-4b7f-a43d-cf9afd87da39" width="130px"</img>
     &nbsp;&nbsp;&nbsp;&nbsp;
</div>

### 1. **Arquitectura: Arquitectura de Componentes de Android (MVVM)**
   -  La arquitectura MVVM (Model-View-ViewModel) facilita la separación de preocupaciones y permite una estructura más escalable y mantenible. La capa ViewModel actúa como un intermediario entre la capa de presentación (View) y los datos (Model), facilitando la manipulación de datos y eventos.

### 2. **Manejo de Imágenes: Glide**
   -  Glide es una biblioteca eficiente para la carga y visualización de imágenes en Android. Facilita la carga asíncrona de imágenes, la memoria caché y la gestión de la memoria, lo que contribuye a una experiencia de usuario más fluida al mostrar las imágenes de las recetas.

### 3. **Diseño de la Interfaz de Usuario: Material Design Components**
   -  Material Design Components proporciona un conjunto coherente de pautas de diseño y componentes visuales que mejoran la apariencia y la usabilidad de la aplicación. Se utilizan componentes como AppBar, RecyclerView y CardView para ofrecer una experiencia de usuario moderna y consistente.

### 4. **API del Usuario: Externa**
   -  Dado que la aplicación no requiere un almacenamiento complejo de datos locales, Se utiliza API del usuario externa para demostrar la capacidad de la aplicación para interactuar con un servicio web externo. La API del usuario proporciona datos de recetas de cocina en formato JSON, que se analizan y muestran en la aplicación.
      Se adjunta coleccion de postman para probar la api externa.

      ```bash
         ./ChikenRecipes.postman_collection.json
      ```

### 5. **Manejo de Errores y Excepciones**
   -  Se implementa un mecanismo de manejo de errores centralizado para capturar y registrar cualquier error de red, proporcionando así una experiencia de usuario más robusta y comprensible.

### 6. **Testing: JUnit y Mockk**
   -  JUnit y Mockk se utilizan para escribir pruebas unitarias y de integración. Las pruebas unitarias garantizan la corrección de las funciones individuales, mientras que las pruebas de integración se centran en la interacción adecuada entre los componentes.

### 7. **Compatibilidad con Versiones: AndroidX**
   -  AndroidX reemplaza la antigua biblioteca de soporte de Android y proporciona una base más moderna y consistente para el desarrollo de aplicaciones Android en diferentes versiones del sistema operativo.

### 8. **Documentación del Código: KDoc**
   - **Justificación:** KDoc se utiliza para documentar el código de manera clara y concisa, facilitando la comprensión y colaboración entre desarrolladores.

## Comenzando 🚀

_Crea una carpeta nueva para el proyecto y Clona el repositorio_

```
git clone https://github.com/igorariza/KitchenRecipes.git
```

### Abrir el proyecto en Android Studio
   
   -  Abrir Android Studio
   -  Seleccionar la opción "Open an existing Android Studio project"
   -  Buscar la carpeta del proyecto y seleccionar "Ok"

### Actualizar el archivo local.properties
   
   -  Abrir el archivo local.properties
   -  Actualiza la ruta del SDK de Android por la ruta de tu equipo
   ```bash
         ./local.properties
   ```
   ```bash
      sdk.dir=/Users/user/Library/Android/sdk
   ```

### Ejecutar el proyecto en el emulador
   -  Seleccionar el emulador en el que se desea ejecutar el proyecto
   -  Seleccionar la opción "Run app" en Android Studio

### Descargar APP de la Play Store
   -  Abrir el siguiente enlace en el navegador del dispositivo móvil
   -  Seleccionar la opción "Descargar"
   -  Instalar la aplicación en el dispositivo móvil
   -  Abrir la aplicación

   ```bash
      https://play.google.com/store/apps/details?id=com.kitchen.recipeskotlin
   ```

## Construido con 🛠️

_Herramientas utilizadas para el proyecto_

* [Android Studio](https://developer.android.com/studio) - IDE
* [Kotlin](https://kotlinlang.org/) - Lenguaje de programación
* [Gradle](https://gradle.org/) - Manejador de dependencias


## Autor ✒️

* **Igor Ariza** - *Develop* - [igorariza](https://github.com/igorariza) - [LinkedIn](https://www.linkedin.com/in/igorariza/)


## Licencia 📄

Este proyecto está bajo la Licencia (MIT) - mira el archivo [LICENSE.md](LICENSE.md) para detalles

##  🎁
   - Cuida el agua
   - Siembra tu comida
   -Habla con las maquinas
