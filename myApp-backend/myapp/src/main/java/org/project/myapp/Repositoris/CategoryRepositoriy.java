package org.project.myapp.Repositoris;

import org.project.myapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoriy extends JpaRepository<Category, Long> {

}
