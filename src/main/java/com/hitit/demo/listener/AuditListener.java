package com.hitit.demo.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditListener {

    private static final Logger logger = LoggerFactory.getLogger(AuditListener.class);

    @PrePersist
    public void beforeSave(Object entity) {
        logger.info("Saving entity of type {}: {}", entity.getClass().getSimpleName(), entity.toString());
    }

}
