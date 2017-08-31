package de.dvinci

class Employee {

    String firstName
    String lastName
    Salutation salutation

    static constraints = {
        salutation nullable: true
        firstName minSize: 2
        lastName minSize: 2
    }

    enum Salutation {
        MR,
        MS
    }

}
