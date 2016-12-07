package cn.no7player.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Faith on 2016/12/7.
 */
@Component
@ConfigurationProperties(prefix="portConfig")
@Data
public class PortConfig {

    private Integer httpPort;

    private Integer httpsPort;
}
