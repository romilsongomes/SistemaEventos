package Controller;

import Model.UsuarioM;

public class UsuarioC {

    UsuarioM user = new UsuarioM();

    public String cadastrarUsuario(String cpf, String codGrupo, String tipoUsuario, String nome, String email, String telefone, String senha) {
        
        //verifica se o usuario existe antes de inserir os dados
        if (this.user.checkSeUsuarioExiste(cpf)) {
            return "Este usuario já está cadastrado";
        } else {
            //Inserindo as informações do usuario no banco de dados
            this.user.cadastroUsuario(cpf, codGrupo, tipoUsuario, nome, email, telefone, senha);
            
            //verificação se os dados foram inseridos no banco de dados
            if(this.user.checkSeUsuarioExiste(cpf)){
                return "O usuario foi cadastrado";
            }
            else{
                return "Algo de errado ocorreu ao cadastrar o usuario";
            }
        }
    }
}
