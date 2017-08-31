package de.dvinci

import de.dvinci.exception.DvinciValidationException
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.*

class EmployeeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def employeeService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def employeeList = employeeService.list(params)
        respond employeeList, model: [employeeCount: employeeList.size()]
    }

    def show(Employee employee) {
        respond employee
    }

    def create(EmployeeCommand employeeCommand) {
        employeeCommand.clearErrors()
        [employeeCommand: employeeCommand]
    }

    def save(EmployeeCommand employeeCommand) {
        try {
            def employee = employeeService.save(employeeCommand)
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                    redirect employee
                }
                '*' { respond employee, [status: CREATED] }
            }
        } catch (DvinciValidationException exp) {
            render(view: 'create', model: [employeeCommand: employeeCommand, errors: employeeCommand.errors])
            return
        }

    }

    def edit(Employee employee) {
        respond employee
    }

    @Transactional
    def update(Employee employee) {
        if (employee == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (employee.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond employee.errors, view: 'edit'
            return
        }

        employee.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                redirect employee
            }
            '*' { respond employee, [status: OK] }
        }
    }

    def delete(Employee employee) {

        if (employee == null) {
            notFound()
            return
        }

        employeeService.delete(employee)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
