package cn.no7player.config;

import com.sun.jna.Native;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Faith on 2016/12/2.
 */
@Configuration
public class HttpsConfig  {

    @Autowired
    private PortConfig portConfig;
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                Ssl ssl = new Ssl();
                //Server.jks中包含服务器私钥和证书
                File file=null;
                try {
                     file=Native.extractFromResourcePath("/www.wana66.com.jks");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ssl.setKeyStore(file.getPath());
                ssl.setKeyStorePassword("xyqjay817104");
                container.setSsl(ssl);
                container.setPort(portConfig.getHttpsPort());
            }
        };
    }


}
