package dev.certificate.playground.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Cinema {

    @Id
    long id;
    String name;
    String address;
    String openFrom;
    String openTo;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}
