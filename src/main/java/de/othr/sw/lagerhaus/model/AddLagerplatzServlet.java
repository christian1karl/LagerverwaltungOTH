package de.othr.sw.lagerhaus.model;

import de.othr.sw.lagerhaus.entity.Lagerplatz;
import de.othr.sw.lagerhaus.enums.Lagerstatus;
import de.othr.sw.lagerhaus.entity.Lagerware;
import de.othr.sw.lagerhaus.service.LagerplatzService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Lagerplatz", urlPatterns = {"/Lagerplatz"})
public class AddLagerplatzServlet extends HttpServlet{
    
    @Inject
    private LagerplatzService service;
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Lagerplatz</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Lagerplatz at " + request.getContextPath() + "</h1>");
            
            
            Lagerplatz antragsformular = new Lagerplatz();
            antragsformular.setLagerpreis(100);
            antragsformular.setLagerstatus(Lagerstatus.Frei);
            Lagerware ware = new Lagerware();
            ware.setLagerplatz(antragsformular);
            ware.setWarenbezeichnung("Holzkohle");
            List<Lagerware> waren = new ArrayList();
            waren.add(ware);
            antragsformular.setLagerwaren(waren);
            Lagerplatz neu = service.LagerplatzAnlegen(antragsformular);
            
            out.println("Lagerplatz wurde angelegt:  " + neu.toString());
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}