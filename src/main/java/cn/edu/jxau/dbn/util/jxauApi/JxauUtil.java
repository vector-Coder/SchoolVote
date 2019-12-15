package cn.edu.jxau.dbn.util.jxauApi;

import java.io.IOException;
import java.util.Map;

public interface JxauUtil {

    Map getStudentData(String userCode, String password) throws IOException;


    Map getStudentBaseData(Integer userCode, String password) throws IOException;
}
