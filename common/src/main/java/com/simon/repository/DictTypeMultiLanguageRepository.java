package com.simon.repository;

import com.simon.model.DictTypeMultiLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author jeesun
* @date 2019-06-03
**/
@Repository
public interface DictTypeMultiLanguageRepository extends JpaRepository<DictTypeMultiLanguage, Long> {
    DictTypeMultiLanguage findByDictTypeIdAndLanguage(Long dictTypeId, String language);
}
