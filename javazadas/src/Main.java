public class Main {
    public static void main(String[] args) throws Exception {

        Conta conta1 = new Conta(1,"Romário",80000,50000);
        Conta conta2 = new Conta(2,"Kristian",54000, 30000);

//        try {
//            conta1.transferencia(60000,conta2);
//        } catch (RuntimeException e){
//            System.out.println(e.getMessage());
//        }

        System.out.println("Ainda está rodando");

        //catch fala o que vai acontecer se uma exceção ocorreu ( try )
    }
}
