package com.alura.forohub.controller;

import com.alura.forohub.domain.usuario.ClavesDeAutenticacion;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import com.alura.forohub.infra.security.DatosJWTToken;
import com.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid ClavesDeAutenticacion clavesDeAutenticacion){
        Authentication token = new UsernamePasswordAuthenticationToken(clavesDeAutenticacion.correo(), clavesDeAutenticacion.password());
        var usuarioAutenticado = authenticationManager.authenticate(token);

        //Usuario usuario = usuarioRepository.findUsuarioByCorreo(clavesDeAutenticacion.correo());
        //System.out.println(usuario);

        var jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        DatosJWTToken datosJWTToken = new DatosJWTToken(jwtToken);

        return ResponseEntity.ok().body(datosJWTToken);
    }

}
