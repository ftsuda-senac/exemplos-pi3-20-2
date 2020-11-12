/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploupload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
@MultipartConfig(maxFileSize = 20848820) // 5MB == 20848820 bytes == 5 * 1024 * 1024
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Part arquivo = request.getPart("arquivo");
        // Definir local onde arquivo vai ser salvo
        String diretorioDestino = "C:/senac/tads/uploads-pi3";
        // Recupera nome original do arquivo enviado
        String nomeArquivo = Paths.get(arquivo.getSubmittedFileName()).getFileName().toString();
        
        InputStream conteudoArquivo = arquivo.getInputStream();

        Path destino = Paths.get(diretorioDestino + "/" + nomeArquivo);
        Files.copy(conteudoArquivo, destino);
        
        // MOSTRA MSG USANDO POST-REDIRECT-GET
        HttpSession sessao = request.getSession();
        sessao.setAttribute("msgSucesso", "Arquivo salvo com sucesso");
        response.sendRedirect("upload");

    }


}
