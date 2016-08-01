import com.ptech.inseguro.InsecureCount
import com.ptech.inseguro.User
import com.ptech.inseguro.InsecureInterval

class BootStrap {

    def init = { servletContext ->

       List <Map> userList =  [
               [name:"Eugenio", ip:"192.168.0.14"],
               [name:"Choi", ip:"192.168.0.30"],
               [name:"Mariano", ip:"192.168.0.133"],
               [name:"Facundo", ip:"192.168.0.69"],
               [name:"Eduardo", ip:"192.168.0.77"],
               [name:"Daniel", ip:"192.168.0.80"],
               [name:"Coco", ip:"192.168.0.87"],
               [name:"Tito", ip:"192.168.0.89"],
               [name:"Juan Martinez", ip:"192.168.0.90"],
               [name:"Juan Cañabate", ip:"192.168.0.91"],
               [name:"Martin Castro", ip:"192.168.0.92"],
               [name:"Leo Buret", ip:"192.168.0.93"]
       ]

        Date now = new Date()
        InsecureInterval interval = InsecureInterval.findByFromDateLessThanAndToDateGreaterThan(now, now)
        if (!interval) {
            interval = new InsecureInterval(fromDate: now, toDate: now.plus(30)).save()

        }
        userList.each { user ->
            if (!User.findByName(user.name)) {
                User newUser = new User(name: user.name, userIP: user.ip).save()
                newUser.addToCounters(new InsecureCount(owner: newUser, interval: interval).save())
                newUser.save()
            }
        }

    }
    def destroy = {
    }
}
