package cn.edu.jxau.dbn.pojo.model;

import cn.edu.jxau.dbn.pojo.BaseModel;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
public class IP extends BaseModel {
    //IP地址
    private String ipId;
    //使用次数
    private Integer ipUseCount;

    public String getIpId() {
        return ipId;
    }

    public void setIpId(String ipId) {
        this.ipId = ipId;
    }


    public Integer getIpUseCount() {
        return ipUseCount;
    }

    public void setIpUseCount(Integer ipUseCount) {
        this.ipUseCount = ipUseCount;
    }

    @Override
    public String toString() {
        return "IP{" +
                "ipId='" + ipId + '\'' +
                ", ipUseCount=" + ipUseCount +
                '}';
    }
}
