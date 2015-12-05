package Util;

import java.sql.Connection;
//import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class DataBase {

    /**
     * ****************************************************
     */
    String dbUsuario = "";
    String dbSenha = "";
    String dbUrl = "";
    Connection dbCon;

    /**
     *
     * @param host Endereço host do banco de dados
     * @param porta Porta do banco de dados
     * @param nome Nome do banco de dados
     * @param usuario Usuário do banco de dados
     * @param senha Senha do usuario do banco
     */
    public void setConfig(String host, String porta, String nome, String usuario, String senha) {
        this.dbUsuario = usuario;
        this.dbSenha = senha;
        this.dbUrl = "jdbc:postgresql://" + host + ":" + porta + "/" + nome;
    }

    public DataBase() {
        String host = "localhost",
                porta = "5432",
                nome = "SistemaEventos",
                usuario = "postgres",
                senha = "268357";
        this.dbUsuario = usuario;
        this.dbSenha = senha;
        this.dbUrl = "jdbc:postgresql://" + host + ":" + porta + "/" + nome;
    }

    private void iniciarConexao() {
        try {
            /**
             * É necessário ter a biblioteca .jar adicionado ao projeto Video
             * tutorial http://www.youtube.com/watch?v=Z1XCcsx8vwo&t=8m37s
             */
            Class.forName("org.postgresql.Driver"); //importar pacote do PostgreSQL

            this.dbCon = DriverManager.getConnection(this.dbUrl, this.dbUsuario, this.dbSenha);
            System.out.println("\n\nConectado ao banco com sucesso.");
        }//fim try
        catch (Exception e) {
            if (e instanceof SQLSyntaxErrorException) { //adicionar a biblioteca ao projeto
                System.out.println("Opa! " + e.getMessage());
            } else {
                throw new RuntimeException("Erro ao conectar-se ao banco.", e);
            }
        }//fim catch
    }//fim iniciarConexao()

    private void fecharConexao() {
        try {
            this.dbCon.close();
            System.out.println("Conexão com o banco foi fechada.");
        } catch (Exception e) {
            if (e instanceof SQLSyntaxErrorException) { //adicionar a biblioteca ao projeto
                System.out.println("Opa! " + e.getMessage());
            } else {
                throw new RuntimeException("Erro ao fechar conexao com o banco.", e);
            }
        }//fim catch
    }//fim fecharConexao()

    public void executaComandoSql(String comandoSql) {
        iniciarConexao();
        System.out.println("Começando a executar o comando SQL \"" + comandoSql + "\"");
        try {
            Statement sttm = this.dbCon.createStatement(); //estabilizar a conexão
            sttm.execute(comandoSql);
            System.out.println("Comando SQL foi executado.");
        } catch (Exception e) {
            if (e instanceof SQLSyntaxErrorException) { //adicionar a biblioteca ao projeto
                System.out.println("Opa! " + e.getMessage());
            } else {
                throw new RuntimeException("Erro ao executar o comando SQL.", e);
            }
        }//fim catch
        fecharConexao();
    }//fim executaComandoSql()

    public ResultSet executaConsultaSql(String comandoSql) {
        iniciarConexao();
        ResultSet retorno = null;
        try {
            System.out.println("Fazendo a Consulta com o comando SQL \"" + comandoSql + "\"");

            Statement sttm = this.dbCon.createStatement();
            retorno = sttm.executeQuery(comandoSql);
        } catch (Exception e) {
            if (e instanceof SQLSyntaxErrorException) { //adicionar a biblioteca ao projeto
                System.out.println("Opa! " + e.getMessage());
            } else {
                throw new RuntimeException("Erro ao executar o comando SQL.", e);
            }
        }
        fecharConexao();
        return retorno;
    }//fim executaConsultaSql()
}
