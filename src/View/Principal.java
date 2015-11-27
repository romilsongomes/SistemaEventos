package View;
import Controller.EnderecoC;
import Controller.EventoC;
import Controller.UsuarioC;
public class Principal {
    
    public static void main(String args[]) {
        /*usuario
        String cpf, codGrupo, tipoUsuario, nome, email, telefone, senha;
        cpf = "05908755590";
        codGrupo = null;
        tipoUsuario = "a";
        nome = "Ennio Sousa";
        email = "ennio21@gmail.com";
        telefone = "912345678";
        senha = "minhaSenha";

        //endereco
        String estado, cidade, cep, rua, bairro, numero;
        estado = "BA";
        cidade = "Feira de Santana";
        cep = "44004258";
        rua = "Rua das Oliveiras";
        bairro = "Costa Verde";
        numero = "123";

        //evento
        String evNome, evDescricao, evDataIni, evDataFim;
        evNome = "Semana Tech FAT";
        evDescricao = "Este evento é destinado a todos os estudantes de TI da instituição e de fora.";
        evDataIni = "2015-11-01";
        evDataFim = "2015-11-05";
                /*
        
        */
        //UsuarioC usuario = new UsuarioC();
        //String cadastrarUsuario = usuario.cadastrarUsuario(cpf, codGrupo, tipoUsuario, nome, email, telefone, senha);
        //System.out.println("\n"+cadastrarUsuario+"\n--------------------------------------------------\n");
//----------------------------------------    
       // EnderecoC end = new EnderecoC();
      //  String cadastrarEndereco = end.cadastrarEndereco(cpf, estado, cidade, cep, rua, bairro, numero);
        //System.out.println(cadastrarEndereco);
//---------------------------------------- 
        //EventoC ev = new EventoC();
        //String CadastrarEvento = ev.CadastrarEvento(evNome, evDescricao, evDataIni, evDataFim);
        //System.out.println(CadastrarEvento);
        Login login = new Login();
        login.setVisible(true);
    }
}
