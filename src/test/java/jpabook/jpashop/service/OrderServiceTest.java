package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class OrderServiceTest {
	@Autowired
	private EntityManager em;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	void 상품주문() {
		// given
		Member member = createMember();

		Book book = createBook("JPA", 10000, 10);

		int orderCount = 2;

		// when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		// then
		Order getOrder = orderRepository.findOne(orderId);

		assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER)
				.withFailMessage(() -> "주문시 상태는 ORDER");
		assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
		assertThat(getOrder.getTotalPrice()).isEqualTo(10000 * orderCount);
		assertThat(book.getStockQuantity()).isEqualTo(8);
	}

	private Book createBook(String name, int price, int stockQuantity) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(stockQuantity);
		em.persist(book);
		return book;
	}

	private Member createMember() {
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "동대문", "1231"));
		em.persist(member);
		return member;
	}

	@Test
	void 상품주문_재고수량초과() {
		// given
		Member member = createMember();
		Book book = createBook("JPA", 10000, 10);

		int orderCount = 11;

		// when
		assertThatThrownBy(() -> {
			orderService.order(member.getId(), book.getId(), orderCount);
		}).isInstanceOf(NotEnoughStockException.class);


		// then
	}

	@Test
	void 주문취소() {
		// given
		Member member = createMember();
		Book item = createBook("시골 JPA", 1000, 10);

		int orderCount = 2;

		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

		// when
		orderService.cancelOrder(orderId);

		// then
		Order getOrder = orderRepository.findOne(orderId);

		assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
		assertThat(item.getStockQuantity()).isEqualTo(10);

	}

	@Test
	void 재고수량초과() {
		// given

		// when

		// then
	}
}
