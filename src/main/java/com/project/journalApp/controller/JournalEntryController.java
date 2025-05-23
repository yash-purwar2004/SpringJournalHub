package com.project.journalApp.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.journalApp.entity.JournalEntry;
import com.project.journalApp.entity.User;
import com.project.journalApp.service.JournalEntryService;
import com.project.journalApp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// A special type of component which is used to handle our HTTP Requests

// We write special end points in this class as a method
// @Request Mapping annotation is used to add mapping in the whole class

@RestController
@RequestMapping("/journal")
@Tag(name = "Journal Entry APIs", description = "Create, Read, Update, and Delete journal entries")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all journal entries of the authenticated user")
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserName = authentication.getName(); // Extract authenticated username
        User user = userService.findByUserName(authenticatedUserName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all!= null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Find Journal Entry by Id
    @GetMapping("id/{myId}")
    @Operation(summary = "Get a journal entry by its id")
    public ResponseEntity<?> getJournalEntryById(@PathVariable String myId){
        ObjectId id = new ObjectId(myId);
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserName = authentication.getName();
        User user = userService.findByUserName(authenticatedUserName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
               }
        }
        
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // It's like saying, "Hey spring, please take the data from the request and turn it into a java object that I can use in my code"
    @PostMapping
    @Operation(summary = "Create a journal entry")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry){
        try{
            org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String authenticatedUserName = authentication.getName();
            journalEntryService.saveEntry(myEntry, authenticatedUserName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }

        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

    // // Delete the entry
    @DeleteMapping("id/{myId}")
    @Operation(summary = "Delete a journal entry by its id")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String myId){
        ObjectId id = new ObjectId(myId);
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserName = authentication.getName();
        journalEntryService.deleteJournalEntryById(id, authenticatedUserName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update the entry
    @PutMapping("id/{id}")
    @Operation(summary = "Update a journal entry by its id")
    public ResponseEntity<?> updateJournalById(@PathVariable String id, @RequestBody JournalEntry newEntry){
        ObjectId myId = new ObjectId(id);
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserName = authentication.getName();
        User user = userService.findByUserName(authenticatedUserName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                JournalEntry old = journalEntry.get();
                old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
                old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
