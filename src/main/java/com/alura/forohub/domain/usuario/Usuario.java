package com.alura.forohub.domain.usuario;

import com.alura.forohub.domain.perfil.Perfil;
import com.alura.forohub.domain.respuesta.Respuesta;
import com.alura.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.PublicKey;
import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String password;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;


    public Usuario() {
    }

    public Usuario(Usuario usuario){
        this.nombre = usuario.nombre;
        this.correo = usuario.correo;
        this.perfil = usuario.perfil;
        this.password = usuario.password;

    }

    public Usuario(AutorDelTopico usuario) {
        this.id = usuario.id();
        this.nombre = usuario.nombreUsuario();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ADMIN";
            }
        });
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
