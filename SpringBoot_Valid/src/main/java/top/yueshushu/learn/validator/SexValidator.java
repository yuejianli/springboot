package top.yueshushu.learn.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 性别验证器
 *
 * @author yuejianli
 * @date 2023-04-04
 */

public class SexValidator implements ConstraintValidator<Sex, String> {

    private static final String MAN = "男";
    private static final String WOMAN = "女";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 不为null才进行校验
        if (value != null) {
            if(!Objects.equals(value,MAN) && !Objects.equals(value,WOMAN)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}