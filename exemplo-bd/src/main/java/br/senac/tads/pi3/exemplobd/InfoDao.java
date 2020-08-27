/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplobd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fedts
 */
public class InfoDao {

    public List<Info> findAllPreJava7() throws SQLException {
        String sql = "SELECT ID, NOME, APELIDO, DATA_NASCIMENTO, EMAIL, TELEFONE FROM CONTATO";
        List<Info> resultados = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // ABRIR CONEXÃO COM BD
            conn = ConnectionUtilMySql8.obterConexao();

            // EXECUTAR COMANDOS SQL
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Info info = new Info();
                info.setId(rs.getInt("ID"));
                info.setNome(rs.getString("NOME"));
                info.setApelido(rs.getString("APELIDO"));
                info.setDataNascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
                info.setEmail(rs.getString("EMAIL"));
                info.setTelefone(rs.getString("TELEFONE"));
                resultados.add(info);
            }
        } finally {
            // FECHA CONEXAO COM BD INDEPENDENTEMENTE DE SUCESSO OU ERRO
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return resultados;
    }

    public List<Info> findAll() throws SQLException {
        String sql = "SELECT ID, NOME, APELIDO, DATA_NASCIMENTO, EMAIL, TELEFONE FROM CONTATO";
        List<Info> resultados = new ArrayList<>();

        // try-with-resources (após Java 7 ou superior)
        // conn/stmt/rs são auto-closeable -> São fechados automaticament ao final do bloco try
        try (Connection conn = ConnectionUtilMySql8.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Info info = new Info();
                info.setId(rs.getInt("ID"));
                info.setNome(rs.getString("NOME"));
                info.setApelido(rs.getString("APELIDO"));
                info.setDataNascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
                info.setEmail(rs.getString("EMAIL"));
                info.setTelefone(rs.getString("TELEFONE"));
                resultados.add(info);
            }
        }
        return resultados;
    }

    public Info findById(Integer id) throws SQLException {
        String sql = "SELECT ID, NOME, APELIDO, DATA_NASCIMENTO, EMAIL, TELEFONE FROM CONTATO WHERE ID=?";
        try (Connection conn = ConnectionUtilMySql8.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Info info = new Info();
                    info.setId(rs.getInt("ID"));
                    info.setNome(rs.getString("NOME"));
                    info.setApelido(rs.getString("APELIDO"));
                    info.setDataNascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
                    info.setEmail(rs.getString("EMAIL"));
                    info.setTelefone(rs.getString("TELEFONE"));
                    return info;
                }
            }
        }
        return null;
    }

    public void addNew(Info info) throws SQLException {
        String sql = "INSERT INTO CONTATO (NOME, APELIDO, DATA_NASCIMENTO, EMAIL, TELEFONE) VALUES (?,?,?,?,?)";

        try (Connection conn = ConnectionUtilMySql8.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, info.getNome());
                stmt.setString(2, info.getApelido());
                stmt.setDate(3, java.sql.Date.valueOf(info.getDataNascimento()));
                stmt.setString(4, info.getEmail());
                stmt.setString(5, info.getTelefone());

                int resultados = stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(1);
                        info.setId(idGerado);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

}
