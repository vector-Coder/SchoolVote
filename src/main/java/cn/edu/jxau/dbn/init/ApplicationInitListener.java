package cn.edu.jxau.dbn.init;


import cn.edu.jxau.dbn.config.GlobalConfig;
import cn.edu.jxau.dbn.global.GlobalData;
import cn.edu.jxau.dbn.pojo.model.Config;
import cn.edu.jxau.dbn.pojo.model.Teacher;
import cn.edu.jxau.dbn.service.ConfigService;
import cn.edu.jxau.dbn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ApplicationInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigService configService;


    @Autowired
    private TeacherService teacherService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {//保证只执行一次
            //系统初始化完成的时候加载权限信息
            Config config = configService.findDefaultConfig().getData();
            if(config == null){
                System.err.println("初始化失败");
            }else{
                GlobalConfig.init(config);
            }
            List<Teacher> teacherList = teacherService.findAllData().getData();
            if(teacherList == null || teacherList.isEmpty()){
                System.err.println("老师数据初始化失败");
            }else{
                GlobalData.init(teacherList);
            }
        }
    }

}
