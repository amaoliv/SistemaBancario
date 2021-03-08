import java.util.List;

public class ControleBancario implements Interface {

    @Override
    public void inserirCliente(Cliente cliente, List<Cliente> clientesList) {

        clientesList.add(cliente);

    }

    @Override
    public boolean excluirCliente(String cpf, List<Cliente> clientesList) {

        boolean retorno = false;

        //PARA CADA CLIENTE NA LISTA DE CLIENTES

        if(clientesList != null && clientesList.size() > 0 ){

            for (int i = 0; i < clientesList.size(); i++) {

                if (clientesList.get(i).getCpf().equals(cpf)) {

                    clientesList.remove(i);

                    retorno = true;
                }
            }

        }

        return retorno;
    }

    @Override
    public int alterarSaldo(String numeroAg, String numeroConta, String cpf, String valorT, boolean isSacar, List<Cliente> clientesList) {

        int retorno = -1;
        double valor = 0.0;

        try { valor = Double.parseDouble(valorT);

        } catch (Exception ex) {

            retorno =0;

        }

        if (valor <= 0) {

            retorno = 0;

        }

        if(retorno != 0){
            //PARA CADA CLIENTE NA LISTA DE CLIENTES
            for (Cliente cliente : clientesList) {

                //ENCONTRANDO O CLIENTE = 2
                if (cliente.getCpf().equals(cpf)) {

                    //VALIDANDO NUMERO DA CONTA = 3
                    if (cliente.getContaCliente().getNumero().equals(numeroConta)) {

                        //VALIDANDO NUMERO DA AGENCIA = 4
                        if (cliente.getContaCliente().getAgencia().equals(numeroAg)) {

                            double saldoNovo = 0.0;

                            if (isSacar) { //SACAR
                                if (valor <= cliente.getContaCliente().getSaldo()){ //VALOR É MENOR OU IGUAL AO SALDO DA CONTA?

                                    saldoNovo = cliente.getContaCliente().getSaldo() - valor;

                                    System.out.println("Saque realizado.\n Seu saldo atual é: " + saldoNovo);

                                } else {
                                    System.out.println("Saldo insuficiente.");
                                }

                            } else { //DEPOSITAR

                                saldoNovo = cliente.getContaCliente().getSaldo() + valor;

                                System.out.println("Depósito realizado.\n Seu saldo atual é: " + saldoNovo);
                            }
                            cliente.getContaCliente().setSaldo(saldoNovo); //SETTANDO NOVO SALDO

                            return 1; //PARA O MÉTODO E RETORNA SUCESSO

                        } else {
                            retorno = 4;
                        }

                    } else {
                        retorno = 3;
                    }

                } else {
                    retorno = 2;
                }
            }


        } else {

            System.out.println("Valor inválido.");

        }

        if (retorno == 3 || retorno == 4 ){

            System.out.println("Conta não existe.");

        }
        return retorno;
    }

    @Override

    public boolean existeConta(List<Cliente> clientesList, String num) {

        boolean existeConta = Boolean.FALSE;


        for (int i = 0; i < clientesList.size(); i++) {

            if (clientesList.get(i).getContaCliente() != null) {

                if (clientesList.get(i).getContaCliente().getNumero().equals(num)) {

                    existeConta = Boolean.TRUE;//RETORNA QUE O NUMERO DA CONTA JÁ EXISTE
                }
            }

            }
            return existeConta;
        }


    @Override

    public boolean naoExisteCliente(List<Cliente> clientesList, String cpf) {

       boolean existeCliente = Boolean.TRUE;

        for (int i = 0; i < clientesList.size(); i++) {

            if (clientesList.get(i).getCpf().equals(cpf)) {

                existeCliente = Boolean.FALSE; //O CLIENTE EXISTE NO SISTEMA

            }
        }
        return existeCliente;
        }

    @Override

    public void abrirConta(List<Cliente> clientesList, String cpf, String ag, String num, String tipoConta){

        double saldoInicial = 0.0;

        Conta novaConta = new Conta();

        //PARA CADA CLIENTE NA LISTA DE CLIENTES
        for (Cliente cliente : clientesList) {

            //ENCONTRANDO CLIENTE
            if (cliente.getCpf().equals(cpf)) {

                //SETTANDO DADOS DA CONTA
                novaConta.setAgencia(ag);
                novaConta.setNumero(num);
                cliente.setContaCliente(novaConta);
                novaConta.setSaldo(saldoInicial);
                novaConta.setTipo(tipoConta);


                }
            }

        }

        @Override
        public boolean validarCadastroConta (List < Cliente > clientesList, String cpf){

            boolean retorno = false;

            //PARA CADA CLIENTE NA LISTA DE CLIENTES
            for (Cliente cliente : clientesList) {

                //VALIDANDO CPF
                if (cliente.getCpf().equals(cpf)) {

                    //SE A CONTA DO CPF NÃO FOR NULA OU SEJA EXISTIR NO SISTEMA
                    if (cliente.getContaCliente() != null) {

                        retorno = true;

                    }

                }
            }

            return retorno;
        }


        @Override
        public boolean encerrar (String encerrar) {

        boolean verificador = Boolean.TRUE;

        if (encerrar.equals("1")){

            verificador = false;
        }

        return verificador;


        }


}

