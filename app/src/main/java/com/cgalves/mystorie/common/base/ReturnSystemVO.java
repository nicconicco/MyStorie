package com.cgalves.mystorie.common.base;

import java.io.Serializable;

/**
 *
 * @author R/GA
 *
 */

public class ReturnSystemVO extends BaseCallback implements Serializable {

    //TODO: Review then made to meet temporarily a return of Capcom for payments.
    public int codigo;
    public String mensagem;
    public String requestURI;
    public ReturnSystemVO(SystemVO system) {
        this.system = system;
    }

}
