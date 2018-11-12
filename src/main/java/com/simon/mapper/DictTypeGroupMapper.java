package com.simon.mapper;

import com.simon.common.mapper.MyMapper;
import com.simon.model.DictTypeGroup;

import java.util.List;

public interface DictTypeGroupMapper extends MyMapper<DictTypeGroup> {
    List<DictTypeGroup> getAll();
}