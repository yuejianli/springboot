package top.yueshushu.learn.stock.util;

import java.util.NoSuchElementException;

public class StockConsts {

    public static final String KEY_AUTH_USER_ID = "user_id";

    public static final String KEY_AUTH_TOKEN = "auth-token";

    public static final String CACHE_KEY_PREFIX = "stock:";

    private static final String CACHE_KEY_DATA_PREFIX = CACHE_KEY_PREFIX + "data:";
    public static final String CACHE_KEY_DATA_STOCK = StockConsts.CACHE_KEY_DATA_PREFIX + "stock";

    private static final String CACHE_KEY_CONFIG_PREFIX = CACHE_KEY_PREFIX + "config:";
    public static final String CACHE_KEY_CONFIG_ROBOT = StockConsts.CACHE_KEY_CONFIG_PREFIX + "robot";

    private static final String CACHE_KEY_TRADE_PREFIX = CACHE_KEY_PREFIX + "trade:";
    public static final String CACHE_KEY_TRADE_USER = StockConsts.CACHE_KEY_TRADE_PREFIX + "tradeUser";
    public static final String CACHE_KEY_TRADE_METHOD = StockConsts.CACHE_KEY_TRADE_PREFIX + "tradeMethod";

    public static final String CACHE_KEY_TOKEN = CACHE_KEY_PREFIX + "auth:token";

    public static final String CACHE_KEY_TRADE_STRATEGY = CACHE_KEY_PREFIX + "trade:tradeStrategy";

    public static final long DURATION_REDIS_DEFAULT = 3600 * 24 * 2;
}
