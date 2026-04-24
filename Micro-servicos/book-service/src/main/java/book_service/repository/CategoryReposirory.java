package book_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import book_service.entity.Category;



@Repository
public interface CategoryReposirory extends JpaRepository<Category,Long>{
    
}
