package es.daw.web.cdi;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@SessionScoped
@Named
public class PreferenciaBean implements Serializable{

    private String autorSeleccionado;


    // ------
    public String guardarAutorFavorito(){
        // Cookie
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();


            if (autorSeleccionado != null && !autorSeleccionado.isBlank()){
                // -----------------------------------------------------------------
                // Guardar en cookie
                Cookie cookie = new Cookie("autorFavorito", autorSeleccionado);
                //cookie.setMaxAge(60 * 60 * 24 * 30); // 30 días
                cookie.setPath("/");


                response.addCookie(cookie);
                // ------------------------------------------------------------------
            }
            else{
                // -----------------------------------------------------------------
                // Borrar en cookie
                Cookie cookie = new Cookie("autorFavorito", "");
                cookie.setPath("/");
                cookie.setMaxAge(0);

                response.addCookie(cookie);
                // ------------------------------------------------------------------
            }

            // página resultado
            return null; // seguir en la misma página.

    }

    // --------
    public String getAutorSeleccionado() {
        return autorSeleccionado;
    }

    public void setAutorSeleccionado(String autorSeleccionado) {
        this.autorSeleccionado = autorSeleccionado;
    }

     


}
