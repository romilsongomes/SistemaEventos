/*
 * @autor_name Ennio Sousa
 * @autor_site http://uid.me/shzlot
 *
 * Copyright (c) 2015 Ennio.
 * All rights reserved. This program and the accompanying materials
 */
package Controller;

/**
 *
 * @author Ennio
 */
public class DadosLogin {

    public static String getCodUsuario() {
        return codUsuario;
    }

    public static void setCodUsuario(String codUsuario) {
        DadosLogin.codUsuario = codUsuario;
    }
    private static String codUsuario;
}
