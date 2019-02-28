package com.cgalves.mystorie.common.base;

import java.io.Serializable;

/**
 *
 * @author R/GA
 *
 */

public class BaseCallback implements Serializable {

    public SystemVO system;

    public String customMessage;

    public Boolean isOK() {
        return (system != null && system.code != null &&
                (system.code.equalsIgnoreCase(SystemVO.OK_CODE_200) || system.code.equalsIgnoreCase(SystemVO.OK_CODE_201)) ? Boolean.TRUE : Boolean.FALSE);
        //return (system != null && (system.code.equalsIgnoreCase(SystemVO.OK_CODE_200) || system.code.equalsIgnoreCase(SystemVO.OK_CODE_201)) ? Boolean.TRUE : Boolean.FALSE);
    }

    public Boolean isCreate() {
        return ((system != null)); //&& system.message.equalsIgnoreCase(SystemDnaVO.OK_CREATE)) ? Boolean.TRUE : Boolean.FALSE);
    }

}
