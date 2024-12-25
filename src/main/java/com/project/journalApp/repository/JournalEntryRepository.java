package com.project.journalApp.repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.journalApp.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId>{

}

// The @Document(collection = "journal_entries") annotation in the JournalEntry class already establishes the link between the class and the journal_entries collection
//  in MongoDB. Spring Data MongoDB uses this mapping internally when performing database operations through the repository.

// The repository (JournalEntryRepository) simply works with the JournalEntry class. It doesn't need to know or directly refer to the collection name. 
// Spring handles this under the hood