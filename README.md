# Server Manager

Gestionnaire de serveurs REST construit avec Spring Boot 3.5.7. Il expose des API simples pour créer et lister des serveurs persistés dans une base MySQL et sert de support aux TD de SOA.

## Stack & prérequis
- Java 17+
- Maven 3.9+
- MySQL 8 avec une base `soa`
- Port HTTP par défaut : `8080`

## Configuration
Les paramètres de connexion se trouvent dans `server-manager/src/main/resources/application.properties`. Adaptez-les à votre environnement (URL, utilisateur, mot de passe). Exemple rapide :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/soa
spring.datasource.username=root
spring.datasource.password=1234AichRoot
spring.jpa.hibernate.ddl-auto=update
```

## Lancer l'application
```bash
cd server-manager
mvn spring-boot:run
```
Une fois démarrée, l'API est disponible sur `http://localhost:8080`.

## API actuelle
| Méthode | Chemin | Description |
|---------|--------|-------------|
| `POST`  | `/api/servers` | Crée un serveur (`name`, `ipAddress`, `running`). |
| `GET`   | `/api/servers` | Liste tous les serveurs enregistrés. |

### Exemple de création
```bash
curl -X POST http://localhost:8080/api/servers \
     -H "Content-Type: application/json" \
     -d '{"name":"App 1","ipAddress":"192.168.0.10","running":false}'
```

## Structure du projet
```
SUPNUM_TD1_23021/
└── server-manager/
    ├── src/main/java/.../entity        # Entité JPA Server
    ├── src/main/java/.../service       # Interface + implémentation métier
    ├── src/main/java/.../contriller    # RestController
    └── src/main/resources              # Configuration Spring Boot
```

## Tests
```bash
cd server-manager
mvn test
```

## Améliorations possibles
- Exposer les opérations de renommage, start/stop et suppression déjà disponibles dans le service.
- Ajouter des validations (`Bean Validation`) sur les payloads.
- Sécuriser les endpoints (Spring Security, JWT, etc.).

