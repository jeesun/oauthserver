package com.simon.mapper;

import com.simon.model.QrCode;

public interface QrCodeMapper {
    QrCode selectByPrimaryKey(Long id);
}