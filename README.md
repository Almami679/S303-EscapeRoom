# S303-EscapeRoom

Descripción
-----------

El proyecto S303-EscapeRoom es una implementación de un juego de Escape Room basado en Java y MySQL. Los jugadores pueden unirse a diferentes juegos, y el sistema gestiona las relaciones entre los jugadores y los juegos en los que participan. Este sistema tiene como objetivo proporcionar una experiencia interactiva donde los jugadores pueden ser asignados a distintas habitaciones del Escape Room, llevando un seguimiento de su progreso.

Características Principales
---------------------------

Gestión de Juegos y Jugadores: Los jugadores pueden ser añadidos a diferentes juegos.
Relaciones entre Jugadores y Juegos: Se gestiona una relación de muchos a muchos entre jugadores y juegos a través de una tabla intermedia.

Base de Datos Relacional: Uso de MySQL para gestionar la persistencia de los datos.
Tecnologías Utilizadas

Java 11: Lenguaje de programación utilizado para la lógica del juego.

MySQL: Base de datos para almacenar información sobre los jugadores, los juegos y sus relaciones.

JDBC: Conexión entre Java y MySQL para la gestión de datos.

Log4j: Para la gestión de logs y errores en el sistema.

Estructura del Proyecto
-----------------------
src: Contiene el código fuente en Java.
Entities: Clases que representan las entidades del sistema (e.g., Player, Game).
Repository: Clases para interactuar con la base de datos.
Services: Servicios de lógica de negocio para manejar las operaciones del juego.
Controller: Toda la logica de negocio y menu.


─S303-EscapeRoom
    ├───.idea
    ├───schema
    ├───src
        ├───main
        │   ├───java
        │   │   └───org
        │   │       └───example
        │   │           ├───Exceptions
        │   │           ├───Main
        │   │           ├───MenuController
        │   │           ├───Modules
        │   │           │   ├───Communicates
        │   │           │   │   └───CommFactory
        │   │           │   └───Entities
        │   │           │       ├───CommunicatesEntities
        │   │           │       ├───EscapeRoomEntities
        │   │           │       ├───GameEntities
        │   │           │       └───RoomEntities
        │   │           ├───observers
        │   │           ├
        │   │           ├───Repository
        │   │           │   ├───Common
        │   │           │   ├───RepositoryRelations
        │   │           │   └───Serializers
        │   │           └───Services
        │   │               ├───CommunicatesServices
        │   │               ├───EscapeRoomServices
        │   │               └───GameServices
        │   └───resources
        └───test
              └───java
    
Patrones Utlizados:
------------------

Todos los communicates se generan desde un Factory usando un player como referencia e implementando una interfaz para el envio.

El EscapeRoom se genera a partir de un builder

Para connection y service queriamos implementar un singleton para no tener que ir creando muchas instancias, pero al final no resulto ser muy optimo.



Instalación
-----------
Clonar el repositorio:
git clone https://github.com/Almami679/S303-EscapeRoom.git

Configurar la base de datos:
Crea una base de datos en MySQL.

Ejecuta el archivo escaperroomV2.sql para insertar los datos iniciales en la base de datos.

Configuración en el código Java:
Modifica la clase dbConnection para que apunte a tu base de datos local.
Asegúrate de que las credenciales de conexión sean correctas.


Ejecutar el proyecto:
Importa el proyecto en tu IDE favorito (e.g., IntelliJ IDEA, Eclipse).
Compila y ejecuta el proyecto.


Dato de Uso y Ejecucion:
-----------------------
Hemos basado la estructura de Repository para que el CRUD pueda ser genérico mediante un enum de todos los atributos de la tabla y un StringBuilder para generar las QUERYS necesarias, así no sobrecargamos de código en exceso por cada clase Repositorio de cada entidad.

Se puede encontrar en el siguiente path:

S303-EscapeRoom\S303-EscapeRoom\src\main\java\org\example\Repository\Common\RepositoryImpl.java

Desde la capa main se llama al controller (lógica de menú), que este a su vez llama al servicio de cada clase para gestionar las funcionalidades. Cada servicio tiene una implementación de Repository para poder serializar y deserializar datos de la DataBase.

Es un proyecto en desarrollo, el cual nos ha llevado bastantes dolores de cabeza y que necesita aún mucha optimización.

Este proyecto ha sido desarrollado para la ItAcademy (Barcelona) para el curso de especialización de BackEnd (JAVA).

Developers: Inga Demetrashvili
			Marc 
			Albert Marín
			Pau Jané