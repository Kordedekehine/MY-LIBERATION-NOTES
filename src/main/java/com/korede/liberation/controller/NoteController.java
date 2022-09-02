package com.korede.liberation.controller;

import com.korede.liberation.ApiRoutes;
import com.korede.liberation.dto.NoteFilterDateRequestDto;
import com.korede.liberation.dto.NoteFilterMoodRequestDto;
import com.korede.liberation.dto.NoteFilterTitleRequestDto;
import com.korede.liberation.dto.NoteRequestDto;
import com.korede.liberation.exception.GeneralServiceException;
import com.korede.liberation.model.Note;
import com.korede.liberation.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/api")
public class NoteController {
    private static Logger log = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    NoteService noteService;

    @PostMapping(value = ApiRoutes.SAVE_NOTES)
    public ResponseEntity<?> save(@ModelAttribute NoteRequestDto noteRequestDto) {
        log.info(noteRequestDto.toString());
        try {
            return new ResponseEntity<>(noteService.savenote(noteRequestDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(ApiRoutes.LIST_OF_NOTES)
    public ResponseEntity<?> listAll(@RequestParam(value = "page",defaultValue = "1") int page,
                                     @RequestParam(value = "size",defaultValue = "3") int size){
      try{
          return new ResponseEntity<>(noteService.listNotes(page, size),HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
      }
    }

   @PostMapping(ApiRoutes.SEARCH_WITH_TITLE)
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

    @PostMapping(ApiRoutes.SEARCH_WITH_MOOD)
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

   @PostMapping(ApiRoutes.SEARCH_WITH_DATE)
   public ResponseEntity<?> searchByDate(@RequestParam(value = "page",defaultValue = "1") int page,
                                         @RequestParam(value = "size",defaultValue = "3") int size,
                                         @RequestBody NoteFilterDateRequestDto noteFilterDateRequestDto){
        try{
            return new ResponseEntity<>(noteService.searchByDate(page,size,noteFilterDateRequestDto),
            HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
   }

   // @GetMapping("/order/{id}")
    //    public ResponseEntity<?> getOrderById(@PathVariable String id){
    //        try {
    //            OrderCreationResponse orderCreationResponse = userServices.getOrderById(id);
    //            return ResponseEntity.status(HttpStatus.OK).body(orderCreationResponse);
    //        }
    //        catch (BusinessLogicException exception){
    //            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    //        }
    //    }

//    @PostMapping
//    public ResponseEntity<?> getNotesByDate(@PathVariable LocalDateTime dateTime){
//        try {
//           return new ResponseEntity<>(noteService.getNoteByDate(dateTime),HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }

//   @PostMapping("/delete-notes")
//   public ResponseEntity<?> deleteNote(@PathVariable("id") Long id){
//        try {
//            return new ResponseEntity<>(noteService.deletenotes(id),HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//   }


}


