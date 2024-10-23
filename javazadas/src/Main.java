import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static BancoDeDados db = new BancoDeDados();
    public static void main(String[] args){
        do {
            mostrarOpcoesMenu();
            int opcaoMenu = sc.nextInt();
            executarOpcaoMenu(opcaoMenu);
        } while (true);



//        Conta conta1 = new Conta(1,"Romário",80000);
//        Conta conta2 = new Conta(2,"Kristian",54000);
//
//        System.out.println("Digite o valor do saque: ");
//        double valor = sc.nextDouble();
//      try {
//           conta1.saque(valor);
//      } catch (SaldoInsuficienteException e){
//          System.out.println(e.getMessage());
//      } catch (ValorInvalidoException | LimiteInsuficienteException e){
//          e.printStackTrace(); // mostra como se tivese crashado a operação
//      } catch (ContaException ignore){
//
//      }
//        System.out.println("Ainda está rodando");
    }

    private static void cadastroConta(){
        System.out.println("Número da conta: ");
        int numero = sc.nextInt();
        try {
            Conta conta = db.buscarConta(numero);
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
        System.out.println(db.buscarContas());
        System.out.println("Número da conta: ");
        int numero = sc.nextInt();
        db.deletarConta(numero);
    }
    private static void editarConta(){
        Conta conta = buscarConta();
        System.out.println("Titular");
        String titular = sc.next();
        System.out.println("Limite");
        double limite = sc.nextDouble();
        conta.setTitular(titular);
        conta.setLimite(limite);
//        db.atualizarConta(conta);
    }
    private static Conta buscarConta(){
        System.out.println(db.buscarContas());
        System.out.println("Número da conta: ");
        int numero = sc.nextInt();
        return db.buscarConta(numero);
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
                4 - voltar
                
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
            case 1: cadastroConta();
            break;
            case 2: editarConta();
            break;
            case 3: removeConta();
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
                    executarOpcaoConta(conta, opcaoConta);
                } while (opcaoConta != 4);
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
                conta.transferencia(solicitarValor(), conta);
                break;
            case 4:
                System.out.println("Até mais!");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}