/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplobd;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author fedts
 */
public class Principal {
    
    private void printInfo(Info info) {
        System.out.println("ID: " + info.getId());
        System.out.println("Nome: " + info.getNome());
        System.out.println("Apelido: " + info.getApelido());
        System.out.println("Data nascimento: " + info.getDataNascimento().format(DateTimeFormatter.ISO_DATE));
        System.out.println("E-mail: " + info.getEmail());
        System.out.println("Telefone: " + info.getTelefone());
    }
    
    private void executar() {
        Scanner entrada = new Scanner(System.in);
        InfoDao dao = new InfoDao();
        
        do {
            System.out.println("******* DIGITE UMA OPÇÃO *******");
            System.out.println("(1) Listar");
            System.out.println("(2) Buscar por ID");
            System.out.println("(3) Incluir novo");
            System.out.println("(9) SAIR");
            
            String opcaoStr = entrada.nextLine();
            try {
                int opcao = Integer.parseInt(opcaoStr);
                String valor;
                
                switch(opcao) {
                    case 1:
                        List<Info> resultados = dao.findAll();
                        if (resultados.isEmpty()) {
                            System.out.println("BANCO DE DADOS VAZIO");
                        } else {
                            System.out.println("VALORES NO BANCO DE DADOS");
                            for (Info info : resultados) {
                                printInfo(info);
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Digite o valor do ID a ser buscado");
                        valor = entrada.nextLine();
                        try {
                            int id = Integer.parseInt(valor);
                            Info info = dao.findById(id);
                            if (info != null) {
                                printInfo(info);
                            } else {
                                System.out.println("ID digitado não encontrado");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("ID digitado é inválido");
                        }
                        break;
                    case 3:
                        Info novo = new Info();
                        System.out.println("Digite o nome");
                        novo.setNome(entrada.nextLine());
                        
                        System.out.println("Digite o apelido");
                        novo.setApelido(entrada.nextLine());
                        
                        System.out.println("Digite a data de nascimento (aaaa-MM-dd)");
                        String dtNascStr = entrada.nextLine();
                        LocalDate dataNascimento = LocalDate.parse(dtNascStr, DateTimeFormatter.ISO_DATE);
                        novo.setDataNascimento(dataNascimento);
                        
                        System.out.println("Digite o e-mail");
                        novo.setEmail(entrada.nextLine());
                        
                        System.out.println("Digite o telefone (formato esperado: (99) 99999-9999");
                        novo.setTelefone(entrada.nextLine());
                        
                        dao.addNew(novo);
                        System.out.println("Informação nova adicionada com sucesso com ID " + novo.getId());                      

                        break;
                    case 9:
                        entrada.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("OPÇÃO DIGITADA INVÁLIDA");
                }
            } catch (NumberFormatException e) {
                System.out.println("OPÇÃO DIGITADA INVÁLIDA");
            } catch (SQLException e) {
                System.out.println("ERRO NO BANCO DE DADOS. " + e.getMessage());
                e.printStackTrace();
            }
        } while (true);
        
    }
    
    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.executar();
    }
}
