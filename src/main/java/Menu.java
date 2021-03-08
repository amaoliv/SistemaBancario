import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Menu {

    private String menuCliente;
    private String menuFuncionario;



    public String getMenuCliente() {

        menuCliente =
                    "1 - Realizar saque\n" +
                    "2 - Realizar depósito\n" +
                    "3 - Retornar à seleção do perfil\n" +
                    "4 - Encerrar programa" ;
        return menuCliente;
        }

    public String getMenuFuncionario() {

        menuFuncionario =
                        "1 - Cadastrar Cliente\n"+
                        "2 - Abrir Conta\n" +
                                "3 - Excluir cliente\n" +
                        "4 - Retornar à seleção do perfil\n" +
                        "5 - Encerrar programa" ;
        return menuFuncionario;
    }


}

