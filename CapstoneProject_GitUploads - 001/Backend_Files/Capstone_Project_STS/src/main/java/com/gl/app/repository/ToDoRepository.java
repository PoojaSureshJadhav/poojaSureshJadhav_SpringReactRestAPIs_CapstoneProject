package com.gl.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.app.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{

}
