package rikkei.academy.usermanagementservlet.service;

import rikkei.academy.usermanagementservlet.model.Students;

import java.util.List;

public interface IStudentService {
    List<Students> findAll();
    void save (Students students);
    Students findById(int id);
    void deleteById(int id);
    List<Students> findByName(String name);
}
