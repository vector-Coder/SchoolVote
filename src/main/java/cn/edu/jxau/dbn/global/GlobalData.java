package cn.edu.jxau.dbn.global;

import cn.edu.jxau.dbn.exception.BaseException;
import cn.edu.jxau.dbn.exception.NotInitException;
import cn.edu.jxau.dbn.pojo.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Vector
 * @Date 2019/11/21
 * @Desc ...
 * @Since 1.0.0
 */
@Component
public class GlobalData {

    private static volatile Map<Integer, Teacher> teacherMap;

    public static void init(List<Teacher> teachers) {
        ConcurrentHashMap<Integer, Teacher> concurrentHashMap = new ConcurrentHashMap<>(teachers.size());
        for (Teacher teacher : teachers) {
            concurrentHashMap.put(teacher.getTeacherId(), teacher);
        }
        GlobalData.teacherMap = concurrentHashMap;
        System.err.println("老师数据初始化完成");
    }

    public static List<Teacher> getAllTeacherData() throws BaseException {
        if (GlobalData.teacherMap == null) {
            throw new NotInitException("请求非法，数据未初始化");
        }
        return new ArrayList<Teacher>(GlobalData.teacherMap.values());
    }
}
