package com.ptech.inseguro

class User {

    String userIP
    String name

    static hasMany = [counters: InsecureCount]

    static constraints = {

    }
}
