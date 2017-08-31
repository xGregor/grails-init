package de.dvinci

class Employee {

    String firstName
    String lastName
    Salutation salutation

    static constraints = {
        salutation nullable: true
    }

    enum Salutation {
        MR,
        MS
    }

}
