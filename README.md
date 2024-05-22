# Redcorp-2024 Microservicios
Proyecto de Fundamentos de Arquitectura de Software orientado a microservicios.

Este proyecto está hecho en la versión `Java 17` como mínimo, usando SpringBoot, Maven y sus dependencias.
Se recomienda usar el IDE de `IntelliJ Idea`.

## Inicialización
Al clonar el repositorio hay que obtener todas las dependencias del proyecto.
Para este proyecto se está usando `MySQL` como motor de base de datos, usando como interfaz MYSQLWORKBENCH.

## Composición
Esta solución está conformada por cinco microservicios, siendo 2, core del negocio:
- `microservice-workAndProject` (Puerto 8090)
- `microservice-authAndProfile` (Puerto 9090)
- `microservice-config` (Puerto 8888)
- `microservice-eureka` (Puerto 8761)
- `microservice-gateway` (Puerto 8080)

## Ejecución
El orden de la ejecución es primero crear las bases de datos en MySQL, las cuales están en los `application.yml` de cada microservicio, en este caso son dos, los cuales son `redcorp_auth_profile_db` y `redcorp_work_project_db`.

Luego de esto, hay que ejecutar las aplicaciones de cada microservicio en el siguiente orden: `microservice-config` - `microservice-eureka` - `microservice-authAndProfile` o `microservice-workAndProject` y `microservice-gateway`
cabe recalcar, que existe un archivo llamado `import.sql` qe contiene valores que se insertarán a la base de datos cuando se empiece la ejecución.

## Endpoints

En el Proyecto existen dos controladores: `EmployeeController` y `SectionController`, hasta el momento solo se tienen estos endpoints: 

Para Employee Controller: 
- http://localhost:8090/api/redcorp/v1/employee (GET, POST)
- http://localhost:8090/api/redcorp/v1/employee/{employeeId} (GET, PUT)
- http://localhost:8090/api/redcorp/v1/employee/search-by-section/{sectionId} (GET)

Para Section Controller:
- http://localhost:9090/api/redcorp/v1/section (GET, POST)
- http://localhost:9090/api/redcorp/v1/section/{sectionId} (GET)
- http://localhost:9090/api/redcorp/v1/section/search-employee/{sectionId} (GET)
