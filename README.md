# Pizzeria Management Web Application

## Overview
Web application developed to manage a pizzeria, providing a responsive
user interface for customers and protected administrative functionalities.
The project was developed and presented as part of a university exam.

## Architecture
The application follows the MVC (Model-View-Controller) pattern to ensure
a clear separation of concerns and maintainability.

## Tech Stack
- Backend: Java, Spring Boot
- Frontend: Angular, HTML, CSS, Bootstrap
- Database: H2
- Architecture: MVC
- Pattern: DAO
- Authentication: HttpSession
- External APIs integration
- Testing: JUnit 5, Mockito, Spring Test, MockMvc, JDBC integration testing

## External Services
-Email service for reservation notifications
-Calendar availability service
-External APIs integration(Unplash API, MailTrap API)

## Features

### Customer Features
- View menu
- Browse available products
- User-friendly responsive interface

### Administrator Features
- Secure authentication
- Menu management
- Reservation management
- Administrative dashboard

## Testing & Quality Assurance
- DAO layer integration tests using @JdbcTest
- Service layer unit tests with mocked dependencies
- Controller layer tests using MockMvc

## Software Engineering Focus
- MVC architecture
- DAO pattern for data access
- Separation of concerns
- Backend RESTful services
- Full-stack development workflow

## Project Context
University project developed for the Web Programming course. The repository also includes a PowerPoint presentation used during the exam.
