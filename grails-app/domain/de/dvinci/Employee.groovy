package de.dvinci

class Employee {

    String firstName
    String lastName
    Salutation salutation
    Integer age

    static hasMany = [skills: Skill]

    static constraints = {
        salutation nullable: true
        firstName minSize: 2
        lastName minSize: 2
        age nullable: true, validator: { val, obj ->
            obj.firstName == "Jascha" ? age > 20 : true
        }
    }

    static mapping=  {
        order: "desc"
    }

    enum Salutation {
        MR,
        MS
    }

}
