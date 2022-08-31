package com.korede.liberation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity lets the database handle the primary key gereration
    private Long id;

    @Column
    private String title;

    //create table note (id bigint not null auto_increment, day varchar(255), description varchar(255), image1 varchar(255), image2 varchar(255), image3 varchar(255), month varchar(255), mood varchar(255), title varchar(255), year varchar(255), primary key (id)) engine=InnoDB
    @NotBlank
    private String description;

    //don't wanna use enum --Note user can search with it
     //frontend restrict it to happy,sad,angry,calm or indefferent-with emojis
    @Column
    @NotBlank
    private String mood;

    //basically for perfect date search--//TODO PERFECT DATE INTEGRATION
    @Column
    @NotBlank
   private String day;

    @Column
    @NotBlank
    private String month;

    @Column
    @NotBlank
    private String year;
    //set the images nullable to true, there are some days we just want to capture anything
    //though the field is nullable by default
    @Column
    private String image1;

    @Column
    private String image2;
    @Column
    private String image3;


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
