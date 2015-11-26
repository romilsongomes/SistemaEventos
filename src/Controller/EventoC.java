package Controller;
import Model.EventoM;

public class EventoC {
    EventoM ev = new EventoM();
    public String CadastrarEvento(String evNome, String evDescricao, String evDataIni, String evDataFim){
        this.ev.CadastroEvento(evNome, evDescricao, evDataIni, evDataFim);        
        boolean check;
        check = this.ev.CheckSeEventoCadastrado(evNome, evDescricao, evDataIni, evDataFim);
        
        if(check){
            return "Evento cadastrado";
        }
        else{
            return "Algo de errado aconteceu ao cadastrar o evento";
        }
    }
}
