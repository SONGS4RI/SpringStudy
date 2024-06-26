package jpabook.jpashop.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;

@SpringBootTest
@Transactional
class OrderServiceTest {
	@Autowired EntityManager em;
	@Autowired OrderService orderService;
	@Autowired OrderRepository orderRepository;

	@Test
	public void 상품주문() throws Exception {
	    // given
		Member member = createMember();

		Book book = createBook("시골 JPA", 10000, 10);

		int orderCount = 2;
	    // when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		// then
		Order order = orderRepository.findOne(orderId);
		assertEquals(OrderStatus.ORDER, order.getStatus());
		assertEquals(1, order.getOrderItems().size());
		assertEquals(10000 * orderCount, order.getTotalPrice());
		assertEquals(8, book.getStockQuantity());
	}


	@Test
	public void 상품주문_재고수량초과() throws Exception {
	    // given
		Member member = createMember();
		Item item = createBook("시골 JPA", 10000, 10);
	    int orderCount = 11;
		// when
		assertThatThrownBy(()-> orderService.order(member.getId(), item.getId(), orderCount))
			.isInstanceOf(NotEnoughStockException.class);
	    // then

	}

	@Test
	public void 주문취소() throws Exception {
	    // given
		Member member = createMember();
		Book item = createBook("시골 JPA", 10000, 10);
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
		// when
		orderService.cancelOrder(orderId);
		// then
		Order order = orderRepository.findOne(orderId);
		assertEquals(OrderStatus.CANCEL, order.getStatus());
		assertEquals(10, item.getStockQuantity());

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
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);
		return member;
	}

}
