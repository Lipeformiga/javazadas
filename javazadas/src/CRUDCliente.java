import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDCliente {

    BancoDeDados banco = new BancoDeDados();
    List<Cliente> clientes = new ArrayList<>();

    public List<Cliente> readAll() {
        try (Connection con = banco.getConexao()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM cliente");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                clientes.add(new Cliente(id,nome,cpf));

            }
            return clientes;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }


    public Cliente readOne(int id) {
        try( Connection con = banco.getConexao()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_cliente WHERE id_cliente = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                return new Cliente(idCliente,nome,cpf);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        throw new ContaInexistenteException();
    }

    public Cliente create(Cliente cliente) {

        try(Connection con = banco.getConexao()){

            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_cliente (nome, cpf) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                cliente.setId(rs.getInt(1));
                return cliente;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Erro ao inserir cliente");
    }

    public void update(Cliente cliente) {
        try (Connection con = banco.getConexao()){

            PreparedStatement ps = con.prepareStatement("Update tb_clientes SET nome = ?, cpf = ? WHERE id = ?");

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setInt(3, cliente.getId());

            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete (int id) {
        try (Connection con = banco.getConexao()){

            PreparedStatement ps = con.prepareStatement("DELETe FROM tb_cliente WHERE id = ?");

            ps.setInt(1, id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}