package top.yueshushu.learn.pojo;

import lombok.Data;

/**
 * @ClassName:ResultCode
 * @Description Tool 工具的错误码信息
 * @Author 岳建立
 * @Date 2022/5/20 20:14
 * @Version 1.0
 **/
@Data
public class ResultCode {
    /**
     * 系统层面， 20000 成交， 30000提示， 50000报错.
     */
    public static final ResultCode SUCCESS = new ResultCode(true, 20000, "操作成功");
    public static final ResultCode ALERT = new ResultCode(false, 30000, "传入信息有误");
    public static final ResultCode FAIL = new ResultCode(false, 50000, "操作失败");

    public static final ResultCode INVALID_PARAM = new ResultCode(false, 3000001, "参数不正确");

    private boolean success;
    private int code;
    private String message;

    public ResultCode() {

    }

    public ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


}
