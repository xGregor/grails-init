package de.dvinci

import de.dvinci.exception.DvinciValidationException
import grails.gorm.transactions.Transactional

class EmployeeService {

    def list(params) {
        Employee.list(params)
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
