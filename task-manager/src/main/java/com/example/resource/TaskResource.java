package com.example.resource;

import com.example.entity.Task;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    EntityManager entityManager;

    @GET
    public List<Task> getAllTasks() {
        return Task.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getTask(@PathParam("id") Long id) {
        Task task = Task.findById(id);
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Task not found with id: " + id + "\"}")
                    .build();
        }
        return Response.ok(task).build();
    }

    @POST
    @Transactional
    public Response createTask(Task task) {
        if (task.title == null || task.title.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Task title is required\"}")
                    .build();
        }
        
        // Ensure timestamps are set
        task.updateTimestamp();
        task.persist();
        
        return Response.status(Response.Status.CREATED)
                .entity(task)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateTask(@PathParam("id") Long id, Task updatedTask) {
        Task existingTask = Task.findById(id);
        if (existingTask == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Task not found with id: " + id + "\"}")
                    .build();
        }

        // Update fields
        if (updatedTask.title != null && !updatedTask.title.trim().isEmpty()) {
            existingTask.title = updatedTask.title;
        }
        if (updatedTask.description != null) {
            existingTask.description = updatedTask.description;
        }
        existingTask.completed = updatedTask.completed;
        existingTask.updateTimestamp();

        return Response.ok(existingTask).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteTask(@PathParam("id") Long id) {
        Task task = Task.findById(id);
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Task not found with id: " + id + "\"}")
                    .build();
        }

        task.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/stats")
    public Response getTaskStats() {
        long totalTasks = Task.count();
        long completedTasks = Task.countCompleted();
        long pendingTasks = Task.countPending();

        String stats = String.format(
            "{\"totalTasks\": %d, \"completedTasks\": %d, \"pendingTasks\": %d}",
            totalTasks, completedTasks, pendingTasks
        );

        return Response.ok(stats).build();
    }

    @GET
    @Path("/completed")
    public List<Task> getCompletedTasks() {
        return Task.find("completed", true).list();
    }

    @GET
    @Path("/pending")
    public List<Task> getPendingTasks() {
        return Task.find("completed", false).list();
    }
}