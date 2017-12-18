package de.othr.sw.lagerhaus.ui;

import de.othr.sw.lagerhaus.entity.Adresse;
import de.othr.sw.lagerhaus.entity.Mitarbeiter;
import de.othr.sw.lagerhaus.service.MitarbeiterService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EntityTester", urlPatterns = {"/EntityTester"})
public class EntityTester extends HttpServlet{
    
    @Inject
    private MitarbeiterService service;
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EntityTester</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EntityTester at " + request.getContextPath() + "</h1>");
            
            
            Mitarbeiter antragsformular = new Mitarbeiter();
            antragsformular.setNachname("Karl");
            antragsformular.setVorname("Christian");
            Adresse adresse = new Adresse();
            adresse.setOrt("Kelheim");
            adresse.setHausnummer(8);
            adresse.setLand("Deutschland");
            adresse.setPostleitzahl(93309);
            adresse.setStrasse("Erhardiweg");
            antragsformular.setGehalt(5000);
            antragsformular.setTaetigkeit("Chef");
            
            antragsformular.setAdresse(adresse);

            Mitarbeiter neu = service.MitarbeiterAnlegen(antragsformular);
            
            out.println("Person wurde angelegt:  " + neu.toString());
            
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