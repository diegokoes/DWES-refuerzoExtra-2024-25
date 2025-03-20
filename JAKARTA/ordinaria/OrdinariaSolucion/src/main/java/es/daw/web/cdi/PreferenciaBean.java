package es.daw.web.cdi;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
//@RequestScoped
public class PreferenciaBean implements Serializable{
     
    private String colorFondo = "purple"; // Valor por defecto

    public String getColorFondo(){
        return colorFondo;
    }

    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
    }
    

    public String cambiarFondo(){
        System.out.println("********* COLOR DE FONDO: "+colorFondo);
        
        return null; // se queda en la misma página.
    }




    
}
