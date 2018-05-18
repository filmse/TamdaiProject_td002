package com.tamdai.model.pointZNet.Dao;

import com.tamdai.model.pointZNet.entity.PUserEntity;

import java.util.List;

public interface PDao {
    PUserEntity pUserEntity(PUserEntity pUserEntity);

    List<PUserEntity> oderList();

    List<PUserEntity> oderListId(String id);

    PUserEntity pService(Long id);

    PUserEntity updatePayment(PUserEntity pUserEntity);

    List<PUserEntity> getOrdersByStatus(String status);
}
