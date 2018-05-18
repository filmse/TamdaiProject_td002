package com.tamdai.model.pointZNet.service;

import com.tamdai.model.pointZNet.entity.PUserEntity;

import java.util.List;

public interface PService {
    PUserEntity pUserOrderCreate(PUserEntity pUserEntity);

    List<PUserEntity> oderList();

    List<PUserEntity> oderListId(String id);

    PUserEntity getPayment(Long id);

    PUserEntity updatePayment(PUserEntity pUserEntity);

    List<PUserEntity> getOrdersByStatus(String status);

    PUserEntity confirmBankStatement(PUserEntity pUserEntity);
}
