package aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public void addAccount() {
        System.out.println(getClass() + ": Doing stuff: adding a membership account");
    }

    public boolean addSillyMember() {
        System.out.println(getClass() + ": Return boolean type");
        return true;
    }

    public void gotoSleep() {
        System.out.println(getClass() + ": I'm going to sleep");
    }
}
