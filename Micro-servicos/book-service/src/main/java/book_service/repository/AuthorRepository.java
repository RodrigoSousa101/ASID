package book_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book_service.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    
}
