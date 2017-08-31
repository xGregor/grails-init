package de.dvinci

class ConstraintSpecUtil {

    static boolean constrainedWith(obj, String property, value, String code) {
        obj."${property}" = value
        obj.validate()
        assert obj.errors.getFieldError(property)?.code == code
        true
    }
}
