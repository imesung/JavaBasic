package com.mesung.demospringdi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;

public interface MyRepository extends JpaRepository<Book, Integer> {

}
