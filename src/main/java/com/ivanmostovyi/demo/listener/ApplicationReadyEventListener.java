package com.ivanmostovyi.demo.listener;

import com.ivanmostovyi.demo.service.AdminUserInitializer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private AdminUserInitializer adminUserInitializer;

    public ApplicationReadyEventListener(AdminUserInitializer adminUserInitializer) {
        this.adminUserInitializer = adminUserInitializer;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        adminUserInitializer.createAdminUserIfNotExists();
    }

}
