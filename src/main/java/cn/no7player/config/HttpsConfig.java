package cn.no7player.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Faith on 2016/12/2.
 */
@Configuration
@Profile("pro")
public class HttpsConfig {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                Ssl ssl = new Ssl();
                //Server.jks中包含服务器私钥和证书
                ssl.setKeyStore("classpath:529782312%40qq.com.p12");
                ssl.setKeyStorePassword("xyqjay817104");
                container.setSsl(ssl);
                container.setPort(8443);
            }
        };
    }
}
