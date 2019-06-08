package com.ljx.chapter4.command;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnkownCommand extends FrontCommand {

    @Override
    public void proceed() throws ServletException, IOException {
        forward("unkown");
    }
}