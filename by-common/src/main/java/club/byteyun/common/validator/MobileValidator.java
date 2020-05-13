package club.byteyun.common.validator;

import club.byteyun.common.annotation.IsMobile;
import club.byteyun.common.constant.RegexpConstant;
import club.byteyun.common.utils.ByteYunUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<IsMobile, String>
{

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE;
                return ByteYunUtils.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}