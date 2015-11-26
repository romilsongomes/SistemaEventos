package Controller;

import Model.EnderecoM;
import Model.UsuarioM;

public class EnderecoC {

    UsuarioM user = new UsuarioM();
    EnderecoM endereco = new EnderecoM();

    /**
     *
     * @param cpf — CPF do dono do endereço
     * @param estado — Estado no formato UF
     * @param cidade — Cidade
     * @param cep — CEP (apenas numeros)
     * @param rua — Rua
     * @param bairro — Bairro
     * @param numero — Número (String)
     * @return String
     */
    public String cadastrarEndereco(String cpf, String estado, String cidade, String cep, String rua, String bairro, String numero) {
        String codUsuario;
        System.out.println("O cpf é:" +cpf);
        if (this.user.checkSeUsuarioExiste(cpf)) {
            System.out.println("cpf existe para cadastrar o endereco");
            //obtem o código do usuario pelo CPF
            System.out.println("Obtendo o código do usuario");
            codUsuario = this.user.getCodUsuario(cpf);

            //cadastra o endereço
            this.endereco.cadastraEndereco(codUsuario, estado, cidade, cep, rua, bairro, numero);

            //verifica se o endereco foi cadastrado no banco
            boolean checkSeEndFoiCadastrado;
            checkSeEndFoiCadastrado = this.endereco.checkSeEnderecoExiste(codUsuario, estado, cidade, cep, rua, bairro, numero);

            //retorna a mensagem
            if (checkSeEndFoiCadastrado) {
                return "Endereco foi cadastrado";
            } else {
                return "Algo de errado aconteceu e o endereço não foi cadastrado";
            }
        } else {
            return "Não é possível atribuir um endereco a um usuário que não esteja cadastrado.";
        }
    }
}
