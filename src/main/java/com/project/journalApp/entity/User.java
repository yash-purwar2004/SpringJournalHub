package com.project.journalApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "users") // Maps this class to the MongoDB "users" collection
@Data
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates a constructor with all fields
public class User {

    @Id
    private ObjectId id; // MongoDB automatically generates an ID

    @Indexed(unique = true) // Unique index on the userName field
    private String userName;

    private String email;

    private boolean sentimentAnalysis;

    private String password;

    @DBRef // Reference to related journal entries in another collection
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles = new ArrayList<>(); // Initialize to avoid NullPointerException
}
