package com.meng.example.agentadvance.filter;

import com.meng.example.agentadvance.IFilter;
import com.meng.example.agentadvance.common.JsonUtil;

import java.io.Serializable;

public class JSONFormat implements IFilter {
    @Override
    public Object doFilter(Object value) {
        if (value == null)
            return null;
        else if (!(value instanceof Serializable)) {
            return null;
        }
        return JsonUtil.toJson(value);
    }
}
