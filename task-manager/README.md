# Task Manager - CRUD Application

A complete Task Manager application built with **Quarkus**, featuring REST APIs, H2 in-memory database, Hibernate + Panache, and a modern web interface.

## 🚀 Features

- **Complete CRUD Operations**: Create, Read, Update, Delete tasks
- **RESTful API**: Well-designed REST endpoints
- **Modern UI**: Beautiful, responsive web interface
- **In-Memory Database**: H2 database with Hibernate + Panache
- **Real-time Stats**: Task statistics and filtering
- **Dependency Injection**: Using Quarkus CDI
- **Comprehensive Testing**: JUnit 5 + REST Assured tests

## 🛠️ Technology Stack

- **Framework**: Quarkus 3.6.4
- **Database**: H2 (In-Memory)
- **ORM**: Hibernate with Panache
- **API**: JAX-RS (RESTEasy Reactive)
- **Frontend**: HTML5, CSS3, JavaScript (ES6+)
- **Testing**: JUnit 5, REST Assured
- **Build Tool**: Maven
- **Java Version**: 17

## 📁 Project Structure

```
task-manager/
├── .project                           # Eclipse project file
├── .classpath                         # Eclipse classpath
├── pom.xml                           # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── TaskManagerApplication.java    # Main application class
│   │   │       ├── entity/
│   │   │       │   └── Task.java                  # Task entity with Panache
│   │   │       └── resource/
│   │   │           └── TaskResource.java          # REST endpoints
│   │   └── resources/
│   │       ├── application.properties             # Quarkus configuration
│   │       ├── import.sql                        # Initial data
│   │       └── META-INF/resources/
│   │           └── index.html                    # Web interface
│   └── test/
│       └── java/
│           └── com/example/resource/
│               └── TaskResourceTest.java         # API tests
└── README.md                                     # This file
```

## 🔧 Setup & Installation

### Prerequisites
- Java 17 or higher
- Maven 3.8+
- Eclipse IDE (optional)

### Running the Application

1. **Clone/Navigate to the project directory**:
   ```bash
   cd task-manager
   ```

2. **Run in development mode**:
   ```bash
   ./mvnw quarkus:dev
   ```
   Or on Windows:
   ```bash
   mvnw.cmd quarkus:dev
   ```

3. **Access the application**:
   - Web Interface: http://localhost:8080
   - API Base URL: http://localhost:8080/api/tasks
   - Dev UI: http://localhost:8080/q/dev

### Building for Production

```bash
./mvnw clean package
java -jar target/quarkus-app/quarkus-run.jar
```

## 📚 API Documentation

### Base URL: `/api/tasks`

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/tasks` | Get all tasks | - | Array of tasks |
| GET | `/tasks/{id}` | Get task by ID | - | Task object |
| POST | `/tasks` | Create new task | Task JSON | Created task |
| PUT | `/tasks/{id}` | Update task | Task JSON | Updated task |
| DELETE | `/tasks/{id}` | Delete task | - | 204 No Content |
| GET | `/tasks/stats` | Get task statistics | - | Stats object |
| GET | `/tasks/completed` | Get completed tasks | - | Array of tasks |
| GET | `/tasks/pending` | Get pending tasks | - | Array of tasks |

### Task Object Structure

```json
{
  "id": 1,
  "title": "Learn Quarkus",
  "description": "Study Quarkus framework and build REST APIs",
  "completed": false,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

## 🧪 API Usage Examples

### 1. Create a New Task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Learn Quarkus",
    "description": "Study Quarkus framework",
    "completed": false
  }'
```

### 2. Get All Tasks
```bash
curl http://localhost:8080/api/tasks
```

### 3. Get Task by ID
```bash
curl http://localhost:8080/api/tasks/1
```

### 4. Update a Task
```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Learn Quarkus - Updated",
    "description": "Study Quarkus framework and build REST APIs",
    "completed": true
  }'
```

### 5. Delete a Task
```bash
curl -X DELETE http://localhost:8080/api/tasks/1
```

### 6. Get Task Statistics
```bash
curl http://localhost:8080/api/tasks/stats
```

Response:
```json
{
  "totalTasks": 5,
  "completedTasks": 2,
  "pendingTasks": 3
}
```

### 7. Get Completed Tasks Only
```bash
curl http://localhost:8080/api/tasks/completed
```

### 8. Get Pending Tasks Only
```bash
curl http://localhost:8080/api/tasks/pending
```

## 🎨 Web Interface Features

The web interface provides:

- **Dashboard**: Real-time statistics (Total, Completed, Pending tasks)
- **Task Creation**: Form to add new tasks with title, description, and status
- **Task List**: Display all tasks with filtering options
- **Task Management**: Edit, complete/uncomplete, and delete tasks
- **Responsive Design**: Works on desktop and mobile devices
- **Modern UI**: Beautiful gradient design with smooth animations

### Interface Screenshots

The web interface includes:
- Header with application title and description
- Statistics bar showing task counts
- Form section for creating/editing tasks
- Task list with filtering capabilities
- Individual task cards with action buttons

## 🧪 Testing

### Run Tests
```bash
./mvnw test
```

### Test Coverage
The project includes comprehensive tests for:
- All CRUD operations
- Error handling (404, 400 errors)
- Task statistics
- Task filtering
- Input validation

## 🔧 Eclipse Integration

The project is configured for Eclipse IDE:

1. **Import Project**:
   - File → Import → Maven → Existing Maven Projects
   - Browse to the `task-manager` directory
   - Click Finish

2. **Run Configuration**:
   - Right-click project → Run As → Maven build
   - Goals: `quarkus:dev`

3. **Debug Mode**:
   - Set breakpoints in your code
   - Run in debug mode for step-through debugging

## 📊 Database Configuration

The application uses H2 in-memory database:

- **Database URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (empty)
- **Console**: Available in dev mode at `/h2-console`

### Initial Data
The `import.sql` file contains sample tasks loaded on startup:
- Learn Quarkus (Pending)
- Setup H2 Database (Completed)
- Implement CRUD Operations (Pending)

## 🚀 Skills Demonstrated

This project showcases:

✅ **REST API Development**: Complete CRUD endpoints  
✅ **JSON Processing**: Request/Response handling  
✅ **JPA & Panache**: Database operations made simple  
✅ **Dependency Injection**: Quarkus CDI container  
✅ **Error Handling**: Proper HTTP status codes  
✅ **Testing**: Unit and integration tests  
✅ **Frontend Integration**: Modern web interface  
✅ **Database Design**: Entity relationships and constraints  

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📝 License

This project is created for educational purposes and is free to use and modify.

---

**Happy Coding! 🎉**