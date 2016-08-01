package com.ptech.inseguro

import grails.transaction.Transactional

@Transactional
class UserService {

    def createNewInterval() {

        Date now = new Date()
        InsecureInterval interval = InsecureInterval.findByFromDateLessThanAndToDateGreaterThan(now, now)
        if (!interval) {
            interval = new InsecureInterval(fromDate: now, toDate: now.plus(30)).save()

        }

        User.list().each { user ->
            user.addToCounters(new InsecureCount(owner: user, interval: interval).save())
            user.save()
            }
    }

    String randomImageName() {
        File file = new File("web-app/images");
        String[] imageNames = file.list()
        Random random = new Random();
        int randomNumber = random.nextInt((imageNames.length -1));
        return imageNames[randomNumber]
    }

}
