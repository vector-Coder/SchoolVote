package cn.edu.jxau.dbn.test;

import cn.edu.jxau.dbn.Application;
import cn.edu.jxau.dbn.pojo.model.Choose;
import cn.edu.jxau.dbn.pojo.model.Teacher;
import cn.edu.jxau.dbn.pojo.model.User;
import cn.edu.jxau.dbn.service.ChooseService;
import cn.edu.jxau.dbn.service.TeacherService;
import cn.edu.jxau.dbn.service.UserService;
import cn.edu.jxau.dbn.util.JsonResult;
import cn.edu.jxau.dbn.util.QRCode.impl.QRCodeUtilImpl;
import cn.edu.jxau.dbn.util.jxauApi.JxauUtil;
import cn.edu.jxau.dbn.util.jxauApi.impl.JxauUtilImpl;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class MainTest {

    /**
     * {Success=true, Message=教务登录成功！,
     * Data={UserCode=20171905, UserName=袁飞煌, Department=软件工程1706, UserType=null, IPAdress=219.229.200.104, Class=null}}
     */

    @Test
    public void getStudentData() {
        JxauUtil jxauUtil = new JxauUtilImpl();
        try {
            Map map = jxauUtil.getStudentData("20171905", "Yuan150920");
            JSONObject jsonObject = JSONObject.fromObject(map);
            System.out.println(jsonObject.get("Data"));
            System.out.println(jsonObject.get("Success"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBaseData() {
        JxauUtil jxauUtil = new JxauUtilImpl();
        try {
            Map map = jxauUtil.getStudentBaseData(20171905, "242412");
            JSONObject jsonObject = JSONObject.fromObject(map);
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQRCode() throws Exception {
        String url = "http://219.229.222.122:825/DBNWeb/index.html";
        String imgPath = "C:\\Users\\26332\\Desktop\\img";
        File file = new File(imgPath);
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f != null) {
                // 生成的二维码的路径及名称
                String destPath = "H:\\QRCode\\" + f.getName();
                System.out.println(destPath);
                //生成二维码
                QRCodeUtilImpl.encode(url, f.getPath(), destPath, true);
                // 解析二维码
            }
        }
    }


    @Autowired
    private UserService userService;


    @Autowired
    private TeacherService teacherService;

    @Test
    public void testUser() {
        User user = new User();
        user.setUserId("20171905");
        Teacher teacher = new Teacher();
        teacher.setTeacherId(20171905);
        User res = userService.findById(user).getData();
        System.out.println(res);
    }


    @Test
    public void encodingTest() throws UnsupportedEncodingException {
        //琚侀鐓�
        //琚侀鐓�
        String str = "琚侀\uE5E3鐓�";
        String str2 = "袁飞煌";
        byte[] bytes = str.getBytes("GBK");
        System.out.println(new String(bytes,"UTF-8"));
        System.out.println(new String(str2.getBytes("UTF-8"),"GBK"));


    }


    @Test
    public void getEncoding(){
        byte[] bytes = "{\"message\":\"测试成功\",\"data\":{\"Success\":true,\"Message\":\"教务登录成功！\",\"Data\":{\"UserCode\":\"20171905\",\"UserName\":\"袁飞煌\",\"Department\":\"软件工程1706\",\"UserType\":null,\"IPAdress\":\"219.229.200.104\",\"Class\":null}},\"code\":200,\"result\":true,\"roleStatue\":null}".getBytes();
        System.out.println(bytes.length);
    }

    @Autowired
    private ChooseService chooseService;

    @Test
    public void testChoose(){
        Choose choose = new Choose();
        choose.setUserId("20171905");
        choose.setUserChooseTeacher(1);
        JsonResult jsonResult = chooseService.findById(choose);
        System.out.println(jsonResult);
    }

}
