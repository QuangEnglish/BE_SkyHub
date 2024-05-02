package com.company_management.common.enums;

import lombok.Getter;

@Getter
public enum ContractType {

    THUVIEC(0,"THU VIEC"),
    CHINHTHUC(1, "CHINH THUC"),
    THOIVU(2, "THOI VU"),
    LAMTHEMGIO(3, "LAM THEM GIO"),
    TUDO(4, "LAO DONG TU DO");

    private final int type;
    private final String typeName;

    ContractType(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }
}
