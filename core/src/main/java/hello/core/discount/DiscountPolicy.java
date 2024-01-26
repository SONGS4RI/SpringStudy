package hello.core.discount;

import hello.core.member.Member;
import org.springframework.stereotype.Component;

public interface DiscountPolicy {

    /**
     *
     * @param member
     * @return
     */
    int discount(Member member, int price);

}
