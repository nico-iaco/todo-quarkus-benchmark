package it.iacovelli.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
public final class TodoDto {

    private UUID id;

    private String message;

    private LocalDateTime publishedDate;

}
