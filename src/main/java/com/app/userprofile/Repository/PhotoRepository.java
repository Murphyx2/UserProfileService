package com.app.userprofile.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.userprofile.model.Photo;
import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {
}
