package ${basePackage}.controller;

import ${basePackage}.common.controller.BaseController;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.repository.${modelNameUpperCamel}Repository;

import ${basePackage}.common.domain.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Locale;

/**
* @author ${AUTHOR}
* @create ${CREATE}
**/

@Slf4j
@Api(value = "xx", description = "xx")
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller extends BaseController{

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

}