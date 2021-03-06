/*
 * Sin licencia.
 * Uso para capacitación
 * 2021 Año de la Prevención y Lucha contra el COVID-19.
 */

package com.mza.biblioteca.servicios;

import com.mza.biblioteca.entidades.Autor;
import com.mza.biblioteca.entidades.Editorial;
import com.mza.biblioteca.entidades.Libro;
import com.mza.biblioteca.excepciones.MiExcepcion;
import com.mza.biblioteca.repositorios.RepoEditorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Adrian E. Camus
 */
@Service
public class EditorialService {

    @Autowired
    private RepoEditorial eRepo;
    
    @Transactional
    public void creaEditorial(String nombre) throws MiExcepcion {

        validar(nombre);

        Editorial edito = new Editorial();
//        List<Libro> listaLibros = new ArrayList<>();
//        
//        edito.setLibros(listaLibros);

//        if(libros.isEmpty()){
//            List<Libro> listLibros = new ArrayList<Libro>();
//            edito.setLibros(listLibros);
//        }else{
//            edito.setLibros(libros);}

        edito.setAlta(Boolean.TRUE);
        edito.setNombre(nombre);

        eRepo.save(edito);
    }

//    @Transactional
//    public void agregaLibro(Libro libro, Editorial editorial)throws MiExcepcion {
//        if(libro!=null && libro.getEditorial().equals(editorial)){
//            editorial.getLibros().add(libro);
//            eRepo.save(editorial);
//        }else{
//            throw new MiExcepcion("No se pudo agregar la editorial al Libro");
//        }
//    }
    
    @Transactional(readOnly = true)
    public List<Editorial> buscaEditoriales(){
        return eRepo.findAll();
    }
    
    @Transactional(readOnly = true)
    public Editorial buscaPorNombre(String nombre){
        return eRepo.buscaPorNombre(nombre);
    }
    
    @Transactional(readOnly = true)
    public Editorial buscaPorId(Editorial editorial) {
        Optional<Editorial> optional = eRepo.findById(editorial.getId());
        if (optional.isPresent())
        {
            editorial = optional.get();
        }
        return editorial;
    }
    
    private void validar(String nombre) throws MiExcepcion{

        if(nombre.isEmpty() || nombre == null){
            throw new MiExcepcion("Nombre no valido");
        }

    }

}
