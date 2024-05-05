package com.company_management.service;

import com.company_management.model.request.MailRequest;

public interface EmailService {
    void send(MailRequest mail);
}
