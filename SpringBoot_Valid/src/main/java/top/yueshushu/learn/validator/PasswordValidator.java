package top.yueshushu.learn.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义密码校验器
 *
 * @author yuejianli
 * @date 2023-04-04
 */

public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {
    private Pattern pattern;

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        // 获取正则表达式
        final String regexp = constraintAnnotation.regexp();
        this.pattern = Pattern.compile(regexp);
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0){
            return false;
        }
        // 进行校验
        final Matcher matcher = this.pattern.matcher(value);
        return matcher.matches();
    }
}
