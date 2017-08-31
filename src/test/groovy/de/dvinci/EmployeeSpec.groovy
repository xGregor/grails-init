package de.dvinci

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class EmployeeSpec extends Specification implements DomainUnitTest<Employee> {

    void validateConstraints(obj, field, error) {
        def validated = obj.validate()
        if (error && error != 'valid') {
            assert !validated
            assert obj.errors[field]
            assert error == obj.errors[field] || (obj.errors[field] instanceof String && obj.errors[field].contains(error)) || (obj.errors[field].hasProperty('codes') && obj.errors[field].codes.any {
                it.contains(error)
            })
        } else {
            assert !obj.errors[field]
        }
    }

    @Unroll
    void "Validate Employee constraints -> field=#field with value=#val has error=#error"() {
        when:
        def obj = new Employee()
        obj."$field" = val

        then:
        validateConstraints(obj, field, error)

        where:
        error      | field       | val
        'nullable' | "firstName" | null
        'minSize'  | "firstName" | "a"
        'valid'    | "firstName" | "First-name"
    }
}
