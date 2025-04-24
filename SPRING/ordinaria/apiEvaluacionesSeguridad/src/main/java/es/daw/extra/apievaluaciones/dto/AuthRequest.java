package es.daw.extra.apievaluaciones.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
