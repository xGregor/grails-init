package de.dvinci

import de.dvinci.exception.DvinciValidationException
import grails.gorm.transactions.Transactional

class EmployeeService {

    def list(params) {
        Employee.list(params)
    }

    def getJascha() {
        Employee.withCriteria {
            skills {
                eq 'name', 'Perl'
            }
        }
    }

    @Transactional
    def createEmployeeWithSkills() {
        def employee111 = new Employee(firstName: "test", lastName: "test", age: 20)
        def skill = new Skill(name: "Perl", value: 1337)
        employee111.addToSkills(skill)
        employee111.addToSkills(name: "Grails", value: 1337)
        employee111.save()
        assert employee111.id
        def x = Employee.findByAge(18, [order: "firstname", sort: "asc"])
        x.firstName = "Dominik"
        x.save()

        Employee.get(1)
    }

    @Transactional
    Employee save(EmployeeCommand employeeCommand) throws DvinciValidationException {
        if (employeeCommand.validate() && !employeeCommand.hasErrors()) {
            new Employee(
                firstName: employeeCommand.firstName,
                lastName: employeeCommand.lastName,
                salutation: employeeCommand.salutation
            ).save()
        } else {
            throw new DvinciValidationException()
        }
    }

    @Transactional
    void delete(Employee employee) {
        employee.delete()
    }
}
