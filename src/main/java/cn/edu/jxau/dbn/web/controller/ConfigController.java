package cn.edu.jxau.dbn.web.controller;

import cn.edu.jxau.dbn.pojo.model.Config;
import cn.edu.jxau.dbn.service.ConfigService;
import cn.edu.jxau.dbn.service.TeacherService;
import cn.edu.jxau.dbn.util.JsonResult;
import cn.edu.jxau.dbn.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Vector
 * @Date 2019/11/22
 * @Desc ...
 * @Since 1.0.0
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@Validated
@RequestMapping("/Config")
public class ConfigController {

    @Autowired
    private ConfigService configService;


    @GetMapping("/reloadConfig")
    public JsonResult reloadSystemConfig() {
        return configService.reloadConfig();
    }


//    @GetMapping("/update")
//    public JsonResult updateConfig(@NotNull(message = "配置信息不可为空") Config config) {
//        return configService.updateById(config);
//    }

    @GetMapping("/defaultConfig")
    public JsonResult findDefaultConfig() {
        return configService.findDefaultConfig();
    }
}
