import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDConta {

    private final BancoDeDados banco = new BancoDeDados();

    public void create(Conta conta) {

        // para fazer isso dentro do parenteses tem que ter uma classe que extende de AutoCloseable algo assim
        try (Connection con = banco.getConexao()) {
//           email = "'email@email';DROP DATABASE db_sistema_bancario";
//           Statement stmt = con.createStatement();
//           stmt.execute("SELECT * FROM td_ususario WHERE email = " + email)

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO tb_conta (numero, id_cliente, saldo, limite) values (?, ?, ?, ?)");

            ps.setInt(1, conta.getNumero());
            ps.setInt(2, conta.getTitular().getId());
            ps.setDouble(3, conta.getSaldo());
            ps.setDouble(4, conta.getLimite());

            // só execute() booleano fala se deu ou nao certo
            ps.execute();

            // quando precisa mais dados do banco de dados, por exemplo um comando select
            // ps.executeQuery();
            // a quantidade de registros alterados linhas afetadas ( retorna um valor inteiro )
            // ps.executeUpdate();
            // mesma coisa que o de cima mas se for um banco de dados muito grande
            // ps.executeLargeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Conta readOne(int numero) {
        try( Connection con = banco.getConexao()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_conta WHERE numero = ?");

            ps.setInt(1,numero);
            ResultSet rs = ps.executeQuery();
            // next alterna as linhas | get... altera as colunas ( pensar na tabela do DB )
            if(rs.next()) {
                int num = rs.getInt("numero");
                int idCliente = rs.getInt("id_cliente");
                double saldo = rs.getDouble("saldo");
                double limite = rs.getDouble("limite");
                CRUDCliente crudCliente = new CRUDCliente();
                Cliente titular = crudCliente.readOne(idCliente);
                return new Conta(num,titular,saldo,limite);
            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        throw new ContaInexistenteException();
    }

    public List<Conta> readAll(){
        try (Connection con = banco.getConexao()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_conta");

            ResultSet rs = ps.executeQuery();
            List<Conta> contas = new ArrayList<>();
            while (rs.next()){
                int num = rs.getInt("numero");
                int idCliente = rs.getInt("id_cliente");
                double saldo = rs.getDouble("saldo");
                double limite = rs.getDouble("limite");
                CRUDCliente crudCliente = new CRUDCliente();
                Cliente titular = crudCliente.readOne(idCliente);
                contas.add(new Conta(num, titular, saldo, limite));
            }
            return contas;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        throw new ContaInexistenteException();
    }

//    public List<Conta> readAll(){
//        try (Connection con = banco.getConexao()){
//            List<Conta> contas = new ArrayList<>();
//
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_conta");
//            ResultSet rs = ps.executeQuery();
//            CRUDCliente crudCliente = new CRUDCliente();
//            // tirar duvida para ver como posso fazer
//            while (rs.next()){
//                contas.add(new Conta(rs.getInt("numero"), rs.getString("titular"), rs.getDouble("saldo"), rs.getDouble("limite")));
//            }
//            return contas;
//        } catch (SQLException e){
//            System.err.println(e.getMessage());
//        }
//        throw new ContaInexistenteException();
//    }

    public void update(Conta conta) {
        try (Connection con = banco.getConexao()) {

            PreparedStatement ps = con.prepareStatement("UPDATE tb_conta SET id_cliente = ?, limite = ?, saldo = ?  WHERE numero = ?");

            ps.setInt(1, conta.getTitular().getId());
            ps.setDouble(2, conta.getLimite());
            ps.setDouble(3, conta.getSaldo());
            ps.setInt(4, conta.getNumero());

            ps.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int numero){
        try (Connection con = banco.getConexao()){

            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_conta WHERE numero = ?");

            ps.setInt(1,numero);
            ps.execute();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}