package com.example.greenlearner.LibraryApplication.repository;

import com.example.greenlearner.LibraryApplication.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Transactional
    @Modifying
    @Query("update Book u set u.name=?2 where u.id=?1")
    int updateAddress(long id, String name);
}
