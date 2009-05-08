package com.wendal.java.dex.decomplier.toolkit;

public class Logger {
    private static final Logger logger = new Logger();

    public static final int ERROR = 5;
    public static final int WARN = 4;
    public static final int INFO = 3;
    public static final int DEBUG = 2;

    private Logger() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public void e(String tag, String msg) {
        e(tag, msg, null);
    }

    public void e(String tag, String msg, Throwable t, Object... objs) {
        log(ERROR, tag, msg, t, objs);
    }

    public void w(String tag, String msg) {
        w(tag, msg, null);
    }

    public void w(String tag, String msg, Throwable t, Object... objs) {
        log(WARN, tag, msg, t, objs);
    }

    public void i(String tag, String msg) {
        i(tag, msg, null);
    }

    public void i(String tag, String msg, Throwable t, Object... objs) {
        log(INFO, tag, msg, t, objs);
    }

    public void d(String tag, String msg) {
        d(tag, msg, null);
    }

    public void d(String tag, String msg, Throwable t, Object... objs) {
        log(DEBUG, tag, msg, t, objs);
    }

    private void log(int level, String tag, String msg, Throwable t,
            Object... objs) {
        StringBuilder sb = new StringBuilder();
        switch (level) {
        case ERROR:
            sb.append("[Error]");
            break;
        case WARN:
            sb.append("[Warn] ");
            break;
        case INFO:
            sb.append("[Info] ");
            break;
        case DEBUG:
            sb.append("[Debug]");
            break;
        default:
            sb.append("[Log]");
            break;
        }
        if(tag != null){
            sb.append(tag).append(" ");
        }
        if (msg != null) {
            sb.append(msg).append(" ");
        }

        if (t != null) {
            t.printStackTrace();
        }
        for (Object object : objs) {
            sb.append(object).append(" ");
        }
        System.out.println(sb.toString());
    }
}
