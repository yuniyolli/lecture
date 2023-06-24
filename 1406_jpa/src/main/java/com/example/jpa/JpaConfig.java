package com.example.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
//Audit: 감시하다 -> application 구동 과정에서
//해당 어노테이션이 추가된 설정(configuration)이 있을 때
//객체의 생성 및 수정 시각을 기록할 수 있다. (특정한 내용이 필요하지는 않음)
public class JpaConfig {

}
