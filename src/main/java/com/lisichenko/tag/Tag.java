package com.lisichenko.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Tag for sending 'name' of account to view.
 */
public class Tag extends TagSupport {
    private static final Logger LOG = Logger.getLogger(Tag.class);

    @Override
    public int doStartTag() {
        LOG.debug("Tag Start");
        try {
            String name = (String) pageContext.getSession().getAttribute("name");
            pageContext.getOut().print(name);
        } catch (IOException e) {
            LOG.error("IO exception == > " + e);
        }
        LOG.debug("Tag is finish");
        return EVAL_BODY_INCLUDE;
    }
}
