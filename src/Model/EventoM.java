package Model;

import Util.DataBase;
import java.sql.ResultSet;

public class EventoM {

    DataBase db = new DataBase();

    public void CadastroEvento(String evNome, String evDescricao, String evDataIni, String evDataFim) {
        String sql = "INSERT INTO EVENTO (NOME, DESCRICAO, DATA_INI, DATA_FIM)"
                + "	VALUES('"+evNome+"', '"+evDescricao+"', '"+evDataIni+"', '"+evDataFim+"');";
        
        this.db.executaComandoSql(sql);
    }
    
    public boolean CheckSeEventoCadastrado(String evNome, String evDescricao, String evDataIni, String evDataFim){
        String sql = "select cod_evento from evento where nome = '"+evNome+"' and descricao = '"+evDescricao+"' and data_ini = '"+evDataIni+"' and data_fim = '"+evDataFim+"';";
        
        ResultSet resConsultaSql = this.db.executaConsultaSql(sql);
        String cod_evento = null;
        try {
            while (resConsultaSql.next()) {
                cod_evento = resConsultaSql.getString("cod_evento");
            }
        } catch (Exception e) {
            //alguma coisa
        }
        
        if(cod_evento == null)
            return false;
        else
            return true;
    }

}
