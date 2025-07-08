# Business Trips Backend - Vollständige Dokumentation

## Projektübersicht
Das Business Trips Backend ist eine Spring Boot REST API Anwendung zur Verwaltung von Geschäftsreisen, Meetings, Mitarbeitern und Flügen. Die Anwendung verwendet eine 2-Tier Architektur mit JPA für die Datenpersistierung.

### Technologie-Stack
- **Backend**: Java 21, Spring Boot 3.2.6
- **Datenbank**: H2 (In-Memory)
- **Build-Tool**: Maven
- **ORM**: Spring Data JPA
- **Weitere**: Lombok, DevTools

## REST Endpoints
Alle API Endpunkte sind unter `/v1` verfügbar.

### BusinessTripController
- `GET /v1/trips` – Alle Geschäftsreisen
- `GET /v1/trips/{id}` – Reise per ID
- `POST /v1/trips` – Neue Reise anlegen
- `PUT /v1/trips/{id}` – Reise aktualisieren
- `DELETE /v1/trips/{id}` – Reise löschen
- `GET /v1/trips/search/{title}` – Reisen nach Titel suchen

### MeetingController
- `GET /v1/meetings` – Alle Meetings
- `GET /v1/meetings/{id}` – Meeting per ID
- `POST /v1/meetings` – Neues Meeting anlegen
- `PUT /v1/meetings/{id}` – Meeting aktualisieren
- `DELETE /v1/meetings/{id}` – Meeting löschen
- `GET /v1/meetings/search/{name}` – Meetings nach Name suchen
- `DELETE /v1/meetings` – Alle Meetings löschen

### EmployeeController
- `GET /v1/employees` – Alle Mitarbeiter
- `GET /v1/employees/{id}` – Mitarbeiter per ID
- `POST /v1/employees` – Mitarbeiter anlegen
- `PUT /v1/employees/{id}` – Mitarbeiter aktualisieren
- `DELETE /v1/employees/{id}` – Mitarbeiter löschen

### FlightController
- `GET /v1/flights` – Alle Flüge
- `GET /v1/flights/{id}` – Flug per ID
- `POST /v1/flights` – Flug anlegen
- `PUT /v1/flights/{id}` – Flug aktualisieren
- `DELETE /v1/flights/{id}` – Flug löschen

## Testdaten
Beim Start der Anwendung werden über einen `CommandLineRunner` Beispieldaten erzeugt:
- 13 BusinessTrips (z.B. BT01–BT13)
- 5 Meetings
- 3 Employees (Alice, Bob, Carol)
- 2 Flights

Die Initialisierung befindet sich in `BusinessTripsBackendApplication.java`.

## Postman Collection
Im Verzeichnis `postman-export` liegt die Collection **BusinessTrips API UPDATED.postman_collection.json**.
Die Collection ist in vier Ordner unterteilt:

1. **Meetings**
2. **Business Trips**
3. **Employee**
4. **Flight**

Jeder Ordner enthält Beispiel-Requests für die oben aufgeführten Endpoints.

## Projekt starten
1. Repository clonen und in das Verzeichnis wechseln
2. Abhängigkeiten bauen und Anwendung starten:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Die H2 Datenbank ist in-memory und benötigt keine weitere Konfiguration.
4. Optional kann das mitgelieferte Dockerfile verwendet werden:
   ```bash
   docker build -t biztrips .
   docker run -p 8080:8080 biztrips
   ```

Nach dem Start ist die API unter `http://localhost:8080/v1/...` erreichbar.
