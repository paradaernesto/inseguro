package com.ptech.inseguro

class UserController {

    def userService

    def index() {
        String userAddress = InetAddress.getLocalHost().hostAddress
        User user = User.findByUserIP(userAddress)
        Date now = new Date()
        InsecureInterval interval = InsecureInterval.findByFromDateLessThanAndToDateGreaterThan(now, now)
        InsecureCount insecureCount = InsecureCount.findByIntervalAndOwner(interval, user)
        insecureCount.count = insecureCount.count + 1
        insecureCount.save(flush:true)

        List <Map> userStatistics = []
        User.list().each {
            Map userMap = [:]
            userMap.name = it.name
            userMap.count = InsecureCount.findByIntervalAndOwner(interval, it).count
            userStatistics.add(userMap)
        }

        String imageName = userService.randomImageName()

        if (user) {
            render model: [
                    users: userStatistics.sort { a,b -> b.count <=> a.count },
                    fromDate: interval.fromDate.format('d-MM-yyyy'),
                    toDate: interval.toDate.format('d-MM-yyyy'),
                    imageName: imageName
            ], view: 'index'
        } else {
            render view: 'unknowUser'
        }

    }
}
