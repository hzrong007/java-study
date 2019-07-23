package com.mysql.db;

public class DataSource {
    //    private String driver = "com.mysql.jdbc.Driver";
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://10.40.10.223:3306/kxservicewechat?autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&allowMultiQueries=true";
    private String user = "mysql";
    private String password = "mysql";
    private int coreNum = 2;
    private int step = 2;
    private int maxNum = 10;
    private int timeout = 2000;

    public DataSource() {
    }

    public DataSource(String driver, String url, String user, String password, int coreNum, int step, int maxNum, int timeout) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.coreNum = coreNum;
        this.step = step;
        this.maxNum = maxNum;
        this.timeout = timeout;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoreNum() {
        return coreNum;
    }

    public void setCoreNum(int coreNum) {
        this.coreNum = coreNum;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", coreNum=" + coreNum +
                ", step=" + step +
                ", maxNum=" + maxNum +
                ", timeout=" + timeout +
                '}';
    }
}
