package com.company_management.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailRequest {
    public final static String BUSINESS_TRIP_STAFF_TEMPLATE = "business-trip-registration.html";
    public final static String ATTENDANCE_LEAVE_PROVIDER_TEMPLATE = "attendance-leave-provider.html";
    public final static String CODE_REGISTER_PROVIDER_TEMPLATE = "code-register-provider.html";
    public final static String CAR_SERVICE_PROVIDER_TEMPLATE = "car-service-provider.html";
    public final static String NEW_BUSINESS_TRIP_CREATION_NOTIFICATION = "new-business-trip-creation-notification.html";
    public final static String BUSINESS_TRIP_ATTENDER_NOTIFICATION = "business-trip-attender-notification.html";
    public final static String BUSINESS_TRIP_PROVIDED_NOTIFICATION = "business-trip-provided-notification.html";
    public final static String BUSINESS_TRIP_EVALUATION_NOTIFICATION = "business-trip-evaluation-notificaion.html";

    public final static String INVENTORY_PERIOD_ON_CREATE_NOTIFICATION = "inventory-period-noti-on-create.html";
    public final static String INVENTORY_PERIOD_UPDATE_NOTIFICATION = "inventory-period-noti-to-update.html";

    @NotNull(message = "Thiếu địa chỉ mail người nhận")
    private String toMail;

    @NotNull(message = "Thiếu tiêu đề")
    private String title;

    private String content;

    private boolean html;

    private String attachmentName;

}
