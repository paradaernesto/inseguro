package com.ptech.inseguro

class InsecureCount {

    Integer count = 0

    static belongsTo = [interval: InsecureInterval, owner: User]

    static constraints = {
    }
}
