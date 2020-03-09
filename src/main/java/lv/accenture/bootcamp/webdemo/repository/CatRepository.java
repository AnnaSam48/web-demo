package lv.accenture.bootcamp.webdemo.repository;

import lv.accenture.bootcamp.webdemo.model.Cat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatRepository {

    private List<Cat> cats = new ArrayList<>();

    public CatRepository() {
        cats.add(new Cat(1L, "Muris", 3, "https://icatcare.org/app/uploads/2018/06/Layer-1704-1920x840.jpg"));
        cats.add(new Cat(2L, "Pucinsh", 5, "https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/327/327448/maine-coon-cat.jpg?w=1155&h=1537"));
    }

    public List<Cat> findAll() {
        return cats;
    }

    public void add(Cat cat) {
        // find biggest id and + 1
        // give new id to incoming cat
        // add incoming cat to list
        Long maxId = 0L;
        for(Cat c: cats) {
            if(c.getId() > maxId) {
                maxId = c.getId();
            }
        }
        maxId = maxId + 1;
        cat.setId(maxId);

        cats.add(cat);
    }

    public Cat findById(Long id) {
        for (Cat cat : cats) {
            if (cat.getId().equals(id)) {
                return cat;
            }
        }
        throw new IllegalArgumentException("Cat by id : " + id + " not found!");
    }

    public void update(Cat editedCat) {
        for(int i = 0; i < cats.size(); i++) {
            Cat cat = cats.get(i);
            if (cat.getId().equals(editedCat.getId())) {
                cats.set(i, editedCat);
                return;
            }
        }
        throw new IllegalArgumentException("Cat by id : " + editedCat.getId() + " not found!");
    }

    public void delete(Long id) {
        Cat catById = findById(id);
        cats.remove(catById);
    }
}
