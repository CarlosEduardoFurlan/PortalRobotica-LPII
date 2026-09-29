package br.edu.ifms.robotica.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/foto-estudante")
public class FotoEstudanteServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String arquivo =
                request.getParameter("arquivo");

        if (arquivo == null || arquivo.isBlank()) {
            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND
            );
            return;
        }

        // Evita caminhos como ../../arquivo
        arquivo = new File(arquivo).getName();

        String pastaUploads =
                System.getProperty("user.home")
                + File.separator
                + "portal-robotica"
                + File.separator
                + "uploads"
                + File.separator
                + "estudantes";

        File foto = new File(
                pastaUploads,
                arquivo
        );

        if (!foto.exists() || !foto.isFile()) {
            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND
            );
            return;
        }

        String nome = foto.getName().toLowerCase();

        if (nome.endsWith(".png")) {
            response.setContentType("image/png");

        } else if (nome.endsWith(".webp")) {
            response.setContentType("image/webp");

        } else {
            response.setContentType("image/jpeg");
        }

        try (FileInputStream input =
                     new FileInputStream(foto)) {

            input.transferTo(
                    response.getOutputStream()
            );
        }
    }
}