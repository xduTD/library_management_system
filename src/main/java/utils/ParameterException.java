package utils;

public class ParameterException extends Exception {

    /**
     * 参数错误异常，即命令正常，但命令的结构/参数格式
     * 等有误，或者删除了不存在的书本，就会抛出此异常
     *****************************************
     * 由抛出此异常的方法来确定具体报错的内容
     * @param message 具体报错信息
     */
    public ParameterException(String message) {
        super("ParameterException: " + message);
    }
}
