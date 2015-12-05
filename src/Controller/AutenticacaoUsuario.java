/*
 * @autor_name Ennio Sousa
 * @autor_site http://uid.me/shzlot
 *
 * Copyright (c) 2015 Ennio.
 * All rights reserved. This program and the accompanying materials
 */
package Controller;

import Model.UsuarioM;
import View.Login;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Ennio
 */
public class AutenticacaoUsuario {

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getCodUsuario() {
        return AutenticacaoUsuario.codUsuario;
    }
    private static String codUsuario;
    private static String tipoUsuario;

    UsuarioM usuario = new UsuarioM();

    /**
     * Método que verifica se o usuario existe e retorna o tipo de usuario.
     * a ou A para administradores
     * i ou I para inscritos
     * null (nullo) caso o usuario não exista
     *
     * @param email - Email do formulário de login
     * @param senha - Senha do formulário de login
     * @return tipo do usuário ou nullo
     */
    public String AutenticacaoUsuario(String email, String senha) {
        ResultSet res = this.usuario.autenticaUsuario(email, senha);
        String bancoEmail = null, bancoSenha = null, bancoTipoUsuario = null, bancoCodUsuario = null;
        try {
            while (res.next()) {
                bancoEmail = res.getString("email");
                bancoSenha = res.getString("senha");
                bancoTipoUsuario = res.getString("tipo_usuario");
                bancoCodUsuario = res.getString("cod_usuario");
            }
        } catch (Exception e) {
            //alguma coisa
        }
        if (email.equals(bancoEmail) && senha.equals(bancoSenha)) {
            //setar o código do usuario e o tipo do usuario logado
            AutenticacaoUsuario.codUsuario = bancoCodUsuario;
            AutenticacaoUsuario.tipoUsuario = bancoTipoUsuario;
        }
        return AutenticacaoUsuario.tipoUsuario;
    }
}
