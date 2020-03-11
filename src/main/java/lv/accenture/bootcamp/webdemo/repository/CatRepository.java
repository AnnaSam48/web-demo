package lv.accenture.bootcamp.webdemo.repository;

import lv.accenture.bootcamp.webdemo.model.Cat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    //      SELECT * FROM Cat WHERE Cat.nickname like %nickname%
    @Query("SELECT c FROM Cat c WHERE c.nickname LIKE %:nickname%")
    List<Cat> findByNickname(@Param(value = "nickname") String nickname);

    List<Cat> findByNicknameContainingIgnoreCase(String nickname);

}
