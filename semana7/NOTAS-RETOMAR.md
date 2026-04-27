# Semana 7 — Notas para retomar el proyecto

Resumen del estado del trabajo (Gamer's Heaven + Spring + H2 / Oracle). Úsalo cuando vuelvas a continuar.

## Proyecto

- **Ruta del módulo Maven:** carpeta `semana7` donde está el `pom.xml`.
- **Spring Boot:** 4.0.6 · **Java:** 21.
- **Caso:** tienda "Gamer's Heaven" — API REST CRUD videojuegos.
- **Paquete base:** `com.duoc.semana7` (`videojuegos` → `entity`, `dto`, `repository`, `service`, `controller`; `exception` para validación).

## Endpoints

- `GET /api/videojuegos` — listar todos.
- `GET /api/videojuegos/{id}` — por ID.
- `GET /api/videojuegos/titulo/{titulo}` — por título (búsqueda parcial).
- `GET /api/videojuegos/plataforma/{plataforma}` — por plataforma.
- `POST /api/videojuegos`, `PUT /api/videojuegos/{id}`, `DELETE /api/videojuegos/{id}` — CRUD contra JPA.

## Datos y lógica

- **Persistencia:** lecturas y escrituras vía **`VideojuegoRepository`** (JPA). Con perfil **`h2`**, si la tabla está vacía, **`VideojuegoDataLoader`** inserta los 5 juegos de ejemplo.

## Perfiles Spring

- **`application.properties`:** nombre de la app, puerto 8080, `spring.profiles.default=h2`, opciones JPA comunes (`show-sql`, formato SQL).
- **`application-h2.properties`:** datasource H2 en memoria, consola `/h2-console`, `ddl-auto=create`, dialecto H2.
- **`application-oracle.properties`:** conexión Oracle Autonomous con wallet (alias tipo `miquintabd_high`, `TNS_ADMIN` hacia `Wallet_miQuintaBD`), usuario/contraseña por variables, `ddl-auto=none`, dialecto Oracle.

### Por qué hay tres archivos `properties` (decisión de diseño)

1. **Separar lo común de lo específico de cada entorno**  
   - `application.properties` concentra lo que **no cambia** entre H2 y Oracle (nombre de la app, puerto, logs JPA comunes) y define **qué perfil usar por defecto** (`h2` para desarrollo sin nube).  
   - Cada base de datos tiene **URLs, drivers y reglas distintas**; mezclarlo todo en un solo archivo obligaría a comentar o descomentar bloques a mano y es propenso a errores.

2. **Dos modos sin editar el código**  
   El profesor pidió poder **probar en local con H2** y **validar la conexión a Oracle Cloud** con el **mismo proyecto**. Con perfiles (`h2` y `oracle`) solo cambias cómo arrancas la app (`--spring.profiles.active=...`); no hace falta tocar el `pom` ni los `.java` para elegir base de datos.

3. **Buenas prácticas y seguridad**  
   Oracle exige **wallet**, **TLS**, credenciales y un dialecto distinto; H2 es en memoria y admite `ddl-auto=create` para practicar. Tener **`application-oracle.properties`** aparte mantiene la configuración de nube **aislada** y facilita usar **variables de entorno** para contraseñas sin mezclarlas con la config de H2.

4. **Convención de Spring Boot**  
   `application-{perfil}.properties` es el mecanismo estándar: Spring **fusiona** el archivo base con el del perfil activo. Así el proyecto queda **claro para revisión** (qué es local vs qué es nube) y alineado con lo visto en clase.

## Oracle Cloud

- **Wallet:** carpeta `Wallet_miQuintaBD` (en el workspace, junto al proyecto o donde la tengas); debe coincidir con `ORACLE_TNS_ADMIN` si no usas la ruta por defecto del `properties`.
- **Usuario:** puede ser `ADMIN` o un usuario de aplicación (ej. **`APP_GAMERS`** creado en SQL Developer). La **contraseña no debe subirse al repositorio**; usar variables de entorno.

### Arranque con Oracle (ejemplo PowerShell)

```powershell
$env:ORACLE_USER = "APP_GAMERS"
$env:ORACLE_PASSWORD = "tu_contraseña"
# Si hace falta:
# $env:ORACLE_TNS_ADMIN = "C:\ruta\completa\Wallet_miQuintaBD"

cd "C:\Users\lisbe\OneDrive\Escritorio\semana_7_backend\bi7_Exp3_S7_Lisbeth_Bilbao\semana7"
mvn spring-boot:run "-Dspring-boot.run.arguments=--spring.profiles.active=oracle"
```

### Arranque con H2 (desarrollo local habitual)

```powershell
cd "C:\Users\lisbe\OneDrive\Escritorio\semana_7_backend\bi7_Exp3_S7_Lisbeth_Bilbao\semana7"
mvn spring-boot:run
```

Probar en navegador: `http://127.0.0.1:8080/api/videojuegos`

## Consola H2

- URL: `http://127.0.0.1:8080/h2-console` (con la app en marcha y perfil `h2`).
- **JDBC URL** (debe coincidir con `application-h2.properties`):  
  `jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
- Usuario: `sa` · Contraseña: vacía.

## Qué podría seguir el trabajo

- Probar conexión Oracle con usuario `APP_GAMERS` y variables de entorno.
- Ajustar `application-oracle.properties` solo para valores no secretos (p. ej. usuario por defecto), sin commitear contraseñas.
- Requisitos del profesor: informe, capturas, defensa oral, etc.

---
*Archivo generado para no perder el contexto al retomar el proyecto.*
