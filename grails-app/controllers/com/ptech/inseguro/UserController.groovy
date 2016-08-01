package com.ptech.inseguro

class UserController {

    def userService

    def index() {
        String clientAddress = request.getRemoteAddr()
        if (clientAddress == "0:0:0:0:0:0:0:1") {
            clientAddress = "192.168.0.89"
        }
        User user = User.findByUserIP(clientAddress)
        print(clientAddress)
        if (user) {
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

            render model: [
                    users: userStatistics.sort { a,b -> b.count <=> a.count },
                    fromDate: interval.fromDate.format('d-MM-yyyy'),
                    toDate: interval.toDate.format('d-MM-yyyy'),
                    imageName: imageName,
                    currentUser: user.name
            ], view: 'index'
        } else {
            render view: 'unknowUser'
        }

    }
}
