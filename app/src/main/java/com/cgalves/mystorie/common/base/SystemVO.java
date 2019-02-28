package com.cgalves.mystorie.common.base;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author R/GA
 *
 */

public class SystemVO implements Serializable {

    public static final String OK_CODE_200 = "200";
    public static final String OK = "OK";
    public static final String OK_CODE_201 = "201";
    public static final String OK_CREATE = "Created";

    public String code;
    public String status;
    public String message;
    public List<String> messages;

    public String concatMessages() {

        String msgsReturn = "No Messages";

        if (!this.messages.isEmpty()) {
            msgsReturn = "";
            if (messages.size() == 1) {
                return messages.get(0);
            } else {
                for (String msg : this.messages) {
                    msgsReturn += "-" + msg + "-\n";
                }
            }
        }

        return msgsReturn;
    }

    public SystemVO(String code, List<String> messages) {
        this.code = code == null? "200" : code;
        this.messages = messages;
    }

    public SystemVO() {
        this.code = "200";
        this.message = "";
        this.status = "";
        this.messages = null;
    }
}
