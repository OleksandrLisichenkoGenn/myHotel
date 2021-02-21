package com.lisichenko.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for implementing new command.
 *
 * Main interface for the Command pattern implementation.
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
