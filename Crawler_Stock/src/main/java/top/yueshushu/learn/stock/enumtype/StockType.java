package top.yueshushu.learn.stock.enumtype;

/**
 * @ClassName:StockType
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/7 16:01
 * @Version 1.0
 **/
public enum StockType {
    A(0), Index(1), ETF(2), B(3);
    private int value;

    private StockType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}