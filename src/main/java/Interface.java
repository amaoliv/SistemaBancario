import java.util.List;

public interface Interface {

    void inserirCliente(Cliente cliente, List<Cliente> clientesList);
    boolean excluirCliente(String cpf, List<Cliente> clientesList);
    int alterarSaldo(String numeroAg, String numeroConta, String cpf, String valorT, boolean isSacar,List<Cliente> clientesList);
    void abrirConta(List<Cliente> clientesList, String cpf, String ag, String num, String tipoConta);
    boolean validarCadastroConta(List<Cliente> clientesList, String cpf);
    boolean existeConta(List<Cliente> clientesList, String num);
    boolean naoExisteCliente(List<Cliente> clientesList, String cpf);
    boolean encerrar(String encerrar);

}
