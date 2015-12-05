package Model;

import Util.DataBase;
import java.sql.ResultSet;

public class EnderecoM {

    DataBase db = new DataBase();
    UsuarioM user = new UsuarioM();

    /**
     *
     * @param cod_usuario - Código do usuario
     * @param estado - Estado
     * @param cidade - Codade
     * @param cep - CEP
     * @param rua - Rua
     * @param bairro - Bairro
     * @param numero - Número
     */
    public void cadastraEndereco(String cod_usuario, String estado, String cidade, String cep, String rua, String bairro, String numero) {
        String sql = "INSERT INTO endereco("
                + "            cod_usuario, estado, cidade, cep, rua, bairro, numero)"
                + "   VALUES ('" + cod_usuario + "', upper('" + estado + "'), upper('" + cidade + "'), '" + cep + "', upper('" + rua + "'), upper('" + bairro + "'), upper('" + numero + "')"
                + ");";
        this.db.executaComandoSql(sql);
    }

    public void upadeteEndereco(String estado, String cidade, String cep, String rua, String bairro, String numero) {
        String codUsuario = "";//enquanto não consegue obter o código do usuario

        String sql = "update endereco("
                + "             estado, cidade, cep, rua, bairro, numero)"
                + "   set ( upper('" + estado + "'), upper('" + cidade + "'), '" + cep + "', upper('" + rua + "'), upper('" + bairro + "'), upper('" + numero + "')where (cod_usuario='" + codUsuario + "')"
                + ");";
        this.db.executaComandoSql(sql);
    }

    /**
     *
     * @param cod_usuario - Código do usuario
     * @param estado - Estado
     * @param cidade - Cidade
     * @param cep - CEP
     * @param rua - Rua
     * @param bairro - Bairro
     * @param numero - Número (string)
     * @return boolean - true para cadastrado e false para não cadastrado
     */
    public boolean checkSeEnderecoExiste(String cod_usuario, String estado, String cidade, String cep, String rua, String bairro, String numero) {
        String sqlQuery = "SELECT cod_endereco FROM endereco WHERE cod_usuario='" + cod_usuario + "' and estado='" + estado + "' and cidade='" + cidade + "' and cep='" + cep + "' and rua='" + rua + "' and 'bairro'='" + bairro + "' and numero='" + numero + "';";
        ResultSet res = this.db.executaConsultaSql(sqlQuery);

        String cod_endereco = null;
        try {
            while (res.next()) {
                cod_endereco = res.getString("cod_endereco");
            }
            if (cod_endereco != null) {
                return true;
            }
        } catch (Exception e) {
            //alguma coisa
        }

        //Se o valor de cpf 2 for null, significa que o CPF não foi cadastrado
        return false;
    }
}
