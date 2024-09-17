package com.sammycode.movie_api_intigration.model;

import jakarta.persistence.Entity;
import lombok.Data;
@Entity
@Data
public class Body {
    Body(){};
    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String rt_score;
    private String[] people;
    private String[] species;
    private String[] locations;
    private String[] vehicles;
    private String url;

}
