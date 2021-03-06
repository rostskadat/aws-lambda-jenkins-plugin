package com.xti.jenkins.plugin.awslambda.service;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class JenkinsLoggerTest {
    private ByteArrayOutputStream logStream = new ByteArrayOutputStream();
    private JenkinsLogger jenkinsLogger;
    private final String newLine = System.getProperty("line.separator");

    @Before
    public void setUp() throws Exception {
        jenkinsLogger = new JenkinsLogger(new PrintStream(logStream));
    }

    @Test
    public void testLog() throws Exception {
        jenkinsLogger.log("test");
        assertEquals("test" + newLine, logStream.toString());
    }

    @Test
    public void testLogMultiLine() throws Exception {
        jenkinsLogger.log("test");
        jenkinsLogger.log("also");
        assertEquals("test" + newLine + "also" + newLine, logStream.toString());
    }

    @Test
    public void testLogMask() throws Exception {
        jenkinsLogger.log("test: %s", "something");
        assertEquals("test: something" + newLine, logStream.toString());
    }

    @Test
    public void testLogMaskNull() throws Exception {
        jenkinsLogger.log(null);
        assertEquals("", logStream.toString());
    }
}