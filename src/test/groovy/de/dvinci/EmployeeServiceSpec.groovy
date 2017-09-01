package de.dvinci

import grails.testing.gorm.DomainUnitTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class EmployeeServiceSpec extends Specification implements ServiceUnitTest<EmployeeService>, DomainUnitTest<Employee> {

    def setup() {
        new Employee(lastName: "LastName", firstName: "firstName", salutation: Employee.Salutation.MS).save(flush: true)
    }

    void "list() should return all employees"() {
        when:
        def result = service.list([:])

        then:
        result.size() == 1
    }

    void "save should create Employee when command is valid"() {
        given: "a command"
        def command = new EmployeeCommand(firstName: "TestName", lastName: "LastName")

        when: "calling method save"
        def result = service.save(command)

        then: "object is persisted"
        result.id

        and: "properties are .."
        result.lastName == "LastName"
        result.firstName == "TestName"
    }
}
