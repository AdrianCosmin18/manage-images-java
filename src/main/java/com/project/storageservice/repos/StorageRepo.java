package com.project.storageservice.repos;

import com.project.storageservice.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface StorageRepo extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String name);
}
