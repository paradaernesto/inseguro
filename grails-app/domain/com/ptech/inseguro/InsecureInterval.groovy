package com.ptech.inseguro

class InsecureInterval {

    Date fromDate
    Date toDate

    static hasMany = [counters: InsecureCount]

    static constraints = {
        toDate nullable: true
        fromDate nullable: true
    }
}
