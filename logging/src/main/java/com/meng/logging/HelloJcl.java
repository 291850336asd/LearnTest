package com.meng.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloJcl {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(HelloJcl.class);
        log.error("error message jcl");
    }
}
