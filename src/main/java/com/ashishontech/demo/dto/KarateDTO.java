package com.ashishontech.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class KarateDTO {
    @NotBlank
    private String userName;
    @NotNull
    private String password;
    private LocalDateTime createdDateTime = LocalDateTime.now();
}
