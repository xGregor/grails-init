package grailsinit

import de.dvinci.Employee

class BootStrap {

    def init = { servletContext ->
        new Employee(firstName: "Gregor", lastName: "Test", salutation: Employee.Salutation.MR).save()
    }
    def destroy = {
    }
}
