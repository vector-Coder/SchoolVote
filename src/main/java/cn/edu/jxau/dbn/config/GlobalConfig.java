package cn.edu.jxau.dbn.config;

import cn.edu.jxau.dbn.pojo.model.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author Vector
 * @Date 2019/11/18
 * @Desc ...
 * @Since 1.0.0
 */
@Configuration
@PropertySource("classpath:config.properties")
public class GlobalConfig {
    //同一个IP最大使用数
    public static int MAX_IP_USE_COUNT = 10;
    //最大投票数量
    public static int MAX_VOTE_COUNT = 10;
    //最大投票次数
    public static int MAX_VOTE_LIMIT = 1;
    //最小投票数量
    public static int MIN_VOTE_COUNT = 2;
    //系统配置记录的主键ID
    public static int CONFIG_ID = 1;

    //配置类
    private static Config config;

    public static Config getConfig() {
        return config;
    }

    public static void init(Config config) {
        GlobalConfig.config = config;
        GlobalConfig.MAX_IP_USE_COUNT = config.getConfigMaxIPUseCount();
        GlobalConfig.MAX_VOTE_COUNT = config.getConfigMaxVoteCount();
        GlobalConfig.MAX_VOTE_LIMIT = config.getConfigMaxVoteLimit();
        GlobalConfig.MIN_VOTE_COUNT = config.getConfigMinVoteCount();
    }


    @Value("${DBN.CONFIG_ID}")
    public void setConfigId(Integer configId){
        GlobalConfig.CONFIG_ID = configId;
    }

}
