package es.daw.web.cdi;

import java.util.Set;

import es.daw.web.entities.Ejemplar;
import es.daw.web.exceptions.JPAException;
import es.daw.web.repositories.CrudRepositoryEjemplar;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class EjemplarBean {

    @Inject
    private CrudRepositoryEjemplar ejemplarRepository;

    private Set<Ejemplar> ejemplares;

    public Set<Ejemplar> getEjemplares() {


        try {
            ejemplares = ejemplarRepository.select();
        } catch (JPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ejemplares = Set.of();
        }

        return ejemplares;
    }



    
}
