package cn.edu.jxau.dbn.web.controller;

import cn.edu.jxau.dbn.service.ChooseService;
import cn.edu.jxau.dbn.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author Vector
 * @Date 2019/11/25
 * @Desc ...
 * @Since 1.0.0
 */
@RestController
//@CrossOrigin(allowCredentials = "true")
@Validated
@RequestMapping("/Choose")
public class ChooseController {

    @Autowired
    private ChooseService chooseService;

    @GetMapping("/allChooseLimit")
    public JsonResult findAllChooseLimit(@NotNull(message = "分页下标不可为空")
                                         @Min(value = 1, message = "分下下标最小为1") Integer pageIndex) {
        return chooseService.findAllChooseLimit(pageIndex);
    }
}
