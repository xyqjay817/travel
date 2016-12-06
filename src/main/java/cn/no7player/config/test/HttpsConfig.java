package cn.no7player.config.test;

import com.sun.jna.Native;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Faith on 2016/12/2.
 */
@Configuration
@Profile("test")
public class HttpsConfig  {
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
                container.setPort(9443);
            }
        };
    }


}
