package com.mza.biblioteca.repositorios;


import com.mza.biblioteca.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, String> {
    
    @Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
    public Usuario buscarPorMail(@Param("mail") String mail);
    
    //@Query("SELECT a FROM Usuario a WHERE a.baja = null")
    //public List<Usuario> buscaActivos();
    
}
