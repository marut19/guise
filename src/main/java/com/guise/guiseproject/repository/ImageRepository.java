package com.guise.guiseproject.repository;

import com.guise.guiseproject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Repository that consists of Image related data operations.
 */
@Transactional
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "FROM Image u WHERE LOWER(u.name)=:imageName")
    Image getByName(@Param("imageName") String imageName);
}
