package es.daw.web.repositories;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import es.daw.web.entities.Socio;
import es.daw.web.exceptions.JPAException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RequestScoped
public class CrudRepositorySocio implements CrudRepository<Socio> {

    @Inject
    EntityManager em;

    @Override
    public Set<Socio> select() throws JPAException {

        try{
            String jpql = "SELECT s FROM Socio s order by s.nombre desc";

            List<Socio> lista = em.createQuery(jpql, Socio.class).getResultList();
    
            /*
             * Si no implementas Comparable o no proporcionas un Comparator 
             * al crear el TreeSet, obtendrás una excepción en tiempo de ejecución 
             * (ClassCastException), porque TreeSet necesita saber cómo comparar los objetos para mantener el orden.
             * java.lang.ClassCastException: class Socio cannot be cast to class java.lang.Comparable
             */
            // Set<Socio> socios2 = new TreeSet<>(lista); //Para que funcione la ordenación natural necesito implementar el Comparable en la clase Socio

            // Set<Socio> sociosPorNombre = new TreeSet<>(Comparator.comparing(Socio::getNombre));
            // sociosPorNombre.addAll(lista);

            // Set<Socio> socios3 = new TreeSet<>((s1, s2) -> s1.getNombre().compareTo(s2.getNombre()));
            // socios2.addAll(lista);
            
            Set<Socio> socios = new LinkedHashSet<>(lista);
    
            return socios;
    
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));
        }
    }

    @Override
    public Optional<Socio> selectById(int id) throws JPAException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public void deleteById(int id) throws JPAException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    @Transactional
    public void save(Socio t) throws JPAException {
        try{
            em.persist(t);
            //em.flush();
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));
        }

    }


}
