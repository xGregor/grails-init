package de.dvinci

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class EmployeeSpec extends Specification implements DomainUnitTest<Employee> {

    @Unroll
    void "Validate Employee constraints -> property=#property with value=#value has error=#expectedCode"() {
        expect:
        ConstraintSpecUtil.constrainedWith(new Employee(), property, value, expectedCode)

        where:
        expectedCode     | property    | value
        'nullable'       | "firstName" | null
        'minSize.notmet' | "firstName" | "a"
        null             | "firstName" | "First-name"
    }
}
