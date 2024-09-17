package com.sammycode.movie_api_intigration.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Details {
    private String source;
    private Body[] body;

}