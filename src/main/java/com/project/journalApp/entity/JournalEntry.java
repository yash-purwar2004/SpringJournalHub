package com.project.journalApp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.project.journalApp.enums.Sentiment;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


// This is called POJO - Plain Old Java Object
@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id  // acts as a primary key
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;
}
