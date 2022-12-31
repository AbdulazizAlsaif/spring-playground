package dev.certificate.playground.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;
    @Length(min = 2, max = 50)
    @NotBlank
    String name;

    @Length(min = 2,max = 4)
    @NotBlank
    String Year;
    @NotBlank
    @Length(min = 3,max = 255)
    String description;
    @NotBlank
    @Length(min = 2,max = 3)
    String rating;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    LocalDateTime modifiedAt;


    public static Movie update(Movie movie,Movie movieToBeUpdated){
        if(movie.getDescription()!=null)
            movieToBeUpdated.setDescription(movie.getDescription());
        if(movie.getName()!=null)
            movieToBeUpdated.setName(movie.getName());
        if (movie.getRating()!= null)
            movieToBeUpdated.setRating(movie.getRating());
        if(movie.getYear()!=null)
            movieToBeUpdated.setYear(movie.getYear());
        movieToBeUpdated.setModifiedAt(LocalDateTime.now());

        return movieToBeUpdated;
    }

}
