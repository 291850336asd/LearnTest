package com.meng.example.monitor.protocol;

import java.io.Serializable;

public interface Protocol extends Serializable {

    /**
     * 传输数据的统一格式化输出
     * @return
     */
    public Serializable toExport();

}
