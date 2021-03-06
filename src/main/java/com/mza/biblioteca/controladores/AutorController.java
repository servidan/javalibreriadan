
package com.mza.biblioteca.controladores;

import com.mza.biblioteca.entidades.Autor;
import com.mza.biblioteca.servicios.AutorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    AutorService autorServicio;

    @GetMapping("/autores")
    public String autores(ModelMap modelo) {
        List<Autor> autores = autorServicio.buscaAutores();
        modelo.addAttribute("autores", autores);
        return "autores.html";
    }
    
    @GetMapping("/registroAutor")
    public String formulario() {
        return "nAutor.html";
    }
    
    @PostMapping("/registroAutor")
    public String registro(ModelMap modelo, @RequestParam String nombre) {
                       
        try
        {
            autorServicio.creaAutor(nombre);
            modelo.put("exito", "Registro Exitoso");
            return "nAutor";
        } catch (Exception e)
        {
            modelo.put("error", "Ya existe un Autor con ese Nombre");
            return "nAutor";
        }
        
    }

    @GetMapping("/lista")
    public String listaLibros(ModelMap modelo, @RequestParam(required = false) String buscar) {
        //si el parametro "buscar" NO es nulo, agrega al modelo una lista de libros buscados
        if (buscar != null)
        {
            modelo.addAttribute("libros", autorServicio.buscaPorNombre(buscar));
        } else //si no viene parametro de busqueda, agrega al modelo una lista con todos los libros
        {
            modelo.addAttribute("libros", autorServicio.buscaAutores());
        }
        return "autores";
    }
}
