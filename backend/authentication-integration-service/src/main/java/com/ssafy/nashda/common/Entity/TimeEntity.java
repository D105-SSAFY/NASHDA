package com.ssafy.nashda.common.Entity;

import lombok.Getter;
import net.bytebuddy.asm.Advice;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity {

    @CreatedDate
    private LocalDateTime createOn;

    @LastModifiedDate
    private LocalDateTime updateOn;

    @PrePersist
    public void onPrePersist() {
        this.createOn = LocalDateTime.now();
        this.updateOn = this.createOn;
    }

    @PreUpdate
    public void onPreUpdate(){
        this.updateOn = LocalDateTime.now();
    }
}
