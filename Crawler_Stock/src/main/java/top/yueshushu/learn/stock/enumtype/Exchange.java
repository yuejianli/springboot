package top.yueshushu.learn.stock.enumtype;

import java.util.NoSuchElementException;

/**
 * @ClassName:Exchange
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/7 16:00
 * @Version 1.0
 **/
public enum Exchange {
    SH("sh"), SZ("sz");
    private String name;

    private Exchange(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isSh() {
        return name.equals("sh");
    }

    public boolean isSz() {
        return name.equals("sh");
    }

    public static Exchange valueOfName(String name) {
        for (Exchange exchange : Exchange.values()) {
            if (exchange.name.equals(name)) {
                return exchange;
            }
        }
        throw new NoSuchElementException("no exchange named " + name);
    }

}
