# ecommerce-dashboard / product-api

Spring Boot 3.2.0 REST API (Java 21, Maven) for product / e-commerce analytics.
Persistence defaults to an in-memory H2 database, so no external services are required for local development.

## Cursor Cloud specific instructions

Environment provisioning (Java 21 toolchain + dependency download) is handled by the startup update script; the notes below are durable, non-obvious caveats for running and developing this service.

### Build / test / run
- Maven is **not** installed system-wide — always use the bundled wrapper `./mvnw`.
- `mvnw` is committed without the executable bit (git mode `100644`). Run `chmod +x mvnw` first, or invoke it as `sh ./mvnw`. Invoke it as `./mvnw` (not `bash mvnw`), otherwise the wrapper mis-resolves its own base directory and fails with `mvnw/.mvn/wrapper/...: Not a directory`.
- Build: `./mvnw -B clean package` · Tests: `./mvnw test` (there is currently **no** `src/test`, so the test phase runs zero tests) · Run (dev): `./mvnw spring-boot:run`.
- App runs on port `8080`. Swagger UI: `/swagger-ui.html`, OpenAPI: `/api-docs`, H2 console: `/h2-console` (JDBC URL `jdbc:h2:mem:analyticsdb`, user `sa`).
- `spring-boot-devtools` is on the classpath, so the dev server hot-reloads on recompile.

### Known preexisting issues (application code, NOT environment)
The committed application currently fails to finish Spring Boot startup because of source-code bugs (independent of environment setup). Fixing these is application work; the dev environment itself (JDK 21, Maven, dependency resolution, compile, package, embedded Tomcat + H2 + Hibernate) is fully functional. Known blockers:
- `ProductApiApplication` (the `@SpringBootApplication` class) lives in `com.example.productapi.controller`, so component scanning / entity / repository scanning only covers `controller`. Services, repositories and entities in sibling packages are never picked up. (Symptom: `No qualifying bean of type ...Service`.)
- `repository/UserTypeRepository` is a plain interface with no implementation bean, but the `demo` `CommandLineRunner` in `ProductApiApplication` injects it.
- `model/DecompositionComercial` imports `org.springframework.data.annotation.Id` instead of `jakarta.persistence.Id` (and has an unmapped `List<Detail>` field), so Hibernate rejects it as an entity with no identifier.

### Dependencies
- `pom.xml` pins Lombok `1.18.30`; Lombok `1.18.28` is incompatible with Java 21 and fails compilation with `JCTree$JCImport ... qualid`.
