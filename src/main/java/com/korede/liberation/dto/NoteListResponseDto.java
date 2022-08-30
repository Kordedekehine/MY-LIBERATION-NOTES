package com.korede.liberation.dto;

import java.util.ArrayList;
import java.util.List;

public class NoteListResponseDto {

    private int sizeOfList;
    private List<NoteResponseDto> noteResponseDtoList = new ArrayList<>();

    public int getSizeOfList() {
        return sizeOfList;
    }

    public void setSizeOfList(int sizeOfList) {
        this.sizeOfList = sizeOfList;
    }

    public List<NoteResponseDto> getNoteResponseDtoList() {
        return noteResponseDtoList;
    }

    public void setNoteResponseDtoList(List<NoteResponseDto> noteResponseDtoList) {
        this.noteResponseDtoList = noteResponseDtoList;
    }
}
