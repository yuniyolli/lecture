package com.example.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
//상속 대상 클래스(다른 클래스들이 이 클래스만 상속받아도 해당 기능 수행)
//슈퍼클래스

@Data
@MappedSuperclass //다른 엔티티들이 상속받기 위한 기반클래스
//여러 Entity가 공유하는 속성을 모으기 위한
//상위 Entity임을 나타내는 어노테이션
@EntityListeners(AuditingEntityListener.class)
//Entity의 변화를 지켜볼 클래스 정의
//엔티티가 변화한다면 이 클래스가 변화과정을 지켜보고 행동할 것이다
public class BaseEntity  {
    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(updatable = true)
    private Instant updatedAt;

}
