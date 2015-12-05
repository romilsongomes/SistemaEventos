package Model;

import Util.DataBase;
import java.sql.ResultSet;

public class GrupoUsuarioM {
    DataBase db = new DataBase();

    /**
     * Método que cria grupos de usuarios no banco de dados
     * 
     * @param nome — Nome do grupo a ser criado
     */
    public void criacaoGrupo(String nome){
        String sql;
        sql = "insert into grupo (nome) values(upper('"+nome+"'));";
        this.db.executaComandoSql(sql);
    }
    
    /**
     * Este método altera o nome do grupo de usuários
     *
     * @param codGrupo - Código do grupo a ser alterado
     * @param novoNomeDoGrupo - Nome do grupo a ser alterado
     */
    public void alteracaoGrupo(String codGrupo, String novoNomeDoGrupo){
        String sql;
        sql = "update grupo set nome = upper('"+novoNomeDoGrupo+"') where cod_grupo = '"+codGrupo+"';";
        this.db.executaComandoSql(sql);
    }
    
    /**
     * Este método exclui um grupo de usuários
     *
     * @param codGrupo - Código do grupo de usuarios a ser excluído
     */
    public void exclusaoGrupo(String codGrupo){
        String sql;
        sql = "delete from grupo where cod_grupo = '"+codGrupo+"';";
        this.db.executaComandoSql(sql);
    }
    
    /**
     * Método que verifica se o grupo de usuarios foi criado
     * 
     * @param nome - Nome do grupo que acabou de ser criado
     * @return boolean - true para criado e false para não criado
     */
    public boolean verificaSeGrupoCriado(String nome){
        String sql;
        sql = "select cod_grupo from grupo where nome = upper('"+nome+"');";
        ResultSet res = this.db.executaConsultaSql(sql);
        String cod_grupo = null;
        try {
            while (res.next()) {
                cod_grupo = res.getString("cod_grupo");
            }
        } catch (Exception e) {
            //alguma coisa
        }

        //Se o valor de cpf 2 for null, significa que o CPF não foi cadastrado
        return (cod_grupo == null) ? false : true;
    }
    
    /**
     * Método que verifica se o grupo teve suas informações alteradas
     *
     * @param codGrupo - Código do grupo que foi alterado
     * @param novoNomeDoGrupo - Nome novo do grupo
     * @return boolean - true para alterado e false para não alterado
     */
    public boolean verificaSeGrupoAlterado(String codGrupo, String novoNomeDoGrupo){
        String sql;
        sql = "select nome from grupo where cod_grupo = '"+codGrupo+"';";
        ResultSet res = this.db.executaConsultaSql(sql);
        String nomeDoGrupoNoBanco = null;
        try {
            while (res.next()) {
                nomeDoGrupoNoBanco = res.getString("nome");
            }
        } catch (Exception e) {
            //alguma coisa
        }

        //Se o valor de cpf 2 for null, significa que o CPF não foi cadastrado
        return (nomeDoGrupoNoBanco.equals(novoNomeDoGrupo)) ? true : false;
    }
    
    /**
     * Método que verifica se o grupo de usuarios foi excluido
     *
     * @param codGrupo - Código do grupo que foi excluido
     * @return boolean - true para excluido e false para não excluido
     */
    public boolean verificaSeGrupoExcluido(String codGrupo){
        String sql;
        sql = "select nome from grupo where cod_grupo = '"+codGrupo+"';";
        ResultSet res = this.db.executaConsultaSql(sql);
        String nome = null;
        try {
            while (res.next()) {
                nome = res.getString("cod_grupo");
            }
        } catch (Exception e) {
            //alguma coisa
        }

        //Se o valor de cpf 2 for null, significa que o CPF não foi cadastrado
        return (nome == null) ? false : true;
    }
    
    
}
