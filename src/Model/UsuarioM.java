package Model;

import Util.DataBase;
import java.sql.ResultSet;

public class UsuarioM {

    String cpf, codGrupo, tipoUsuario, nome, email, telefone, senha, codUsuario;

    public String getCpf() {
        return this.cpf;
    }

    public String getCodUsuario() {
        return this.codUsuario;
    }
    DataBase db = new DataBase();

    /**
     *
     * @param cpf CPF do usuário
     * @param codGrupo Código do Grupo de usuários, caso existe informe o valor
     * null
     * @param tipoUsuario Passar "i", para inscrito, ou "a" para administrador
     * @param nome Nome do usuário
     * @param email Email do usuário
     * @param telefone Telefone do usuário no formato +DDI DDD NÚMERO Ex.:
     * 5574912345678
     * @param senha Senha do usuario
     */
    public void cadastroUsuario(String cpf, String codGrupo, String tipoUsuario, String nome, String email, String telefone, String senha) {
        this.cpf = cpf;
        this.codGrupo = codGrupo;
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;

        //Insere o usuario no banco de dados
        String codGrupo2 = (codGrupo == null) ? null : "'" + codGrupo + "'";
        String sql;
        sql = "INSERT INTO usuario (cpf, nome, email, telefone, senha, cod_grupo, tipo_usuario)"
                + " VALUES ('" + cpf + "', upper('" + nome + "'), upper('" + email + "'), '" + telefone + "', '" + senha + "', " + codGrupo2 + ", '" + tipoUsuario + "');";
        this.db.executaComandoSql(sql);
    }

    public boolean checkSeUsuarioExiste(String cpf) {
        System.out.println("Verificando se o usuario existe em UsuarioM");
        String sqlQuery = "SELECT cpf FROM usuario WHERE cpf = '" + cpf + "';";
        ResultSet res = this.db.executaConsultaSql(sqlQuery);

        String cpf2 = null;
        try {
            while (res.next()) {
                cpf2 = res.getString("cpf");
            }
        } catch (Exception e) {
            //alguma coisa
        }

        //Se o valor de cpf 2 for null, significa que o CPF não foi cadastrado
        return (cpf2 == null) ? false : true;
    }

    //Obtem o código de um usuario através do CPF
    public String getCodUsuario(String cpf) {
        System.out.println("Obtendo o código do usuario em UsuarioM");
        String sqlQuery = "SELECT cod_usuario FROM usuario WHERE cpf = '" + cpf + "';";
        ResultSet res = this.db.executaConsultaSql(sqlQuery);

        String cod_usuario = null;
        try {
            while (res.next()) {
                cod_usuario = res.getString("cod_usuario");
            }
        } catch (Exception e) {
            //alguma coisa
        }
        return cod_usuario;
    }

    public ResultSet getDadosUsuario(String pCodUsuario) {
        String sqlQuery;
        sqlQuery = "SELECT cod_usuario, cpf, nome, email, telefone, senha, cod_grupo, tipo_usuario "
                + "FROM usuario WHERE cod_usuario = '" + pCodUsuario + "';";
        ResultSet res = this.db.executaConsultaSql(sqlQuery);
        return res;
    }
    
    public void setDadosUsuario(String cod_usuario, String cpf, String nome, String email, String telefone, String senha, String cod_grupo, char tipo_usuario){
        //ordem cod_usuario, cpf, nome, email, telefone, senha, cod_grupo, tipo_usuario
        
        //verificar se o código do grupo foi passado vazio ou nulo
        String cod_grupo2 = ("".equals(cod_grupo) || cod_grupo == null) ? null : "'" + cod_grupo + "'";
        String sql;
        sql = "update usuario set "
                + "cpf = '"+cpf+"', "
                + "nome = upper('"+nome+"'), "
                + "email = upper('"+email+"'), "
                + "telefone = '"+telefone+"', "
                + "senha = '"+senha+"', "
                + "cod_grupo = "+cod_grupo2+", "
                + "tipo_usuario = '"+tipo_usuario+"' "
                + "     where cod_usuario = "+cod_usuario+";";
        this.db.executaComandoSql(sql);
    }
    
    public ResultSet getTotalUsuarios() {
        String sqlQuery;
        sqlQuery = "SELECT count(cod_usuario) as total_usuarios from usuario;";
        ResultSet res = this.db.executaConsultaSql(sqlQuery);
        return res;
    }
    
    public ResultSet getTodosUsuarios(){
        String sqlQuery;
        sqlQuery = "SELECT * from usuario;";
        ResultSet res = this.db.executaConsultaSql(sqlQuery);
        return res;
    }

}
