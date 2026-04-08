# 📚 Livre Service

Microservice de gestion des livres dans le cadre d'un système de bibliothèque.

## 🏗️ Architecture

Ce service fait partie d'un système de 3 microservices :
- **livre-service** (ce service) → port 8081
- **utilisateur-service** → port 8082
- **emprunt-service** → port 8083

## 🛠️ Technologies utilisées

- Java 17
- Spring Boot 3.2
- Spring Data JPA
- H2 Database
- Lombok
- Docker
- GitHub Actions CI/CD

## 📋 Endpoints

| Méthode | URL | Description |
|---|---|---|
| GET | /api/livres | Liste tous les livres |
| GET | /api/livres/{id} | Récupère un livre |
| POST | /api/livres | Crée un livre |
| PUT | /api/livres/{id} | Modifie un livre |
| DELETE | /api/livres/{id} | Supprime un livre |
| GET | /api/livres/{id}/disponibilite | Vérifie la disponibilité |

## 🚀 Lancer le service

### En local
```bash
mvn spring-boot:run
```

### Avec Docker
```bash
docker-compose up
```

## 🧪 Exemple de requête

### Créer un livre
```json
POST http://localhost:8081/api/livres
{
    "titre": "Clean Code",
    "auteur": "Robert Martin",
    "isbn": "978-0132350884",
    "nombreExemplaires": 3,
    "disponible": true
}
```

## 🐳 Image Docker

L'image est disponible sur DockerHub :
```bash
docker pull salioudieng/livre-service:latest
```

## ⚙️ CI/CD

Le pipeline GitHub Actions :
1. Build avec Maven
2. Création de l'image Docker
3. Push automatique sur DockerHub