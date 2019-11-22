package com.meng.example.agentadvance.output;

import com.meng.example.agentadvance.IOutput;

import java.util.logging.Logger;

public class JulOutput implements IOutput {

    static Logger logger = Logger.getLogger(JulOutput.class.getName());

    @Override
    public boolean out(Object value) {
        logger.info(value.toString());
        return true;
    }
}
