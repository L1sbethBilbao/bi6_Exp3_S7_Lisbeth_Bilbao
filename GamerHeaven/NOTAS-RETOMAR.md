# Semana 7 â€” Notas para retomar el proyecto

Resumen del estado del trabajo (Gamer's Heaven + Spring + H2 / Oracle). Ãšsalo cuando vuelvas a continuar.

## Proyecto

- **Ruta del módulo Maven:** carpeta `GamerHeaven` donde está el `pom.xml`.
- **Spring Boot:** 4.0.6 Â· **Java:** 21.
- **Caso:** tienda "Gamer's Heaven" â€” API REST CRUD videojuegos.
- **Paquete base:** `com.duoc.GamerHeaven` (`videojuegos` â†’ `entity`, `dto`, `repository`, `service`, `controller`; `exception` para validaciÃ³n).

## Endpoints

- `GET /api/videojuegos` â€” listar todos.
- `GET /api/videojuegos/{id}` â€” por ID.
- `GET /api/videojuegos/titulo/{titulo}` â€” por tÃ­tulo (bÃºsqueda parcial).
- `GET /api/videojuegos/plataforma/{plataforma}` â€” por plataforma.
- `POST /api/videojuegos`, `PUT /api/videojuegos/{id}`, `DELETE /api/videojuegos/{id}` â€” CRUD contra JPA.

## Datos y lÃ³gica

- **Persistencia:** lecturas y escrituras vÃ­a **`VideojuegoRepository`** (JPA). Con perfil **`h2`**, si la tabla estÃ¡ vacÃ­a, **`VideojuegoDataLoader`** inserta los 5 juegos de ejemplo.

## Perfiles Spring

- **`application.properties`:** nombre de la app, puerto 8080, `spring.profiles.default=h2`, opciones JPA comunes (`show-sql`, formato SQL).
- **`application-h2.properties`:** datasource H2 en memoria, consola `/h2-console`, `ddl-auto=create`, dialecto H2.
- **`application-oracle.properties`:** conexiÃ³n Oracle Autonomous con wallet (alias tipo `miquintabd_high`, `TNS_ADMIN` hacia `Wallet_miQuintaBD`), usuario/contraseÃ±a por variables, `ddl-auto=none`, dialecto Oracle.

### Por quÃ© hay tres archivos `properties` (decisiÃ³n de diseÃ±o)

1. **Separar lo comÃºn de lo especÃ­fico de cada entorno**  
   - `application.properties` concentra lo que **no cambia** entre H2 y Oracle (nombre de la app, puerto, logs JPA comunes) y define **quÃ© perfil usar por defecto** (`h2` para desarrollo sin nube).  
   - Cada base de datos tiene **URLs, drivers y reglas distintas**; mezclarlo todo en un solo archivo obligarÃ­a a comentar o descomentar bloques a mano y es propenso a errores.

2. **Dos modos sin editar el cÃ³digo**  
   El profesor pidiÃ³ poder **probar en local con H2** y **validar la conexiÃ³n a Oracle Cloud** con el **mismo proyecto**. Con perfiles (`h2` y `oracle`) solo cambias cÃ³mo arrancas la app (`--spring.profiles.active=...`); no hace falta tocar el `pom` ni los `.java` para elegir base de datos.

3. **Buenas prÃ¡cticas y seguridad**  
   Oracle exige **wallet**, **TLS**, credenciales y un dialecto distinto; H2 es en memoria y admite `ddl-auto=create` para practicar. Tener **`application-oracle.properties`** aparte mantiene la configuraciÃ³n de nube **aislada** y facilita usar **variables de entorno** para contraseÃ±as sin mezclarlas con la config de H2.

4. **ConvenciÃ³n de Spring Boot**  
   `application-{perfil}.properties` es el mecanismo estÃ¡ndar: Spring **fusiona** el archivo base con el del perfil activo. AsÃ­ el proyecto queda **claro para revisiÃ³n** (quÃ© es local vs quÃ© es nube) y alineado con lo visto en clase.

## Oracle Cloud

- **Wallet:** carpeta `Wallet_miQuintaBD` (en el workspace, junto al proyecto o donde la tengas); debe coincidir con `ORACLE_TNS_ADMIN` si no usas la ruta por defecto del `properties`.
- **Usuario:** puede ser `ADMIN` o un usuario de aplicaciÃ³n (ej. **`APP_GAMERS`** creado en SQL Developer). La **contraseÃ±a no debe subirse al repositorio**; usar variables de entorno.

### Arranque con Oracle (ejemplo PowerShell)

```powershell
$env:ORACLE_USER = "APP_GAMERS"
$env:ORACLE_PASSWORD = "tu_contraseÃ±a"
# Si hace falta:
# $env:ORACLE_TNS_ADMIN = "C:\ruta\completa\Wallet_miQuintaBD"

cd "C:\Users\lisbe\OneDrive\Escritorio\semana_7_backend\bi6_Exp3_S7_Lisbeth_Bilbao\GamerHeaven"
mvn spring-boot:run "-Dspring-boot.run.arguments=--spring.profiles.active=oracle"
```

### Arranque con H2 (desarrollo local habitual)

```powershell
cd "C:\Users\lisbe\OneDrive\Escritorio\semana_7_backend\bi6_Exp3_S7_Lisbeth_Bilbao\GamerHeaven"
mvn spring-boot:run
```

Probar en navegador: `http://127.0.0.1:8080/api/videojuegos`

## Consola H2

- URL: `http://127.0.0.1:8080/h2-console` (con la app en marcha y perfil `h2`).
- **JDBC URL** (debe coincidir con `application-h2.properties`):  
  `jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
- Usuario: `sa` Â· ContraseÃ±a: vacÃ­a.

## QuÃ© podrÃ­a seguir el trabajo

- Probar conexiÃ³n Oracle con usuario `APP_GAMERS` y variables de entorno.
- Ajustar `application-oracle.properties` solo para valores no secretos (p. ej. usuario por defecto), sin commitear contraseÃ±as.
- Requisitos del profesor: informe, capturas, defensa oral, etc.

---
*Archivo generado para no perder el contexto al retomar el proyecto.*
