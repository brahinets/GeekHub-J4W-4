package com.main;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="translator", urlPatterns={"/translator/*"})
public class TranslationServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ApplicationContext context = (ApplicationContext) request.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        Translator translator = context.getBean(Translator.class);
        String textToTranslate = request.getParameter("textToTranslate");
        Dictionary.dictionariesDirPath = request.getServletContext().getRealPath("WEB-INF/dict/");
        RequestDispatcher view;

        if(null != textToTranslate){
            request.setAttribute("translatedText", translator.translate(textToTranslate));
        }

        view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }
}