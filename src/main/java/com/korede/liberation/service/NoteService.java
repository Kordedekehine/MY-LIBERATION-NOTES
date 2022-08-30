package com.korede.liberation.service;

import com.korede.liberation.dto.*;
import com.korede.liberation.exception.GeneralServiceException;
import com.korede.liberation.exception.ImageUploadException;

public interface NoteService {
    NoteResponseDto savenote(NoteRequestDto noteRequestDto) throws GeneralServiceException, ImageUploadException;
     NoteListResponseDto listNotes(int page, int size) throws GeneralServiceException;
    NoteListResponseDto searchNoteByTitle(int page, int size, NoteFilterTitleRequestDto noteFilterTitleRequestDto) throws GeneralServiceException;

    NoteListResponseDto searchNoteByMood(int page,int size, NoteFilterMoodRequestDto noteFilterMoodRequestDto) throws GeneralServiceException;
    NoteListResponseDto searchNoteByDate(NoteFilterDateRequestDto noteFilterDateRequestDto) throws GeneralServiceException;

   // You cannot tear a page of a diary
   // NoteDeleteResponseDto deletenotes(Long id) throws  GeneralServiceException;
}
