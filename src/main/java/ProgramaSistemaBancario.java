/* TREINAMENTO DE JAVA - Avaliação de orientação a objeto e exceptions.

Crie um programa que simulará um sistema bancário, esse programa terá um menu
onde o usuário terá a opção de inserir e excluir clientes, também terá uma opção
de abrir conta para um determinado cliente, podendo ser uma conta corrente ou uma
conta poupança, alem disso, deverá ter uma função para saque e deposito de uma
determinada conta desse cliente.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramaSistemaBancario {

    static boolean isRodandoSistema = true;

    public static void main(String[] args) {

        ControleBancario controleBancario = new ControleBancario();

        List<Cliente> clientesList = new ArrayList<>();

        Menu menu = new Menu();

        Scanner sc = new Scanner(System.in);

        while (isRodandoSistema) {

            int perfil = 0;

            try {
                System.out.println("Bem vindo ao Sistema Bancário!\nPara começar, selecione seu perfil:\n [ 1 ] Sou funcionário\n [ 2 ] Sou cliente \n [ 3 ] Encerrar programa");
                perfil = sc.nextInt();

            }catch(java.util.InputMismatchException ex) {

                String erro = sc.next(); //O QUE QUER QUE O USUÁRIO DIGITE NO CMD QUE FOR DIFERENTE DE UM INTEIRO
                // SERÁ GUARDADO COMO STRING NA VARIÁVEL erro

                System.out.println(erro + " não é uma opção válida.\n");
                continue;

            }

            if(perfil == 1) {

                boolean isRodandoFuncionario = true;

                while(isRodandoFuncionario){


                    int opcao = 0;

                    try {
                        System.out.println("Selecione a opção desejada:\n" + menu.getMenuFuncionario());
                        opcao = sc.nextInt(); //SE O USUÁRIO DIGITAR ALGO DIFERENTE DE UM INTEIRO, A VARIÁVEL opcao NÃO SERÁ PREENCHIDA


                    } catch (java.util.InputMismatchException ex) {

                        String erro = sc.next(); //O QUE QUER QUE O USUÁRIO DIGITE NO CMD QUE FOR DIFERENTE DE UM INTEIRO
                        // SERÁ GUARDADO COMO STRING NA VARIÁVEL erro

                        System.out.println(erro + " não é uma opção válida.\n");
                        continue;

                    }


                    if (opcao == 1) {

                        Cliente novoCliente = new Cliente();

                        System.out.println("Informe o nome do cliente: ");
                        String nome = sc.next();
                        novoCliente.setNome(nome); //SETTANDO NOME

                        System.out.println("Informe o CPF do cliente: ");
                        String cpf = sc.next();
                        novoCliente.setCpf(cpf); //SETTANDO CPF

                        System.out.println("Informe o RG do cliente: ");
                        String rg = sc.next();
                        novoCliente.setRg(rg); //SETTANDO RG

                        if (!controleBancario.naoExisteCliente(clientesList, cpf)) {
                            System.out.println("Cliente já existe.");
                        } else {
                            controleBancario.inserirCliente(novoCliente, clientesList); //ADICIONANDO O NOVO CLIENTE NA LISTA

                            System.out.println("Novo cliente cadastrado com sucesso!!\n");
                        }


                    } else if (opcao == 2) {

                        System.out.print("Informe o CPF do cliente que deseja abrir uma conta: ");
                        String cpf = sc.next();

                        if (controleBancario.naoExisteCliente(clientesList, cpf)) { //VERIFICANDO SE CLIENTE JA POSSUI CPF CADASTRADO
                            System.out.println("Cliente não cadastrado no sistema.");

                        } else {
                            if (!controleBancario.validarCadastroConta(clientesList, cpf)) { //VERIFICANDO SE CLIENTE JÁ POSSUI CONTA

                                System.out.println("Selecione o tipo de conta:\n [ 1 ] Conta Poupança\n [ 2 ] Conta corrente");
                                int n = sc.nextInt();

                                switch (n) {

                                    case 1:

                                        String tipoCp = "Conta Poupança";

                                        System.out.print("[ABERTURA DE CONTA POUPANÇA]\n\nInsira o número da agência: ");
                                        String ag = sc.next();
                                        System.out.print("Insira um número para a nova conta poupança: ");
                                        String num = sc.next();

                                        try {
                                            Integer.parseInt(num);
                                            Integer.parseInt(ag);

                                            if (controleBancario.existeConta(clientesList, num)) {
                                                System.out.println("A conta não está disponível para cadastro");

                                            } else {
                                                controleBancario.abrirConta(clientesList, cpf, ag, num, tipoCp);//CADASTRANDO A NOVA CONTA POUPANÇA

                                                System.out.println("A conta poupança foi aberta com sucesso!!");
                                                break;
                                            }

                                        } catch (Exception ex) {

                                            System.out.println("Valor inválido.");

                                            break;

                                        }

                                    case 2:

                                        String tipoCc = "Conta Corrente";

                                        System.out.print("[ABERTURA DE CONTA CORRENTE]\n\nInsira o número da agência: ");
                                        String ag1 = sc.next();

                                        System.out.print("Insira um número para a nova conta corrente: ");
                                        String num1 = sc.next();

                                        try {
                                            Integer.parseInt(num1);
                                            Integer.parseInt(ag1);

                                            if (controleBancario.existeConta(clientesList, num1)) {
                                                System.out.println("A conta não está disponível para cadastro");

                                            } else {
                                                controleBancario.abrirConta(clientesList, cpf, ag1, num1, tipoCc);//CADASTRANDO A NOVA CONTA CORRENTE

                                                System.out.println("A conta corrente foi aberta com sucesso!!");
                                                break;
                                            }

                                        } catch (Exception ex) {

                                            System.out.println("Valor inválido.");

                                            break;

                                        }
                                }

                            } else {
                                System.out.print("\nO cliente já possui conta.\n\n");

                            }

                        }

                    } else if (opcao == 3) {

                        System.out.println("Informe o CPF do cliente: ");
                        String cpf = sc.next();

                        if (controleBancario.excluirCliente(cpf, clientesList)) {
                            System.out.println("Cliente excluído.");

                        } else {
                            System.out.println("Cliente não está cadastrado.");

                        }

                    } else if(opcao == 4){

                        //RETORNAR À SELEÇAO DO PERFIL

                        isRodandoFuncionario = false;


                    } else if(opcao == 5){

                        System.out.println("Deseja mesmo encerrar o programa? Digite 1 para sim");
                        String n = sc.next();
                        isRodandoSistema = controleBancario.encerrar(n);

                    }
                }



            }if (perfil == 2){

                boolean isRodandoCliente = true;

                while (isRodandoCliente){

                    int opcao = 0;

                    try {
                        System.out.println("Selecione a opção desejada:\n" + menu.getMenuCliente());
                        opcao = sc.nextInt(); //SE O USUÁRIO DIGITAR ALGO DIFERENTE DE UM INTEIRO, A VARIÁVEL opcao NÃO SERÁ PREENCHIDA


                    } catch (java.util.InputMismatchException ex) {

                        String erro = sc.next(); //O QUE QUER QUE O USUÁRIO DIGITE NO CMD QUE FOR DIFERENTE DE UM INTEIRO
                        // SERÁ GUARDADO COMO STRING NA VARIÁVEL erro

                        System.out.println(erro + " não é uma opção válida.\n");
                        continue;

                    }

                    if (opcao == 1) {

                        boolean sacar = true;

                        System.out.print("Informe seu CPF: ");
                        String cpf = sc.next();

                        if (controleBancario.validarCadastroConta(clientesList, cpf)) { //VERIFICANDO SE CLIENTE JÁ POSSUI CONTA


                            System.out.print("Digite a agência da conta: ");
                            String ag = sc.next();
                            System.out.print("Digite o número da conta: ");
                            String num = sc.next();
                            System.out.print("Digite o valor a ser sacado: ");
                            String valorSaque = sc.next();
                            controleBancario.alterarSaldo(ag, num, cpf, valorSaque, sacar, clientesList);

                        } else {
                            System.out.println("Você não possui uma conta.");
                        }

                    } else if (opcao == 2) {

                        boolean sacar = false;

                        System.out.print("Informe seu CPF: ");
                        String cpf = sc.next();

                        if (controleBancario.validarCadastroConta(clientesList, cpf)) { //VERIFICANDO SE CLIENTE JÁ POSSUI CONTA

                            System.out.print("Digite a agência da conta: ");
                            String ag = sc.next();
                            System.out.print("Digite o número da conta: ");
                            String num = sc.next();
                            System.out.print("Digite o valor a ser depositado: ");
                            String valorDeposito = sc.next();
                            controleBancario.alterarSaldo(ag, num, cpf, valorDeposito, sacar, clientesList);


                        } else {
                            System.out.println("Você não possui uma conta.");
                        }


                    } else if (opcao == 5) {

                        System.out.println("Deseja mesmo encerrar o programa? Digite 1 para sim");
                        String n = sc.next();
                        isRodandoSistema = controleBancario.encerrar(n);

                    } else if (opcao == 4) {

                        //RETORNAR À SELEÇAO DO PERFIL

                        isRodandoCliente = false;
                    }

                }

                }


        }




    }

}

