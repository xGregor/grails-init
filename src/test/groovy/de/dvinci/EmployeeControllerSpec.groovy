package de.dvinci

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class EmployeeControllerSpec extends Specification implements ControllerUnitTest<EmployeeController>, DomainUnitTest<Employee> {

    def setup() {
        controller.employeeService = Mock(EmployeeService)
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index(50)

        then: "The model is correct"
        1 * controller.employeeService.list(_) >> []
        !model.employeeList
        model.employeeCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        def model = controller.create(new EmployeeCommand())

        then: "The model is correctly created"
        model.employeeCommand != null
    }
/*
    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def employee = new Employee()
        employee.validate()
        controller.save(employee)

        then: "The create view is rendered again with the correct model"
        model.employee != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        employee = new Employee(params)

        controller.save(employee)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/employee/show/1'
        controller.flash.message != null
        Employee.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def employee = new Employee(params)
        controller.show(employee)

        then: "A model is populated containing the domain instance"
        model.employee == employee
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def employee = new Employee(params)
        controller.edit(employee)

        then: "A model is populated containing the domain instance"
        model.employee == employee
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/employee/index'
        flash.message != null

        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def employee = new Employee()
        employee.validate()
        controller.update(employee)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.employee == employee

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        employee = new Employee(params).save(flush: true)
        controller.update(employee)

        then: "A redirect is issued to the show action"
        employee != null
        response.redirectedUrl == "/employee/show/$employee.id"
        flash.message != null
    }
*/

    void "Test that the delete action deletes an instance if it exists"() {
        given:
        def employee = new Employee().save(flush: true, validate: false)

        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        0 * controller.employeeService.delete(_)
        response.redirectedUrl == '/employee/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()

        and: "The domain instance is passed to the delete action"
        controller.delete(employee)

        then: "The instance is deleted"
        1 * controller.employeeService.delete(employee)
        response.redirectedUrl == '/employee/index'
        flash.message != null
    }
}
