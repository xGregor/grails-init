package de.dvinci

import grails.validation.Validateable

class EmployeeCommand implements Validateable {
    String firstName
    String lastName
    Employee.Salutation salutation

    static constraints = {
        firstName(blank: false, minSize: 5)
        lastName(blank: false, minSize: 2)
        salutation(nullable: true)
    }
}
