package com.tamdai.model.pointZNet.repository;

import com.tamdai.model.pointZNet.entity.PUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PUserRepository extends JpaRepository<PUserEntity, Long> {
    List<PUserEntity> findPUserEntityByUserId(String id);

    PUserEntity findPUserEntityById(Long id);

    List<PUserEntity> findPUserEntityByStatus(String status);
}
