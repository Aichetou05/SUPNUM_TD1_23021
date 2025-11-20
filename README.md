# Server Manager

Ce projet illustre une mini‑application Spring Boot 3.5.7 destinée aux TD de SOA. Elle permet de gérer des serveurs (nom, IP, statut de fonctionnement) stockés dans une base MySQL. Les couches principales sont : entité JPA, repository, service métier et contrôleur REST.

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

## Fonctionnement interne
- **Entity `Server`** (`src/main/java/.../entity/Server.java`) : persiste `id`, `name`, `ipAddress`, `running`, `createdAt`, `updatedAt` avec des hooks `@PrePersist/@PreUpdate` pour gérer automatiquement les dates.
- **Repository `ServerRepository`** : hérite de `JpaRepository` et ajoute une recherche par nom.
- **Service `ServerService` + `ServerServiceImpl`** : encapsule toute la logique métier. Au-delà des endpoints déjà exposés, il fournit des méthodes pour renommer un serveur, vérifier son statut, le démarrer/arrêter ou le supprimer (avec blocage si le serveur est encore actif).
- **Exceptions dédiées** (`ServerNotFoundException`, `ServerDeletionException`) : améliorent les retours d’erreurs.
- **Controller `ServerController`** : mappe actuellement les opérations de création et de liste sur `/api/servers`.

## API actuelle
| Méthode | Chemin | Description |
|---------|--------|-------------|
| `POST`  | `/api/servers` | Crée un serveur (`name`, `ipAddress`, `running`). |
| `GET`   | `/api/servers` | Liste tous les serveurs enregistrés. |

### Opérations disponibles côté service (à exposer si besoin)
- `renameServer(id, newName)` : met à jour le nom d’un serveur.
- `getServerStatus(id)` : retourne un booléen indiquant si le serveur tourne.
- `startServer(id)` / `stopServer(id)` : changent l’état `running`.
- `deleteServer(id)` : supprime un serveur stoppé, sinon `ServerDeletionException`.

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
- Exposer les opérations de renommage, start/stop, statut et suppression déjà codées dans le service.
- Ajouter des validations (`Bean Validation`) pour garantir la présence d’un nom et d’une IP bien formée.
- Centraliser les gestionnaires d’exceptions (`@ControllerAdvice`) pour retourner des réponses JSON cohérentes.
- Sécuriser les endpoints (Spring Security, JWT, etc.).

