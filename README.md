# 🔗 Tshortly

Tshortly is a scalable backend platform for creating, managing, and tracking short URLs.  
It provides URL shortening, redirection, analytics, notifications, and event-driven processing using **Spring Boot** and **Apache Kafka**.

The system is designed to support high concurrency, extensibility, and reliable asynchronous workflows.

---

## ✨ Features

### Core Features
- Create short URLs from long URLs
- Redirect short URLs to original destinations
- Collision-safe short code generation
- URL format validation
- Thread-safe request handling

### Tracking & Analytics
- Track access count for each short URL
- Capture metadata such as timestamp of access
- Designed to support location, device, and referrer tracking *(in progress)*
- Analytics data available for dashboard visualization

### Notifications
- Event-driven notifications using Kafka
- Notifications on URL creation
- Notifications on URL access *(planned)*
- Expiry-based notifications *(planned)*
- Retry handling for failed notification delivery

### Dashboard Support
- APIs designed to power a dashboard UI
- View created URLs and their analytics
- Monitor access trends over time *(planned)*

### URL Lifecycle Management
- URL expiration support *(in progress)*
- Automatic handling of expired URLs
- Planned background cleanup jobs

---

## 🧭 High-Level Flow

1. Client sends a request to create a short URL
2. Service validates the URL and generates a unique short code
3. URL mapping is stored in the database
4. An event is published to Kafka for downstream processing
5. Short URL is returned to the client
6. When accessed, the short URL:
    - Redirects to the original URL
    - Triggers tracking and notification events

---

## 🏗️ Architecture

```
Client
  |
  v
Controller Layer
  |
  v
Service Layer
  |
  |-- URL Generation & Validation
  |-- Tracking & Analytics Logic
  |-- Notification Event Publishing
  |
  v
Repository Layer
  |
  v
Database
```

### Event Processing (Kafka)

- URL creation events are published to Kafka
- URL access events are published asynchronously
- Kafka consumers handle notifications and analytics updates
- Retry logic ensures resilience against transient failures

---

## 🛠️ Tech Stack

- **Java**
- **Spring Boot**
- **Apache Kafka**
- **Maven**
- **Relational Database** (configurable)
- **Java Concurrency Utilities**

---

## ⚙️ Configuration

Configuration is managed using `application.yml`.

Key configuration areas:
- Server and application ports
- Database connection properties
- Kafka bootstrap servers
- Producer and consumer configurations
- Retry attempts and retry intervals
- Topic names and consumer group IDs

---

## 🧪 Error Handling & Reliability

- Input validation for malformed URLs
- Graceful handling of duplicate or conflicting short codes
- Kafka consumer retry mechanism for transient errors
- Structured and centralized logging

---

## ▶️ Running the Application

### Prerequisites

- Java 17+
- Maven
- Apache Kafka
- A running relational database

### Steps

```bash
# Clone the repository
git clone https://github.com/your-username/tshortly.git

# Navigate to the project directory
cd tshortly

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

---

## 🔌 API Examples

### Create Trackable Short URL

```
POST /api/urls
```

**Request Body**
```json
{
  "longUrl": "https://example.com/some/very/long/url",
  "enableTracking": true,
  "enableNotifications": true
}
```

**Response**
```json
{
  "shortUrl": "https://tshort.ly/abc123"
}
```

---

### Redirect & Track

```
GET /{shortCode}
```

- Redirects to the original URL
- Updates access analytics
- Publishes access event to Kafka

---

## 🧩 Planned Enhancements

- Geo-location and device-based analytics
- Custom URL aliases
- Redis caching for faster redirects
- Rate limiting and abuse prevention
- Background schedulers for expired URLs
- Monitoring with Prometheus and Grafana
- Authentication and user-level dashboards

---

## 📄 License

MIT License