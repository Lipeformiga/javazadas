import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Conta conta1 = new Conta(1,"Romário",80000,50000);
        Conta conta2 = new Conta(2,"Kristian",54000, 30000);

        System.out.println("Digite o valor do saque: ");
        double valor = sc.nextDouble();
      try {
           conta1.saque(valor);
      } catch (SaldoInsuficienteException e){
          System.out.println(e.getMessage());
      } catch (ValorInvalidoException | LimiteInsuficienteException e){
          e.printStackTrace(); // mostra como se tivese crashado a operação
      } catch (ContaException ignore){

      }
        System.out.println("Ainda está rodando");
    }

    private void cadastroConta(){

    }
    private void deletarConta(){

    }
    private void editarConta(){

    }
    private void buscarConta(){

    }

    private void login(){

    }
    private void logout(){

    }
    private void mostrarDadosConta(){

    }
    private void mostrarDadosTitular(){

    }
    private void mostrarOpcoes(){

    }
    private void cadastroTitular(){

    }
    private void deletarTitular(){

    }
    private void editarTitular(){

    }
    private void buscarTitular(){

    }
    private void executarOpcao(){
        
    }
}