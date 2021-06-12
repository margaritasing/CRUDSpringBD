# CRUDSpringBD

## Objetivos
1.- Crear un sistema de ABMC (CRUD) para nuestro refugio.

2.- Se pueden crear nuevos animales, modificar su nombre, 
edad o color y eliminarlos.

3.- Proveer un sistema con criterios de búsqueda (por nombre 
que contengan un valor, por color, por edad mayor o menor que y por id_animal).

## Interfaz del front
1.-Los colores se envían como String.

2.-El front necesita contar con una lista de posibles colores.

3.-El buscador del front no conoce todos los endpoints del back.

## Base de datos : Tabla animales.
Columnas: id_animal, nombre, edad, color.

1.-Entidad en Java : Animal. Mapear las columnas con atributos.
Crear bean. @Entity.

2.-Repository en Java : @Repository public interface AnimalRepo extends
JpaRepository<AnimalEntity, Integer>

3.-Servicio en Java: @Service AnimalService
Inyectamos el repo -> @Autowired de AnimalRepo

4.-Controller en Java: @Controller AnimalController
Inyectamos el service -> @Autowired de AnimalService

## Endpoints
### GET /animales
    Lista todos los animales    
### GET /animales/nombre/{nombre}
    Busca por nombres que contengan el valor    
### GET /animales/color/{color}
    Busca por color exacto    
### GET /animales/edad/{edadDesde}/{edadHasta}
    animales en un rango de edad (usar between en el repo)    
### GET /animales/id/{id}
    Busca un animal por ID    
### GET /animales/nuevo
    Muestra formulario para nuevo animal    
### POST /animales/guardar
    Guarda o actualiza un nuevo animal    
### DELETE /animales/id/{id}
    Elimina un animal por ID
    
Java backend 5 - ADA 2021
