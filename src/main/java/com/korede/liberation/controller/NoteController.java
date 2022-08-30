package com.korede.liberation.controller;

import com.korede.liberation.dto.NoteFilterDateRequestDto;
import com.korede.liberation.dto.NoteFilterMoodRequestDto;
import com.korede.liberation.dto.NoteFilterTitleRequestDto;
import com.korede.liberation.dto.NoteRequestDto;
import com.korede.liberation.exception.GeneralServiceException;
import com.korede.liberation.exception.ImageUploadException;
import com.korede.liberation.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    private static Logger log = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    NoteService noteService;

    @PostMapping("/savenote")
    public ResponseEntity<?> save(@ModelAttribute NoteRequestDto noteRequestDto) {
        log.info(noteRequestDto.toString());
        try {
            return new ResponseEntity<>(noteService.savenote(noteRequestDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listnotes")
    public ResponseEntity<?> listAll(@RequestParam(value = "page",defaultValue = "1") int page,
                                     @RequestParam(value = "size",defaultValue = "3") int size){
      try{
          return new ResponseEntity<>(noteService.listNotes(page, size),HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
      }
    }

   @PostMapping("/title-search")
    public ResponseEntity<?> searchByTitle(@RequestParam(value = "page",defaultValue = "1") int page,
                                           @RequestParam(value = "size",defaultValue = "3") int size,
                                           @RequestBody NoteFilterTitleRequestDto noteFilterTitleRequestDto){
        try {
            return new ResponseEntity<>(noteService.searchNoteByTitle(page,size,noteFilterTitleRequestDto),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
   }

    @PostMapping("/mood-search")
    public ResponseEntity<?> searchByTitle(@RequestParam(value = "page",defaultValue = "1") int page,
                                           @RequestParam(value = "size",defaultValue = "3") int size,
                                           @RequestBody NoteFilterMoodRequestDto noteFilterMoodRequestDto){
        try {
            return new ResponseEntity<>(noteService.searchNoteByMood(page,size,noteFilterMoodRequestDto),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

   @PostMapping("/date-search")
   public ResponseEntity<?> searchByDate(@RequestBody NoteFilterDateRequestDto noteFilterDateRequestDto){
        try {
            return new ResponseEntity<>(noteService.searchNoteByDate(noteFilterDateRequestDto),
                    HttpStatus.OK);
        } catch (Exception e) {
             return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
   }

//   @PostMapping("/delete-notes")
//   public ResponseEntity<?> deleteNote(@PathVariable("id") Long id){
//        try {
//            return new ResponseEntity<>(noteService.deletenotes(id),HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//   }


}


