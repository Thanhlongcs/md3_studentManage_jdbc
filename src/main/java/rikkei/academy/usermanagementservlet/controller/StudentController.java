package rikkei.academy.usermanagementservlet.controller;

import rikkei.academy.usermanagementservlet.model.Students;
import rikkei.academy.usermanagementservlet.service.IStudentService;
import rikkei.academy.usermanagementservlet.service.StudentServiceIMPL;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = {"/","/students"})
public class StudentController extends HttpServlet {
    private IStudentService studentService = new StudentServiceIMPL();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                showFormCreate(request, response);
                break;
            case "edit":
                showFormEdit(request, response);
                break;
            case "delete":
                showFormDelete(request, response);
                break;
            case "detail":
                showFormDetail(request, response);
                break;
            default:
                showListStudent(request,response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                actionCreate(req, resp);
                break;
            case "edit":
                actionEdit(req, resp);
                break;
            case "delete":
                actionDelete(req, resp);
                break;
            default:
                actionSearch(req, resp);
        }
    }

    public void destroy() {
    }
    public void showListStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Students> studentsList = studentService.findAll();
        request.setAttribute("listStudent",studentsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/list.jsp");
        dispatcher.forward(request,response);
    }
    public void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/create.jsp");
        dispatcher.forward(request,response);
    }
    public void actionCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        Students students = new Students(name,age);
        studentService.save(students);
        request.setAttribute("message","create success");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/create.jsp");
        dispatcher.forward(request,response);
    }
    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Students students = studentService.findById(id);
        System.out.println("students show: " + students);
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/edit.jsp");
        dispatcher.forward(request, response);
    }

    public void actionEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Students students = studentService.findById(id);
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        students.setName(name);
        students.setAge(age);
        System.out.println("std edit: " + students);
        studentService.save(students);
        request.setAttribute("message","edit students success");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/edit.jsp");
        dispatcher.forward(request,response);
    }
    private void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Students students =studentService.findById(id);
        System.out.println("Student show ----->"+students);
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/delete.jsp");
        dispatcher.forward(request,response);
    }
    private void actionDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deleteById(id);
        request.setAttribute("message","delete students success");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/delete.jsp");
        dispatcher.forward(request,response);
    }
    private void showFormDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Students students = studentService.findById(id);
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/detail.jsp");
        dispatcher.forward(request,response);
    }
    private void actionSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        List<Students> studentsListSearch = studentService.findByName(name);
        request.setAttribute("listStudent", studentsListSearch);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/list.jsp");
        dispatcher.forward(request, response);
        System.out.println("search----->"+studentService.findByName(name));
    }


}