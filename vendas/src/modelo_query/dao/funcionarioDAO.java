/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo_query.dao;

import conexao.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import model.bean.Produto;
import model.bean.funcionario;

/**
 *
 * @author Samuelson
 */
public class funcionarioDAO {

    public void create(funcionario f) {
        
        Connection con = Banco.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `sistema_vendas`.`funcionario` (nome,senha,login) VALUES (?,?,?)");
            stmt.setString(1, f.getnome());
                stmt.setString(2, f.getsenha());
                stmt.setString(3, f.getlogin());
            
           // stmt.setInt(2, f.getQtd());
           // stmt.setDouble(3, f.getPreco());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Banco.closeConnection(con, stmt);
        }

    }

    public List<funcionario> read() {

        Connection con = Banco.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {

               funcionario f = new funcionario();

                f.setId(rs.getInt("idfuncionario"));
                 f.setnome(rs.getString("nome"));
                  f.setemail(rs.getString("email"));
                   f.setlogin(rs.getString("login"));
                    f.setsenha(rs.getString("senha"));
                /*produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));*/
                funcionarios.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Banco.closeConnection(con, stmt, rs);
        }

        return funcionarios;

    }
 
    }
    
    
    
    
    


