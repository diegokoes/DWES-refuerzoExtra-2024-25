package es.daw.extra.apievaluaciones.controller;

import es.daw.extra.apievaluaciones.dto.CursoDTO;
import es.daw.extra.apievaluaciones.entities.Curso;
import es.daw.extra.apievaluaciones.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    // PENDIENTE USAR UN SERVICIO QUE TRABAJE CON EL REPOSITORIO

    private final CursoRepository cursoRepository;

    @GetMapping
//    public ResponseEntity<List<Curso>> getCursos(){
//        List<Curso> cursos = cursoRepository.findAll();
//        return ResponseEntity.ok(cursos);
//    }
    public ResponseEntity<List<CursoDTO>> getCursos(){
        List<Curso> cursos = cursoRepository.findAll();
        List<CursoDTO> cursosDTO = cursos.stream()
                .map(curso -> new CursoDTO(curso.getNombre(),curso.getDescripcion()))
//                .map(curso -> {
//                    CursoDTO c = new CursoDTO();
//                    c.setNombre(curso.getNombre());
//                    c.setDescripcion(curso.getDescripcion());
//                    return c;
//                })
                .toList();
        return ResponseEntity.ok(cursosDTO);
    }




}
