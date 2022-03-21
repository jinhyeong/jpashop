package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Test
	@Transactional
	@Rollback(value = false)
	void testMember() {
		// given
		Member member = new Member();
		member.setUsername("memberA");

		// when
		Long saveId = memberRepository.save(member);

		// then
		memberRepository.clear();
		Member findMember = memberRepository.find(saveId);
		assertThat(findMember).isNotNull();
		assertThat(findMember).isEqualTo(member);
		System.out.println("findMember == member:" + (findMember == member));

	}
}
