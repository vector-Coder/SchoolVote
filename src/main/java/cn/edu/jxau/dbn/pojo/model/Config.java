package cn.edu.jxau.dbn.pojo.model;

import cn.edu.jxau.dbn.pojo.BaseModel;

/**
 * @Author Vector
 * @Date 2019/11/21
 * @Desc ...
 * @Since 1.0.0
 */
public class Config extends BaseModel {
    //配置ID
    private Integer configId;
    //配置标题
    private String configTitle;
    //项目的介绍文字
    private String configIntroduce;
    //配置图片
    private String configUrlImg;
    //单人最大投票总数
    private Integer configMaxVoteCount;
    //单人投票最小数
    private Integer configMinVoteCount;
    //最大投票次数
    private Integer configMaxVoteLimit;
    //IP最大使用次数
    private Integer configMaxIPUseCount;


    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getConfigTitle() {
        return configTitle;
    }

    public void setConfigTitle(String configTitle) {
        this.configTitle = configTitle;
    }

    public String getConfigUrlImg() {
        return configUrlImg;
    }

    public void setConfigUrlImg(String configUrlImg) {
        this.configUrlImg = configUrlImg;
    }

    public Integer getConfigMaxVoteCount() {
        return configMaxVoteCount;
    }

    public void setConfigMaxVoteCount(Integer configMaxVoteCount) {
        this.configMaxVoteCount = configMaxVoteCount;
    }

    public Integer getConfigMinVoteCount() {
        return configMinVoteCount;
    }

    public void setConfigMinVoteCount(Integer configMinVoteCount) {
        this.configMinVoteCount = configMinVoteCount;
    }

    public Integer getConfigMaxVoteLimit() {
        return configMaxVoteLimit;
    }

    public void setConfigMaxVoteLimit(Integer configMaxVoteLimit) {
        this.configMaxVoteLimit = configMaxVoteLimit;
    }

    public String getConfigIntroduce() {
        return configIntroduce;
    }

    public void setConfigIntroduce(String configIntroduce) {
        this.configIntroduce = configIntroduce;
    }


    public Integer getConfigMaxIPUseCount() {
        return configMaxIPUseCount;
    }

    public void setConfigMaxIPUseCount(Integer configMaxIPUseCount) {
        this.configMaxIPUseCount = configMaxIPUseCount;
    }

    @Override
    public String toString() {
        return "Config{" +
                "configId=" + configId +
                ", configTitle='" + configTitle + '\'' +
                ", configIntroduce='" + configIntroduce + '\'' +
                ", configUrlImg='" + configUrlImg + '\'' +
                ", configMaxVoteCount=" + configMaxVoteCount +
                ", configMinVoteCount=" + configMinVoteCount +
                ", configMaxVoteLimit=" + configMaxVoteLimit +
                ", configMaxIPUseCount=" + configMaxIPUseCount +
                '}';
    }
}
