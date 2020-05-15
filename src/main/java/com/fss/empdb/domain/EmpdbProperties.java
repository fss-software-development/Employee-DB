package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class EmpdbProperties {
    public Spring getSpring() {
        return spring;
    }

    public void setSpring(Spring spring) {
        this.spring = spring;
    }

    Spring spring;
    public static class Spring{
        public Mail getMail() {
            return mail;
        }

        public void setMail(Mail mail) {
            this.mail = mail;
        }

        Mail mail;
        public static class Mail{
            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            String username;
        }
    }
}
