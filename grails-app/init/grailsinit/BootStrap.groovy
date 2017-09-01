package grailsinit

import de.dvinci.Employee
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.DEVELOPMENT)
            new Employee(firstName: "Gregor", lastName: "Test", salutation: Employee.Salutation.MR, age: 33).save()
    }
    def destroy = {
    }
}
