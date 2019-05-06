package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.dto.vue.CascaderOptionDto;
import com.simon.service.CityService;
import com.simon.service.CountryService;
import com.simon.service.ProvinceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * 中国地理信息
 *
 * @author simon
 * @date 2019-04-24
 **/
@Slf4j
@Api(value = "中国地理信息")
@RestController
@RequestMapping("/api/chinaRegions")
public class ChinaRegionController extends BaseController {
    /**
     * 省
     */
    @Autowired
    private ProvinceService provinceService;

    /**
     * 市
     */
    @Autowired
    private CityService cityService;

    /**
     * 区
     */
    @Autowired
    private CountryService countryService;

    @PermitAll
    @GetMapping("/provinces")
    public ResultMsg<List<CascaderOptionDto>> getProvinces(){
        return ResultMsg.success(provinceService.getCascaderOptionDtos());
    }

    @PermitAll
    @GetMapping("/provinces/{provinceId}/cities")
    public ResultMsg<List<CascaderOptionDto>> getCities(@PathVariable String provinceId){
        return ResultMsg.success(cityService.getCascaderOptionDtos(provinceId));
    }

    @PermitAll
    @GetMapping("/provinces/{provinceId}/cities/{cityId}/countries")
    public ResultMsg<List<CascaderOptionDto>> getCountries(@PathVariable String provinceId, @PathVariable String cityId){
        return ResultMsg.success(countryService.getCascaderOptionDtos(cityId));
    }
}
