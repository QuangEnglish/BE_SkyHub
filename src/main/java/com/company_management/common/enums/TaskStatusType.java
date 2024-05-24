package com.company_management.common.enums;

import lombok.Getter;

@Getter
public enum TaskStatusType {

    MOI(1,"Mới"),
    DANGXULY(2, "Đang xử lý"),
    REVIEW(3, "Review"),
    REOPEN(4, "Reopen"),
    HOANTHANH(5, "Hoàn thành");

    private final int type;
    private final String typeName;

    TaskStatusType(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static String getNameByType(int type) {
        for (TaskStatusType status : TaskStatusType.values()) {
            if (status.getType() == type) {
                return status.getTypeName();
            }
        }
        throw new IllegalArgumentException("No matching type for " + type);
    }

}
