package com.example.demo.JWTSecurity.Enum;

import lombok.Getter;

/**
 * 管理员的状态
 */
@Getter
public enum ManagerStatus {

    OK("ENABLE", "启用"), FREEZED("LOCKED", "冻结"), DELETED("DELETED", "被删除");

    String code;
    String message;

    ManagerStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getDescription(String value) {
        if (value == null) {
            return "";
        } else {
            for (ManagerStatus ms : ManagerStatus.values()) {
                if (ms.getCode().equals(value)) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}