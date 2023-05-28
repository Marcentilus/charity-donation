package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

   private final CategoryRepository categoryRepository;

    public Optional<Category> findCategoryById(long id){
        return categoryRepository.findById(id);
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }
}
