import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static BancoDeDados db = new BancoDeDados();
    public static void main(String[] args){
        do {
            mostrarOpcoesMenu();
            int opcaoMenu = sc.nextInt();
            try {
                executarOpcaoMenu(opcaoMenu);
            } catch (ContaInexistenteException | ContaJaCadastradaException e) {
                System.out.println(e.getMessage());
            }

        } while (true);
    }

    private static void cadastroConta(){
        System.out.println("Número da conta: ");
        int numero = sc.nextInt();
        try {
            Conta conta = db.buscaConta(numero);
        } catch (ContaInexistenteException e) {
            System.out.println("Titular:");
            String titular = sc.next();
            System.out.println("Limite:");
            double limite = sc.nextDouble();
            db.inserirConta(new Conta(numero, titular, limite));
            return;
        }
        throw new ContaJaCadastradaException();
    }
    private static void removeConta(){
        Conta conta = buscarConta();
        db.deletarConta(conta);
    }
    private static void editarConta(){
        Conta conta = buscarConta();
        System.out.println("Titular");
        String titular = sc.next();
        System.out.println("Limite");
        double limite = sc.nextDouble();
        conta.setTitular(titular);
        conta.setLimite(limite);
//      db.atualizarConta(conta);
    }
    private static Conta buscarConta(){
        System.out.println(db.buscarContas());
        System.out.println("Número da conta: ");
        int numero = sc.nextInt();
        return db.buscaConta(numero);
    }

    private static void login(){

    }
    private static void logout(){

    }
    private static void mostrarDadosConta(){

    }
    private static void mostrarDadosTitular(){

    }
    private static void mostrarOpcoesMenu(){
        System.out.print("""
                MENU:
                
                1 - Cadastrar Conta
                2 - Editar Conta
                3 - Deletar Conta
                4 - Mostrar Todas
                5 - selecionar
                6 - Sair
                
                >
                """);
    }
    private static void mostrarOpcoesConta(){
        System.out.print("""
                OPERAÇÔES
                
                1 - Deposito
                2 - Saque
                3 - Transferencia
                4 - Saldo
                5 - voltar
                
                >
                """);
    }

    private static void cadastroTitular(){

    }
    private static void deletarTitular(){

    }
    private static void editarTitular(){

    }
    private static void buscarTitular(){

    }
    private static void executarOpcao(){
        
    }

    private static void executarOpcaoMenu(int opcao){
        switch(opcao){
            case 1:
                cadastroConta();
            break;
            case 2:
                editarConta();
            break;
            case 3:
                removeConta();
            break;
            case 4:
                System.out.println(db.buscarContas());
                break;
            case 5:
                int opcaoConta = 0;
                Conta conta = buscarConta();

                do {
                    mostrarOpcoesConta();
                    opcaoConta = sc.nextInt();
                    do {
                        try {
                            executarOpcaoConta(conta, opcaoConta);
                            break;
                        } catch (ValorInvalidoException | ContaInexistenteException e) {
                            System.err.println(e.getMessage());
                        } catch (SaldoInsuficienteException | LimiteInsuficienteException | PropriaContaException e){
                            System.err.println(e.getMessage());
                            break;
                        }
                    } while (true);
                } while (opcaoConta != 5);
                break;
            case 6:
                System.out.println("Até mais!");
                System.exit(0);
            default:
                System.out.println("Opção inválida");
        }
    }

    private static double solicitarValor(){
        System.out.println("Valor: R$");
        return sc.nextDouble();
    }
    private static void executarOpcaoConta(Conta conta,int opcao){
        switch(opcao){
            case 1:
                conta.deposito(solicitarValor());
                break;
            case 2:
                conta.saque(solicitarValor());
                break;
            case 3:
                //  Saldo insuficiente, Limite, Prorpia conta retornam para o menu
                // Valor invalido, Containexistente solicita o valor e a conta para transferencia
                conta.transferencia(solicitarValor(), buscarConta());
                break;
            case 4:
                System.out.println("Saldo: R$" + conta.getSaldo());
                break;
            case 5:
                System.out.println("Até mais!");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}