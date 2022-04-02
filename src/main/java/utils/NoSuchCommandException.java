package utils;

public class NoSuchCommandException extends Exception {
    /**
     * 命令异常，找不到该命令，可能是拼写错误，或者多
     * 加了空格，可以通过命令options来获取目前支持的
     * 命令
     */
    public NoSuchCommandException() {
       super("Your command is incorrect. Check if typo or type 'options' to view available command.");
    }
}
