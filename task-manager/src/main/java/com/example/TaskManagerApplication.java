package com.example;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class TaskManagerApplication extends Application {
    // This class serves as the JAX-RS application configuration
    // All REST endpoints will be available under /api path
}