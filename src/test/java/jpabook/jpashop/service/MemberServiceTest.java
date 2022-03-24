package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@Test
//	@Rollback(value = false) // 눈으로 insert문을 확인하고 싶을때
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("bak");

		// when
		Long saveId = memberService.join(member);

		// then
		assertThat(member).isEqualTo(memberRepository.findOne(saveId));
	}

	@Test
	void 중복회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("bak");

		Member member2 = new Member();
		member2.setName("bak");

		// when
		memberService.join(member1);

		assertThatThrownBy(() -> {
			memberService.join(member2);
		}).isInstanceOf(IllegalStateException.class);
	}
}
