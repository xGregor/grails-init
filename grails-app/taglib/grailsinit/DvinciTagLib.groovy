package grailsinit

class DvinciTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "dv"

    def employee = { attrs ->
        def employee = attrs.employee
        out << render(template: "/taglib/employee", model: [employee: employee])
    }
}
