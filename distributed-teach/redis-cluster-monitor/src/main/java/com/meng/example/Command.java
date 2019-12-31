package com.meng.example;

import com.google.common.collect.Lists;
import com.meng.example.enums.CommandTypeEnum;

import java.io.Serializable;
import java.util.List;

public class Command implements Serializable {

    private String business;
    private CommandTypeEnum commandName;
    private List<Object> parameters;

    public Command() {
    }

    public Command(CommandTypeEnum commandName, Object... parameters) {
        this.commandName = commandName;
        this.parameters = Lists.newArrayList();
        for (Object s : parameters) {
            this.parameters.add(s);
        }
    }

    public Command(CommandTypeEnum commandName, List<Object> parameters) {
        this.commandName = commandName;
        this.parameters = parameters;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public CommandTypeEnum getCommandName() {
        return commandName;
    }

    public void setCommandName(CommandTypeEnum commandName) {
        this.commandName = commandName;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }
}
