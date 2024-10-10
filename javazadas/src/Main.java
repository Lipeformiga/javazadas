public class Main {
    public static void main(String[] args) throws Exception {

        Conta conta1 = new Conta(1,"Romário",80000,50000);
        Conta conta2 = new Conta(2,"Kristian",54000, 30000);

      try {
           conta1.saque(60000);
       } catch (SaldoInsuficienteException e){
          System.out.println(e.getMessage());
       } catch (ValorInvalidoException | LimiteInsuficienteException e){
          e.printStackTrace(); // mostra como se tivese crashado a operação
      }
        System.out.println("Ainda está rodando");
    }
}