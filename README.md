# Exp3 — Semana 7 (PBY2201)

**Gamer’s Heaven** — mantenedor CRUD de videojuegos con Spring Boot, JPA, validación, Oracle (perfil `oracle`) y H2 (perfil `h2` por defecto).

Proyecto Maven **`GamerHeaven`** · paquete base **`com.duoc.GamerHeaven`** (como indica la actividad / Spring Initializr).

## Arranque

- **H2 (local):** desde la carpeta `GamerHeaven/`: `mvnw spring-boot:run`
- **Oracle Cloud:** definir `ORACLE_TNS_ADMIN`, `ORACLE_PASSWORD` (y opcionalmente `ORACLE_USER`) y ejecutar con `--spring.profiles.active=oracle`

## Evidencias

Incluir capturas según la guía (Initializr, `application.properties`, CRUD, prueba `http://127.0.0.1:8080/api/videojuegos`).
