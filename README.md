# SpringJournalHub

SpringJournalHub is a robust and scalable web application designed to manage journals with CRUD operations, leveraging the power of the Spring Framework. It incorporates modern technologies for an enhanced and secure user experience.

## Features

- **Database Management**: Journals and user data are stored in MongoDB, ensuring flexibility and high performance for document-based data.
- **Caching**: Redis Cloud is utilized for real-time data caching to optimize application performance.
- **Real-time Updates**: Integrated with Apache Kafka for real-time message streaming, enabling seamless synchronization and notifications.
- **RESTful APIs**: Built with Spring Boot to provide a standardized interface for interaction with the application.
- **Security**: Implements Spring Security and JWT for robust authentication and authorization mechanisms.
- **OAuth Integration**: Enables secure third-party login options for users.
- **Mail Service**: Sends automated email notifications for registration, password recovery, and updates.
- **API Documentation**: Features Swagger UI for easy exploration and testing of APIs.

## Prerequisites

Before running the project, ensure you have the following installed:

1. **Java 17** or higher
2. **Maven 3.8+**
3. **MongoDB**
4. **Redis**
5. **Apache Kafka**
6. **Postman** (optional, for testing APIs)
7. **An IDE** (e.g., IntelliJ IDEA or Eclipse)

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/your-repo/springjournalhub.git
cd springjournalhub
```

### Configure Environment Variables

Create a file named `.env` in the project root and configure the following variables:

```properties
# MongoDB Configuration
MONGO_URI=mongodb://localhost:27017/journals

# Redis Configuration
REDIS_HOST=localhost
REDIS_PORT=6379

# Kafka Configuration
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
KAFKA_TOPIC=journal-updates

# Security
JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION=86400000

# Mail Service
MAIL_HOST=smtp.example.com
MAIL_PORT=587
MAIL_USERNAME=your_email@example.com
MAIL_PASSWORD=your_email_password

# OAuth2 Configuration
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
```

### Build the Project

Run the following command to build the project:
```bash
mvn clean install
```

### Run the Application

You can start the application using:
```bash
mvn spring-boot:run
```

Alternatively, you can run the JAR file:
```bash
java -jar target/springjournalhub-0.0.1-SNAPSHOT.jar
```

### Access the Application

- **API Documentation**: Visit [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **MongoDB Database**: Ensure MongoDB is running on the configured URI.
- **Redis Cache**: Ensure Redis is active and accessible.
- **Kafka Broker**: Make sure Kafka is running and configured properly.

## Support Us!
If you find this project valuable, please give it a ⭐ on GitHub. Your support means the world to us and motivates further innovation!
