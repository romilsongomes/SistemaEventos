package Controller;

import Model.EnderecoM;
import Model.EventoM;
import Model.GrupoUsuarioM;
import Model.UsuarioM;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Administrador {

    UsuarioM usuario = new UsuarioM();
    EventoM evento = new EventoM();
    EnderecoM endereco = new EnderecoM();
    GrupoUsuarioM grupo = new GrupoUsuarioM();

    /**
     * ***********************************************************************************
     * Usuários
     */
    public String cadastrarUsuario(String cpf, String codGrupo, String tipoUsuario, String nome, String email, String telefone, String senha) {

        //verifica se o usuario existe antes de inserir os dados
        if (this.usuario.checkSeUsuarioExiste(cpf)) {
            return "Este usuario já está cadastrado";
        } else {
            //Inserindo as informações do usuario no banco de dados
            this.usuario.cadastroUsuario(cpf, codGrupo, tipoUsuario, nome, email, telefone, senha);

            //verificação se os dados foram inseridos no banco de dados
            if (this.usuario.checkSeUsuarioExiste(cpf)) {
                return "O usuario foi cadastrado";
            } else {
                return "Algo de errado ocorreu ao cadastrar o usuario";
            }
        }
    }

    /**
     * Obtem dos dados de um usuario específico
     *
     * @param cod_usuario
     * @return Array String
     */
    public String[] getDadosUsuario(String cod_usuario) {
        ResultSet res = this.usuario.getDadosUsuario(cod_usuario);

        String[] dados = new String[8];
        try {
            while (res.next()) {
                dados[0] = res.getString("cod_usuario");
                dados[1] = res.getString("cpf");
                dados[2] = res.getString("nome");
                dados[3] = res.getString("email");
                dados[4] = res.getString("telefone");
                dados[5] = res.getString("senha");
                dados[6] = res.getString("cod_grupo");
                dados[7] = res.getString("tipo_usuario");
            }
        } catch (Exception e) {
            //alguma coisa
        }
        return dados;
    }

    /**
     * Atualiza dados de um usuario no banco de dados pelo cod_do usuario
     *
     * @param cod_usuario
     * @param cpf
     * @param nome
     * @param email
     * @param telefone
     * @param senha
     * @param cod_grupo
     * @param tipo_usuario
     */
    public void setDadosUsuario(String cod_usuario, String cpf, String nome, String email, String telefone, String senha, String cod_grupo, char tipo_usuario) {
        //ordem cod_usuario, cpf, nome, email, telefone, senha, cod_grupo, tipo_usuario
        this.usuario.updateDadosUsuario(cod_usuario, cpf, nome, email, telefone, senha, cod_grupo, tipo_usuario);
    }

    /**
     * Obtem o total de usuarios cadastrados no banco de dados
     *
     * @return
     */
    public int getTotalUsuarios() {
        int totalUsuarios = 0;
        ResultSet res = this.usuario.getTotalUsuarios();
        try {
            while (res.next()) {
                totalUsuarios = res.getInt("total_usuarios");
            }
        } catch (Exception e) {
            //alguma coisa
        }
        return totalUsuarios;
    }

    /**
     * Obtem a lista de usuarios em Object para setar na tabela do view
     *
     * @return - matriz de objeto
     */
    public Object[][] getUsuariosParaLista() {
        //dados do banco
        ResultSet res = this.usuario.getTodosUsuarios();

        //Obter o total de colunas do resultado
        int linhas = this.getTotalUsuarios();
        int colunas = 0;
        try {
            ResultSetMetaData rsmd = res.getMetaData();
            colunas = rsmd.getColumnCount(); //seta a quantidade de colunas
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }

        //pegando os dados da tabela e setando na matriz (array mult.) de objeto
        Object[][] dadosTabela = new Object[linhas][colunas];
        int contador = 0;
        try {
            while (res.next()) {
                /**
                 * ordem dos dados: "Cod. Usuário", "Nome", "CPF", "Email",
                 * "Senha", "Telefone", "Tipo Usuário", "Cod. Grupo"
                 */
                dadosTabela[contador][0] = res.getInt("cod_usuario");
                dadosTabela[contador][1] = res.getString("nome");
                dadosTabela[contador][2] = res.getString("cpf");
                dadosTabela[contador][3] = res.getString("email");
                dadosTabela[contador][4] = res.getString("senha");
                dadosTabela[contador][5] = res.getString("telefone");
                dadosTabela[contador][6] = res.getString("tipo_usuario");
                dadosTabela[contador][7] = res.getString("cod_grupo");

                contador++;
            }
        } catch (Exception e) {
            //alguma coisa
        }
        return dadosTabela;
    }//fim método

    /**
     * ***********************************************************************************
     * Endereco
     */
    public String cadastrarEndereco(String cpf, String estado, String cidade, String cep, String rua, String bairro, String numero) {
        String codUsuario;
        System.out.println("O cpf é:" + cpf);
        if (this.usuario.checkSeUsuarioExiste(cpf)) {
            System.out.println("cpf existe para cadastrar o endereco");
            //obtem o código do usuario pelo CPF
            System.out.println("Obtendo o código do usuario");
            codUsuario = this.usuario.getCodUsuario(cpf);

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

    /**
     * ***********************************************************************************
     * Grupos de usuarios
     */
    public String criarGrupo(String nome) {
        this.grupo.criacaoGrupo(nome);

        boolean grupoCriado = this.grupo.verificaSeGrupoCriado(nome);
        if (grupoCriado) {
            return "O grupo foi criado com sucesso!";
        } else {
            return "Algo de errado ocorreu ao criar o grupo.";
        }
    }

    public String alterarGrupo(String codGrupo, String novoNomeDoGrupo) {
        this.grupo.alteracaoGrupo(codGrupo, novoNomeDoGrupo);

        boolean grupoAlterado = this.grupo.verificaSeGrupoAlterado(codGrupo, novoNomeDoGrupo);
        if (grupoAlterado) {
            return "O nome do grupo foi alterado!";
        } else {
            return "Algo de errado ocorreu ao alterar o nome do grupo.";
        }
    }

    public String excluirGrupo(String codGrupo) {
        this.grupo.exclusaoGrupo(codGrupo);

        boolean grupoExcluido;
        grupoExcluido = this.grupo.verificaSeGrupoExcluido(codGrupo);
        if (grupoExcluido) {
            return "O grupo foi excluido!";
        } else {
            return "Algo de errado ocorreu ao excluir o grupo.";
        }
    }

    /**
     * ***********************************************************************************
     * Evento
     */
    public String CadastrarEvento(String evNome, String evDescricao, String evDataIni, String evDataFim) {
        this.evento.CadastroEvento(evNome, evDescricao, evDataIni, evDataFim);
        boolean check;
        check = this.evento.CheckSeEventoCadastrado(evNome, evDescricao, evDataIni, evDataFim);

        if (check) {
            return "Evento cadastrado";
        } else {
            return "Algo de errado aconteceu ao cadastrar o evento";
        }
    }
}
