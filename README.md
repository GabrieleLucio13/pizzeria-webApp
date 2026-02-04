# 🍕 Pizzeria Web Application

Applicazione web **full-stack** per la gestione completa di una pizzeria, che include **prenotazioni online**, **menu dinamico** e **pannello amministrativo**.  
Il progetto è strutturato come **monorepo** con backend Java/Spring Boot e frontend SPA in Angular.

L’obiettivo è fornire una soluzione moderna, scalabile e ben architettata, mettendo in pratica pattern consolidati, separazione delle responsabilità e testing automatizzato.

## 🚀 Funzionalità principali

### Area Pubblica (Clienti)
- Homepage dinamica con immagini caricate da API esterna (Unsplash)
- Visualizzazione del menu con filtraggio per categoria
- Sistema di prenotazione tavoli con validazione delle disponibilità
- Modifica e cancellazione prenotazioni tramite codice univoco
- Calendario interattivo delle aperture
- Invio automatico email di conferma prenotazione

### Area Amministrativa
- Autenticazione session-based
- Dashboard amministrativa server-side (Thymeleaf)
- CRUD completo del menu
- Gestione calendario aperture/chiusure
- Visualizzazione di tutte le prenotazioni effettuate

## 🧱 Architettura

- **Client–Server** con separazione netta frontend / backend
- **REST API** per la comunicazione con la SPA Angular
- **Pannello admin** server-rendered separato
- Architettura a layer:
  - Controller
  - Service (business logic)
  - DAO + Proxy (accesso ai dati)

### Pattern utilizzati
- MVC (Model-View-Controller)
- DAO (Data Access Object)
- Proxy Pattern
- Service Layer
- Dependency Injection
- Component-Based Architecture (frontend)

## 🛠️ Stack Tecnologico

### Backend
- Java 17
- Spring Boot 3
- Spring Data JPA (Hibernate)
- H2 Database
- Thymeleaf
- Maven
- Lombok
- JavaMailSender (Mailtrap)

### Frontend
- Angular 19
- TypeScript
- RxJS
- Bootstrap 5
- Font Awesome

### Testing
- JUnit 5
- Mockito
- MockMvc
- Jasmine
- Karma

## 🔗 REST API (principali endpoint)

| Metodo | Endpoint | Descrizione |
|------|--------|------------|
| GET | `/api/foods` | Recupera menu |
| GET | `/api/calendar` | Calendario disponibilità |
| POST | `/api/reservation` | Crea prenotazione |
| GET | `/api/reservation/{code}` | Recupera prenotazione |
| PUT | `/api/reservation/{code}` | Modifica prenotazione |
| DELETE | `/api/reservation/{code}` | Cancella prenotazione |

## 🧪 Testing

Il progetto include test automatici a più livelli:
- Unit test della business logic
- Integration test del layer DAO
- Test dei controller REST
- Test di componenti e servizi Angular

## 🎯 Obiettivi del progetto

- Applicare buone pratiche di **clean architecture**
- Dimostrare competenze **full-stack**
- Utilizzare pattern architetturali reali
- Implementare **testing strutturato**
- Integrare servizi esterni (email, immagini)

## 📌 Tecnologie & Keywords

Java, Spring Boot, Angular, TypeScript, REST API, JPA, Hibernate,  
H2 Database, Maven, JUnit, Mockito, Jasmine, Karma, Bootstrap,  
RxJS, MVC, DAO Pattern, Proxy Pattern, Git, Full-Stack Development
