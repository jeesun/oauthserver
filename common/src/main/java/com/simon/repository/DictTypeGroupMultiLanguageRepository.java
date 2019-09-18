package com.simon.repository;

import com.simon.model.DictTypeGroupMultiLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author jeesun
* @date 2019-06-03
**/
@Repository
public interface DictTypeGroupMultiLanguageRepository extends JpaRepository<DictTypeGroupMultiLanguage, Long> {
    DictTypeGroupMultiLanguage findByDictTypeGroupIdAndLanguage(Long dictTypeGroupId, String language);

    void deleteByDictTypeGroupId(Long dictTypeGroupId);
}
