# Ejemplo Spring MVC + Thymeleaf
- Ejemplo de proyecto Spring MVC
- Ejemplo de uso del motor de plantillas Thymeleaf

## Previo
### Requisitos previos

* Servidor de BD MySQL
* Maven (versión > 3.5.x)
* (opcional) GIT
* (opcional) IDE Java (Eclipse, Netbeans, IntelliJ)

### Crear BD para los ejemplos  (si no se ha hecho antes)

* Crear BD "pruebas_si" en MySQL 

```
mysql -u root -p    [pedirá la contraseña de MySQL]

mysql> create database pruebas_si;
mysql> create user si@localhost identified by "si";
mysql> grant all privileges on pruebas_si.* to si@localhost;

```

Adicionalmente, puede ser necesario establecer un formato de fecha compatible
```
mysql> set @@global.time_zone = '+00:00';
mysql> set @@session.time_zone = '+00:00';
```

### Características del proyecto
```
Spring Boot version: 2.2.0
Type: Maven
Java version: 8
Packaging: Jar
```

### Ejecutar la aplicación Web

En Spring Tool Suite: Proyecto `[botón derecho] > Run As > Spring Boot App`

Desde línea de comandos:
```
mvn spring-boot:run
```

Estará accesible en la URL (http://localhost:8080/)
