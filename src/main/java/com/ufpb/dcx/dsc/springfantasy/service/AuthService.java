package com.ufpb.dcx.dsc.springfantasy.service;

import com.ufpb.dcx.dsc.springfantasy.dto.AuthResponse;
import com.ufpb.dcx.dsc.springfantasy.dto.LoginRequest;
import com.ufpb.dcx.dsc.springfantasy.dto.RegisterRequest;
import com.ufpb.dcx.dsc.springfantasy.enums.UserRole;
import com.ufpb.dcx.dsc.springfantasy.model.Usuario;
import com.ufpb.dcx.dsc.springfantasy.repository.UsuarioRepository;
import com.ufpb.dcx.dsc.springfantasy.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já está em uso!");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setRole(UserRole.USER);

        usuarioRepository.save(usuario);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );

        String jwt = tokenProvider.generateToken(authentication);

        return new AuthResponse(jwt, usuario.getEmail(), usuario.getNome(), usuario.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );

        String jwt = tokenProvider.generateToken(authentication);
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new AuthResponse(jwt, usuario.getEmail(), usuario.getNome(), usuario.getRole().name());
    }
}
