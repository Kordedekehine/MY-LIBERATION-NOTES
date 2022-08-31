package com.korede.liberation.service;

import com.korede.liberation.cloudinaryService.CloudStorageService;
import com.korede.liberation.dto.*;
import com.korede.liberation.exception.GeneralServiceException;
import com.korede.liberation.exception.ImageUploadException;
import com.korede.liberation.model.Note;
import com.korede.liberation.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
     NoteRepository noteRepository;

    @Autowired
    CloudStorageService cloudStorageService;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public NoteResponseDto savenote(NoteRequestDto noteRequestDto) throws GeneralServiceException, ImageUploadException {

       Note note = new Note();

       modelMapper.map(noteRequestDto,note);

       //set image
        note.setImage1(imageUrlFromCloudinary(noteRequestDto.getImage1()));
        note.setImage2(imageUrlFromCloudinary(noteRequestDto.getImage2()));
        note.setImage3(imageUrlFromCloudinary(noteRequestDto.getImage3()));

      noteRepository.save(note);
      NoteResponseDto noteResponseDto = new NoteResponseDto();
      modelMapper.map(note,noteResponseDto);
      return noteResponseDto;
    }

    @Override
    public NoteListResponseDto listNotes(int page, int size) throws GeneralServiceException {

        Pageable pageable= PageRequest.of((page-1),size);

        Page<Note> notes = noteRepository.findAll(pageable);

        int totalSizeOfNotesList= noteRepository.findAll().size();

        List<Note> noteList = notes.getContent();

        List<NoteResponseDto> noteResponseDtoList = new ArrayList<>();
        for (Note note: noteList){
            //loop through contents
            NoteResponseDto noteResponseDto = new NoteResponseDto();
             modelMapper.map(note,noteResponseDto);
             noteResponseDtoList.add(noteResponseDto);
        }

        NoteListResponseDto noteListResponseDto = new NoteListResponseDto();
        noteListResponseDto.setNoteResponseDtoList(noteResponseDtoList);
        noteListResponseDto.setSizeOfList(totalSizeOfNotesList);
        return noteListResponseDto;
    }

    @Override
    public NoteListResponseDto searchNoteByTitle(int page, int size, NoteFilterTitleRequestDto noteFilterTitleRequestDto) throws GeneralServiceException {

        Pageable pageable = PageRequest.of((page - 1),size);

        NoteListResponseDto noteListResponseDto = new NoteListResponseDto();

        Page<Note> notes = noteRepository.findAll(pageable);

        List<Note> noteList = notes.getContent();

        if (noteFilterTitleRequestDto.getTitle() != null){
          noteList =  noteList.stream().filter(note -> note.getTitle().
                  contains(noteFilterTitleRequestDto.getTitle())).collect(Collectors.toList());
        }
        int totalSizeOfList = noteList.size();

        List<NoteResponseDto> noteResponseDtoList = new ArrayList<>();
        for (Note note: noteList){
            NoteResponseDto noteResponseDto = new NoteResponseDto();
            modelMapper.map(note,noteResponseDto);
            noteResponseDtoList.add(noteResponseDto);
        }
        noteListResponseDto.setNoteResponseDtoList(noteResponseDtoList);
        noteListResponseDto.setSizeOfList(totalSizeOfList);
        return noteListResponseDto;
    }

    @Override
    public NoteListResponseDto searchNoteByMood(int page, int size, NoteFilterMoodRequestDto noteFilterMoodRequestDto) throws GeneralServiceException {
 //loop through amd search segments
        Pageable pageable = PageRequest.of((page - 1),size);

        NoteListResponseDto noteListResponseDto = new NoteListResponseDto();

        Page<Note> notes = noteRepository.findAll(pageable);

        List<Note> noteList = notes.getContent();

        if (noteFilterMoodRequestDto.getMood() != null){
            noteList = noteList.stream().filter(note -> note.getMood().contains(noteFilterMoodRequestDto
                    .getMood())).collect(Collectors.toList());
        }
        //map segments
        int totalSizeOfList = noteList.size();

        List<NoteResponseDto> noteResponseDtoList = new ArrayList<>();
        for (Note note: noteList){
            NoteResponseDto noteResponseDto = new NoteResponseDto();
            modelMapper.map(note,noteResponseDto);
            noteResponseDtoList.add(noteResponseDto);
        }
        noteListResponseDto.setNoteResponseDtoList(noteResponseDtoList);
        noteListResponseDto.setSizeOfList(totalSizeOfList);
        return noteListResponseDto;

    }

    @Override
    public NoteListResponseDto searchByDate(int page, int size, NoteFilterDateRequestDto noteFilterDateRequestDto) throws GeneralServiceException {

        Pageable pageable = PageRequest.of((page - 1),size);

        NoteListResponseDto noteListResponseDto = new NoteListResponseDto();

        Page<Note> notes = noteRepository.findAll(pageable);

        List<Note> noteList = notes.getContent();

        if (noteFilterDateRequestDto.getDay() != null){
            noteList = noteList.stream().filter(note -> note.getDay().contains(noteFilterDateRequestDto.getDay()
            )).collect(Collectors.toList());
        }
        if (noteFilterDateRequestDto.getMonth() != null){
            noteList = noteList.stream().filter(note -> note.getMonth().contains(noteFilterDateRequestDto.getMonth()
            )).collect(Collectors.toList());
        }
        if (noteFilterDateRequestDto.getYear() != null){
            noteList = noteList.stream().filter(note -> note.getYear().contains(noteFilterDateRequestDto.getYear()
            )).collect(Collectors.toList());
        }

        int totalSizeOfList = noteList.size();

        List<NoteResponseDto> noteResponseDtoList = new ArrayList<>();

        for (Note note: noteList){

            NoteResponseDto noteResponseDto = new NoteResponseDto();

            modelMapper.map(note,noteResponseDto);
            noteResponseDtoList.add(noteResponseDto);
        }
       noteListResponseDto.setNoteResponseDtoList(noteResponseDtoList);
        noteListResponseDto.setSizeOfList(totalSizeOfList);
        return noteListResponseDto;
    }


//    @Override
//    public NoteDeleteResponseDto deletenotes(Long id) throws GeneralServiceException {
//
//        Optional<Note> note = noteRepository.findById(id);
//
//        if (note.isEmpty()){
//            throw new GeneralServiceException("Note with such id does not exist");
//        }
//        noteRepository.deleteById(note.get().getId());
//        NoteDeleteResponseDto noteDeleteResponseDto = new NoteDeleteResponseDto();
//        modelMapper.map(note.get(),noteDeleteResponseDto);
//        return noteDeleteResponseDto;
//    }

    private String imageUrlFromCloudinary(MultipartFile image) throws ImageUploadException {
        String imageUrl="";
        if(image!=null && !image.isEmpty()){
            Map<Object,Object> params=new HashMap<>();
            params.put("public_id","Note/"+extractFileName(image.getName()));
            params.put("overwrite",true);

            try{
                Map<?,?> uploadResult = cloudStorageService.uploadImage(image,params);
                imageUrl= String.valueOf(uploadResult.get("url"));
            }catch (IOException e){
                e.printStackTrace();
                throw new ImageUploadException("Error uploading images,vehicle upload failed");
            }
        }
        return imageUrl;
    }

    private String extractFileName(String fileName){
        return fileName.split("\\.")[0];
    }

}
