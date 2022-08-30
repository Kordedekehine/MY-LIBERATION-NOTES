package com.korede.liberation.dto;

import java.time.LocalDateTime;

public class NoteFilterDateRequestDto {

    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
