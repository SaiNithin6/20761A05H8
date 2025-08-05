package com.example.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskResourceTest {

    @Test
    @Order(1)
    public void testGetAllTasks() {
        given()
            .when().get("/api/tasks")
            .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    @Order(2)
    public void testCreateTask() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Test Task\", \"description\": \"Test Description\", \"completed\": false}")
            .when().post("/api/tasks")
            .then()
                .statusCode(201)
                .body("title", is("Test Task"))
                .body("description", is("Test Description"))
                .body("completed", is(false))
                .body("id", notNullValue());
    }

    @Test
    @Order(3)
    public void testCreateTaskWithoutTitle() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"description\": \"Test Description\", \"completed\": false}")
            .when().post("/api/tasks")
            .then()
                .statusCode(400)
                .body("error", containsString("required"));
    }

    @Test
    @Order(4)
    public void testGetTaskById() {
        // First create a task
        Integer taskId = given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Get Test Task\", \"description\": \"Test Description\"}")
            .when().post("/api/tasks")
            .then()
                .statusCode(201)
                .extract().path("id");

        // Then get it by ID
        given()
            .when().get("/api/tasks/" + taskId)
            .then()
                .statusCode(200)
                .body("id", is(taskId))
                .body("title", is("Get Test Task"));
    }

    @Test
    @Order(5)
    public void testGetNonExistentTask() {
        given()
            .when().get("/api/tasks/99999")
            .then()
                .statusCode(404)
                .body("error", containsString("not found"));
    }

    @Test
    @Order(6)
    public void testUpdateTask() {
        // First create a task
        Integer taskId = given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Update Test Task\", \"description\": \"Original Description\"}")
            .when().post("/api/tasks")
            .then()
                .statusCode(201)
                .extract().path("id");

        // Then update it
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Updated Task\", \"description\": \"Updated Description\", \"completed\": true}")
            .when().put("/api/tasks/" + taskId)
            .then()
                .statusCode(200)
                .body("title", is("Updated Task"))
                .body("description", is("Updated Description"))
                .body("completed", is(true));
    }

    @Test
    @Order(7)
    public void testUpdateNonExistentTask() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Updated Task\", \"completed\": true}")
            .when().put("/api/tasks/99999")
            .then()
                .statusCode(404)
                .body("error", containsString("not found"));
    }

    @Test
    @Order(8)
    public void testDeleteTask() {
        // First create a task
        Integer taskId = given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Delete Test Task\", \"description\": \"To be deleted\"}")
            .when().post("/api/tasks")
            .then()
                .statusCode(201)
                .extract().path("id");

        // Then delete it
        given()
            .when().delete("/api/tasks/" + taskId)
            .then()
                .statusCode(204);

        // Verify it's deleted
        given()
            .when().get("/api/tasks/" + taskId)
            .then()
                .statusCode(404);
    }

    @Test
    @Order(9)
    public void testDeleteNonExistentTask() {
        given()
            .when().delete("/api/tasks/99999")
            .then()
                .statusCode(404)
                .body("error", containsString("not found"));
    }

    @Test
    @Order(10)
    public void testGetTaskStats() {
        given()
            .when().get("/api/tasks/stats")
            .then()
                .statusCode(200)
                .body("totalTasks", greaterThanOrEqualTo(0))
                .body("completedTasks", greaterThanOrEqualTo(0))
                .body("pendingTasks", greaterThanOrEqualTo(0));
    }

    @Test
    @Order(11)
    public void testGetCompletedTasks() {
        given()
            .when().get("/api/tasks/completed")
            .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    @Order(12)
    public void testGetPendingTasks() {
        given()
            .when().get("/api/tasks/pending")
            .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }
}